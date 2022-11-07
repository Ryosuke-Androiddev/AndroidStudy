package com.example.androidestudy.feature.retrofit.data.repository

import com.example.androidestudy.feature.retrofit.data.mapper.toUserPostItemDto
import com.example.androidestudy.feature.retrofit.data.remote.UserPostApi
import com.example.androidestudy.feature.retrofit.data.remote.dto.UserPostItemDto
import com.example.androidestudy.feature.retrofit.data.remote.inValidUserPostById
import com.example.androidestudy.feature.retrofit.data.remote.inValidUserPostResponse
import com.example.androidestudy.feature.retrofit.data.remote.parse.createUserPosts
import com.example.androidestudy.feature.retrofit.data.remote.parse.createUserPostsDto
import com.example.androidestudy.feature.retrofit.data.remote.parse.getPostById
import com.example.androidestudy.feature.retrofit.data.remote.parse.getPostDtoById
import com.example.androidestudy.feature.retrofit.data.remote.validUserPostById
import com.example.androidestudy.feature.retrofit.data.remote.validUserPostResponse
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.DeleteUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostByIdState
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostsState
import com.example.androidestudy.feature.retrofit.domain.model.result.PostUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.result.UpdateUserPostState
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserPostRepositoryImplTest {

    private lateinit var userPosts: List<UserPostItem>
    private lateinit var userPostsDto: List<UserPostItemDto>
    private lateinit var getUserPostById: UserPostItem
    private lateinit var getUserPostDtoById: UserPostItemDto
    private lateinit var mockWebServer: MockWebServer
    private lateinit var userPostApi: UserPostApi
    private lateinit var repository: UserPostRepositoryImpl
    private lateinit var requestUserPostItem: UserPostItem

    @Before
    fun setup() {
        userPosts = createUserPosts()
        userPostsDto = createUserPostsDto()
        getUserPostById = getPostById()
        getUserPostDtoById = getPostDtoById()
        mockWebServer = MockWebServer()
        userPostApi = mockk(relaxed = true)
        repository = UserPostRepositoryImpl(
            userPostApi = userPostApi
        )
        requestUserPostItem = UserPostItem(
            body = "",
            id = 1,
            title = "",
            userId = 1
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    // titleを変更したときだけパースが有効になる原因を探る
    @Test
    fun `GET all User Posts with valid result`() = runTest {

        val expectedState = GetUserPostsState.GetUserPosts(
            userPosts = userPosts
        )

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validUserPostResponse)
        )

        coEvery {
            userPostApi.getUserPosts()
        } returns userPostsDto

        val actualResult = repository.getUserPosts()

        println(actualResult)

        assertThat(expectedState).isEqualTo(actualResult)
    }

    @Test
    fun `GET all User Posts with 403`() = runTest {

        val expectedState = GetUserPostsState.Failure

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validUserPostResponse)
        )

        coEvery {
            userPostApi.getUserPosts()
        } throws Exception()

        val actualResult = repository.getUserPosts()
        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `GET all User Posts with Malformed results`() = runTest {

        val expectedState = GetUserPostsState.Failure

        mockWebServer.enqueue(
            MockResponse()
                .setBody(inValidUserPostResponse)
        )

        coEvery {
            userPostApi.getUserPosts()
        } throws Exception()

        val actualResult = repository.getUserPosts()
        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `GET User Posts By Id with valid result`() = runTest {

        val expectedState = GetUserPostByIdState.GetUserPostById(
            userPost = getUserPostById
        )

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validUserPostById)
        )

        coEvery {
            userPostApi.getPostById(id = "1")
        } returns getUserPostDtoById

        val actualResult = repository.getPostById("1")
        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `GET User Posts By Id with 403`() = runTest {

        val expectedState = GetUserPostByIdState.Failure

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validUserPostById)
        )

        coEvery {
            userPostApi.getPostById(id = "1")
        } throws Exception()

        val actualResult = repository.getPostById("1")
        assertThat(actualResult).isEqualTo(expectedState)
    }

    // titleの部分を変えた時にしかパースエラーにならない原因を探す必要がある
    @Test
    fun `GET User Posts By Id with Malformed results`() = runTest {

        val expectedState = GetUserPostByIdState.Failure

        mockWebServer.enqueue(
            MockResponse()
                .setBody(inValidUserPostById)
        )

        coEvery {
            userPostApi.getPostById(id = "1")
        } throws Exception()

        val actualResult = repository.getPostById("1")
        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Post User Post with valid result`() = runTest {

        val expectedState = PostUserPostState.PostUserPost(
            statusCode = "200"
        )

        val userPostItem = UserPostItem(
            body = "abcdefg",
            id = 1,
            title = "abcdefg",
            userId = 1
        )

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
        )

        // Mockkがいるのは戻り値があるとき
        val actualResult = repository.postUserPost(userPostItem)
        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Post User Post with 500`() = runTest {

        val expectedState = PostUserPostState.Failure

        val userPostItem = UserPostItem(
            body = "abcdefg",
            id = 1,
            title = "abcdefg",
            userId = 1
        )

        val userPostItemDto = userPostItem.toUserPostItemDto()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
        )

        coEvery {
            userPostApi.postUserPost(userPostItemDto = userPostItemDto)
        } throws Exception()

        val actualResult = repository.postUserPost(userPostItem)
        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Update User Post with valid result`() = runTest {

        val expectedState = UpdateUserPostState.UpdateUserPost(
            statusCode = "200"
        )

        val userPostItem = UserPostItem(
            body = "abcdefg",
            id = 1,
            title = "abcdefg",
            userId = 1
        )

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(201)
        )
        val actualResult = repository.updatePost(userPostItem = userPostItem)
        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Update User Post with 500`() = runTest {

        val expectedState = UpdateUserPostState.Failure

        val userPostItem = UserPostItem(
            body = "abcdefg",
            id = 1,
            title = "abcdefg",
            userId = 1
        )

        val userPostItemDto = userPostItem.toUserPostItemDto()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
        )

        coEvery {
            userPostApi.updatePost("1")
        } throws Exception()

        val actualResult = repository.updatePost(requestUserPostItem)
        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Delete User Post with valid result`() = runTest {

        val expectedState = DeleteUserPostState.DeleteUserPost(
            statusCode = "200"
        )

        val userPostItem = UserPostItem(
            body = "abcdefg",
            id = 1,
            title = "abcdefg",
            userId = 1
        )

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
        )
        val actualResult = repository.deletePost(userPostItem = userPostItem)
        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Delete User Post with 404`() = runTest {

        val expectedState = DeleteUserPostState.Failure

        val userPostItem = UserPostItem(
            body = "abcdefg",
            id = 1,
            title = "abcdefg",
            userId = 1
        )

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(404)
        )

        coEvery {
            userPostApi.deletePost("1")
        } throws Exception()

        val actualResult = repository.deletePost(userPostItem = userPostItem)
        assertThat(actualResult).isEqualTo(expectedState)
    }
}