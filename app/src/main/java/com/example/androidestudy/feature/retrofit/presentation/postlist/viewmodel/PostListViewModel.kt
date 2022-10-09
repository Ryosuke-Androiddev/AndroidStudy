package com.example.androidestudy.feature.retrofit.presentation.postlist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidestudy.feature.retrofit.domain.usecase.DeleteUserPostUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.GetAllUserPostsUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.GetUserPostByIdUseCase
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.PostListEvent
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.PostListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val deleteUserPostUseCase: DeleteUserPostUseCase,
    private val getAllUserPostsUseCase: GetAllUserPostsUseCase,
    private val getUserPostByIdUseCase: GetUserPostByIdUseCase
): ViewModel() {

    var state by mutableStateOf(PostListScreenState())
        private set

    fun onEvent(event: PostListEvent) {
        when (event) {
            is PostListEvent.Order -> {

            }
            is PostListEvent.DeletePost -> {

            }
            is PostListEvent.RestorePost -> {

            }
            is PostListEvent.ToggleOrderSection -> {

            }
        }
    }
}