package com.example.androidestudy.feature.domain.use_case.datastore.preferences

import com.example.androidestudy.feature.domain.repository.datastore.preferences.DataStoreRepository

class SaveOnBoardingState(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(isCompleted: Boolean) {
        dataStoreRepository.saveOnBoardingState(isCompleted = isCompleted)
    }
}