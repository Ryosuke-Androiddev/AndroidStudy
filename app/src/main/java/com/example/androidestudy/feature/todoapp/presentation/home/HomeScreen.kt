package com.example.androidestudy.feature.todoapp.presentation.home

import android.util.Log
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
        Spacer(modifier = Modifier.height(8.dp))
        DailyWeatherCard(
            dayOfWeek = "Monday",
            currentTime = "13:24",
            currentTemperature = 22f,
            maxTemperature = 30f,
            minTemperature = 18f,
            weatherImage = R.drawable.ic_sunnycloudy,
            imageDescription = "a",
            wind = 5f,
            pressure = 1013f,
            humidity = 51f,
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