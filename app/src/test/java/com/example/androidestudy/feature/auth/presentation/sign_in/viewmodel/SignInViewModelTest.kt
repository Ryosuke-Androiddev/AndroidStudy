package com.example.androidestudy.feature.auth.presentation.sign_in.viewmodel

import com.example.androidestudy.feature.auth.domain.repository.AuthRepository
import com.example.androidestudy.feature.auth.test_rule.ComposeStateTestRule
import com.example.androidestudy.feature.auth.test_rule.MainDispatcherRule
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
class SignInViewModelTest {

    private lateinit var repository: AuthRepository
    private lateinit var viewModel: SignInViewModel

    @get:Rule
    val composeStateTestRule = ComposeStateTestRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        viewModel = SignInViewModel(repository)
    }
}