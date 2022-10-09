package com.example.androidestudy.feature.retrofit.presentation.postlist.component

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.order.OrderType
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder

data class PostListScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val postList: List<UserPostItem> = emptyList(),
    val postOrder: PostOrder = PostOrder.Id(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
