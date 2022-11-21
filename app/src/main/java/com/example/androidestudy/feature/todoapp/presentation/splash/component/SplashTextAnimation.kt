package com.example.androidestudy.feature.todoapp.presentation.splash.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplashTextAnimation(
    modifier: Modifier = Modifier,
    verticalDistance: Dp = 20.dp,
    spaceBetween: Dp = 10.dp
) {

    val texts = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
    )

    texts.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            // ここで遅延をかける
            delay(index * 100L)
            // targetValueで、成長させなければ最後に上に移動するアニメーションがなくなる
            animatable.animateTo(
                targetValue = 0f,
                animationSpec = repeatable(
                    iterations = 1,
                    animation = keyframes {
                        durationMillis = 1600
                        // start
                        0.0f at 0 with LinearOutSlowInEasing
                        // 一番上までいく
                        1.0f at 400 with LinearOutSlowInEasing
                        // startポジションに戻る
                        0.0f at 800 with LinearOutSlowInEasing
                        // startポジションで待機
                        0.0f at 1600 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    // 順番決めてる??
    val textValues = texts.map { it.value }
    val distance = with(LocalDensity.current) {
        verticalDistance.toPx()
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        textValues.forEach { value ->
            Text(
                modifier = Modifier
                    .graphicsLayer {
                        translationY = -value * distance
                    },
                text = "A",
                fontWeight = FontWeight.Bold
            )
        }
    }
}