package com.example.androidestudy.feature.domain.model.onboarding

sealed class OnBoardingState {
    data class Success(val isCompleted: Boolean): OnBoardingState()
    object Failure: OnBoardingState()
}
