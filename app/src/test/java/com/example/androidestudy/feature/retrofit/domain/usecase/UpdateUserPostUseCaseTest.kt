package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.domain.model.UserOperationResult
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
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
        textInputValidationUseCase = TextInputValidationUseCase()
        updateUserPostUseCase = UpdateUserPostUseCase(
            repository = userPostRepository,
            textInputValidationUseCase = textInputValidationUseCase
        )
    }

    @Test
    fun `Title Less than 10 characters, confirm its state`() = runTest {
        // スタブを用意
        val userPostItemTitleLess = UserPostItem(
            body = "12345678910",
            id = 1,
            title = "123",
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

        val actualResult = updateUserPostUseCase(userPostItem = userPostItemTitleLess).getOrDefault(defaultUserOperationResult)

        assertThat(actualResult).isEqualTo(expectedUserOperationResult)
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

        val actualResult = updateUserPostUseCase(userPostItem = userPostItemBodyLess).getOrDefault(defaultUserOperationResult)

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

        val actualResult = updateUserPostUseCase(userPostItem = userPostItemTitleOver).getOrDefault(defaultUserOperationResult)

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

        val actualResult = updateUserPostUseCase(userPostItem = userPostItemBodyOver).getOrDefault(defaultUserOperationResult)

        assertThat(actualResult).isEqualTo(expectedUserOperationResult)
    }

    // ここのテストが通ってないけど諸事情で一旦プッシュ
    @Test
    fun `Pass Validation Check and Success Internet Connection`() = runTest {
        // スタブを用意
        val userPostItem = UserPostItem(
            body = "12345678910",
            id = 1,
            title = "12345678910",
            userId = 1
        )

        val expectedUserOperationResult = UserOperationResult(
            statusCode = "200",
            textInputValidationResult = true
        )

        // 値がなかった時のためにデフォルト値を用意しておく
        // 予想する変数の構成とずらして用意する
        val defaultUserOperationResult = UserOperationResult(
            statusCode = "500",
            textInputValidationResult = false
        )

        coEvery {
            userPostRepository.updatePost(userPostItem = userPostItem).isSuccess
        } returns true

        coEvery {
            userPostRepository.updatePost(userPostItem = userPostItem)
        } returns Result.success("200")

        val actualResult = updateUserPostUseCase(userPostItem = userPostItem).getOrDefault(defaultUserOperationResult)

        assertThat(actualResult).isEqualTo(expectedUserOperationResult)
    }

    @Test
    fun `Pass Validation Check but Occurred Exception`() = runTest {

    }
}