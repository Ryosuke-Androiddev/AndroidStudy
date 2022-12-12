package com.example.androidestudy.feature.todoapp.data.remote.dto

data class DailyWeatherDto(
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>
)