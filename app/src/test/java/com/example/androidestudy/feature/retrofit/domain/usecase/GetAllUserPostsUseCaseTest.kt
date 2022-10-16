package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.data.repository.FakeUserpostRepository
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.order.OrderType
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAllUserPostsUseCaseTest {

    private lateinit var getAllUserPostsUseCase: GetAllUserPostsUseCase
    private lateinit var fakeUserPostRepository: UserPostRepository

    @Before
    fun setup() {
        fakeUserPostRepository = FakeUserpostRepository()
        getAllUserPostsUseCase = GetAllUserPostsUseCase(repository = fakeUserPostRepository)

        // FakeRepositoryに追加する用にDummyListを作成
        val dummyPost = mutableListOf<UserPostItem>()
        ('a'..'z').forEachIndexed { index, c ->
            dummyPost.add(
                UserPostItem(
                    body = c.toString(),
                    id = index,
                    title = c.toString(),
                    userId = index
                )
            )
        }
        dummyPost.shuffle()

        // dummyデータをここでFakeRepositoryのリストに追加
        runBlocking {
            dummyPost.forEach {
                fakeUserPostRepository.postUserPost(it)
            }
        }
    }

    @Test
    fun `Order posts by Title Ascending`() = runTest {
        // 戻り値がない場合はデフォルトで設定したからのリストを返す
        val actualPosts = getAllUserPostsUseCase(PostOrder.Title(OrderType.Ascending))
        val actualPostsSize = actualPosts.size

        // 最後の要素は、比較する要素がないので size - 2とする
        for (i in 0..actualPostsSize - 2) {
            assertThat(actualPosts[i].title).isLessThan(actualPosts[i + 1].title)
        }
    }

    @Test
    fun `Order posts by Title Descending`() = runTest {
        val actualPosts = getAllUserPostsUseCase(PostOrder.Title(OrderType.Descending))
        val actualPostsSize = actualPosts.size

        // 最後の要素は、比較する要素がないので size - 2とする
        for (i in 0..actualPostsSize - 2) {
            assertThat(actualPosts[i].title).isGreaterThan(actualPosts[i + 1].title)
        }
    }

    @Test
    fun `Order posts by Id Ascending`() = runTest {
        val actualPosts = getAllUserPostsUseCase(PostOrder.Id(OrderType.Ascending))
        val actualPostsSize = actualPosts.size

        // 最後の要素は、比較する要素がないので size - 2とする
        for (i in 0..actualPostsSize - 2) {
            assertThat(actualPosts[i].id).isLessThan(actualPosts[i + 1].id)
        }
    }

    @Test
    fun `Order posts by Id Descending`() = runTest {
        val actualPosts = getAllUserPostsUseCase(PostOrder.Id(OrderType.Descending))
        val actualPostsSize = actualPosts.size

        // 最後の要素は、比較する要素がないので size - 2とする
        for (i in 0..actualPostsSize - 2) {
            assertThat(actualPosts[i].id).isGreaterThan(actualPosts[i + 1].id)
        }
    }
}