package com.example.androidestudy.feature.todoapp.presentation.splash.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
    verticalDistance: Dp = 20.dp,
    spaceBetween: Dp = 10.dp
) {

    val textMap = mapOf(
        "M" to remember { Animatable(initialValue = 0f) },
        "a" to remember { Animatable(initialValue = 0f) },
        "n" to remember { Animatable(initialValue = 0f) },
        "a" to remember { Animatable(initialValue = 0f) },
        "g" to remember { Animatable(initialValue = 0f) },
        "e" to remember { Animatable(initialValue = 0f) },
        "Y" to remember { Animatable(initialValue = 0f) },
        "o" to remember { Animatable(initialValue = 0f) },
        "u" to remember { Animatable(initialValue = 0f) },
    )

    textMap.values.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            // ここで遅延をかける
            delay(index * 80L)
            // targetValueで、成長させなければ最後に上に移動するアニメーションがなくなる
            animatable.animateTo(
                targetValue = 0f,
                animationSpec = repeatable(
                    iterations = 1,
                    animation = keyframes {
                        durationMillis = 1200
                        // start
                        0.0f at 0 with LinearOutSlowInEasing
                        // 一番上までいく
                        1.0f at 300 with LinearOutSlowInEasing
                        // startポジションに戻る
                        0.0f at 600 with LinearOutSlowInEasing
                        // startポジションで待機
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    // 順番決めてる??
    val textValues = textMap.keys.map { it }
    val distance = with(LocalDensity.current) {
        verticalDistance.toPx()
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        textValues.forEach { animatable ->
            Text(
                modifier = Modifier
                    .graphicsLayer {
                        translationY = -textMap[animatable]?.value!! * distance
                    },
                text = animatable,
                fontWeight = FontWeight.Bold
            )
        }
    }
}