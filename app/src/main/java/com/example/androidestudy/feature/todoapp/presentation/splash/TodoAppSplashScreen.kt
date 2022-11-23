package com.example.androidestudy.feature.todoapp.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.androidestudy.feature.todoapp.presentation.splash.component.SplashIconAnimation
import com.example.androidestudy.feature.todoapp.presentation.splash.component.SplashTextAnimation

@Composable
fun TodoAppSplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SplashIconAnimation()
        SplashTextAnimation()
    }
}