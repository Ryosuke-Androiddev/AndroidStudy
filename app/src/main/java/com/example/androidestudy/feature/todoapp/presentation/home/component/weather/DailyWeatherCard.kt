package com.example.androidestudy.feature.todoapp.presentation.home.component.weather

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidestudy.R

@Composable
fun DailyWeatherCard(
    dayOfWeek: String,
    currentTime: String,
    temperature: Float,
    @DrawableRes
    weatherImage: Int,
    wind: Float,
    pressure: Float,
    humidity: Float
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    ) {

    }
}

@Preview
@Composable
fun DailyWeatherCardPreview() {
    DailyWeatherCard(
        dayOfWeek = "Monday",
        currentTime = "1:24",
        temperature = 22f,
        weatherImage = R.drawable.ic_sunnycloudy,
        wind = 5f,
        pressure = 1013f,
        humidity = 51f
    )
}