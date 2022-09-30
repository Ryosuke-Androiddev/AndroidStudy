package com.example.androidestudy.feature.retrofit.domain.repository

import com.example.androidestudy.feature.retrofit.domain.model.ResponseState
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem

interface UserPostRepository {

    // GET all
    suspend fun getUserPosts(): Result<ResponseState>

    // GET by Id
    suspend fun getPostById(id: String): Result<ResponseState>

    // POST
    suspend fun postUserPost(userPostItem: UserPostItem): Result<ResponseState>

    // UPDATE
    suspend fun updatePost(id: String): Result<ResponseState>

    // DELETE
    suspend fun deletePost(id: String): Result<ResponseState>
}