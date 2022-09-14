package com.example.androidestudy.feature.presentation.goal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androidestudy.R
import com.example.androidestudy.ui.theme.EXTRA_LARGE_PADDING

@Composable
fun GoalScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.done))
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .padding(EXTRA_LARGE_PADDING)
        )
    }
}