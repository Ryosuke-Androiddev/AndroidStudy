package com.example.androidestudy.feature.todoapp.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class TodoTaskContent(
    val color: Color,
    val title: String,
    @DrawableRes
    val imageRes: Int,
    val description: String,
    val priority: TodoPriority
)
