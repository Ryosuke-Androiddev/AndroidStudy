package com.example.androidestudy.feature.todoapp.presentation.home.component.todo

import com.example.androidestudy.feature.todoapp.presentation.home.component.Priority

data class TodoItemState(
    val todoId: Int,
    val title: String,
    val content: String,
    val priority: Priority,
    val createdAt: String
)