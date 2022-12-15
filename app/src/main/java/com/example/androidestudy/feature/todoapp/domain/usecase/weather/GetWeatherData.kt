package com.example.androidestudy.feature.todoapp.domain.usecase.weather

import com.example.androidestudy.feature.todoapp.data.mapper.WeatherData
import com.example.androidestudy.feature.todoapp.domain.repository.TodoWeatherApiRepository
import javax.inject.Inject

class GetWeatherData @Inject constructor(
    private val repository: TodoWeatherApiRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): WeatherData {
        return repository.getWeatherInfo(latitude = latitude, longitude = longitude)
    }
}