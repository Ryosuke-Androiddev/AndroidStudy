package com.example.androidestudy.feature.todoapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface TodoAppDataStoreRepository {
    // 読み込みは、Flowで値を取ってくるから非同期関数
    fun getOnBoardingState(): Flow<Boolean>
    // 書き込みは、suspend関数にして非同期で処理
    suspend fun saveOnBoardingState()
}