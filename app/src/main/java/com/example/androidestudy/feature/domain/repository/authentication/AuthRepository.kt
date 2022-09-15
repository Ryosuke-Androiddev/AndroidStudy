package com.example.androidestudy.feature.domain.repository.authentication

import com.example.androidestudy.feature.domain.model.authentication.AuthUserInfo
import com.example.androidestudy.feature.domain.model.util.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun createUser(
        authUserInfo: AuthUserInfo
    ): Flow<ResultState>

    fun loginUser(
        authUserInfo: AuthUserInfo
    ): Flow<ResultState>
}