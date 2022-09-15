package com.example.androidestudy.feature.data.repository.authentication

import com.example.androidestudy.feature.domain.model.authentication.AuthUserInfo
import com.example.androidestudy.feature.domain.model.util.ResultState
import com.example.androidestudy.feature.domain.repository.authentication.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository{

    override fun createUser(authUserInfo: AuthUserInfo): Flow<ResultState> {
        TODO("Not yet implemented")
    }

    override fun loginUser(authUserInfo: AuthUserInfo): Flow<ResultState> {
        TODO("Not yet implemented")
    }
}