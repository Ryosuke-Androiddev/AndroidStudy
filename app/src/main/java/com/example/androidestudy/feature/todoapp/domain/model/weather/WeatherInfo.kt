package com.example.androidestudy.feature.todoapp.domain.model.weather

import java.time.LocalDateTime

data class WeatherInfo(
    val time: LocalDateTime,
    val weatherType: WeatherType,
    // hourly_weather_dto
    val relativehumidity_2m: Int,
    val surface_pressure: Double,
    val temperature_2m: Double,
    val windspeed_10m: Double,
    // daily_weather_dto
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
)
