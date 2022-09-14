package com.example.androidestudy.feature.presentation.spalsh

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androidestudy.R

@Composable
fun SplashScreen(
    navController: NavController
) {

    // AnimatedVisibilityで、ゴールスクリーンを表示するかOnBoardingScreenに遷移するかを決定する
    // UseCaseで引っ張ってきたPreferencesDataStoreで確認する
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.circle_loading))
        LottieAnimation(
            composition = composition,
            iterations = Int.MAX_VALUE,
            speed = 0.8f
        )
    }
}