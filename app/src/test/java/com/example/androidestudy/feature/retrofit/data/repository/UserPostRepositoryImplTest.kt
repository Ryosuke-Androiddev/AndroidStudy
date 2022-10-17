package com.example.androidestudy.feature.retrofit.data.repository

import com.example.androidestudy.feature.retrofit.data.mapper.toUserPostItem
import com.example.androidestudy.feature.retrofit.data.remote.UserPostApi
import com.example.androidestudy.feature.retrofit.data.remote.dto.UserPostItemDto
import com.example.androidestudy.feature.retrofit.data.remote.inValidUserPostById
import com.example.androidestudy.feature.retrofit.data.remote.inValidUserPostResponse
import com.example.androidestudy.feature.retrofit.data.remote.parse.createUserPosts
import com.example.androidestudy.feature.retrofit.data.remote.parse.createUserPostsDto
import com.example.androidestudy.feature.retrofit.data.remote.validUserPostById
import com.example.androidestudy.feature.retrofit.data.remote.validUserPostResponse
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostByIdState
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostsState
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class UserPostRepositoryImplTest {

    private lateinit var userPosts: List<UserPostItem>
    private lateinit var userPostsDto: List<UserPostItemDto>
    private lateinit var mockWebServer: MockWebServer
    private lateinit var userPostApi: UserPostApi
    private lateinit var repository: UserPostRepositoryImpl
    private lateinit var requestUserPostItem: UserPostItem

    @Before
    fun setup() {
        userPosts = createUserPosts()
        userPostsDto = createUserPostsDto()
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
        // JSONをパースしてプロパティとして持たせる
        val expectedState = GetUserPostsState.GetUserPosts(
            userPosts = userPosts
        )

        // val expectedState = GetUserPostsState.Failure

        // MockkでそのJSONのレスポンスがあったと想定して
        // ここでのパースは全てモックしたオブジェクトがやってくれると想定している
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validUserPostResponse)
        )

        coEvery {
            userPostApi.getUserPosts()
        } returns userPostsDto

        // Dtoの変換がうまく処理できてない
        //println(userPostApi.getUserPosts())

        val actualResult = repository.getUserPosts()

        println(actualResult)

        assertThat(expectedState).isEqualTo(actualResult)
    }

    @Test
    fun `GET all User Posts with 403`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validUserPostResponse)
        )
        val actualResult = repository.getUserPosts()
        //assertThat(actualResult.isFailure).isTrue()
    }

    @Test
    fun `GET all User Posts with Malformed results`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(inValidUserPostResponse)
        )
        val actualResult = repository.getUserPosts()
        //assertThat(actualResult.isFailure).isTrue()
    }

    @Test
    fun `GET User Posts By Id with valid result`() = runTest {
        val expectedState = GetUserPostByIdState.Failure
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validUserPostById)
        )
        val actualResult = repository.getPostById("1")
        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `GET User Posts By Id with 403`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validUserPostById)
        )
        val actualResult = repository.getPostById("1")
        //assertThat(actualResult.isFailure).isTrue()
    }

    // titleの部分を変えた時にしかパースエラーにならない原因を探す必要がある
    @Test
    fun `GET User Posts By Id with Malformed results`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(inValidUserPostById)
        )
        val actualResult = repository.getPostById("1")
        //assertThat(actualResult.isFailure).isTrue()
    }

    @Test
    fun `Post User Post with valid result`() = runTest {
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
        val actualResult = repository.postUserPost(userPostItem)
        //assertThat(actualResult.isSuccess).isTrue()
    }

    @Test
    fun `Post User Post with 500`() = runTest {
        val userPostItem = UserPostItem(
            body = "abcdefg",
            id = 1,
            title = "abcdefg",
            userId = 1
        )
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
        )
        val actualResult = repository.postUserPost(userPostItem)
        //assertThat(actualResult.isFailure).isTrue()
    }

    @Test
    fun `Update User Post with valid result`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(201)
        )
        val actualResult = repository.updatePost(requestUserPostItem)
        //assertThat(actualResult.isSuccess).isTrue()
    }

    @Test
    fun `Update User Post with 500`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
        )
        val actualResult = repository.updatePost(requestUserPostItem)
        //assertThat(actualResult.isFailure).isTrue()
    }

    @Test
    fun `Delete User Post with valid result`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
        )
        val actualResult = repository.deletePost(requestUserPostItem)
        //assertThat(actualResult.isSuccess).isTrue()
    }

    @Test
    fun `Delete User Post with 404`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(404)
        )
        val actualResult = repository.deletePost(requestUserPostItem)
        //assertThat(actualResult.isFailure).isTrue()
    }
}