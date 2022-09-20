package com.example.androidestudy.feature.data_store.domain.repository

import com.example.androidestudy.feature.data_store.domain.model.OnBoardingState
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveOnBoardingState(isCompleted: Boolean)
    fun readOnBoardingState(): Flow<OnBoardingState>
}