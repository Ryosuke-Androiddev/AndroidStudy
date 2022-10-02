package com.example.androidestudy.feature.retrofit.domain.model.order

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
