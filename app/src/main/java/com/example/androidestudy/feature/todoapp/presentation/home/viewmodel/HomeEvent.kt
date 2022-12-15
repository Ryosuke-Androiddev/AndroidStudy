package com.example.androidestudy.feature.todoapp.presentation.home.viewmodel

import com.example.androidestudy.feature.todoapp.domain.model.weather.Location

sealed class HomeEvent {
    data class SetWeatherLocation(
        val location: Location,
        val latitude: Double,
        val longitude: Double
    ): HomeEvent()
    object GetWeatherLocation: HomeEvent()

    // TODO
    // 天気予報を表示
    // 週間天気予報クリックをイベント処理
    object GetWeatherData: HomeEvent()
}
