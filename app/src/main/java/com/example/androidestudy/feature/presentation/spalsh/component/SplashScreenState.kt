package com.example.androidestudy.feature.presentation.spalsh.component

import com.example.androidestudy.feature.domain.model.OnBoardingState

data class SplashScreenState(
    val isCompleted: Boolean = false,
    val onBoardingState: OnBoardingState
)