package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.order.OrderType
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository

class GetAllUserPostsUseCase(
    private val repository: UserPostRepository
) {
    suspend operator fun invoke(
        postOrder: PostOrder = PostOrder.Id(OrderType.Descending)
    ) : List<UserPostItem> {

        // RepositoryのKotlin Resultを直接書くとテストの難易度が跳ね上がったのでリストを返すように修正
        val userPosts = repository.getUserPosts().getOrDefault(emptyList())
        return when (postOrder.orderType) {
            is OrderType.Ascending -> {
                when (postOrder) {
                    is PostOrder.Title -> userPosts.sortedBy { it.title.lowercase() }
                    is PostOrder.Id -> userPosts.sortedBy { it.id }
                }
            }
            is OrderType.Descending -> {
                when (postOrder) {
                    is PostOrder.Title -> userPosts.sortedByDescending { it.title.lowercase() }
                    is PostOrder.Id -> userPosts.sortedByDescending { it.id }
                }
            }
        }
    }
}