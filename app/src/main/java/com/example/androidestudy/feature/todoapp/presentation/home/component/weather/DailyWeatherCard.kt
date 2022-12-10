package com.example.androidestudy.feature.todoapp.presentation.home.component.weather

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.util.Screen
import com.example.androidestudy.ui.theme.Bottom
import com.example.androidestudy.ui.theme.Purple200
import com.example.androidestudy.ui.theme.Purple500
import com.example.androidestudy.ui.theme.Purple700
import com.example.androidestudy.ui.theme.Top
import com.example.androidestudy.ui.theme.TransparentBlack
import com.example.androidestudy.ui.theme.TransparentGray

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
    humidity: Float,
    navController: NavController
) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(50.dp))
    ) {
        Column(
            modifier = Modifier
                .background(Brush.verticalGradient(
                    listOf(
                        Top,
                        Bottom
                    )
                ))
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
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = dayOfWeek,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                // TODO AM or PMは、ドメイン実装後に再度考慮する
                Text(
                    modifier = Modifier
                        .padding(end = 35.dp),
                    text = currentTime,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
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
                    fontSize = 60.sp,
                    color = Color.White
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
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.LocationPickerScreen.route)
                        }
                        .padding(end = 4.dp),
                    text = stringResource(id = R.string.select_location),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(
                modifier = Modifier
                    .height(36.dp)
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
                    detailWeatherImage = R.drawable.ic_windy,
                    contentDescription = stringResource(id = R.string.wind_description),
                    detailWeatherTitle = stringResource(id = R.string.wind_title),
                    amount = wind,
                    stringRes = R.string.wind
                )
                DailyWeatherCardBottomSection(
                    modifier = Modifier
                        .weight(1f),
                    detailWeatherImage = R.drawable.ic_temperature,
                    contentDescription = stringResource(id = R.string.pressure_description),
                    detailWeatherTitle = stringResource(id = R.string.pressure_title),
                    amount = pressure,
                    stringRes = R.string.pressure
                )
                DailyWeatherCardBottomSection(
                    modifier = Modifier
                        .weight(1f),
                    detailWeatherImage = R.drawable.ic_water_drop,
                    contentDescription = stringResource(id = R.string.humidity_description),
                    detailWeatherTitle = stringResource(id = R.string.humidity_title),
                    amount = humidity,
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