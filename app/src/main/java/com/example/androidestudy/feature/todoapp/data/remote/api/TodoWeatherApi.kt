package com.example.androidestudy.feature.todoapp.data.remote.api

import com.example.androidestudy.feature.todoapp.data.remote.dto.WeatherInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoWeatherApi {
    // 引数で取ってしまえばその値を渡してくれる??
    @GET("v1/forecast?hourly=temperature_2m,relativehumidity_2m,weathercode,surface_pressure,windspeed_10m&daily=weathercode,temperature_2m_max,temperature_2m_min&current_weather=true&timezone=auto")
    suspend fun getWeatherInfo(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): WeatherInfoDto
}