package com.example.androidestudy.feature.todoapp.presentation.home.component

sealed class TodoPriority {
    object High: TodoPriority()
    object Medium: TodoPriority()
    object Low: TodoPriority()
}

enum class Priority(val order: Int) {
    High(1),
    Medium(2),
    Low(3),
    Ordinal(0);

    companion object {
        // converterを省くための処理をここで行う
        fun priorityToInt(priority: Priority): Int {
            return priority.order
        }

        fun intToPriority(order: Int): Priority {
            return when (order) {
                1 -> {
                    High
                }
                2 -> {
                    Medium
                }
                3 -> {
                    Low
                }
                else -> {
                    // ここに分岐することはない
                    Ordinal
                }
            }
        }
    }
}