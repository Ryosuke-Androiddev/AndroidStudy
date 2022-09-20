package com.example.androidestudy.feature.auth.domain.repository

import com.example.androidestudy.feature.auth.domain.model.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun createUser(
        email: String,
        password: String
    ): Flow<ResultState>

    fun loginUser(
        email: String,
        password: String
    ): Flow<ResultState>
}