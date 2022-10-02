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
    override suspend fun getUserPosts(): Result<List<UserPostItem>> {
        return try {
            // UserPostItem型へ変更
            val userPosts = userPostApi.getUserPosts().map { it.toUserPostItem() }
            Result.success(userPosts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPostById(id: String): Result<UserPostItem> {
        return try {
            // UserPostItem型へ変更
            val userPost = userPostApi.getPostById(id = id).toUserPostItem()
            Result.success(userPost)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun postUserPost(userPostItem: UserPostItem): Result<String> {
        return try {
            // Mapperに入れることで、ドメインの処理漏れにならない??
            val userPostItemDto = userPostItem.toUserPostItemDto()
            userPostApi.postUserPost(userPostItemDto = userPostItemDto)
            Result.success(SUCCESS_STATUS_CODE)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePost(userPostItem: UserPostItem): Result<String> {
        return try {
            val targetId = userPostItem.id.toString()
            userPostApi.updatePost(id = targetId)
            Result.success(SUCCESS_STATUS_CODE)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deletePost(userPostItem: UserPostItem): Result<String> {
        return try {
            val targetId = userPostItem.id.toString()
            userPostApi.deletePost(id = targetId)
            Result.success(SUCCESS_STATUS_CODE)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        private const val SUCCESS_STATUS_CODE = "200"
    }
}