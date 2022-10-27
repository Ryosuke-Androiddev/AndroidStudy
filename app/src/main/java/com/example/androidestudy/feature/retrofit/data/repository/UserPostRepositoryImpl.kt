package com.example.androidestudy.feature.retrofit.data.repository

import android.util.Log
import com.example.androidestudy.feature.retrofit.data.mapper.toUserPostItem
import com.example.androidestudy.feature.retrofit.data.mapper.toUserPostItemDto
import com.example.androidestudy.feature.retrofit.data.remote.UserPostApi
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.DeleteUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostByIdState
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostsState
import com.example.androidestudy.feature.retrofit.domain.model.result.PostUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.result.UpdateUserPostState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import com.example.androidestudy.feature.util.createFakeData

class UserPostRepositoryImpl(
    private val userPostApi: UserPostApi
): UserPostRepository {
    override suspend fun getUserPosts(): GetUserPostsState {
        // APIコール後のListの変換がうまくいっていない
        return try {
            // UserPostItem型へ変更
            // Log.d("UserPostList", "${userPostApi.getUserPosts()}")
            // val userPosts = userPostApi.getUserPosts().map { it.toUserPostItem() }
            // Log.d("UserPostList", "$userPosts")
            GetUserPostsState.GetUserPosts(userPosts = createFakeData())
        } catch (e: Exception) {
            Log.d("UserPostList", "${e.message}")
            GetUserPostsState.Failure
        }
    }

    override suspend fun getPostById(id: String): GetUserPostByIdState {
        return try {
            // UserPostItem型へ変更
            val userPost = userPostApi.getPostById(id = id).toUserPostItem()
            GetUserPostByIdState.GetUserPostById(userPost = userPost)
        } catch (e: Exception) {
            GetUserPostByIdState.Failure
        }
    }

    override suspend fun postUserPost(userPostItem: UserPostItem): PostUserPostState {
        return try {
            // Mapperに入れることで、ドメインの処理漏れにならない??
            val userPostItemDto = userPostItem.toUserPostItemDto()
            userPostApi.postUserPost(userPostItemDto = userPostItemDto)
            PostUserPostState.PostUserPost(statusCode = SUCCESS_STATUS_CODE)
        } catch (e: Exception) {
            PostUserPostState.Failure
        }
    }

    override suspend fun updatePost(userPostItem: UserPostItem): UpdateUserPostState {
        return try {
            val targetId = userPostItem.id.toString()
            userPostApi.updatePost(id = targetId)
            UpdateUserPostState.UpdateUserPost(statusCode = SUCCESS_STATUS_CODE)
        } catch (e: Exception) {
            UpdateUserPostState.Failure
        }
    }

    override suspend fun deletePost(userPostItem: UserPostItem): DeleteUserPostState {
        return try {
            val targetId = userPostItem.id.toString()
            userPostApi.deletePost(id = targetId)
            DeleteUserPostState.DeleteUserPost(statusCode = SUCCESS_STATUS_CODE)
        } catch (e: Exception) {
            DeleteUserPostState.Failure
        }
    }

    companion object {
        private const val SUCCESS_STATUS_CODE = "200"
    }
}