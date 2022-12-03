package com.example.androidestudy.feature.todoapp.presentation.home.component.weather

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.androidestudy.R

@Composable
fun DailyWeatherCard(
    modifier: Modifier = Modifier,
    dayOfWeek: String,
    currentTime: String,
    currentTemperature: Float,
    maxTemperature: Float,
    minTemperature: Float,
    @DrawableRes
    weatherImage: Int,
    imageDescription: String,
    wind: Float,
    pressure: Float,
    humidity: Float
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(50.dp))
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.Black.copy(alpha = 0.4f))
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .height(40.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dayOfWeek,
                    fontWeight = FontWeight.Bold
                )
                // TODO AM or PMは、ドメイン実装後に再度考慮する
                Text(
                    text = currentTime,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.temperature, currentTemperature),
                    fontWeight = FontWeight.Bold,
                    fontSize = 60.sp
                )
                Image(
                    modifier = Modifier
                        .size(100.dp),
                    painter = painterResource(id = weatherImage),
                    contentDescription = imageDescription
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.max_min_temperature, maxTemperature, minTemperature),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.see_forecast),
                    fontSize = 10.sp
                )
            }

            Spacer(
                modifier = Modifier
                    .height(42.dp)
            )

            Row(
                modifier = Modifier
                    .padding(bottom = 12.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )
                DailyWeatherCardBottomSection(
                    modifier = Modifier
                        .weight(1f),
                    detailWeatherImage = R.drawable.windy,
                    contentDescription = "",
                    detailWeatherTitle = "Wind",
                    amount = 8f,
                    stringRes = R.string.wind
                )
                DailyWeatherCardBottomSection(
                    modifier = Modifier
                        .weight(1f),
                    detailWeatherImage = R.drawable.windy,
                    contentDescription = "",
                    detailWeatherTitle = "Pressure",
                    amount = 8f,
                    stringRes = R.string.pressure
                )
                DailyWeatherCardBottomSection(
                    modifier = Modifier
                        .weight(1f),
                    detailWeatherImage = R.drawable.windy,
                    contentDescription = "",
                    detailWeatherTitle = "Humidity",
                    amount = 8f,
                    stringRes = R.string.humidity
                )
                Spacer(
                    modifier = Modifier
                        .width(4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun DailyWeatherCardPreview() {
    DailyWeatherCard(
        dayOfWeek = "Monday",
        currentTime = "1:24",
        currentTemperature = 22f,
        maxTemperature = 30f,
        minTemperature = 18f,
        weatherImage = R.drawable.ic_sunnycloudy,
        imageDescription = "a",
        wind = 5f,
        pressure = 1013f,
        humidity = 51f
    )
}