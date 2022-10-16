package com.example.androidestudy.feature.retrofit.presentation.postlist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.order.OrderType
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder
import com.example.androidestudy.feature.retrofit.domain.usecase.DeleteUserPostUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.GetAllUserPostsUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.GetUserPostByIdUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.PostUserPostUseCase
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.PostListEvent
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.PostListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val deleteUserPostUseCase: DeleteUserPostUseCase,
    private val getAllUserPostsUseCase: GetAllUserPostsUseCase,
    private val postUserPostUseCase: PostUserPostUseCase
): ViewModel() {

    var state by mutableStateOf(PostListScreenState())
        private set

    private var recentlyDeletePost: UserPostItem? = null

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
                getAllPosts(event.postOrder)
            }
            is PostListEvent.DeletePost -> {
                deletePost(event.userPostItem)
            }
            is PostListEvent.RestorePost -> {
                postUserPost(recentlyDeletePost ?: return)
            }
            is PostListEvent.ToggleOrderSection -> {
                state = state.copy(
                    isOrderSectionVisible = !state.isOrderSectionVisible
                )
            }
        }
    }

    private fun getAllPosts(postOrder: PostOrder) = viewModelScope.launch {
        state = state.copy(
            isLoading = true,
            postList = emptyList()
        )

        // このtry-catchももっとスマートにできるはずやけど実装方法がわからん
        // 自分でsealed classを定義して再実装し直せば良さそう??
        state = try {
            val userPosts = getAllUserPostsUseCase(postOrder = postOrder)
            state.copy(
                isLoading = false,
                postList = userPosts
            )
        } catch (e: Exception) {
            state.copy(
                isLoading = false,
                isError = true
            )
        }
    }

    private fun deletePost(userPostItem: UserPostItem) = viewModelScope.launch {
        deleteUserPostUseCase(userPostItem = userPostItem)
        recentlyDeletePost = userPostItem
    }

    private fun postUserPost(userPostItem: UserPostItem) = viewModelScope.launch {
        postUserPostUseCase(userPostItem = userPostItem)
        recentlyDeletePost = null
    }
}