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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
                .fillMaxHeight(0.67f)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 24.dp, start = 24.dp, bottom = 16.dp),
                    text = "地名を選択してください"
                )
                Spacer(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth()
                        .height(1.5.dp)
                        .background(color = Color.Black)
                )
                Location.values().forEach { location ->
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp, start = 24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = location == defaultLocation,
                            onClick = {
                                viewModel.onEvent(HomeEvent.SetWeatherLocation(location = location))
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = location.name)
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
                    TextButton(
                        onClick = {
                            navController.popBackStack()
                            navController.navigate(Screen.HomeScreen.route)
                        }
                    ) {
                        Text(
                            text = "今は設定しない",
                            color = Color.Black
                        )
                    }

                    TextButton(
                        onClick = {
                            // 保存しない場合は東京を保持する
                            viewModel.onEvent(HomeEvent.SetWeatherLocation(Location.Tokyo))
                            navController.popBackStack()
                            navController.navigate(Screen.HomeScreen.route)
                        }
                    ) {
                        Text(
                            text = "選択肢を保存",
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}