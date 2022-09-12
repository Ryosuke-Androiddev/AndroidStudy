package com.example.androidestudy.feature.domain.use_case.datastore.preferences

import com.example.androidestudy.feature.domain.model.OnBoardingState
import com.example.androidestudy.feature.domain.repository.datastore.preferences.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReadOnBoardingState(
    private val dataStoreRepository: DataStoreRepository
) {
    // ここで変えてあげるの大事 → 初めてのやつもtrueに変更されて表示されなくなる
    operator fun invoke(): Flow<Boolean> {
        val result = dataStoreRepository.readOnBoardingState().map { onBoardingState ->
            when (onBoardingState) {
                is OnBoardingState.Success -> {
                    // 上のコメントで書いた問題はここで解決する
                    onBoardingState.isCompleted
                }
                is OnBoardingState.Failure -> {
                    false
                }
            }
        }
        return result
    }
}