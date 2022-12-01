package com.example.androidestudy.feature.todoapp.presentation.home.component

sealed class TodoPriority {
    object High: TodoPriority()
    object Medium: TodoPriority()
    object Low: TodoPriority()
}
