package com.example.androidestudy.feature.retrofit.data.repository

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository

class FakeUserpostRepository: UserPostRepository {

    private val posts = mutableListOf<UserPostItem>()

    private val notFound = UserPostItem(
        body = "",
        id = 1,
        title = "",
        userId = 1
    )

    override suspend fun getUserPosts(): Result<List<UserPostItem>> {
        return Result.success(posts)
    }

    override suspend fun getPostById(id: String): Result<UserPostItem> {
        return Result.success(posts.find { it.id == id.toInt() } ?: notFound)
    }

    // UseCaseのテストは用意しない → GetAllUserPostsUseCaseTestの@Beforeで動作が確認できるから
    override suspend fun postUserPost(userPostItem: UserPostItem): Result<String> {
        posts.add(userPostItem)
        return Result.success(STATUS_CODE)
    }

    // 本来のPATCHとしての処理ではない
    // Test用にコードを思いつかなかったので以下の処理に
    override suspend fun updatePost(userPostItem: UserPostItem): Result<String> {
        val requestTargetId = userPostItem.id
        val removeTarget = posts.find { it.id == requestTargetId }
        // 過去のやつを削除
        posts.remove(removeTarget)
        // 同じIdを持ったオブジェクトを追加
        posts.add(userPostItem)
        return Result.success(STATUS_CODE)
    }

    override suspend fun deletePost(userPostItem: UserPostItem): Result<String> {
        val requestTargetId = userPostItem.id
        val removeTarget = posts.find { it.id == requestTargetId }
        posts.remove(removeTarget)
        return Result.success(STATUS_CODE)
    }

    companion object {
        private const val STATUS_CODE = "200"
    }
}