package com.example.androidestudy.feature.auth.domain.model

sealed class ResultState {
    object Success: ResultState()
    object Failure: ResultState()
    object Loading: ResultState()
}
