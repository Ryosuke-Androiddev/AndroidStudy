package com.example.androidestudy.feature.retrofit.domain.repository

import com.example.androidestudy.feature.retrofit.domain.model.ResponseState
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import kotlinx.coroutines.flow.Flow

interface UserPostRepository {

    // GET all
    fun getUserPosts(): Flow<ResponseState>

    // GET by Id
    suspend fun getPostById(id: String): ResponseState

    // POST
    suspend fun postUserPost(userPostItem: UserPostItem): ResponseState

    // UPDATE
    suspend fun updatePost(id: String): ResponseState

    // DELETE
    suspend fun deletePost(id: String): ResponseState
}