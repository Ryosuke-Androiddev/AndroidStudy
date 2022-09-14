package com.example.androidestudy.feature.presentation.spalsh

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androidestudy.R
import com.example.androidestudy.feature.presentation.screen.Screen
import com.example.androidestudy.feature.presentation.spalsh.viewmodel.SplashViewModel

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {

    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()

    // AnimatedVisibilityで、ゴールスクリーンを表示するかOnBoardingScreenに遷移するかを決定する
    // UseCaseで引っ張ってきたPreferencesDataStoreで確認する
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.circle_loading))
        val animationState =
            animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
            composition = composition,
            progress = animationState.progress
        )
        if (animationState.isAtEnd && animationState.isPlaying) {
            navController.popBackStack()
            if (onBoardingCompleted) {
                navController.navigate(Screen.GoalScreen.route)
            } else {
                navController.navigate(Screen.PreferencesDataStore.route)
            }
        }
    }
}