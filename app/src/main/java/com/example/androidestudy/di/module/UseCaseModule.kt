package com.example.androidestudy.di.module

import android.content.Context
import com.example.androidestudy.feature.auth.domain.use_case.TextInputValidation
import com.example.androidestudy.feature.data_store.data.repository.DataStoreRepositoryImpl
import com.example.androidestudy.feature.data_store.domain.repository.DataStoreRepository
import com.example.androidestudy.feature.data_store.domain.use_case.ReadOnBoardingState
import com.example.androidestudy.feature.data_store.domain.use_case.SaveOnBoardingState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository {
        return DataStoreRepositoryImpl(context = context)
    }

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

    @Provides
    @ViewModelScoped
    fun provideCoroutineDispatcher() : CoroutineDispatcher = Dispatchers.IO

    @Provides
    @ViewModelScoped
    fun provideTextInputValidation() : TextInputValidation = TextInputValidation()
}