package com.example.androidestudy.di.module

import com.example.androidestudy.feature.retrofit.data.remote.UserPostApi
import com.example.androidestudy.feature.retrofit.data.repository.UserPostRepositoryImpl
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Firebase

    // 認証関連のものはViewModelが破棄されたらどうすべき
    // スコープについて考える必要がある
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    // Retrofit
    // TODO Retrofit Instanceの作成
    @Provides
    @Singleton
    fun provideUserPostRepository(userPostApi: UserPostApi): UserPostRepository {
        return UserPostRepositoryImpl(userPostApi = userPostApi)
    }
}