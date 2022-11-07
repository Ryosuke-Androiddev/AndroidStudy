package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.DeleteUserPostState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository

class DeleteUserPostUseCase(
    private val repository: UserPostRepository
) {
    suspend operator fun invoke(userPostItem: UserPostItem): DeleteUserPostState {
        return repository.deletePost(userPostItem = userPostItem)
    }
}