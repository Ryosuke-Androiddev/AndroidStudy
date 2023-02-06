package com.example.androidestudy.feature.todoapp.presentation.todo.daily

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidestudy.feature.todoapp.presentation.todo.daily.component.MonthGridLine
import com.example.androidestudy.feature.todoapp.presentation.todo.daily.component.ScheduleHeader
import com.example.androidestudy.feature.todoapp.presentation.todo.daily.viewmodel.DailyViewModel

@Composable
fun DailyScreen(
    navController: NavController,
    viewModel: DailyViewModel = hiltViewModel()
) {

    val state = viewModel.state
    Log.d("CalendarStateDaily", "${state.calendar}")
    Log.d("CalendarStateDaily", "${state.isLoading}")

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScheduleHeader(navController = navController, month = "2月")
        Spacer(modifier = Modifier.height(4.dp))

        // ここの動機処理を考える
        if (state.calendar.size > 30) {
            MonthGridLine()
        }
    }
}