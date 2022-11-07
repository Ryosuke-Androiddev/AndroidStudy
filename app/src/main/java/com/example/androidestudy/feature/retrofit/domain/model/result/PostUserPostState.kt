package com.example.androidestudy.feature.retrofit.domain.model.result

sealed class PostUserPostState {
    data class PostUserPost(val statusCode: String): PostUserPostState()
    object Failure: PostUserPostState()
}
