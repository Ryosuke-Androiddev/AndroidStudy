package com.example.androidestudy.feature.todoapp.presentation.home.component

sealed class TodoPriority {
    object High: TodoPriority()
    object Medium: TodoPriority()
    object Low: TodoPriority()
}

enum class Priority(val order: Int) {
    High(1),
    Medium(2),
    Low(3)
}