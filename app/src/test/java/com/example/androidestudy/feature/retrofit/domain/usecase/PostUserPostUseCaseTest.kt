package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.domain.model.TextInputValidationResult
import com.example.androidestudy.feature.retrofit.domain.model.UserOperationResult
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.PostUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.util.ScreenState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PostUserPostUseCaseTest {

    private lateinit var postUserPostUseCase: PostUserPostUseCase
    private lateinit var textInputValidationUseCase: TextInputValidationUseCase
    private lateinit var userPostRepository: UserPostRepository

    @Before
    fun setup() {
        userPostRepository = mockk(relaxed = true)
        textInputValidationUseCase = mockk(relaxed = true)
        postUserPostUseCase = PostUserPostUseCase(
            repository = userPostRepository,
            textInputValidationUseCase = textInputValidationUseCase
        )
    }

    @Test
    fun `Title Less than 10 characters, confirm its state`() = runTest {
        // スタブを用意
        val titleInvalidUserPostItem = UserPostItem(
            body = "12345678910",
            id = 1,
            title = "123",
            userId = 1
        )

        val textInputValidationResult = TextInputValidationResult(
            successful = false,
            errorMessage = "Please Input 10 more characters"
        )

        val expectedState = ScreenState.TextInputError(
            titleInputValidationResult = textInputValidationResult
        )

        coEvery {
            textInputValidationUseCase.validate(any())
        } returns textInputValidationResult

        val actualResult = postUserPostUseCase(userPostItem = titleInvalidUserPostItem)

        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Body Less than 10 characters, confirm its state`() = runTest {
        // スタブを用意
        val userPostItemBodyLess = UserPostItem(
            body = "123",
            id = 1,
            title = "12345678910",
            userId = 1
        )

        val expectedUserOperationResult = UserOperationResult(
            textInputValidationResult = false
        )

        // 値がなかった時のためにデフォルト値を用意しておく
        // 予想する変数の構成とずらして用意する
        val defaultUserOperationResult = UserOperationResult(
            textInputValidationResult = true
        )

        val actualResult = postUserPostUseCase(userPostItem = userPostItemBodyLess)

        assertThat(actualResult).isEqualTo(expectedUserOperationResult)
    }

    @Test
    fun `Title more than 20 characters, confirm its state`() = runTest {
        // スタブを用意
        // 21文字を設定
        val userPostItemTitleOver = UserPostItem(
            body = "123456789101112131415",
            id = 1,
            title = "12345678910",
            userId = 1
        )

        val expectedUserOperationResult = UserOperationResult(
            textInputValidationResult = false
        )

        // 値がなかった時のためにデフォルト値を用意しておく
        // 予想する変数の構成とずらして用意する
        val defaultUserOperationResult = UserOperationResult(
            textInputValidationResult = true
        )

        val actualResult = postUserPostUseCase(userPostItem = userPostItemTitleOver)

        assertThat(actualResult).isEqualTo(expectedUserOperationResult)
    }

    @Test
    fun `Body more than 20 characters, confirm its state`() = runTest {
        // スタブを用意
        // 21文字を設定
        val userPostItemBodyOver = UserPostItem(
            body = "12345678910",
            id = 1,
            title = "123456789101112131415",
            userId = 1
        )

        val expectedUserOperationResult = UserOperationResult(
            textInputValidationResult = false
        )

        // 値がなかった時のためにデフォルト値を用意しておく
        // 予想する変数の構成とずらして用意する
        val defaultUserOperationResult = UserOperationResult(
            textInputValidationResult = true
        )

        val actualResult = postUserPostUseCase(userPostItem = userPostItemBodyOver)

        assertThat(actualResult).isEqualTo(expectedUserOperationResult)
    }

    @Test
    fun `Pass Validation Check and Success Internet Connection`() = runTest {
        // スタブを用意
        val userPostItem = UserPostItem(
            body = "1234567891011",
            id = 1,
            title = "1234567891011",
            userId = 1
        )

        val expectedState = ScreenState.Success

        val textInputValidationResult = TextInputValidationResult(
            successful = true
        )

        coEvery {
            textInputValidationUseCase.validate(any())
        } returns textInputValidationResult

        coEvery {
            userPostRepository.postUserPost(userPostItem = userPostItem)
        } returns PostUserPostState.PostUserPost(statusCode = "200")

        val actualResult = postUserPostUseCase(userPostItem = userPostItem)

        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Pass Validation Check but Occurred Exception`() = runTest {
        // スタブを用意
        val userPostItem = UserPostItem(
            body = "12345678910",
            id = 1,
            title = "12345678910",
            userId = 1
        )

        val expectedUserOperationResult = UserOperationResult(
            statusCode = "500",
            textInputValidationResult = false
        )

        // 値がなかった時のためにデフォルト値を用意しておく
        // 予想する変数の構成とずらして用意する
        val defaultUserOperationResult = UserOperationResult(
            textInputValidationResult = false
        )

        coEvery {
            userPostRepository.postUserPost(userPostItem = userPostItem)
        } throws Exception()

        val actualResult = postUserPostUseCase(userPostItem = userPostItem)

        assertThat(actualResult).isEqualTo(expectedUserOperationResult)
    }
}