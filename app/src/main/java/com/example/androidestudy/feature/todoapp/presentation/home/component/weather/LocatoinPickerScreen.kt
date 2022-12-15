package com.example.androidestudy.feature.todoapp.presentation.home.component.weather

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.presentation.home.viewmodel.HomeViewModel

@Composable
fun LocationPickerScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val location = homeViewModel.state.location
    Log.d("LocationPicker", "$location")
    WeatherLocationPickerDialog(
        navController = navController,
        defaultLocation = location
    )
}