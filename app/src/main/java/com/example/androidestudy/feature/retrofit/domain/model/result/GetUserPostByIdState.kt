package com.example.androidestudy.feature.retrofit.domain.model.result

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem

sealed class GetUserPostByIdState {
    data class GetUserPostById(val userPost: UserPostItem): GetUserPostByIdState()
    object Failure: GetUserPostByIdState()
}