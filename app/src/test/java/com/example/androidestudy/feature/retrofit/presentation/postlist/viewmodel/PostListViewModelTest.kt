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
import com.example.androidestudy.feature.retrofit.util.createIdAscendingData
import com.example.androidestudy.feature.retrofit.util.createIdDescendingData
import com.example.androidestudy.feature.retrofit.util.createDummyData
import com.example.androidestudy.feature.retrofit.util.createTitleAscendingData
import com.example.androidestudy.feature.retrofit.util.createTitleDescendingData
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
class PostListViewModelTest {

    private lateinit var idAscendingPost: List<UserPostItem>
    private lateinit var idDescendingPost: List<UserPostItem>
    private lateinit var titleAscendingPost: List<UserPostItem>
    private lateinit var titleDescendingPost: List<UserPostItem>
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
        idAscendingPost = createIdAscendingData()
        idDescendingPost = createIdDescendingData()
        titleAscendingPost = createTitleAscendingData()
        titleDescendingPost = createTitleDescendingData()
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
        } returns idAscendingPost

        viewModel.onEvent(PostListEvent.Order(PostOrder.Id(OrderType.Ascending)))
        job.join()

        assertThat(actualState?.size).isEqualTo(1)

        // sealed classの有効な比較方法が思いつかなかったのでいったん以下の処理
        assertThat(actualState?.get(0)?.postList).isEqualTo(expectedState.postList)
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
        } returns idDescendingPost

        viewModel.onEvent(PostListEvent.Order(PostOrder.Id(OrderType.Descending)))

        job.join()

        assertThat(actualState?.size).isEqualTo(1)

        // sealed classの有効な比較方法が思いつかなかったのでいったん以下の処理
        // 比較しているStateの変化がない
        assertThat(actualState?.get(0)?.postList).isEqualTo(expectedState.postList)
    }

    @Test
    fun `Get All Valid UserPosts with Title Ascending Order`() = runTest {
        val expectedState = PostListScreenState(
            postList = dummyUserPosts.sortedBy {
                it.title
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
        } returns titleAscendingPost

        viewModel.onEvent(PostListEvent.Order(PostOrder.Id(OrderType.Ascending)))
        job.join()

        assertThat(actualState?.size).isEqualTo(1)

        // sealed classの有効な比較方法が思いつかなかったのでいったん以下の処理
        assertThat(actualState?.get(0)?.postList).isEqualTo(expectedState.postList)
    }

    @Test
    fun `Get All Valid UserPosts with Title Descending Order`() = runTest {

        // 想定されるソート処理をしておく
        // PostOrderがclassの比較だから、プロパティの比較を行う拡張関数を用意したい
        val expectedState = PostListScreenState(
            postList = dummyUserPosts.sortedByDescending {
                it.title
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
        } returns titleDescendingPost

        viewModel.onEvent(PostListEvent.Order(PostOrder.Id(OrderType.Descending)))

        job.join()

        assertThat(actualState?.size).isEqualTo(1)

        // sealed classの有効な比較方法が思いつかなかったのでいったん以下の処理
        // 比較しているStateの変化がない
        assertThat(actualState?.get(0)?.postList).isEqualTo(expectedState.postList)
    }
}