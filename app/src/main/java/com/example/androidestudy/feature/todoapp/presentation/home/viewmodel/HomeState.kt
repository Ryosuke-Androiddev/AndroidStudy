package com.example.androidestudy.feature.todoapp.presentation.home.viewmodel

import com.example.androidestudy.feature.todoapp.data.mapper.WeatherData
import com.example.androidestudy.feature.todoapp.domain.model.weather.Location

data class HomeState(
    val location: Location = Location.Default,
    val weatherData: WeatherData? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)
