package com.example.androidestudy.feature.todoapp.presentation.onboarding.component

import android.annotation.SuppressLint
import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@SuppressLint("ResourceType")
@Composable
fun OnBoardingItem(
    modifier: Modifier = Modifier,
    title: String,
    @RawRes
    resId: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        // resIdのコンポジションを形成する
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId = resId))
        val animationState = animateLottieCompositionAsState(
            composition = composition,
            iterations = 1
        )

        LottieAnimation(
            modifier = Modifier.weight(2f),
            composition = composition,
            progress = animationState.progress
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.weight(2f),
            text = title,
            fontSize = 12.sp
        )
    }
}