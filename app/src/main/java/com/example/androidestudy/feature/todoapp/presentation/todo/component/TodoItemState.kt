package com.example.androidestudy.feature.todoapp.presentation.todo.component

import com.example.androidestudy.feature.todoapp.domain.model.todo.order.TodoPostOrder
import com.example.androidestudy.feature.todoapp.presentation.home.component.todo.TodoItemState

data class TodoItemState(
    val query: String = "",
    val todoItemList: List<TodoItemState> = emptyList(),
    val todoPostOrder: TodoPostOrder? = null,
    val todoListByPriority: List<TodoItemState> = emptyList()
)