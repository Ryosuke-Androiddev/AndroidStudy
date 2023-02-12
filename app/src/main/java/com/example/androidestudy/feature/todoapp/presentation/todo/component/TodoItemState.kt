package com.example.androidestudy.feature.todoapp.presentation.todo.component

import com.example.androidestudy.feature.todoapp.domain.model.todo.order.OrderType
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.TodoOrder
import com.example.androidestudy.feature.todoapp.presentation.home.component.todo.TodoItemState

data class TodoItemState(
    val query: String = "",
    val todoItemList: List<TodoItemState> = emptyList(),
    val todoOrder: TodoOrder = TodoOrder.Date(OrderType.Descending),
    val todoListByPriority: List<TodoItemState> = emptyList(),
    val isOrderSectionVisible: Boolean = false
)