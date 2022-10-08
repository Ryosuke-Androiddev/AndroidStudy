package com.example.androidestudy.feature.retrofit.presentation.postlist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidestudy.feature.retrofit.domain.usecase.DeleteUserPostUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.GetAllUserPostsUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.GetUserPostByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val deleteUserPostUseCase: DeleteUserPostUseCase,
    private val getAllUserPostsUseCase: GetAllUserPostsUseCase,
    private val getUserPostByIdUseCase: GetUserPostByIdUseCase
): ViewModel() {
}