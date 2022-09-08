package com.example.androidestudy.di.module

import com.example.androidestudy.feature.domain.repository.datastore.preferences.DataStoreRepository
import com.example.androidestudy.feature.domain.use_case.datastore.preferences.ReadOnBoardingState
import com.example.androidestudy.feature.domain.use_case.datastore.preferences.SaveOnBoardingState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    // 別モジュールのメソッドが有効なのかを確認する
    @Provides
    @ViewModelScoped
    fun provideSaveOnBoardingState(dataStoreRepository: DataStoreRepository) : SaveOnBoardingState {
        return SaveOnBoardingState(dataStoreRepository = dataStoreRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideReadOnBoardingState(dataStoreRepository: DataStoreRepository) : ReadOnBoardingState {
        return ReadOnBoardingState(dataStoreRepository = dataStoreRepository)
    }
}