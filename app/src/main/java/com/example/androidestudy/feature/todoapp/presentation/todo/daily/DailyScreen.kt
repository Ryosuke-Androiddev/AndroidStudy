package com.example.androidestudy.feature.todoapp.presentation.todo.daily

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidestudy.feature.todoapp.presentation.todo.daily.component.MonthGridLine
import com.example.androidestudy.feature.todoapp.presentation.todo.daily.component.ScheduleHeader

@Composable
fun DailyScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScheduleHeader(navController = navController, month = "2月")
        Spacer(modifier = Modifier.height(4.dp))
        MonthGridLine()
    }
}