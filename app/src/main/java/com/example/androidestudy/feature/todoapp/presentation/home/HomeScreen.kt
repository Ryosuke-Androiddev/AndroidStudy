package com.example.androidestudy.feature.todoapp.presentation.home

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoPriority
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoTaskContent
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoTaskItem
import com.example.androidestudy.feature.todoapp.presentation.home.component.weather.DailyWeatherCard
import com.example.androidestudy.feature.todoapp.presentation.home.viewmodel.HomeViewModel
import com.example.androidestudy.ui.theme.LimeGreen
import java.text.DateFormat
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Log.d("WeatherInfo", "${state.weatherData?.currentWeatherData}")

    val todoContent = listOf(
        TodoTaskContent(
            color = Color.Red,
            title = stringResource(id = R.string.high),
            imageRes = R.drawable.ic_time_is_money,
            description = stringResource(id = R.string.important_task),
            priority = TodoPriority.High
        ),
        TodoTaskContent(
            color = Color.Blue,
            title = stringResource(id = R.string.medium),
            imageRes = R.drawable.ic_man,
            description = stringResource(id = R.string.medium_important_task),
            priority = TodoPriority.Medium
        ),
        TodoTaskContent(
            color = LimeGreen,
            title = stringResource(id = R.string.low),
            R.drawable.ic_clean_accommodation,
            description = stringResource(id = R.string.low_important_task),
            priority = TodoPriority.Low
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray.copy(alpha = 0.15f))
    ) {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.JAPAN)
        Spacer(modifier = Modifier.height(8.dp))
        DailyWeatherCard(
            dayOfWeek = "${LocalDateTime.now().dayOfWeek}",
            currentTime = simpleDateFormat.format(Date()),
            currentTemperature = state.weatherData?.currentWeatherData?.temperature_2m,
            maxTemperature = state.weatherData?.currentWeatherData?.temperature_2m_max?.get(0),
            minTemperature = state.weatherData?.currentWeatherData?.temperature_2m_min?.get(0),
            weatherImage = state.weatherData?.currentWeatherData?.weatherType?.iconRes ?: R.drawable.ic_snowy,
            imageDescription = state.weatherData?.currentWeatherData?.weatherType?.weatherDesc ?: "null",
            wind = state.weatherData?.currentWeatherData?.windspeed_10m,
            pressure = state.weatherData?.currentWeatherData?.surface_pressure,
            humidity = state.weatherData?.currentWeatherData?.relativehumidity_2m?.toDouble(),
            navController = navController
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        LazyColumn {
            items(todoContent.size) { index ->
                val imageRes = todoContent[index].imageRes
                val color = todoContent[index].color
                val title = todoContent[index].title
                val description = todoContent[index].description
                val priority = todoContent[index].priority
                TodoTaskItem(
                    imageRes = imageRes,
                    color = color,
                    title = title,
                    description = description,
                    priority = priority,
                    navController = navController
                )
            }
        }
    }
}