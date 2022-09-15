package com.example.androidestudy.feature.data_store.domain.use_case

import com.example.androidestudy.feature.data_store.domain.repository.DataStoreRepository

class SaveOnBoardingState(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(isCompleted: Boolean) {
        dataStoreRepository.saveOnBoardingState(isCompleted = isCompleted)
    }
}