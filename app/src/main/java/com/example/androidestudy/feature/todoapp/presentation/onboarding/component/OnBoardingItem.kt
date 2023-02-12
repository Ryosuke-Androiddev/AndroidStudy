package com.example.androidestudy.feature.todoapp.presentation.onboarding.component

import android.annotation.SuppressLint
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
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
    // resIdのコンポジションを形成する
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId = resId))
    val animationState = animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.7f),
            composition = composition,
            progress = animationState.progress
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 28.dp),
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}