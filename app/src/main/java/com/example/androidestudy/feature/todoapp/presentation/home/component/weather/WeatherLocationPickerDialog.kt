package com.example.androidestudy.feature.todoapp.presentation.home.component.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.presentation.home.viewmodel.HomeEvent
import com.example.androidestudy.feature.todoapp.presentation.home.viewmodel.HomeViewModel
import com.example.androidestudy.feature.util.Screen

@Composable
fun WeatherLocationPickerDialog(
    navController: NavController,
    defaultLocation: Location,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.6f)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 24.dp, start = 24.dp, bottom = 16.dp),
                    text = stringResource(id = R.string.pick_location)
                )
                Spacer(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth()
                        .height(1.5.dp)
                        .background(color = Color.Black)
                )
                Location.values().forEach { location ->
                    if (location != Location.Default) {
                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp, start = 24.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = location == defaultLocation,
                                onClick = {
                                    when (location) {
                                        Location.Hokkaido -> {
                                            viewModel.onEvent(
                                                HomeEvent.SetWeatherLocation(
                                                    location = location,
                                                    longitude = 141.35,
                                                    latitude = 43.07
                                                )
                                            )
                                        }
                                        Location.Sendai -> {
                                            viewModel.onEvent(
                                                HomeEvent.SetWeatherLocation(
                                                    location = location,
                                                    longitude = 140.87,
                                                    latitude = 38.27
                                                )
                                            )
                                        }
                                        Location.Tokyo -> {
                                            viewModel.onEvent(
                                                HomeEvent.SetWeatherLocation(
                                                    location = location,
                                                    longitude = 139.69,
                                                    latitude = 35.69
                                                )
                                            )
                                        }
                                        Location.Nagoya -> {
                                            viewModel.onEvent(
                                                HomeEvent.SetWeatherLocation(
                                                    location = location,
                                                    longitude = 139.69,
                                                    latitude = 35.69
                                                )
                                            )
                                        }
                                        Location.Osaka -> {
                                            viewModel.onEvent(
                                                HomeEvent.SetWeatherLocation(
                                                    location = location,
                                                    longitude = 135.50,
                                                    latitude = 34.69
                                                )
                                            )
                                        }
                                        Location.Fukuoka -> {
                                            viewModel.onEvent(
                                                HomeEvent.SetWeatherLocation(
                                                    location = location,
                                                    longitude = 130.42,
                                                    latitude = 33.60
                                                )
                                            )
                                        }
                                        Location.Default -> {
                                            // Nothing To Do
                                        }
                                        else -> {
                                            // ここに処理が流れてくることはない
                                        }
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = location.name)
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .height(1.5.dp)
                        .background(color = Color.Black)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 12.dp, end = 12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (defaultLocation == Location.Default) {
                        TextButton(
                            onClick = {
                                // 保存しない場合は東京を保持する
                                viewModel.onEvent(
                                    HomeEvent.SetWeatherLocation(
                                        location = Location.Tokyo,
                                        longitude = 139.69,
                                        latitude = 35.69
                                    )
                                )
                                navController.popBackStack()
                                navController.navigate(Screen.HomeScreen.route)
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.not_select_now),
                                color = Color.Black
                            )
                        }
                    } else {
                        TextButton(
                            onClick = {
                            }
                        ) {
                        }
                    }

                    TextButton(
                        onClick = {
                            navController.popBackStack()
                            navController.navigate(Screen.HomeScreen.route)
                        }
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(end = 4.dp),
                            text = stringResource(id = R.string.select_now),
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}