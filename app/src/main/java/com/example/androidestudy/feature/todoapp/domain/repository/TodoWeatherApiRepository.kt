package com.example.androidestudy.feature.todoapp.domain.repository

import com.example.androidestudy.feature.todoapp.data.mapper.WeatherData

interface TodoWeatherApiRepository {
    suspend fun getWeatherInfo(latitude: Double, longitude: Double): WeatherData
}