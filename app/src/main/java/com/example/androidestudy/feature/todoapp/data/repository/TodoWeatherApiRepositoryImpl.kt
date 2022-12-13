package com.example.androidestudy.feature.todoapp.data.repository

import com.example.androidestudy.feature.todoapp.data.remote.api.TodoWeatherApi
import com.example.androidestudy.feature.todoapp.data.remote.dto.WeatherInfoDto
import com.example.androidestudy.feature.todoapp.domain.repository.TodoWeatherApiRepository
import javax.inject.Inject

class TodoWeatherApiRepositoryImpl @Inject constructor(
    private val api: TodoWeatherApi
) : TodoWeatherApiRepository {
    override suspend fun getWeatherInfo(latitude: Double, longitude: Double): WeatherInfoDto {
        return api.getWeatherInfo(latitude = latitude, longitude = longitude)
    }
}