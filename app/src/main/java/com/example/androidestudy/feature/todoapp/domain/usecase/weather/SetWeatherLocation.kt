package com.example.androidestudy.feature.todoapp.domain.usecase.weather

import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.domain.repository.WeatherLocationSettings
import javax.inject.Inject

class SetWeatherLocation @Inject constructor(
    private val protoSettings: WeatherLocationSettings
) {
    suspend operator fun invoke(location: Location, latitude: Double, longitude: Double) {
        protoSettings.setLocation(location = location, latitude = latitude, longitude = longitude)
    }
}