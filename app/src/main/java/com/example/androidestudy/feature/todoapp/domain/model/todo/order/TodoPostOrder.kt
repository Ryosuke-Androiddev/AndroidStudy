package com.example.androidestudy.feature.todoapp.domain.model.todo.order

sealed class TodoPostOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): TodoPostOrder(orderType)
    class Date(orderType: OrderType): TodoPostOrder(orderType)
    class Priority(orderType: OrderType): TodoPostOrder(orderType)
}
