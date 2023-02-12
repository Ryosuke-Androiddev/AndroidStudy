package com.example.androidestudy.feature.todoapp.domain.model.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo_App")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val todoId: Int,
    val title: String,
    val content: String,
    val priority: Int,
    val createdAt: String,
    val deadLine: String
)
