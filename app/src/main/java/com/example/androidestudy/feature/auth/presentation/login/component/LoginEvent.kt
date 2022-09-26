package com.example.androidestudy.feature.auth.presentation.login.component

sealed class LoginEvent {
    // Login Event
    data class LoginEmailChanged(val value: String): LoginEvent()
    data class LoginUserPasswordChanged(val value: String): LoginEvent()
    data class LoginPasswordVisibility(val showText: Boolean): LoginEvent()
    // ここのオブジェクトを作るのめっちゃいい
    object Login: LoginEvent()
}
