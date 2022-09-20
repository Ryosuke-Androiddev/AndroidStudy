package com.example.androidestudy.feature.auth.presentation.util

data class AuthState(
    val isLoading: Boolean = false,
    val loginEmail: String = "",
    val loginPassword: String = "",
    val signInEmail: String = "",
    val signInPassword: String = ""
)
