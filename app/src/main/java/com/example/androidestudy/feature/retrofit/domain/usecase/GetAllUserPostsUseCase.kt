package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.order.OrderType
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostsState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class GetAllUserPostsUseCase(
    private val repository: UserPostRepository
) {
    operator fun invoke(
        postOrder: PostOrder = PostOrder.Id(OrderType.Descending)
    ) : Flow<List<UserPostItem>> = flow {

        // RepositoryのKotlin Resultを直接書くとテストの難易度が跳ね上がったのでリストを返すように修正

        repository.getUserPosts().onEach { postsState ->
            when (postsState) {
                is GetUserPostsState.GetUserPosts -> {
                    val userPosts = postsState.userPosts
                    when (postOrder.orderType) {
                        is OrderType.Ascending -> {
                            when (postOrder) {
                                is PostOrder.Title -> emit(userPosts.sortedBy { it.title.lowercase() })
                                is PostOrder.Id -> emit(userPosts.sortedBy { it.id })
                            }
                        }
                        is OrderType.Descending -> {
                            when (postOrder) {
                                is PostOrder.Title -> emit(userPosts.sortedByDescending { it.title.lowercase() })
                                is PostOrder.Id -> emit(userPosts.sortedByDescending { it.id })
                            }
                        }
                    }
                }
                is GetUserPostsState.Failure -> {
                    emit(emptyList())
                }
            }
        }
    }
}