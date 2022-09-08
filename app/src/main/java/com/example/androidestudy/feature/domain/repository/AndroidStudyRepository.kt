package com.example.androidestudy.feature.domain.repository

import kotlinx.coroutines.flow.Flow

interface AndroidStudyRepository {
    suspend fun saveOnBoardingState(isCompleted: Boolean)
    fun readIsCompleted(): Flow<Boolean>
}