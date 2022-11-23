package com.example.androidestudy.feature.todoapp.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidestudy.feature.todoapp.presentation.splash.component.SplashIconAnimation
import com.example.androidestudy.feature.todoapp.presentation.splash.component.SplashTextAnimation

@Composable
fun TodoAppSplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SplashIconAnimation()
        SplashTextAnimation()

        Spacer(modifier = Modifier.height(250.dp))
    }
}