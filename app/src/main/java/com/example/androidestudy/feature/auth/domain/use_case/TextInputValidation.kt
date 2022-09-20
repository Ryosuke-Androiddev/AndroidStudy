package com.example.androidestudy.feature.auth.domain.use_case

import com.example.androidestudy.feature.auth.domain.model.ValidationResult

class TextInputValidation {
    fun validate(value: String): ValidationResult {
        if (value.length < AT_LEAST_TEXT_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please Input 10 more characters"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    companion object {
        const val AT_LEAST_TEXT_LENGTH = 10
    }
}