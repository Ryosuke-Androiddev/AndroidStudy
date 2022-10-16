package com.example.androidestudy.feature.retrofit.domain.model.result

sealed class DeleteUserPostState {
    data class DeleteUserPost(val statusCode: String): DeleteUserPostState()
    object Failure: DeleteUserPostState()
}
