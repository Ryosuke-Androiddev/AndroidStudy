package com.example.androidestudy.feature.auth.presentation.login.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.auth.domain.model.AuthUserInfo
import com.example.androidestudy.feature.auth.domain.model.ResultState
import com.example.androidestudy.feature.auth.domain.repository.AuthRepository
import com.example.androidestudy.feature.auth.presentation.util.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

// 今回の場合は1つのViewModelにまとめてもよかったかな
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {
    private val _loginState = mutableStateOf(AuthState())
    val loginState: State<AuthState> = _loginState

    fun loginUser(email: String, password: String) =
        viewModelScope.launch {
            when (repository.loginUser(email, password).first()) {
                is ResultState.Loading -> {
                    _loginState.value = _loginState.value.copy(
                        isLoading = true
                    )
                }
                is ResultState.Success -> {
                    _loginState.value = _loginState.value.copy(
                        isLoading = false,
                        isError = false,
                        isSuccess = true
                    )
                }
                is ResultState.Failure -> {
                    _loginState.value = _loginState.value.copy(
                        isLoading = false,
                        isError = true,
                        isSuccess = false
                    )
                }
            }
        }

    // onEvent → Sealed Classを使って、イベントの処理をする
}