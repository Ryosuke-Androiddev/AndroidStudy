package com.example.androidestudy.feature.todoapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyWeatherDto(
    @field:Json(name = "temperature_2m_max")
    val temperature_2m_max: List<Double>,
    @field:Json(name = "temperature_2m_min")
    val temperature_2m_min: List<Double>,
    @field:Json(name = "time")
    val time: List<String>,
    @field:Json(name = "weathercode")
    val weathercode: List<Int>
)