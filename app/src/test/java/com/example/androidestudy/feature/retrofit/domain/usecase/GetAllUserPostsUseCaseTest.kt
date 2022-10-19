package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.data.remote.parse.createUserPosts
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.order.OrderType
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostsState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAllUserPostsUseCaseTest {

    private lateinit var fakeUserPosts: List<UserPostItem>
    private lateinit var getAllUserPostsUseCase: GetAllUserPostsUseCase
    private lateinit var userPostRepository: UserPostRepository

    @Before
    fun setup() {
        // ソートする前にシャッフルしておくことでソートの正確性を確かめる
        fakeUserPosts = createUserPosts().shuffled()
        userPostRepository = mockk(relaxed = true)
        getAllUserPostsUseCase = GetAllUserPostsUseCase(repository = userPostRepository)
    }

    @Test
    fun `Order posts by Title Ascending`() = runTest {

        val expectedMockkValue = GetUserPostsState.GetUserPosts(userPosts = fakeUserPosts)
        coEvery {
            userPostRepository.getUserPosts()
        } returns expectedMockkValue

        // 戻り値がない場合はデフォルトで設定したからのリストを返す
        val actualPosts = getAllUserPostsUseCase(PostOrder.Title(OrderType.Ascending))
        val actualPostsSize = actualPosts.size

        // 最後の要素は、比較する要素がないので size - 2とする
        for (i in 0..actualPostsSize - 2) {
            println(actualPosts[i].title)
            assertThat(actualPosts[i].title).isLessThan(actualPosts[i + 1].title)
        }
    }

    @Test
    fun `Order posts by Title Descending`() = runTest {

        val expectedMockkValue = GetUserPostsState.GetUserPosts(userPosts = fakeUserPosts)
        coEvery {
            userPostRepository.getUserPosts()
        } returns expectedMockkValue

        val actualPosts = getAllUserPostsUseCase(PostOrder.Title(OrderType.Descending))
        val actualPostsSize = actualPosts.size

        // 最後の要素は、比較する要素がないので size - 2とする
        for (i in 0..actualPostsSize - 2) {
            println(actualPosts[i].title)
            assertThat(actualPosts[i].title).isGreaterThan(actualPosts[i + 1].title)
        }
    }

    @Test
    fun `Order posts by Id Ascending`() = runTest {

        val expectedMockkValue = GetUserPostsState.GetUserPosts(userPosts = fakeUserPosts)
        coEvery {
            userPostRepository.getUserPosts()
        } returns expectedMockkValue

        val actualPosts = getAllUserPostsUseCase(PostOrder.Id(OrderType.Ascending))
        val actualPostsSize = actualPosts.size

        // 最後の要素は、比較する要素がないので size - 2とする
        for (i in 0..actualPostsSize - 2) {
            println(actualPosts[i].id)
            assertThat(actualPosts[i].id).isLessThan(actualPosts[i + 1].id)
        }
    }

    @Test
    fun `Order posts by Id Descending`() = runTest {

        val expectedMockkValue = GetUserPostsState.GetUserPosts(userPosts = fakeUserPosts)
        coEvery {
            userPostRepository.getUserPosts()
        } returns expectedMockkValue

        val actualPosts = getAllUserPostsUseCase(PostOrder.Id(OrderType.Descending))
        val actualPostsSize = actualPosts.size

        // 最後の要素は、比較する要素がないので size - 2とする
        for (i in 0..actualPostsSize - 2) {
            println(actualPosts[i].id)
            assertThat(actualPosts[i].id).isGreaterThan(actualPosts[i + 1].id)
        }
    }

    @Test
    fun `Order posts by Title Ascending With Exception`() = runTest {

        val expectedMockkValue = GetUserPostsState.Failure
        coEvery {
            userPostRepository.getUserPosts()
        } returns expectedMockkValue

        // 戻り値がない場合はデフォルトで設定したからのリストを返す
        val actualPosts = getAllUserPostsUseCase(PostOrder.Title(OrderType.Ascending))
        val actualPostsSize = actualPosts.size

        assertThat(actualPostsSize).isEqualTo(0)
    }

    @Test
    fun `Order posts by Title Descending With Exception`() = runTest {

        val expectedMockkValue = GetUserPostsState.Failure
        coEvery {
            userPostRepository.getUserPosts()
        } returns expectedMockkValue

        val actualPosts = getAllUserPostsUseCase(PostOrder.Title(OrderType.Descending))
        val actualPostsSize = actualPosts.size

        assertThat(actualPostsSize).isEqualTo(0)
    }

    @Test
    fun `Order posts by Id Ascending With Exception`() = runTest {

        val expectedMockkValue = GetUserPostsState.Failure
        coEvery {
            userPostRepository.getUserPosts()
        } returns expectedMockkValue

        val actualPosts = getAllUserPostsUseCase(PostOrder.Id(OrderType.Ascending))
        val actualPostsSize = actualPosts.size

        assertThat(actualPostsSize).isEqualTo(0)
    }

    @Test
    fun `Order posts by Id Descending With Exception`() = runTest {

        val expectedMockkValue = GetUserPostsState.Failure
        coEvery {
            userPostRepository.getUserPosts()
        } returns expectedMockkValue

        val actualPosts = getAllUserPostsUseCase(PostOrder.Id(OrderType.Descending))
        val actualPostsSize = actualPosts.size

        assertThat(actualPostsSize).isEqualTo(0)
    }
}