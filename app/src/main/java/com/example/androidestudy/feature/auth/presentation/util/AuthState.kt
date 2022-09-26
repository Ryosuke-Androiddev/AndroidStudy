package com.example.androidestudy.feature.auth.presentation.util

data class AuthState(
    val isLoading: Boolean = false,
    val loginEmail: String = "",
    val loginPassword: String = "",
    val signInEmail: String = "",
    val signInPassword: String = "",
    val showText: Boolean = false,
    val loginEmailError: String? = null,
    val loginPasswordError: String? = null,
    val signInEmailError: String? = null,
    val signInPasswordError: String? = null
)
