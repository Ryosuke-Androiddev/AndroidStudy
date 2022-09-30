package com.example.androidestudy.feature.retrofit.data.repository

import com.example.androidestudy.feature.retrofit.data.remote.UserPostApi
import com.example.androidestudy.feature.retrofit.data.remote.inValidUserPostById
import com.example.androidestudy.feature.retrofit.data.remote.inValidUserPostResponse
import com.example.androidestudy.feature.retrofit.data.remote.validUserPostById
import com.example.androidestudy.feature.retrofit.data.remote.validUserPostResponse
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.google.common.truth.Truth.assertThat
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

    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var userPostApi: UserPostApi
    private lateinit var repository: UserPostRepositoryImpl

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        userPostApi = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(UserPostApi::class.java)
        repository = UserPostRepositoryImpl(
            userPostApi = userPostApi
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    // titleを変更したときだけパースが有効になる原因を探る
    @Test
    fun `GET all User Posts with valid result`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validUserPostResponse)
        )
        val actualResult = repository.getUserPosts()
        assertThat(actualResult.isSuccess).isTrue()
    }

    @Test
    fun `GET all User Posts with 403`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validUserPostResponse)
        )
        val actualResult = repository.getUserPosts()
        assertThat(actualResult.isFailure).isTrue()
    }

    @Test
    fun `GET all User Posts with Malformed results`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(inValidUserPostResponse)
        )
        val actualResult = repository.getUserPosts()
        assertThat(actualResult.isFailure).isTrue()
    }

    @Test
    fun `GET User Posts By Id with valid result`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validUserPostById)
        )
        val actualResult = repository.getPostById("1")
        assertThat(actualResult.isSuccess).isTrue()
    }

    @Test
    fun `GET User Posts By Id with 403`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validUserPostById)
        )
        val actualResult = repository.getPostById("1")
        assertThat(actualResult.isFailure).isTrue()
    }

    // titleの部分を変えた時にしかパースエラーにならない原因を探す必要がある
    @Test
    fun `GET User Posts By Id with Malformed results`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(inValidUserPostById)
        )
        val actualResult = repository.getPostById("1")
        assertThat(actualResult.isFailure).isTrue()
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
        assertThat(actualResult.isSuccess).isTrue()
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
        assertThat(actualResult.isFailure).isTrue()
    }

    @Test
    fun `Update User Post with valid result`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(201)
        )
        val actualResult = repository.updatePost("1")
        assertThat(actualResult.isSuccess).isTrue()
    }

    @Test
    fun `Update User Post with 500`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
        )
        val actualResult = repository.updatePost("1")
        assertThat(actualResult.isFailure).isTrue()
    }

    @Test
    fun `Delete User Post with valid result`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
        )
        val actualResult = repository.deletePost("1")
        assertThat(actualResult.isSuccess).isTrue()
    }

    @Test
    fun `Delete User Post with 404`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(404)
        )
        val actualResult = repository.deletePost("1")
        assertThat(actualResult.isFailure).isTrue()
    }
}