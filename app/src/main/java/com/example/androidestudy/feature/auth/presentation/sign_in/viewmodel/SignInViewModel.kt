package com.example.androidestudy.feature.auth.presentation.sign_in.viewmodel

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

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val _signInState = mutableStateOf(AuthState())
    val signInState: State<AuthState> = _signInState

    fun createUser(email: String, password: String) =
        viewModelScope.launch {
            when (repository.createUser(email, password).first()) {
                is ResultState.Loading -> {
                    _signInState.value = _signInState.value.copy(
                        isLoading = true
                    )
                }
                is ResultState.Success -> {
                    _signInState.value = _signInState.value.copy(
                        isLoading = false,
                        isError = false,
                        isSuccess = true
                    )
                }
                is ResultState.Failure -> {
                    _signInState.value = _signInState.value.copy(
                        isLoading = false,
                        isError = true,
                        isSuccess = false
                    )
                }
            }
        }

    // onEvent → Sealed Classを使って、イベントの処理をする
}