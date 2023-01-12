package com.example.androidestudy.feature.todoapp.presentation.todo.daily

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.androidestudy.feature.todoapp.presentation.todo.daily.component.ScheduleHeader

@Composable
fun DailyScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScheduleHeader(navController = navController, month = "2月")
    }
}