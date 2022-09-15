package com.example.androidestudy.feature.domain.model.util

sealed class ResultState {
    data class Success<T>(val data: T): ResultState()
    object Failure: ResultState()
    object Loading: ResultState()
}
