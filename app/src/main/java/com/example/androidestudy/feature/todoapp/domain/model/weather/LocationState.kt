package com.example.androidestudy.feature.todoapp.domain.model.weather

sealed class LocationState {
    object Failure: LocationState()
    data class Success(val weatherLocation: WeatherLocation): LocationState()
}
