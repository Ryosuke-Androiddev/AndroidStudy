package com.example.androidestudy.feature.retrofit.domain.model.order

sealed class PostOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): PostOrder(orderType)
    class Id(orderType: OrderType): PostOrder(orderType)

    // 昇順、降順を入れ替えるための処理
    fun copy(orderType: OrderType): PostOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Id -> Id(orderType)
        }
    }
}
