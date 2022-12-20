package com.example.androidestudy.feature.todoapp.data.repository

import com.example.androidestudy.feature.todoapp.data.local.TodoDao
import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem
import com.example.androidestudy.feature.todoapp.domain.repository.TodoLocalDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoLocalDBRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
): TodoLocalDBRepository {

    override fun getAllTodo(): Flow<List<TodoItem>> {
        return todoDao.getAllTodo()
    }
}