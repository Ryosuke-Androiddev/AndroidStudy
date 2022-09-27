package com.example.androidestudy.feature.auth.presentation.login.viewmodel

import androidx.compose.runtime.snapshotFlow
import com.example.androidestudy.feature.auth.domain.model.ResultState
import com.example.androidestudy.feature.auth.domain.model.ValidationResult
import com.example.androidestudy.feature.auth.domain.repository.AuthRepository
import com.example.androidestudy.feature.auth.domain.use_case.TextInputValidation
import com.example.androidestudy.feature.auth.presentation.login.component.LoginEvent
import com.example.androidestudy.feature.auth.presentation.util.AuthState
import com.example.androidestudy.feature.auth.test_rule.ComposeStateTestRule
import com.example.androidestudy.feature.auth.test_rule.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
class LoginViewModelTest {

    private lateinit var repository: AuthRepository
    private lateinit var textInputValidation: TextInputValidation
    private lateinit var viewModel: LoginViewModel

    @get:Rule
    val composeStateTestRule = ComposeStateTestRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        textInputValidation = mockk(relaxed = true)
        viewModel = LoginViewModel(
            textInputValidation,
            repository
        )
    }

    @Test
    fun `Login with Valid User Email and Confirm its value Changed`() = runTest {

        val expectedState = AuthState(
            loginEmail = "abcdefg@gmail.com"
        )

        var actualState : List<AuthState>? = null

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.loginState }
                .take(1)
                .toList()
        }

        viewModel.onLoginEvent(LoginEvent.LoginEmailChanged("abcdefg@gmail.com"))
        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }

    @Test
    fun `Login with Password and Confirm its value Changed`() = runTest {
        val expectedState = AuthState(
            loginPassword = "12345678910"
        )

        var actualState : List<AuthState>? = null

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.loginState }
                .take(1)
                .toList()
        }

        viewModel.onLoginEvent(LoginEvent.LoginUserPasswordChanged(value = "12345678910"))
        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }

    @Test
    fun `Login with Password and Confirm its Icon Visibility Changed`() = runTest {
        val expectedState = AuthState(
            showText = true
        )

        var actualState : List<AuthState>? = null

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.loginState }
                .take(1)
                .toList()
        }

        viewModel.onLoginEvent(LoginEvent.LoginPasswordVisibility(showText = viewModel.loginState.showText))
        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }

    @Test
    fun `successfully User Sign In`() = runTest {

        // スタブを用意
        val expectedState = AuthState(
            isLoading = false,
            loginEmail = "abcdefg@gmail.com",
            loginPassword = "1234567891011"
        )

        var actualState : List<AuthState>? = null

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.loginState }
                .take(1)
                .toList()
        }

        viewModel.onLoginEvent(LoginEvent.LoginEmailChanged(value = "abcdefg@gmail.com"))
        viewModel.onLoginEvent(LoginEvent.LoginUserPasswordChanged(value = "1234567891011"))

        every {
            textInputValidation.validate(viewModel.loginState.loginEmail)
        } returns ValidationResult(
            successful = true
        )

        every {
            textInputValidation.validate(viewModel.loginState.loginPassword)
        } returns ValidationResult(
            successful = true
        )

        // いまいち定義すべき順番がわかってない
        // Auth State
        val actualAuthState = ResultState.Success

        every {
            repository.loginUser(
                viewModel.loginState.loginEmail,
                viewModel.loginState.loginPassword
            )
        } returns flowOf(actualAuthState)

        viewModel.onLoginEvent(LoginEvent.Login)

        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }

    @Test
    fun `Failed User Login Because Email and Password need to input 10 more character`() = runTest {

        // スタブを用意
        val expectedState = AuthState(
            isLoading = false,
            loginEmailError = "Please Input 10 more characters",
            loginPasswordError = "Please Input 10 more characters"
        )

        var actualState : List<AuthState>? = null

        // errorMessageがここで与えたものだからこの処理は有効である
        every {
            textInputValidation.validate(viewModel.loginState.loginEmail)
        } returns ValidationResult(
            successful = false,
            errorMessage = "Please Input 10 more characters"
        )
        every {
            textInputValidation.validate(viewModel.loginState.loginPassword)
        } returns ValidationResult(
            successful = false,
            errorMessage = "Please Input 10 more characters"
        )

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.loginState }
                .take(1)
                .toList()
        }

        // SignInの呼び出しがかかる
        viewModel.onLoginEvent(LoginEvent.Login)
        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }

    @Test
    fun `Failed User Login Because Email and Password over 20 more character`() = runTest {
        // スタブを用意
        val expectedState = AuthState(
            isLoading = false,
            loginEmail = "abcdefghijklmnopqrstu@gmail.com",
            loginPassword = "1234567891011121314151617181920",
            loginEmailError = "Please Input less than 20 characters",
            loginPasswordError = "Please Input less than 20 characters"
        )

        var actualState : List<AuthState>? = null

        val job = launch {
            actualState = snapshotFlow { viewModel.loginState }
                .take(1)
                .toList()
        }

        viewModel.onLoginEvent(LoginEvent.LoginEmailChanged(value = "abcdefghijklmnopqrstu@gmail.com"))
        viewModel.onLoginEvent(LoginEvent.LoginUserPasswordChanged(value = "1234567891011121314151617181920"))

        // errorMessageがここで与えたものだからこの処理は有効である
        every {
            textInputValidation.validate(viewModel.loginState.loginEmail)
        } returns ValidationResult(
            successful = false,
            errorMessage = "Please Input less than 20 characters"
        )
        every {
            textInputValidation.validate(viewModel.loginState.loginPassword)
        } returns ValidationResult(
            successful = false,
            errorMessage = "Please Input less than 20 characters"
        )

        val actualAuthState = ResultState.Success

        every {
            repository.createUser(
                viewModel.loginState.loginEmail,
                viewModel.loginState.loginPassword
            )
        } returns flowOf(actualAuthState)

        // SignInの呼び出しがかかる
        viewModel.onLoginEvent(LoginEvent.Login)
        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }

    @Test
    fun `User Sign In with Exception`() = runTest {

        // スタブを用意
        val expectedState = AuthState(
            isLoading = false,
            loginEmail = "abcdefg@gmail.com",
            loginPassword = "1234567891011"
        )

        var actualState : List<AuthState>? = null

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.loginState }
                .take(1)
                .toList()
        }

        viewModel.onLoginEvent(LoginEvent.LoginEmailChanged(value = "abcdefg@gmail.com"))
        viewModel.onLoginEvent(LoginEvent.LoginUserPasswordChanged(value = "1234567891011"))

        every {
            textInputValidation.validate(viewModel.loginState.loginEmail)
        } returns ValidationResult(
            successful = true
        )

        every {
            textInputValidation.validate(viewModel.loginState.loginPassword)
        } returns ValidationResult(
            successful = true
        )

        // いまいち定義すべき順番がわかってない
        // Auth State
        val actualAuthState = ResultState.Failure

        every {
            repository.loginUser(
                viewModel.loginState.loginEmail,
                viewModel.loginState.loginPassword
            )
        } returns flowOf(actualAuthState)

        viewModel.onLoginEvent(LoginEvent.Login)

        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }
}