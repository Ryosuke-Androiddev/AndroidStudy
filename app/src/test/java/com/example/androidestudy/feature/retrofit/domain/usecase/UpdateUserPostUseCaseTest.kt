package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.domain.model.TextInputValidationResult
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.UpdateUserPostState
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
class UpdateUserPostUseCaseTest {

    private lateinit var updateUserPostUseCase: UpdateUserPostUseCase
    private lateinit var textInputValidationUseCase: TextInputValidationUseCase
    private lateinit var userPostRepository: UserPostRepository

    @Before
    fun setup() {
        // 階層を上げたタイミングでモックする?
        userPostRepository = mockk(relaxed = true)
        textInputValidationUseCase = mockk(relaxed = true)
        updateUserPostUseCase = UpdateUserPostUseCase(
            repository = userPostRepository,
            textInputValidationUseCase = textInputValidationUseCase
        )
    }

    @Test
    fun `Title Less than 10 characters, confirm its state`() = runTest {
        // スタブを用意
        val expectedState = ScreenState.TextInputError

        val userPostItemTitleLess = UserPostItem(
            body = "12345678910",
            id = 1,
            title = "123",
            userId = 1
        )

        val titleLessConstraintValidationResult = TextInputValidationResult(
            successful = false,
            errorMessage = "Please Input 10 more characters"
        )

        coEvery {
            textInputValidationUseCase.validate(any())
        } returns titleLessConstraintValidationResult

        val actualResult = updateUserPostUseCase(userPostItem = userPostItemTitleLess)

        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Body Less than 10 characters, confirm its state`() = runTest {
        // スタブを用意
        val expectedState = ScreenState.TextInputError

        val userPostItemBodyLess = UserPostItem(
            body = "123",
            id = 1,
            title = "12345678910",
            userId = 1
        )

        val bodyLessConstraintValidationResult = TextInputValidationResult(
            successful = false,
            errorMessage = "Please Input 10 more characters"
        )

        coEvery {
            textInputValidationUseCase.validate(any())
        } returns bodyLessConstraintValidationResult

        val actualResult = updateUserPostUseCase(userPostItem = userPostItemBodyLess)

        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Title more than 20 characters, confirm its state`() = runTest {
        // スタブを用意
        val expectedState = ScreenState.TextInputError

        // 21文字を設定
        val userPostItemTitleOver = UserPostItem(
            body = "123456789101112131415",
            id = 1,
            title = "12345678910",
            userId = 1
        )

        val titleOverValidationResult = TextInputValidationResult(
            successful = false,
            errorMessage = "Please Input less than 20 characters"
        )

        coEvery {
            textInputValidationUseCase.validate(any())
        } returns titleOverValidationResult

        val actualResult = updateUserPostUseCase(userPostItem = userPostItemTitleOver)

        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Body more than 20 characters, confirm its state`() = runTest {
        // スタブを用意
        val expectedState = ScreenState.TextInputError

        // 21文字を設定
        val userPostItemBodyOver = UserPostItem(
            body = "12345678910",
            id = 1,
            title = "123456789101112131415",
            userId = 1
        )

        val bodyOverValidationResult = TextInputValidationResult(
            successful = false,
            errorMessage = "Please Input less than 20 characters"
        )

        coEvery {
            textInputValidationUseCase.validate(any())
        } returns bodyOverValidationResult

        val actualResult = updateUserPostUseCase(userPostItem = userPostItemBodyOver)

        assertThat(actualResult).isEqualTo(expectedState)
    }

    // ここのテストが通ってないけど諸事情で一旦プッシュ
    @Test
    fun `Pass Validation Check and Success Internet Connection`() = runTest {
        // スタブを用意
        val expectedState = ScreenState.Success

        val userPostItem = UserPostItem(
            body = "12345678910",
            id = 1,
            title = "12345678910",
            userId = 1
        )

        val textInputValidationResult = TextInputValidationResult(
            successful = true
        )

        coEvery {
            textInputValidationUseCase.validate(any())
        } returns textInputValidationResult

        coEvery {
            userPostRepository.updatePost(userPostItem = userPostItem)
        } returns UpdateUserPostState.UpdateUserPost(statusCode = "200")

        val actualResult = updateUserPostUseCase(userPostItem = userPostItem)

        assertThat(actualResult).isEqualTo(expectedState)
    }

    @Test
    fun `Pass Validation Check but Occurred Exception`() = runTest {
        // スタブを用意
        val expectedState = ScreenState.Failure

        val userPostItem = UserPostItem(
            body = "1234567891011",
            id = 1,
            title = "1234567891011",
            userId = 1
        )

        val textInputValidationResult = TextInputValidationResult(
            successful = true
        )

        coEvery {
            textInputValidationUseCase.validate(any())
        } returns textInputValidationResult

        coEvery {
            userPostRepository.updatePost(userPostItem = userPostItem)
        } returns UpdateUserPostState.Failure

        val actualResult = updateUserPostUseCase(userPostItem = userPostItem)

        assertThat(actualResult).isEqualTo(expectedState)
    }
}