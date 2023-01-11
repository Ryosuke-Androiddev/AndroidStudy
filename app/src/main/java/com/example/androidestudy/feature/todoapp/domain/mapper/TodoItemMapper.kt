package com.example.androidestudy.feature.todoapp.domain.mapper

import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem
import com.example.androidestudy.feature.todoapp.presentation.home.component.Priority
import com.example.androidestudy.feature.todoapp.presentation.home.component.todo.TodoItemState

fun TodoItem.toTodoItemState() : TodoItemState {
    return TodoItemState(
        todoId = todoId,
        title = title,
        content = content,
        priority = Priority.intToPriority(priority),
        createdAt = createdAt,
        deadLine = deadLine
    )
}