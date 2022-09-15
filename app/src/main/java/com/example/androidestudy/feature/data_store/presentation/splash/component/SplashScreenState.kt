package com.example.androidestudy.feature.data_store.presentation.splash.component

import com.example.androidestudy.feature.data_store.domain.model.OnBoardingState

data class SplashScreenState(
    val isCompleted: Boolean = false,
    val onBoardingState: OnBoardingState
)