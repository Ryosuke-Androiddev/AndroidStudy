package com.example.androidestudy.feature.retrofit.presentation.postlist.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.order.OrderType
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder
import com.example.androidestudy.feature.retrofit.domain.model.result.DeleteUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.util.ScreenState
import com.example.androidestudy.feature.retrofit.domain.usecase.DeleteUserPostUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.GetAllUserPostsUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.PostUserPostUseCase
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.PostListEvent
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.PostListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val deleteUserPostUseCase: DeleteUserPostUseCase,
    private val getAllUserPostsUseCase: GetAllUserPostsUseCase,
    private val postUserPostUseCase: PostUserPostUseCase
): ViewModel() {

    private var getNotesJob: Job? = null

    var state by mutableStateOf(PostListScreenState())
        private set

    init {
        getAllPosts(PostOrder.Id(OrderType.Descending))
    }

    fun onEvent(event: PostListEvent) {
        when (event) {
            is PostListEvent.Order -> {
                // data classじゃないから以下のように比較する
                if (state.postOrder::class == event.postOrder::class &&
                        state.postOrder.orderType == event.postOrder.orderType) {
                    return
                }
                getAllPosts(postOrder = event.postOrder)
            }
            is PostListEvent.DeletePost -> {
                deletePost(userPostItem = event.userPostItem)
            }
            is PostListEvent.RestorePost -> {
                // ViewModelから、Eventを送ってるのこれが良くないのか
                // postUserPost(state.recentlyDeletePost)
                // Deleteの流れを汲んで保持しておかないと一生nullのまんまだ
                postUserPost()
            }
            is PostListEvent.ToggleOrderSection -> {
                state = state.copy(
                    isOrderSectionVisible = !state.isOrderSectionVisible
                )
            }
        }
    }

    // ここをFlowに置き換えて処理する
    // List表示をJobで管理する
    private fun getAllPosts(postOrder: PostOrder) = viewModelScope.launch {

        getNotesJob?.cancel()

        state = state.copy(
            isLoading = true,
            postList = mutableListOf()
        )

        getNotesJob = getAllUserPostsUseCase(postOrder = postOrder)
            .onEach { userPosts ->
                state = state.copy(
                    postList = userPosts.toMutableList(),
                    postOrder = postOrder
                )
            }.launchIn(this)

        state = state.copy(
            isLoading = false
        )
    }

    private fun deletePost(userPostItem: UserPostItem) = viewModelScope.launch {
        state = when (deleteUserPostUseCase(userPostItem = userPostItem)) {
            is DeleteUserPostState.DeleteUserPost -> {
                if (state.postList.remove(userPostItem)) {
                    state.copy(
                        recentlyDeletePost = userPostItem
                    )
                } else {
                    state.copy(
                        recentlyDeletePost = userPostItem
                    )
                }
            }
            is DeleteUserPostState.Failure -> {
                state.copy(
                    recentlyDeletePost = null
                )
            }
        }
    }

    // Listの更新をかける必要がある??
    private fun postUserPost() = viewModelScope.launch {
        val userPostItem = state.recentlyDeletePost
        Log.d("UndoList", "PostUserPost")
        if (userPostItem != null) {
            Log.d("UndoList", "Post Id ${userPostItem.id}")
            when (postUserPostUseCase(userPostItem = userPostItem)) {
                is ScreenState.Success -> {
                    Log.d("UndoList", "Success")
                    val newList = state.postList
                    if (newList.add(userPostItem)) {
                        state = state.copy(
                            recentlyDeletePost = null
                        )
                    }

                    Log.d("UndoListAfter", "${state.postList.size}")
                }
                is ScreenState.Failure -> {
                    Log.d("UndoList", "Failure")
                    state = state.copy(
                        recentlyDeletePost = userPostItem
                    )
                }
                is ScreenState.TextInputError -> {
                    // Undoの際にここが引っかかってる
                    Log.d("UndoList", "TextInputError")
                    // 削除の時に再登録するので、title, bodyの文字制限がかからないので処理を省略
                }
            }
        }
    }
}