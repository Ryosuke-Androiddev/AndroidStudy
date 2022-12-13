package com.example.androidestudy.feature.todoapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.androidestudy.feature.todoapp.data.mapper.WeatherData
import com.example.androidestudy.feature.todoapp.data.mapper.toWeatherData
import com.example.androidestudy.feature.todoapp.data.remote.api.TodoWeatherApi
import com.example.androidestudy.feature.todoapp.domain.repository.TodoWeatherApiRepository
import javax.inject.Inject

class TodoWeatherApiRepositoryImpl @Inject constructor(
    private val api: TodoWeatherApi
) : TodoWeatherApiRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherInfo(latitude: Double, longitude: Double): WeatherData {
        return api.getWeatherInfo(latitude = latitude, longitude = longitude).toWeatherData()
    }
}