package com.example.androidestudy.feature.retrofit.domain.usecase

import android.util.Log
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.order.OrderType
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostsState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class GetAllUserPostsUseCase(
    private val repository: UserPostRepository
) {
    operator fun invoke(
        postOrder: PostOrder = PostOrder.Id(OrderType.Descending)
    ) : Flow<List<UserPostItem>> {

        // ここでは、emitしない??
        return repository.getUserPosts().map { postsState ->
            when (postsState) {
                is GetUserPostsState.GetUserPosts -> {
                    val userPosts = postsState.userPosts
                    when (postOrder.orderType) {
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
                is GetUserPostsState.Failure -> {
                    emptyList()
                }
            }
        }
    }
}