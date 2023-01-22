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

        val calendar = listOf(
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            15,
            16,
            17,
            18,
            19,
            20,
            21,
            22,
            23,
            24,
            25,
            26,
            27,
            28,
            29,
            30,
            31
        )

        val nextCalendar = listOf(
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
        )

        // 表示の変換を行うドメイン知識が必要
        MonthGridLine(
            calendar,
            nextCalendar
        )
    }
}