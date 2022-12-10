package com.example.androidestudy.feature.todoapp.presentation.home.component.weather

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.androidestudy.feature.todoapp.domain.model.weather.Location

@Composable
fun LocationPickerScreen(
    navController: NavController
) {
    WeatherLocationPickerDialog(navController = navController, defaultLocation = Location.Hokkaido)
}