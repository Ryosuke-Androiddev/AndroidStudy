package com.example.androidestudy.feature.auth.presentation.util

data class AuthState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false
)
