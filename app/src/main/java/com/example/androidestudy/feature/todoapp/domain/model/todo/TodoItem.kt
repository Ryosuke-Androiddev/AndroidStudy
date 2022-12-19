package com.example.androidestudy.feature.todoapp.domain.model.todo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoPriority

@Entity(tableName = "Todo_App")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val todoId: Int,
    val title: String,
    val content: String,
    val priority: TodoPriority,
    val createdAt: String
)
