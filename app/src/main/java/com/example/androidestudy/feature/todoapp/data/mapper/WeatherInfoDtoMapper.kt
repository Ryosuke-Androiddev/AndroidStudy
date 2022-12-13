package com.example.androidestudy.feature.todoapp.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.androidestudy.feature.todoapp.data.remote.dto.WeatherInfoDto
import com.example.androidestudy.feature.todoapp.domain.model.weather.WeatherInfo
import com.example.androidestudy.feature.todoapp.domain.model.weather.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherInfoDto.toWeatherDataMap(): Map<Int, List<WeatherInfo>> {
    return hourly.time.mapIndexed { index, time ->
        val weatherCode = hourly.weathercode[index]
        val relativeHumidity = hourly.relativehumidity_2m[index]
        val surfacePressure = hourly.surface_pressure[index]
        val temperature = hourly.temperature_2m[index]
        val windSpeed = hourly.windspeed_10m[index]
        val maxTemperature = dailyWeatherDto.temperature_2m_max[index]
        val minTemperature = dailyWeatherDto.temperature_2m_min[index]
        IndexedWeatherData(
            index = index,
            weatherInfo = WeatherInfo(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                weatherType = WeatherType.fromWMO(weatherCode),
                relativehumidity_2m = relativeHumidity,
                surface_pressure = surfacePressure,
                temperature_2m = temperature,
                windspeed_10m = windSpeed,
                temperature_2m_max = maxTemperature,
                temperature_2m_min = minTemperature
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.weatherInfo }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherInfoDto.toWeatherData(): WeatherData {
    val weatherDatePerDay = toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDatePerDay[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherData(
        weatherDatePerDay = weatherDatePerDay,
        currentWeatherData = currentWeatherData
    )
}

data class WeatherData(
    // 1日の時間ごとの天気をまとめてる
    // keyを変えてあげたら動的に週間予報とかが作れる
    val weatherDatePerDay: Map<Int, List<WeatherInfo>>,
    // こっちはその日のある特定の時間を取得する
    val currentWeatherData: WeatherInfo?
)

private data class IndexedWeatherData(
    val index: Int,
    val weatherInfo: WeatherInfo
)