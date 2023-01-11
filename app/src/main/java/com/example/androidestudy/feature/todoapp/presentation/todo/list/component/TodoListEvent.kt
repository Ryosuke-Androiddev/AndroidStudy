package com.example.androidestudy.feature.todoapp.presentation.todo.list.component

import com.example.androidestudy.feature.todoapp.domain.model.todo.order.TodoOrder

sealed class TodoListEvent {
    data class EnterSearchQuery(val query: String): TodoListEvent()
    data class Order(val postOrder: TodoOrder): TodoListEvent()
    data class GetTodoListByPriority(val priority: Int): TodoListEvent()
    object ClearSearchQuery: TodoListEvent()
    object GetTodoAllTodoList: TodoListEvent()
    object SearchTodoItem: TodoListEvent()
    object ToggleOrderSection: TodoListEvent()
}
