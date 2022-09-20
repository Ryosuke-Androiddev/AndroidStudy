package com.example.androidestudy.feature.auth.presentation.util

sealed class AuthEvent {

    // Login Event
    data class LoginUserNameChanged(val value: String): AuthEvent()
    data class LoginUserPasswordChanged(val value: String): AuthEvent()
    // ここのオブジェクトを作るのめっちゃいい
    object Login: AuthEvent()

    // Sign In
    data class SignInUseNameChanged(val value: String): AuthEvent()
    data class SignInPasswordChanged(val value: String): AuthEvent()
    object SignIn: AuthEvent()
}
