package com.example.androidestudy.feature.todoapp.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidestudy.feature.todoapp.presentation.splash.component.SplashIconAnimation
import com.example.androidestudy.feature.todoapp.presentation.splash.component.SplashTextAnimation

@Composable
fun TodoAppSplashScreen(
    navController: NavController
) {
    // ViewModel差し込んで遷移させるかを決定する SharedFlowでワンタイムイベント化してcollectする
    Box(
        modifier = Modifier
            .padding(bottom = 100.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SplashIconAnimation()
        SplashTextAnimation()
    }
}