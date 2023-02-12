package com.example.androidestudy.feature.todoapp.domain.model.weather

import kotlinx.serialization.Serializable

// 適宜、APIコールのパラメータを追加する
@Serializable
data class WeatherLocation(
    val location: Location = Location.Default,
    val latitude: Double? = null,
    val longitude: Double? = null
)

enum class Location {
    Hokkaido,
    Sendai,
    Tokyo,
    Nagoya,
    Osaka,
    Fukuoka,
    Default
}