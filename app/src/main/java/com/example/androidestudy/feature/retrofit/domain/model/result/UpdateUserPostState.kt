package com.example.androidestudy.feature.retrofit.domain.model.result

sealed class UpdateUserPostState {
    data class UpdateUserPost(val statusCode: String): UpdateUserPostState()
    object Failure: UpdateUserPostState()
}
