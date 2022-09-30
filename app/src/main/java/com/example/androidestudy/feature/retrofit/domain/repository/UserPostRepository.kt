package com.example.androidestudy.feature.retrofit.domain.repository

import com.example.androidestudy.feature.retrofit.domain.model.ResponseState
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem

interface UserPostRepository {

    // GET all
    suspend fun getUserPosts(): Result<List<UserPostItem>>

    // GET by Id
    suspend fun getPostById(id: String): Result<UserPostItem>

    // POST
    suspend fun postUserPost(userPostItem: UserPostItem): Result<String>

    // UPDATE
    suspend fun updatePost(id: String): Result<String>

    // DELETE
    suspend fun deletePost(id: String): Result<String>
}