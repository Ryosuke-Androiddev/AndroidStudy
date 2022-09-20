package com.example.androidestudy.feature.auth.presentation.login.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.auth.domain.model.ResultState
import com.example.androidestudy.feature.auth.domain.repository.AuthRepository
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
    private val repository: AuthRepository
): ViewModel() {
    private val _loginState = mutableStateOf(AuthState())
    val loginState: State<AuthState> = _loginState

    private val loginChannel = Channel<ResultState>()
    val loginResult = loginChannel.receiveAsFlow()

    fun onLoginEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginEmailChanged -> {
                _loginState.value = _loginState.value.copy(
                    loginEmail = event.value
                )
            }
            is LoginEvent.LoginUserPasswordChanged -> {
                _loginState.value = _loginState.value.copy(
                    loginPassword = event.value
                )
            }
            is LoginEvent.Login -> {
                loginUser()
            }
        }
    }

    private fun loginUser() = viewModelScope.launch {
        _loginState.value = _loginState.value.copy(
            isLoading = true
        )
        val result = repository.loginUser(
            email = _loginState.value.loginEmail,
            password = _loginState.value.loginPassword
        ).first()
        loginChannel.send(result)
        _loginState.value = _loginState.value.copy(
            isLoading = false
        )
    }
}