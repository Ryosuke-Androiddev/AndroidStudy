package com.example.androidestudy.feature.auth.presentation.login.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.auth.domain.model.ResultState
import com.example.androidestudy.feature.auth.domain.repository.AuthRepository
import com.example.androidestudy.feature.auth.domain.use_case.TextInputValidation
import com.example.androidestudy.feature.auth.presentation.login.component.LoginEvent
import com.example.androidestudy.feature.auth.presentation.util.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// 今回の場合は1つのViewModelにまとめてもよかったかな
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val textInputValidation: TextInputValidation,
    private val repository: AuthRepository
): ViewModel() {
    var loginState by mutableStateOf(AuthState())
        private set

    private val loginChannel = Channel<ResultState>()
    val loginResult = loginChannel.receiveAsFlow()

    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginEmailChanged -> {
                loginState = loginState.copy(
                    loginEmail = event.value
                )
            }
            is LoginEvent.LoginUserPasswordChanged -> {
                loginState = loginState.copy(
                    loginPassword = event.value
                )
            }
            is LoginEvent.Login -> {
                loginUser()
            }
        }
    }

    private fun loginUser() = viewModelScope.launch {
        loginState = loginState.copy(
            isLoading = true
        )

        val email = textInputValidation.validate(loginState.loginEmail)
        val password = textInputValidation.validate(loginState.loginPassword)

        val hasError = listOf(
            email,
            password
        ).any { !it.successful }

        if (hasError) {
            loginState = loginState.copy(
                loginEmailError = email.errorMessage,
                loginPasswordError = password.errorMessage
            )
            return@launch
        } else {
            val result = repository.loginUser(
                email = loginState.loginEmail,
                password = loginState.loginPassword
            ).first()
            loginChannel.send(result)
        }

        loginState = loginState.copy(
            isLoading = false
        )
    }
}