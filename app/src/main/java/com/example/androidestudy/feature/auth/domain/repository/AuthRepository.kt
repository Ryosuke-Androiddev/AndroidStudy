package com.example.androidestudy.feature.auth.domain.repository

import com.example.androidestudy.feature.auth.domain.model.AuthUserInfo
import com.example.androidestudy.feature.auth.domain.model.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun createUser(
        authUserInfo: AuthUserInfo
    ): Flow<ResultState>

    fun loginUser(
        authUserInfo: AuthUserInfo
    ): Flow<ResultState>
}