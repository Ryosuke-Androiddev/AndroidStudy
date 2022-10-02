package com.example.androidestudy.feature.retrofit.domain.model

sealed class ResponseState {
    data class Success<T>(val data: T): ResponseState()
}
