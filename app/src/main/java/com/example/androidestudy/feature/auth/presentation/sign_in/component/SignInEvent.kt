package com.example.androidestudy.feature.auth.presentation.sign_in.component

sealed class SignInEvent {
    // Sign In
    data class SignInEmailChanged(val value: String): SignInEvent()
    data class SignInPasswordChanged(val value: String): SignInEvent()
    data class SignInPasswordVisibility(val showText: Boolean): SignInEvent()
    object SignIn: SignInEvent()
}
