package com.example.androidestudy.feature.retrofit.domain.model.util

import com.example.androidestudy.feature.retrofit.domain.model.TextInputValidationResult

sealed class ScreenState {
    object Success: ScreenState()
    object Failure: ScreenState()
    data class TextInputError(
        val titleInputValidationResult: TextInputValidationResult? = null,
        val bodyInputValidationResult: TextInputValidationResult? = null
    ): ScreenState()
}
