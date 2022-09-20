package com.example.androidestudy.feature.auth.presentation.util

data class AuthState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val loginUserName: String = "",
    val loginPassword: String = "",
    val signInUserName: String = "",
    val signInPassword: String = ""
)
