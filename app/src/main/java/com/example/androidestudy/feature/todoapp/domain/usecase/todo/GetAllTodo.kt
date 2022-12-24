package com.example.androidestudy.feature.todoapp.domain.usecase.todo

import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem
import com.example.androidestudy.feature.todoapp.domain.repository.TodoLocalDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTodo @Inject constructor(
    private val repository: TodoLocalDBRepository
) {

    // sort 一覧 title, content, priority, createdAt
    operator fun invoke(): Flow<List<TodoItem>> {
        return repository.getAllTodo()
    }
}