package com.example.androidestudy.feature.domain.use_case.datastore.preferences

import com.example.androidestudy.feature.domain.model.OnBoardingState
import com.example.androidestudy.feature.domain.repository.datastore.preferences.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingState(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke(): Flow<OnBoardingState> {
        return dataStoreRepository.readOnBoardingState()
    }
}