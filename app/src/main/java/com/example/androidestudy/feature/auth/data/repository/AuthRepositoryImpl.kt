package com.example.androidestudy.feature.auth.data.repository

import com.example.androidestudy.feature.auth.domain.model.AuthUserInfo
import com.example.androidestudy.feature.util.ResultState
import com.example.androidestudy.feature.auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override fun createUser(authUserInfo: AuthUserInfo): Flow<ResultState> {
        TODO("Not yet implemented")
    }

    override fun loginUser(authUserInfo: AuthUserInfo): Flow<ResultState> {
        TODO("Not yet implemented")
    }
}