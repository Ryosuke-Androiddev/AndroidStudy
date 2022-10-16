package com.example.androidestudy.feature.retrofit.domain.model.result

sealed class ResultState {
    data class Success<T>(val result: T): ResultState()
    object Failure: ResultState()
}
