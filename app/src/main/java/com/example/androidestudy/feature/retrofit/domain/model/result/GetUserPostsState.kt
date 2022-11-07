package com.example.androidestudy.feature.retrofit.domain.model.result

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem

sealed class GetUserPostsState {
    data class GetUserPosts(val userPosts: List<UserPostItem>): GetUserPostsState()
    object Failure: GetUserPostsState()
}
