package com.example.androidestudy.feature.todoapp.domain.repository

import com.example.androidestudy.feature.todoapp.data.remote.dto.WeatherInfoDto

interface TodoWeatherApiRepository {
    suspend fun getWeatherInfo(latitude: Double, longitude: Double): WeatherInfoDto
}