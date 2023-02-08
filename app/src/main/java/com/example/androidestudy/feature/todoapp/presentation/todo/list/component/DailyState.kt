package com.example.androidestudy.feature.todoapp.presentation.todo.list.component

data class DailyState(
    val isLoading: Boolean = false,
    val calendar: List<String> = emptyList()
)
