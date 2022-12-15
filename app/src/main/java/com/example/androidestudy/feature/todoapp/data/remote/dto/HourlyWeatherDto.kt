package com.example.androidestudy.feature.todoapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyWeatherDto(
    @field:Json(name = "relativehumidity_2m")
    val relativehumidity_2m: List<Int>,
    @field:Json(name = "surface_pressure")
    val surface_pressure: List<Double>,
    @field:Json(name = "temperature_2m")
    val temperature_2m: List<Double>,
    @field:Json(name = "time")
    val time: List<String>,
    @field:Json(name = "weathercode")
    val weathercode: List<Int>,
    @field:Json(name = "windspeed_10m")
    val windspeed_10m: List<Double>
)