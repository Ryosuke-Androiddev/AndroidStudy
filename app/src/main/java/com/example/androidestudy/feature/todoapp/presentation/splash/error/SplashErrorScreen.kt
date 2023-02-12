package com.example.androidestudy.feature.todoapp.presentation.splash.error

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.androidestudy.feature.todoapp.presentation.util.SplashErrorDialog

@Composable
fun SplashErrorScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SplashErrorDialog(navController = navController)
    }
}