package com.example.androidestudy.feature.domain.model

sealed class OnBoardingState {
    data class Success(val isCompleted: Boolean): OnBoardingState()
    object Failure: OnBoardingState()
}
