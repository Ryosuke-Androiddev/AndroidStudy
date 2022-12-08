package com.example.androidestudy.feature.todoapp.domain.model.weather

import kotlinx.serialization.Serializable

// 適宜、APIコールのパラメータを追加する
@Serializable
data class WeatherLocation(
    val location: Location = Location.Tokyo
)

enum class Location {
    Hokkaido,
    Sendai,
    Tokyo,
    Nagoya,
    Osaka,
    Fukuoka
}