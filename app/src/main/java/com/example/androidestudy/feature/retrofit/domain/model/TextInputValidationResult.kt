package com.example.androidestudy.feature.retrofit.domain.model

data class TextInputValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
