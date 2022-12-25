package com.example.androidestudy.feature.todoapp.domain.model.todo.order

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
