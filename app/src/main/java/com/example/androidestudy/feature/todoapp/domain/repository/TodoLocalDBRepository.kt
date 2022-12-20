package com.example.androidestudy.feature.todoapp.domain.repository

import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoLocalDBRepository {
    fun getAllTodo(): Flow<List<TodoItem>>
}