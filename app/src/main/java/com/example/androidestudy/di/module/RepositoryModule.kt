package com.example.androidestudy.di.module

import com.example.androidestudy.feature.data.repository.authentication.AuthRepositoryImpl
import com.example.androidestudy.feature.domain.repository.authentication.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}