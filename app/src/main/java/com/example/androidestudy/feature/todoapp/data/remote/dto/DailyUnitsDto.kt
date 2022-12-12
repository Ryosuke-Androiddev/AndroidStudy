package com.example.androidestudy.feature.todoapp.data.remote.dto

data class DailyUnitsDto(
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val weathercode: String
)