package com.example.androidestudy.feature.todoapp.domain.model.weather

data class WeatherInfo(
    // hourly_weather_dto
    val weathercode: List<Int>,
    val relativehumidity_2m: List<Int>,
    val surface_pressure: List<Double>,
    val temperature_2m: List<Double>,
    val windspeed_10m: List<Double>,
    // daily_weather_dto
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
)
