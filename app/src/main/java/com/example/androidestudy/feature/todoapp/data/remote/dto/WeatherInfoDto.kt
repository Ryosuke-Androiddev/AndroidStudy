package com.example.androidestudy.feature.todoapp.data.remote.dto

data class WeatherInfoDto(
    val dailyWeatherDto: DailyWeatherDto,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: HourlyWeatherDto,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)