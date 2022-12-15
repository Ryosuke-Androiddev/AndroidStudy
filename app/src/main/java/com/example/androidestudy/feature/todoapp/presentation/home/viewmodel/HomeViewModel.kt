package com.example.androidestudy.feature.todoapp.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.domain.usecase.weather.GetWeatherData
import com.example.androidestudy.feature.todoapp.domain.usecase.weather.GetWeatherLocation
import com.example.androidestudy.feature.todoapp.domain.usecase.weather.SetWeatherLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val setWeatherLocation: SetWeatherLocation,
    private val getWeatherLocation: GetWeatherLocation,
    private val getWeatherData: GetWeatherData
): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        // Flowから返却される値をコンストラクタで呼び出しておく
        getLocation()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SetWeatherLocation -> {
                setLocation(
                    location = event.location,
                    latitude = event.latitude,
                    longitude = event.longitude
                )
            }
            is HomeEvent.GetWeatherLocation -> {
                getLocation()
            }
            is HomeEvent.GetWeatherData -> {
                getWeatherData()
            }
        }
    }

    private fun setLocation(
        location: Location,
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            setWeatherLocation(location = location, latitude = latitude, longitude = longitude)
        }
        state = state.copy(
            location = location,
            latitude = latitude,
            longitude = longitude
        )
    }

    private fun getLocation() {
        viewModelScope.launch {
            val weatherLocation = getWeatherLocation().stateIn(viewModelScope).value
            if (weatherLocation.latitude != null && weatherLocation.longitude != null) {
                state = state.copy(
                    location = weatherLocation.location,
                    latitude = weatherLocation.latitude,
                    longitude = weatherLocation.longitude
                )
            }
        }
    }

    private fun getWeatherData() {
        val latitude = state.latitude
        val longitude = state.longitude
        if (latitude != null && longitude != null) {
            viewModelScope.launch(Dispatchers.IO) {
                val weatherData = getWeatherData(
                    latitude = latitude,
                    longitude = longitude
                )
                state = state.copy(
                    weatherData = weatherData
                )
            }
        }
    }
}