package com.example.androidestudy.feature.retrofit.data.repository

import com.example.androidestudy.feature.retrofit.data.remote.parse.createUserPosts
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.DeleteUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostByIdState
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostsState
import com.example.androidestudy.feature.retrofit.domain.model.result.PostUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.result.UpdateUserPostState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserPostRepository: UserPostRepository {

    private val posts = createUserPosts()

    private val notFound = UserPostItem(
        body = "",
        id = 1,
        title = "",
        userId = 1
    )

    override fun getUserPosts(): Flow<GetUserPostsState> = flow {
        emit(GetUserPostsState.GetUserPosts(userPosts = posts))
    }

    override suspend fun getPostById(id: String): GetUserPostByIdState {
        val postById = posts.find { it.id == id.toInt() }
        return GetUserPostByIdState.GetUserPostById(userPost = postById ?: notFound)
    }

    // UseCaseのテストは用意しない → GetAllUserPostsUseCaseTestの@Beforeで動作が確認できるから
    override suspend fun postUserPost(userPostItem: UserPostItem): PostUserPostState {
        posts.add(userPostItem)
        return PostUserPostState.PostUserPost(statusCode = STATUS_CODE)
    }

    // 本来のPATCHとしての処理ではない
    // Test用にコードを思いつかなかったので以下の処理に
    override suspend fun updatePost(userPostItem: UserPostItem): UpdateUserPostState {
        val requestTargetId = userPostItem.id
        val removeTarget = posts.find { it.id == requestTargetId }
        // 過去のやつを削除
        posts.remove(removeTarget)
        // 同じIdを持ったオブジェクトを追加
        posts.add(userPostItem)
        return UpdateUserPostState.UpdateUserPost(statusCode = STATUS_CODE)
    }

    override suspend fun deletePost(userPostItem: UserPostItem): DeleteUserPostState {
        val requestTargetId = userPostItem.id
        val removeTarget = posts.find { it.id == requestTargetId }
        posts.remove(removeTarget)
        return DeleteUserPostState.DeleteUserPost(statusCode = STATUS_CODE)
    }

    companion object {
        private const val STATUS_CODE = "200"
    }
}