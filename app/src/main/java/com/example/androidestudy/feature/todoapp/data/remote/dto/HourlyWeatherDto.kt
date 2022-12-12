package com.example.androidestudy.feature.todoapp.data.remote.dto

data class HourlyWeatherDto(
    val relativehumidity_2m: List<Int>,
    val surface_pressure: List<Double>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>,
    val windspeed_10m: List<Double>
)