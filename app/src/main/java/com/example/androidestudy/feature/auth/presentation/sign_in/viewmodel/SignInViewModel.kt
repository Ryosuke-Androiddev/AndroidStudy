package com.example.androidestudy.feature.auth.presentation.sign_in.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.auth.domain.model.ResultState
import com.example.androidestudy.feature.auth.domain.repository.AuthRepository
import com.example.androidestudy.feature.auth.domain.use_case.TextInputValidation
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
    private val repository: AuthRepository,
    private val textInputValidation: TextInputValidation
): ViewModel() {

    var signInState by mutableStateOf(AuthState())
        private set

    private val signInChannel = Channel<ResultState>()
    val signInResult = signInChannel.receiveAsFlow()

    fun onSignInEvent(event: SignInEvent) {
        when (event) {
            // この処理方法まじで理想的すぎる
            // 意図が明確にわかるし、Kotlinのいいところが詰まってる
            is SignInEvent.SignInEmailChanged -> {
                signInState = signInState.copy(
                    signInEmail = event.value
                )
            }
            is SignInEvent.SignInPasswordChanged -> {
                signInState = signInState.copy(
                    signInPassword = event.value
                )
            }
            is SignInEvent.SignInPasswordVisibility -> {
                signInState = signInState.copy(
                    showText = !event.showText
                )
            }
            is SignInEvent.SignIn -> {
                createUser()
            }
        }
    }

    private fun createUser()  {

        signInState = signInState.copy(
            isLoading = true
        )
        val email = textInputValidation.validate(signInState.signInEmail)
        val password = textInputValidation.validate(signInState.signInPassword)

        // anyで条件にあったらエラーを有効にする
        // 1つでも条件が合わないものがある時??
        val hasError = listOf(
            email,
            password
        ).any {
            // 以下処理と同じ意味
            // if (!it.successful) {
            //    return true
            // } else {
            //    return false
            // }
            !it.successful
        }

        // ここの処理次第でテストの予測が変化してしまう
        if (hasError) {
            // こっちに処理が流れてる
            // SignIn, Loginどっちも
            signInState = signInState.copy(
                isLoading = false,
                signInEmailError = email.errorMessage,
                signInPasswordError = password.errorMessage
            )
            return
        }

        viewModelScope.launch {

            // catch {}で例外を検知する必要がある
            // 例外が起きたかどうかでなく、Sealed classで状況を判断するように変更
            // catchする必要がない
            val result = repository.createUser(
                email = signInState.signInEmail,
                password = signInState.signInPassword
            ).first()

            // ViewModelで分岐するのは処理を分けるときだけ
            // Sealed classごとに何らかの処理をするのは可読性が低下する
            signInChannel.send(result)
        }

        signInState = signInState.copy(
            isLoading = false
        )
    }
}