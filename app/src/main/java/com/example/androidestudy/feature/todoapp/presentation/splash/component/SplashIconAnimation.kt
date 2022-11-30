package com.example.androidestudy.feature.todoapp.presentation.splash.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androidestudy.R

@Composable
fun SplashIconAnimation(
    navController: NavController
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.manage_you))

    val animationState = animateLottieCompositionAsState(
        composition = composition,
        iterations = 1
    )
    LottieAnimation(
        modifier = Modifier
            .size(300.dp),
        composition = composition,
        progress = animationState.progress
    )
    if (animationState.isAtEnd && animationState.isPlaying) {
        navController.popBackStack()
    }
}