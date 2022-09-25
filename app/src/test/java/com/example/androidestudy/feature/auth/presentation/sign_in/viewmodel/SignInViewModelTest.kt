package com.example.androidestudy.feature.auth.presentation.sign_in.viewmodel

import androidx.compose.runtime.snapshotFlow
import com.example.androidestudy.feature.auth.domain.model.ResultState
import com.example.androidestudy.feature.auth.domain.model.ValidationResult
import com.example.androidestudy.feature.auth.domain.repository.AuthRepository
import com.example.androidestudy.feature.auth.domain.use_case.TextInputValidation
import com.example.androidestudy.feature.auth.presentation.login.component.LoginEvent
import com.example.androidestudy.feature.auth.presentation.sign_in.component.SignInEvent
import com.example.androidestudy.feature.auth.presentation.util.AuthState
import com.example.androidestudy.feature.auth.test_rule.ComposeStateTestRule
import com.example.androidestudy.feature.auth.test_rule.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.AuthResult
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
class SignInViewModelTest {

    private lateinit var repository: AuthRepository
    private lateinit var textInputValidation: TextInputValidation
    private lateinit var viewModel: SignInViewModel

    @get:Rule
    val composeStateTestRule = ComposeStateTestRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        textInputValidation = mockk(relaxed = true)
        viewModel = SignInViewModel(repository, textInputValidation)
    }

    @Test
    fun `Input Valid User Email and Confirm its value Changed`() = runTest {

        val expectedState = AuthState(
            signInEmail = "abcdefg@gmail.com"
        )

        var actualState : List<AuthState>? = null

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.signInState }
                .take(1)
                .toList()
        }

        viewModel.onSignInEvent(SignInEvent.SignInEmailChanged(value = "abcdefg@gmail.com"))
        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }

    @Test
    fun `Input Password and Confirm its value Changed`() = runTest {
        val expectedState = AuthState(
            signInPassword = "12345678910"
        )

        var actualState : List<AuthState>? = null

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.signInState }
                .take(1)
                .toList()
        }

        viewModel.onSignInEvent(SignInEvent.SignInPasswordChanged(value = "12345678910"))
        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }

    @Test
    fun `Sign In with Password and Confirm its Icon Visibility Changed`() = runTest {
        val expectedState = AuthState(
            showText = true
        )

        var actualState : List<AuthState>? = null

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.signInState }
                .take(1)
                .toList()
        }

        viewModel.onSignInEvent(SignInEvent.SignInPasswordVisibility(showText = viewModel.signInState.showText))
        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }

    // Email, Passwordが、10文字以上になっているかも確認する
    // returnしてるからこのテストはこれでいい気がする
    // TextInputを変更した後に処理を投げる
    @Test
    fun `successfully User Sign In`() = runTest {

        // スタブを用意
        val expectedState = AuthState(
            isLoading = false,
            signInEmail = "abcdefg@gmail.com",
            signInPassword = "1234567891011"
        )

        var actualState : List<AuthState>? = null

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.signInState }
                .take(1)
                .toList()
        }

        // SignInの呼び出しがかかる
        viewModel.onSignInEvent(SignInEvent.SignInEmailChanged(value = "abcdefg@gmail.com"))
        viewModel.onSignInEvent(SignInEvent.SignInPasswordChanged(value = "1234567891011"))

        // ここの処理を呼び出す順番を気にする必要があった
        // 入力があった後に、戻り値を明示的に示す必要がある
        every {
            textInputValidation.validate(viewModel.signInState.signInEmail)
        } returns ValidationResult(
            successful = true
        )

        every {
            textInputValidation.validate(viewModel.signInState.signInPassword)
        } returns ValidationResult(
            successful = true
        )

        val actualAuthState = ResultState.Success

        every {
            repository.createUser(
                viewModel.signInState.signInEmail,
                viewModel.signInState.signInPassword
            )
        } returns flowOf(actualAuthState)

        viewModel.onSignInEvent(SignInEvent.SignIn)
        job.join()

        assertThat(actualState?.size).isEqualTo(1)

        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }

    // 文字列が制限されるのはここで認証飛ばす前にUseCaseで弾いているかを確認する
    @Test
    fun `Failed User Sign In Because Email and Password need to input 10 more character`() = runTest {
        // スタブを用意
        val expectedState = AuthState(
            isLoading = false,
            signInEmailError = "Please Input 10 more characters",
            signInPasswordError = "Please Input 10 more characters"
        )

        var actualState : List<AuthState>? = null

        // errorMessageがここで与えたものだからこの処理は有効である
        every {
            textInputValidation.validate(viewModel.signInState.signInEmail)
        } returns ValidationResult(
            successful = false,
            errorMessage = "Please Input 10 more characters"
        )
        every {
            textInputValidation.validate(viewModel.signInState.signInPassword)
        } returns ValidationResult(
            successful = false,
            errorMessage = "Please Input 10 more characters"
        )

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.signInState }
                .take(1)
                .toList()
        }

        // SignInの呼び出しがかかる
        viewModel.onSignInEvent(SignInEvent.SignIn)
        job.join()

        assertThat(actualState?.size).isEqualTo(1)
        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }

    @Test
    fun `User Sign In with Exception`() = runTest {

        // スタブを用意
        val expectedState = AuthState(
            isLoading = false,
            signInEmail = "abcdefg@gmail.com",
            signInPassword = "1234567891011"
        )

        var actualState : List<AuthState>? = null

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            // 中間の処理を抜けきれてない
            actualState = snapshotFlow { viewModel.signInState }
                .take(1)
                .toList()
        }

        // SignInの呼び出しがかかる
        viewModel.onSignInEvent(SignInEvent.SignInEmailChanged(value = "abcdefg@gmail.com"))
        viewModel.onSignInEvent(SignInEvent.SignInPasswordChanged(value = "1234567891011"))

        // ここの処理を呼び出す順番を気にする必要があった
        // 入力があった後に、戻り値を明示的に示す必要がある
        every {
            textInputValidation.validate(viewModel.signInState.signInEmail)
        } returns ValidationResult(
            successful = true
        )

        every {
            textInputValidation.validate(viewModel.signInState.signInPassword)
        } returns ValidationResult(
            successful = true
        )

        val actualAuthState = ResultState.Failure

        every {
            repository.createUser(
                viewModel.signInState.signInEmail,
                viewModel.signInState.signInPassword
            )
        } returns flowOf(actualAuthState)

        viewModel.onSignInEvent(SignInEvent.SignIn)
        job.join()

        assertThat(actualState?.size).isEqualTo(1)

        assertThat(actualState?.get(0)).isEqualTo(expectedState)
    }
}