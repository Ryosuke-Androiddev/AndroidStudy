package com.example.androidestudy.feature.todoapp.domain.repository

import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.domain.model.weather.LocationState
import kotlinx.coroutines.flow.Flow

interface WeatherLocationSettings {
    suspend fun setLocation(location: Location)
    fun getLocation(): Flow<LocationState>
}