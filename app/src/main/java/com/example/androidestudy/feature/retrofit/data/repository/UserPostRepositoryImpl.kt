package com.example.androidestudy.feature.retrofit.data.repository

import com.example.androidestudy.feature.retrofit.data.mapper.toUserPostItem
import com.example.androidestudy.feature.retrofit.data.mapper.toUserPostItemDto
import com.example.androidestudy.feature.retrofit.data.remote.UserPostApi
import com.example.androidestudy.feature.retrofit.domain.model.ResponseState
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException

class UserPostRepositoryImpl(
    private val userPostApi: UserPostApi
): UserPostRepository {
    override fun getUserPosts(): Flow<ResponseState> = flow {
        try {
            // UserPostItem型へ変更
            val userPosts = userPostApi.getUserPosts().map { it.toUserPostItem() }
            emit(ResponseState.Success(data = userPosts))
        } catch (e: IOException) {
            emit(ResponseState.Failure)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPostById(id: String): ResponseState {
        return try {
            // UserPostItem型へ変更
            val userPost = userPostApi.getPostById(id = id).toUserPostItem()
            ResponseState.Success(data = userPost)
        } catch (e: IOException) {
            ResponseState.Failure
        }
    }

    override suspend fun postUserPost(userPostItem: UserPostItem): ResponseState {
        return try {
            // Mapperに入れることで、ドメインの処理漏れにならない??
            val userPostItemDto = userPostItem.toUserPostItemDto()
            userPostApi.postUserPost(userPostItemDto = userPostItemDto)
            ResponseState.Success(data = "200")
        } catch (e: IOException) {
            ResponseState.Failure
        }
    }

    override suspend fun updatePost(id: String): ResponseState {
        return try {
            userPostApi.updatePost(id = id)
            ResponseState.Success(data = "200")
        } catch (e: IOException) {
            ResponseState.Failure
        }
    }

    override suspend fun deletePost(id: String): ResponseState {
        return try {
            userPostApi.deletePost(id = id)
            ResponseState.Success(data = "200")
        } catch (e: IOException) {
            ResponseState.Failure
        }
    }
}