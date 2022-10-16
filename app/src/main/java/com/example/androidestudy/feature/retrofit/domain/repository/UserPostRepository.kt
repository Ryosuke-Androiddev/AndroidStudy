package com.example.androidestudy.feature.retrofit.domain.repository

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.ResultState

interface UserPostRepository {

    // GET all
    suspend fun getUserPosts(): ResultState

    // GET by Id
    suspend fun getPostById(id: String): ResultState

    // POST
    suspend fun postUserPost(userPostItem: UserPostItem): ResultState

    // UPDATE
    suspend fun updatePost(userPostItem: UserPostItem): ResultState

    // DELETE
    suspend fun deletePost(userPostItem: UserPostItem): ResultState
}