package com.example.androidestudy.feature.auth.presentation.login.viewmodel

import androidx.compose.runtime.snapshotFlow
import com.example.androidestudy.feature.auth.domain.model.ResultState
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
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
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
    fun `successfully User Sign In`() = runTest {

        // スタブを用意
        val expectedState1 = AuthState(
            isLoading = false
        )
        val expectedState2 = AuthState(
            isLoading = true,
            loginEmailError = "",
            loginPasswordError = ""
        )
        val expectedState3 = AuthState(
            isLoading = true,
            loginEmailError = "Please Input 10 more characters",
            loginPasswordError = "Please Input 10 more characters"
        )
        val expectedState4 = AuthState(
            isLoading = false
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

        // SignInの呼び出しがかかる
        viewModel.onLoginEvent(LoginEvent.Login)
        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState2)
        // assertThat(actualState?.get(1)).isEqualTo(expectedState2)

        // ここにSendChannelの処理を完了させる必要があるのでは??
        // assertThat(actualState?.get(2)).isEqualTo(expectedState4)

        // assertThat(actualState?.get(2)).isEqualTo(expectedState4)
    }

    @Test
    fun `Failed User Login Because Email and Password need to input 10 more character`() = runTest {

    }
}