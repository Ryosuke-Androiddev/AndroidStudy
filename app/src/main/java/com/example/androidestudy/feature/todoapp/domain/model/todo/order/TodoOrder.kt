package com.example.androidestudy.feature.todoapp.domain.model.todo.order

sealed class TodoOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): TodoOrder(orderType)
    class Date(orderType: OrderType): TodoOrder(orderType)
    class Priority(orderType: OrderType): TodoOrder(orderType)

    fun copy(orderType: OrderType): TodoOrder {
        return when(this) {
            is Title -> Title(orderType = orderType)
            is Date -> Date(orderType = orderType)
            is Priority -> Priority(orderType = orderType)
        }
    }
}
