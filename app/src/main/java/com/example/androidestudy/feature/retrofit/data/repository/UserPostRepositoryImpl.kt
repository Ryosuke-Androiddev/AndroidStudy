package com.example.androidestudy.feature.retrofit.data.repository

import com.example.androidestudy.feature.retrofit.data.mapper.toUserPostItem
import com.example.androidestudy.feature.retrofit.data.mapper.toUserPostItemDto
import com.example.androidestudy.feature.retrofit.data.remote.UserPostApi
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.ResultState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository

class UserPostRepositoryImpl(
    private val userPostApi: UserPostApi
): UserPostRepository {
    override suspend fun getUserPosts(): ResultState {
        return try {
            // UserPostItem型へ変更
            val userPosts = userPostApi.getUserPosts().map { it.toUserPostItem() }
            ResultState.Success(result = userPosts)
        } catch (e: Exception) {
            ResultState.Failure
        }
    }

    override suspend fun getPostById(id: String): ResultState {
        return try {
            // UserPostItem型へ変更
            val userPost = userPostApi.getPostById(id = id).toUserPostItem()
            ResultState.Success(result = userPost)
        } catch (e: Exception) {
            ResultState.Failure
        }
    }

    override suspend fun postUserPost(userPostItem: UserPostItem): ResultState {
        return try {
            // Mapperに入れることで、ドメインの処理漏れにならない??
            val userPostItemDto = userPostItem.toUserPostItemDto()
            userPostApi.postUserPost(userPostItemDto = userPostItemDto)
            ResultState.Success(SUCCESS_STATUS_CODE)
        } catch (e: Exception) {
            ResultState.Failure
        }
    }

    override suspend fun updatePost(userPostItem: UserPostItem): ResultState {
        return try {
            val targetId = userPostItem.id.toString()
            userPostApi.updatePost(id = targetId)
            ResultState.Success(SUCCESS_STATUS_CODE)
        } catch (e: Exception) {
            ResultState.Failure
        }
    }

    override suspend fun deletePost(userPostItem: UserPostItem): ResultState {
        return try {
            val targetId = userPostItem.id.toString()
            userPostApi.deletePost(id = targetId)
            ResultState.Success(SUCCESS_STATUS_CODE)
        } catch (e: Exception) {
            ResultState.Failure
        }
    }

    companion object {
        private const val SUCCESS_STATUS_CODE = "200"
    }
}