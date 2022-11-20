package com.example.androidestudy.feature.todoapp.presentation.splash.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
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
    )

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
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .graphicsLayer {
                        translationY = -value * distance
                    }
                    .background(
                        color = MaterialTheme.colors.primary,
                        shape = CircleShape
                    )
            )
        }
    }
}