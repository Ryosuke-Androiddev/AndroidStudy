package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.PostUserPostState
import com.example.androidestudy.feature.retrofit.domain.model.util.ScreenState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository

class PostUserPostUseCase(
    private val repository: UserPostRepository,
    private val textInputValidationUseCase: TextInputValidationUseCase
) {
    suspend operator fun invoke(userPostItem: UserPostItem): ScreenState {

        // userIdってどうやって振り分けられる??
        val title = textInputValidationUseCase.validate(userPostItem.title)
        val body = textInputValidationUseCase.validate(userPostItem.body)

        println("$title")
        println("$body")

        val hasError = listOf(
            title,
            body
        ).any {
            !it.successful
        }

        // title, bodyのエラーを分けるとテストを書くのが難しくなる
        if (hasError) {
            return ScreenState.TextInputError
        }

        return when (repository.postUserPost(userPostItem = userPostItem)) {
            is PostUserPostState.PostUserPost -> {
                ScreenState.Success
            }
            is PostUserPostState.Failure -> {
                ScreenState.Failure
            }
        }
    }
}