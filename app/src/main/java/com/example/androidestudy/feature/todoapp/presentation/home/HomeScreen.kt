package com.example.androidestudy.feature.todoapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoPriority
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoTaskContent
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoTaskItem

@Composable
fun HomeScreen(navController: NavController) {
    val todoContent = listOf(
        TodoTaskContent(
            color = Color.Red,
            title = stringResource(id = R.string.high),
            taskSymbol = stringResource(id = R.string.symbol_h),
            description = stringResource(id = R.string.important_task),
            priority = TodoPriority.High
        ),
        TodoTaskContent(
            color = Color.Green,
            title = stringResource(id = R.string.medium),
            taskSymbol = stringResource(id = R.string.symbol_m),
            description = stringResource(id = R.string.medium_important_task),
            priority = TodoPriority.Medium
        ),
        TodoTaskContent(
            color = Color.Blue,
            title = stringResource(id = R.string.low),
            taskSymbol = stringResource(id = R.string.symbol_l),
            description = stringResource(id = R.string.low_important_task),
            priority = TodoPriority.Low
        )
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            items(todoContent.size) { index ->
                val color = todoContent[index].color
                val title = todoContent[index].title
                val taskSymbol = todoContent[index].taskSymbol
                val description = todoContent[index].description
                val priority = todoContent[index].priority
                TodoTaskItem(
                    color = color,
                    title = title,
                    taskSymbol = taskSymbol,
                    description = description,
                    priority = priority,
                    navController = navController
                )
            }
        }
    }
}