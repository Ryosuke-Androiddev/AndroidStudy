package com.example.androidestudy.feature.todoapp.domain.usecase.weather

import android.util.Log
import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.domain.model.weather.LocationState
import com.example.androidestudy.feature.todoapp.domain.model.weather.WeatherLocation
import com.example.androidestudy.feature.todoapp.domain.repository.WeatherLocationSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWeatherLocation @Inject constructor(
    private val protoSettings: WeatherLocationSettings
) {
    operator fun invoke(): Flow<WeatherLocation> {
        return protoSettings.getLocation().map { locationState ->
            when (locationState) {
                is LocationState.Success -> {
                    Log.d("LocationGetState", "Flow: ${locationState.weatherLocation}")
                    locationState.weatherLocation
                }
                is LocationState.Failure -> {
                    // 取得に失敗した東京の天気を表示する
                    WeatherLocation(
                        location = Location.Tokyo,
                        latitude = 35.69,
                        longitude = 139.69
                    )
                }
            }
        }
    }
}