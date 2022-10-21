package com.example.androidestudy.feature.retrofit.presentation.postlist.viewmodel

import androidx.compose.runtime.snapshotFlow
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.order.OrderType
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import com.example.androidestudy.feature.retrofit.domain.usecase.DeleteUserPostUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.GetAllUserPostsUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.PostUserPostUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.TextInputValidationUseCase
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.PostListEvent
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.PostListScreenState
import com.example.androidestudy.feature.retrofit.test_rule.ComposeStateTestRule
import com.example.androidestudy.feature.retrofit.test_rule.MainDispatcherRule
import com.example.androidestudy.feature.retrofit.util.createAscendingData
import com.example.androidestudy.feature.retrofit.util.createDescendingData
import com.example.androidestudy.feature.retrofit.util.createDummyData
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
class PostListViewModelTest {

    private lateinit var ascendingPost: List<UserPostItem>
    private lateinit var descendingPost: List<UserPostItem>
    private lateinit var dummyUserPosts: List<UserPostItem>
    private lateinit var repository: UserPostRepository
    private lateinit var deleteUserPostUseCase: DeleteUserPostUseCase
    private lateinit var getAllUserPostsUseCase: GetAllUserPostsUseCase
    private lateinit var postUserPostUseCase: PostUserPostUseCase
    private lateinit var textInputValidationUseCase: TextInputValidationUseCase
    private lateinit var viewModel: PostListViewModel

    @get:Rule
    val composeStateTestRule = ComposeStateTestRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        dummyUserPosts = createDummyData()
        ascendingPost = createAscendingData()
        descendingPost = createDescendingData()
        repository = mockk(relaxed = true)
        deleteUserPostUseCase = mockk(relaxed = true)
        getAllUserPostsUseCase = mockk(relaxed = true)
        textInputValidationUseCase = mockk(relaxed = true)
        postUserPostUseCase = mockk(relaxed = true)
        viewModel = PostListViewModel(
            deleteUserPostUseCase = deleteUserPostUseCase,
            getAllUserPostsUseCase = getAllUserPostsUseCase,
            postUserPostUseCase = postUserPostUseCase
        )
    }

    @Test
    fun `Get All Valid UserPosts with Id Descending Order`() = runTest {

        // 想定されるソート処理をしておく
        // PostOrderがclassの比較だから、プロパティの比較を行う拡張関数を用意したい
        val expectedState = PostListScreenState(
            postList = dummyUserPosts.sortedByDescending {
                it.id
            }
        )

        var actualState: List<PostListScreenState>? = null

        val job = launch {
            actualState = snapshotFlow { viewModel.state }
                .take(1)
                .toList()
        }

        // onSuccessの処理がうまく行っていないのどうにかしたいなぁ
        // Successの部分をオーバーライドするしかない??
        coEvery {
            // Mockkで定義しているなら渡す引数も気をつけるべき
            getAllUserPostsUseCase(any())
        } returns descendingPost

        viewModel.onEvent(PostListEvent.Order(PostOrder.Id(OrderType.Descending)))

        job.join()

        assertThat(actualState?.size).isEqualTo(1)

        // sealed classの有効な比較方法が思いつかなかったのでいったん以下の処理
        // 比較しているStateの変化がない
        assertThat(actualState?.get(0)?.postList).isEqualTo(expectedState.postList)
    }

    @Test
    fun `Get All Valid UserPosts with Id Ascending Order`() = runTest {
        val expectedState = PostListScreenState(
            postList = dummyUserPosts.sortedBy {
                it.id
            }
        )

        println(expectedState.postList)

        var actualState: List<PostListScreenState>? = null

        val job = launch {
            actualState = snapshotFlow { viewModel.state }
                .take(1)
                .toList()
        }

        coEvery {
            // Mockkで定義しているなら渡す引数も気をつけるべき
            getAllUserPostsUseCase(any())
        } returns ascendingPost

        viewModel.onEvent(PostListEvent.Order(PostOrder.Id(OrderType.Ascending)))
        job.join()

        assertThat(actualState?.size).isEqualTo(1)

        // sealed classの有効な比較方法が思いつかなかったのでいったん以下の処理
        assertThat(actualState?.get(0)?.postList).isEqualTo(expectedState.postList)
    }
}