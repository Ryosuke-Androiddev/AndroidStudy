package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.domain.model.TextInputValidationResult

class TextInputValidationUseCase {
    fun validate(value: String): TextInputValidationResult {
        if (value.length < AT_LEAST_TEXT_LENGTH) {
            return TextInputValidationResult(
                successful = false,
                errorMessage = "Please Input 10 more characters"
            )
        }
        // 20文字までは含んで良い
        if (LONGEST_TEXT_LENGTH <= value.length) {
            return TextInputValidationResult(
                successful = false,
                errorMessage = "Please Input less than 20 characters"
            )
        }
        return TextInputValidationResult(
            successful = true
        )
    }

    companion object {
        const val AT_LEAST_TEXT_LENGTH = 10
        const val LONGEST_TEXT_LENGTH = 500
    }
}