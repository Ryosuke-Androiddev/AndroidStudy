package com.example.androidestudy.feature.auth.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
