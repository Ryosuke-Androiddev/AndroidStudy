package com.example.androidestudy.feature.auth.presentation.sign_in.viewmodel

import androidx.compose.runtime.snapshotFlow
import com.example.androidestudy.feature.auth.domain.repository.AuthRepository
import com.example.androidestudy.feature.auth.domain.use_case.TextInputValidation
import com.example.androidestudy.feature.auth.presentation.sign_in.component.SignInEvent
import com.example.androidestudy.feature.auth.presentation.util.AuthState
import com.example.androidestudy.feature.auth.test_rule.ComposeStateTestRule
import com.example.androidestudy.feature.auth.test_rule.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.AuthResult
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
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
    fun `successfully User Sign In`() = runTest {

        // スタブを用意
        val expectedState = AuthState(
            isLoading = false
        )

        var actualState = AuthState()

        val job = launch {
            // これだと最初のStateしか確認できない
            // ここをどうにかして管理したい
            actualState = snapshotFlow { viewModel.signInState }
                .last()
        }

        // SignInの呼び出しがかかる
        viewModel.onSignInEvent(SignInEvent.SignIn)
        job.join()

        assertThat(actualState).isEqualTo(expectedState)
    }

    @Test
    fun `Input Valid User Email with 10 more characters`() = runTest {

    }

    @Test
    fun `Input Valid Password with 10 more characters`() = runTest {

    }

    // nullじゃないことを確認する
    @Test
    fun `Failed User Sign In Because Email and Password need to input 10 more character`() = runTest {

    }

    @Test
    fun `Input InValid User Email with less 10 characters`() = runTest {

    }

    @Test
    fun `Input InValid Password with less 10 characters`() = runTest {

    }
}