package com.example.androidestudy.feature.todoapp.domain.usecase.todo

import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem
import com.example.androidestudy.feature.todoapp.domain.repository.TodoLocalDBRepository
import com.example.androidestudy.feature.todoapp.presentation.home.component.Priority
import com.example.androidestudy.feature.todoapp.presentation.home.component.todo.TodoItemState
import javax.inject.Inject

class InsertTodoItem @Inject constructor(
    private val repository: TodoLocalDBRepository
) {
    suspend operator fun invoke(todoItemState: TodoItemState) {
        val todoItem = TodoItem(
            todoId = todoItemState.todoId,
            title = todoItemState.title,
            content = todoItemState.content,
            priority = Priority.priorityToInt(todoItemState.priority),
            createdAt = todoItemState.createdAt
        )
        repository.insertTodoItem(
            todoItem = todoItem
        )
    }
}