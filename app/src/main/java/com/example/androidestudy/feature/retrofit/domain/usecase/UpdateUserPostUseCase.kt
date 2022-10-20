package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.UpdateUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.util.ScreenState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository

class UpdateUserPostUseCase(
    private val repository: UserPostRepository,
    private val textInputValidationUseCase: TextInputValidationUseCase
) {
    suspend operator fun invoke(userPostItem: UserPostItem): ScreenState {

        // userIdってどうやって振り分けられる??
        val title = textInputValidationUseCase.validate(userPostItem.title)
        val body = textInputValidationUseCase.validate(userPostItem.body)

        val hasError = listOf(
            title,
            body
        ).any {
            !it.successful
        }

        if (hasError) {
            return ScreenState.TextInputError
        }

        return when (repository.updatePost(userPostItem = userPostItem)) {
            is UpdateUserPostState.UpdateUserPost -> {
                ScreenState.Success
            }
            is UpdateUserPostState.Failure -> {
                ScreenState.Failure
            }
        }
    }
}