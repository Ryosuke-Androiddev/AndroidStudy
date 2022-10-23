package com.example.androidestudy.feature.retrofit.presentation.postlist.viewmodel

import androidx.compose.runtime.snapshotFlow
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.order.OrderType
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder
import com.example.androidestudy.feature.retrofit.domain.model.result.DeleteUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.util.ScreenState
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

    @Test
    fun `Delete UserPostItem with Successfully State`() = runTest {

        val userPostItem = UserPostItem(
            body = "",
            id = 1,
            title = "",
            userId = 1
        )

        val expectedState = PostListScreenState(
            recentlyDeletePost = userPostItem
        )

        var actualState: List<PostListScreenState>? = null

        val job = launch {
            actualState = snapshotFlow { viewModel.state }
                .take(2)
                .toList()
        }

        // 削除対象のリストを保持しておく必要があるからここのテストでこの記述が必要
        coEvery {
            getAllUserPostsUseCase(any())
        } returns dummyUserPosts

        coEvery {
            deleteUserPostUseCase(userPostItem = any())
        } returns DeleteUserPostState.DeleteUserPost(statusCode = "200")

        // ここで渡したUserPostItemを保持していない理由はなぜ??
        viewModel.onEvent(PostListEvent.DeletePost(userPostItem = userPostItem))

        job.join()

        assertThat(actualState?.size).isEqualTo(2)

        // 2つの処理を抜き出して確認する
        assertThat(actualState?.get(0)?.recentlyDeletePost).isEqualTo(null)
        assertThat(actualState?.get(1)?.recentlyDeletePost).isEqualTo(expectedState.recentlyDeletePost)
    }

    @Test
    fun `Delete UserPostItem with Failure State`() = runTest {

        val userPostItem = UserPostItem(
            body = "",
            id = 1,
            title = "",
            userId = 1
        )

        val expectedState = PostListScreenState(
            recentlyDeletePost = null
        )

        var actualState: List<PostListScreenState>? = null

        val job = launch {
            actualState = snapshotFlow { viewModel.state }
                .take(1)
                .toList()
        }

        // 削除対象のリストを保持しておく必要があるからここのテストでこの記述が必要
        coEvery {
            getAllUserPostsUseCase(any())
        } returns dummyUserPosts

        coEvery {
            deleteUserPostUseCase(userPostItem = any())
        } returns DeleteUserPostState.Failure

        // ここで渡したUserPostItemを保持していない理由はなぜ??
        viewModel.onEvent(PostListEvent.DeletePost(userPostItem = userPostItem))

        job.join()

        assertThat(actualState?.size).isEqualTo(1)

        // 2つの処理を抜き出して確認する
        assertThat(actualState?.get(0)?.recentlyDeletePost).isEqualTo(expectedState.recentlyDeletePost)
    }

    @Test
    fun `ToggleOrderSection is Visible State`() = runTest {

        val expectedState = PostListScreenState(
            isOrderSectionVisible = true
        )

        var actualState: List<PostListScreenState>? = null

        val job = launch {
            actualState = snapshotFlow { viewModel.state }
                .take(1)
                .toList()
        }

        coEvery {
            getAllUserPostsUseCase(any())
        } returns dummyUserPosts

        viewModel.onEvent(PostListEvent.ToggleOrderSection)

        job.join()

        assertThat(actualState?.size).isEqualTo(1)

        assertThat(actualState?.get(0)?.isOrderSectionVisible).isEqualTo(expectedState.isOrderSectionVisible)
    }

    @Test
    fun `ToggleOrderSection is not Visible State`() = runTest {

        val expectedState = PostListScreenState(
            isOrderSectionVisible = false
        )

        var actualState: List<PostListScreenState>? = null

        val job = launch {
            actualState = snapshotFlow { viewModel.state }
                .take(1)
                .toList()
        }

        // ClassCastExceptionが発生するから以下の処理を記述
        // 直接的に関係ないけど
        coEvery {
            getAllUserPostsUseCase(any())
        } returns dummyUserPosts

        // 2回Eventを発生させれば反転すると考えた
        viewModel.onEvent(PostListEvent.ToggleOrderSection)
        viewModel.onEvent(PostListEvent.ToggleOrderSection)

        job.join()

        assertThat(actualState?.size).isEqualTo(1)

        assertThat(actualState?.get(0)?.isOrderSectionVisible).isEqualTo(expectedState.isOrderSectionVisible)
    }

    @Test
    fun `RePost UserPostItem with Successfully State`() = runTest {

        val userPostItem = UserPostItem(
            body = "",
            id = 1,
            title = "",
            userId = 1
        )

        val expectedState = PostListScreenState(
            recentlyDeletePost = null
        )

        var actualState: List<PostListScreenState>? = null

        // 必要以上に取得しすぎないこと
        val job = launch {
            actualState = snapshotFlow { viewModel.state }
                .take(1)
                .toList()
        }

        // 削除対象のリストを保持しておく必要があるからここのテストでこの記述が必要
        coEvery {
            getAllUserPostsUseCase(any())
        } returns dummyUserPosts

        coEvery {
            deleteUserPostUseCase(userPostItem = any())
        } returns DeleteUserPostState.DeleteUserPost(statusCode = "200")

        coEvery {
            postUserPostUseCase(userPostItem = any())
        } returns ScreenState.Success

        viewModel.onEvent(PostListEvent.DeletePost(userPostItem = userPostItem))
        viewModel.onEvent(PostListEvent.RestorePost)

        job.join()

        println(actualState?.get(0))

        assertThat(actualState?.size).isEqualTo(1)

        assertThat(actualState?.get(0)?.recentlyDeletePost).isEqualTo(expectedState.recentlyDeletePost)
    }

    @Test
    fun `RePost UserPostItem with Failure State`() = runTest {

        val userPostItem = UserPostItem(
            body = "",
            id = 1,
            title = "",
            userId = 1
        )

        val expectedState = PostListScreenState(
            recentlyDeletePost = userPostItem
        )

        var actualState: List<PostListScreenState>? = null

        // 必要以上に取得しすぎないこと
        val job = launch {
            actualState = snapshotFlow { viewModel.state }
                .take(2)
                .toList()
        }

        // 削除対象のリストを保持しておく必要があるからここのテストでこの記述が必要
        coEvery {
            getAllUserPostsUseCase(any())
        } returns dummyUserPosts

        coEvery {
            deleteUserPostUseCase(userPostItem = any())
        } returns DeleteUserPostState.DeleteUserPost(statusCode = "200")

        coEvery {
            postUserPostUseCase(userPostItem = any())
        } returns ScreenState.Failure

        viewModel.onEvent(PostListEvent.DeletePost(userPostItem = userPostItem))
        viewModel.onEvent(PostListEvent.RestorePost)

        job.join()

        assertThat(actualState?.size).isEqualTo(2)

        // この辺りのListの使い方を考えてやる必要がある
        // 1つ目に取得したやつは最初の状態で、2つ目からのやつが最終的に評価したいState
        assertThat(actualState?.get(1)?.recentlyDeletePost).isEqualTo(expectedState.recentlyDeletePost)
    }
}