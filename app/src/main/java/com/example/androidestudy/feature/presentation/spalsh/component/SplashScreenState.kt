package com.example.androidestudy.feature.presentation.spalsh.component

import com.example.androidestudy.feature.domain.model.onboarding.OnBoardingState

data class SplashScreenState(
    val isCompleted: Boolean = false,
    val onBoardingState: OnBoardingState
)