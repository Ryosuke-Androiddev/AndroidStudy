package com.example.androidestudy.feature.retrofit.domain.repository

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import kotlinx.coroutines.flow.Flow

interface UserPostRepository {

    // GET all
    fun getUserPosts(): Flow<List<UserPostItem>>

    // GET by Id
    suspend fun getPostById(id: String): UserPostItem

    // POST
    suspend fun postUserPost(userPostItem: UserPostItem)

    // UPDATE
    suspend fun updatePost(id: String)

    // DELETE
    suspend fun deletePost(id: String)
}