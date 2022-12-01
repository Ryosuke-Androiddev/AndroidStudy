package com.example.androidestudy.feature.todoapp.presentation.home.component

import androidx.compose.ui.graphics.Color

data class TodoTaskContent(
    val color: Color,
    val title: String,
    val taskSymbol: String,
    val description: String,
    val priority: TodoPriority
)
