package com.example.androidestudy.feature.retrofit.presentation.postlist.component

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder

sealed class PostListEvent {
    data class Order(val postOrder: PostOrder): PostListEvent()
    data class DeletePost(val userPostItem: UserPostItem): PostListEvent()
    object RestorePost: PostListEvent()
    object ToggleOrderSection: PostListEvent()
}
