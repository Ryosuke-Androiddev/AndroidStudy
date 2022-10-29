package com.example.androidestudy.feature.retrofit.domain.repository

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.DeleteUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostByIdState
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostsState
import com.example.androidestudy.feature.retrofit.domain.model.result.PostUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.result.UpdateUserPostState
import kotlinx.coroutines.flow.Flow

interface UserPostRepository {

    // GET all
    fun getUserPosts(): Flow<GetUserPostsState>

    // GET by Id
    suspend fun getPostById(id: String): GetUserPostByIdState

    // POST
    suspend fun postUserPost(userPostItem: UserPostItem): PostUserPostState

    // UPDATE
    suspend fun updatePost(userPostItem: UserPostItem): UpdateUserPostState

    // DELETE
    suspend fun deletePost(userPostItem: UserPostItem): DeleteUserPostState
}