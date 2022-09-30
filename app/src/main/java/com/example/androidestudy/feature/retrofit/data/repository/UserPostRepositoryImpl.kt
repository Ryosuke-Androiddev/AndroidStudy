package com.example.androidestudy.feature.retrofit.data.repository

import android.util.Log
import com.example.androidestudy.feature.retrofit.data.mapper.toUserPostItem
import com.example.androidestudy.feature.retrofit.data.mapper.toUserPostItemDto
import com.example.androidestudy.feature.retrofit.data.remote.UserPostApi
import com.example.androidestudy.feature.retrofit.domain.model.ResponseState
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import okio.IOException

class UserPostRepositoryImpl(
    private val userPostApi: UserPostApi
): UserPostRepository {
    override suspend fun getUserPosts(): Result<ResponseState> {
        return try {
            // UserPostItem型へ変更
            println("Hello world")
            val userPosts = userPostApi.getUserPosts().map { it.toUserPostItem() }
            val responseState = ResponseState.Success(userPosts)
            Result.success(responseState)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPostById(id: String): Result<ResponseState> {
        return try {
            // UserPostItem型へ変更
            val userPost = userPostApi.getPostById(id = id).toUserPostItem()
            val responseState = ResponseState.Success(data = userPost)
            Result.success(responseState)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun postUserPost(userPostItem: UserPostItem): Result<ResponseState> {
        return try {
            // Mapperに入れることで、ドメインの処理漏れにならない??
            val userPostItemDto = userPostItem.toUserPostItemDto()
            userPostApi.postUserPost(userPostItemDto = userPostItemDto)
            Result.success(ResponseState.Success(SUCCESS_STATUS_CODE))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePost(id: String): Result<ResponseState> {
        return try {
            userPostApi.updatePost(id = id)
            Result.success(ResponseState.Success(SUCCESS_STATUS_CODE))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deletePost(id: String): Result<ResponseState> {
        return try {
            userPostApi.deletePost(id = id)
            Result.success(ResponseState.Success(SUCCESS_STATUS_CODE))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        private const val SUCCESS_STATUS_CODE = "200"
    }
}