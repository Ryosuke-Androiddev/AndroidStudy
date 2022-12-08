package com.example.androidestudy.feature.todoapp.domain.usecase.weather

import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.domain.model.weather.LocationState
import com.example.androidestudy.feature.todoapp.domain.repository.WeatherLocationSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWeatherLocation @Inject constructor(
    private val protoSettings: WeatherLocationSettings
) {
    operator fun invoke(): Flow<Location> {
        return protoSettings.getLocation().map { locationState ->
            when (locationState) {
                is LocationState.Success -> {
                    locationState.weatherLocation.location
                }
                is LocationState.Failure -> {
                    Location.Tokyo
                }
            }
        }
    }
}