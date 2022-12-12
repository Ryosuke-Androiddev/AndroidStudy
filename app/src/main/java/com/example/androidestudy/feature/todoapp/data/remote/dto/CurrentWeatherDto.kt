package com.example.androidestudy.feature.todoapp.data.remote.dto

data class CurrentWeatherDto(
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Double,
    val windspeed: Double
)