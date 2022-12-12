package com.example.androidestudy.feature.todoapp.data.remote.dto

data class WeatherInfoDto(
    val current_weather: CurrentWeatherDto,
    val dailyWeatherDto: DailyWeatherDto,
    val daily_units: DailyUnitsDto,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: HourlyWeatherDto,
    val hourly_units: HourlyUnitsDto,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)