package com.example.androidestudy.feature.todoapp.domain.repository

import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoLocalDBRepository {
    fun getAllTodo(): Flow<List<TodoItem>>
    suspend fun insertTodoItem(todoItem: TodoItem)
    fun getTodoItemByPriority(priority: Int): Flow<List<TodoItem>>
    suspend fun updateTodoItem(todoItem: TodoItem)
    suspend fun deleteTodoItem(todoItem: TodoItem)
    suspend fun deleteAllTodoItem(priority: Int)
}