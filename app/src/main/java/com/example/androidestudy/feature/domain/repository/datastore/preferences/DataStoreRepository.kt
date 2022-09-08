package com.example.androidestudy.feature.domain.repository.datastore.preferences

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveOnBoardingState(isCompleted: Boolean)
    fun readIsCompleted(): Flow<Boolean>
}