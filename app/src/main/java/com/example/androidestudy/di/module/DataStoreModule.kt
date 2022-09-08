package com.example.androidestudy.di.module

import android.content.Context
import com.example.androidestudy.feature.data.repository.datastore.preferences.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepositoryImpl {
        return DataStoreRepositoryImpl(context = context)
    }
}