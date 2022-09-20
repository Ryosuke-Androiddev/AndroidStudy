package com.example.androidestudy.feature.auth.presentation.sign_in.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.auth.domain.model.ResultState
import com.example.androidestudy.feature.auth.domain.repository.AuthRepository
import com.example.androidestudy.feature.auth.presentation.sign_in.component.SignInEvent
import com.example.androidestudy.feature.auth.presentation.util.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val _signInState = mutableStateOf(AuthState())
    val signInState: State<AuthState> = _signInState

    private val signInChannel = Channel<ResultState>()
    val signInResult = signInChannel.receiveAsFlow()

    fun onSignInEvent(event: SignInEvent) {
        when (event) {
            // この処理方法まじで理想的すぎる
            // 意図が明確にわかるし、Kotlinのいいところが詰まってる
            is SignInEvent.SignInEmailChanged -> {
                _signInState.value = _signInState.value.copy(
                    signInEmail = event.value
                )
            }
            is SignInEvent.SignInPasswordChanged -> {
                _signInState.value = _signInState.value.copy(
                    signInPassword = event.value
                )
            }
            is SignInEvent.SignIn -> {
                createUser()
            }
        }
    }

    private fun createUser() = viewModelScope.launch {

        _signInState.value = _signInState.value.copy(
            isLoading = true
        )
        // Flowでコールバックされて戻ってきたオブジェクトを使用する
        // 直接Stateで管理しているのを渡せるのがこの処理方法の強み
        val result = repository.createUser(
            email = _signInState.value.signInEmail,
            password = _signInState.value.signInPassword
        ).first()

        // ViewModelで分岐するのは処理を分けるときだけ
        // Sealed classごとに何らかの処理をするのは可読性が低下する
        signInChannel.send(result)

        _signInState.value = _signInState.value.copy(
            isLoading = false
        )
    }
}