package com.example.androidestudy.feature.domain.repository.datastore.preferences

import com.example.androidestudy.feature.domain.model.OnBoardingState
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveOnBoardingState(isCompleted: Boolean)
    fun readOnBoardingState(): Flow<OnBoardingState>
}