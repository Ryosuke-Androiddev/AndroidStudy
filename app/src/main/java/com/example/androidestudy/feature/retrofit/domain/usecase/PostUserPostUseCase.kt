package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.domain.model.UserOperationResult
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository

class PostUserPostUseCase(
    private val repository: UserPostRepository,
    private val textInputValidationUseCase: TextInputValidationUseCase
) {
    suspend operator fun invoke(userPostItem: UserPostItem): Result<UserOperationResult> {

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
            // 文字列の長さを確認しているので、ステータスコードは省略
            // ViewModelは、以下のValidationResultを使ってstateを更新する
            val userOperationResult = UserOperationResult(
                textInputValidationResult = false
            )
            // 厳密には成功していない
            // 例外処理と文字列が足りる足りないを分けたいので以下のような処理を実装
            // PostUserPostUseCase.onSuccess {
            //     if (!it.textInputValidationResult)
            // }
            // 上記のような処理が必要になる
            return Result.success(userOperationResult)
        }

        val statusCode = repository.postUserPost(userPostItem = userPostItem)

        if (statusCode.isSuccess) {
            // ViewModelは、以下のValidationResultを使ってstateを更新する
            // Defaultでは、何らかの通信エラーが発生したときを想定
            // サーバー側のエラーとして処理する
            val userOperationResult = UserOperationResult(
                statusCode = statusCode.getOrDefault(DEFAULT_VALUE),
                textInputValidationResult = false
            )

            // ViewModelは、以下のValidationResultを使ってstateを更新する
            return Result.success(userOperationResult)
        } else {
            // ViewModelは、以下のValidationResultを使ってstateを更新する
            val userFailureOperationResult = UserOperationResult(
                statusCode = DEFAULT_VALUE,
                textInputValidationResult = false
            )

            // ViewModelは、以下のValidationResultを使ってstateを更新する
            return Result.success(userFailureOperationResult)
        }
    }

    companion object {
        private const val DEFAULT_VALUE = "500"
    }
}