package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostByIdState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository

class GetUserPostByIdUseCase(
    private val repository: UserPostRepository
) {
    suspend operator fun invoke(id: Int): GetUserPostByIdState {
        return repository.getPostById(id = id.toString())
    }
}