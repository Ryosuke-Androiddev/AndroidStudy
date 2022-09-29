package com.example.androidestudy.feature.retrofit.data.repository

import com.example.androidestudy.feature.retrofit.data.remote.UserPostApi
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import kotlinx.coroutines.flow.Flow

class UserPostRepositoryImpl(
    private val userPostApi: UserPostApi
): UserPostRepository {
    override fun getUserPosts(): Flow<List<UserPostItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPostById(id: String): UserPostItem {
        TODO("Not yet implemented")
    }

    override suspend fun postUserPost(userPostItem: UserPostItem) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePost(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePost(id: String) {
        TODO("Not yet implemented")
    }
}