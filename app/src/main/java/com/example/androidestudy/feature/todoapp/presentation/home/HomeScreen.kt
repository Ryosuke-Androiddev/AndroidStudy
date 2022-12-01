package com.example.androidestudy.feature.todoapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoPriority
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoTaskContent
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoTaskItem

@Composable
fun HomeScreen(navController: NavController) {
    val todoContent = listOf(
        TodoTaskContent(
            color = Color.Red,
            title = "High",
            taskSymbol = "H",
            description = "重要度の高いタスク",
            priority = TodoPriority.High
        ),
        TodoTaskContent(
            color = Color.Green,
            title = "Medium",
            taskSymbol = "M",
            description = "重要度が普通のタスク",
            priority = TodoPriority.Medium
        ),
        TodoTaskContent(
            color = Color.Blue,
            title = "Low",
            taskSymbol = "L",
            description = "重要度の低いタスク",
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