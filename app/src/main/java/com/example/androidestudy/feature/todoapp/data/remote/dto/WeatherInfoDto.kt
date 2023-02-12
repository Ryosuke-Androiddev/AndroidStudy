package com.example.androidestudy.feature.todoapp.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherInfoDto(
    @field:Json(name = "daily")
    val dailyWeatherDto: DailyWeatherDto,
    val elevation: Double,
    val generationtime_ms: Double,
    @field:Json(name = "hourly")
    val hourly: HourlyWeatherDto,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)