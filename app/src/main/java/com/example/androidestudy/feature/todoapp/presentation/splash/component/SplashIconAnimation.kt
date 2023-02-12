package com.example.androidestudy.feature.todoapp.presentation.splash.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androidestudy.R
import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.presentation.home.viewmodel.HomeViewModel
import com.example.androidestudy.feature.todoapp.presentation.splash.viewmodel.TodoSplashViewModel
import com.example.androidestudy.feature.util.Screen

@Composable
fun SplashIconAnimation(
    navController: NavController,
    todoSplashViewModel: TodoSplashViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val onBoardingState = todoSplashViewModel.onBoardingState.collectAsState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.manage_you))

    val animationState = animateLottieCompositionAsState(
        composition = composition,
        iterations = 1
    )
    LottieAnimation(
        modifier = Modifier
            .size(300.dp),
        composition = composition,
        progress = animationState.progress
    )
    if (animationState.isAtEnd && animationState.isPlaying) {
        navController.popBackStack()
        if (onBoardingState.value.isCompleted && homeViewModel.state.location == Location.Default) {
            navController.navigate(Screen.LocationPickerScreen.route)
        } else if (onBoardingState.value.isCompleted && homeViewModel.state.location != Location.Default) {
            navController.navigate(Screen.HomeScreen.route)
        } else if (onBoardingState.value.hasError) {
            // Dialogを表示して、再度Splashかアプリの終了かを選択する
            navController.navigate(Screen.SplashErrorScreen.route)
        } else {
            navController.navigate(Screen.OnBoardingScreen.route)
        }
    }
}