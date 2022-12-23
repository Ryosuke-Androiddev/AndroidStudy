package com.example.androidestudy.di.module

import android.content.Context
import com.example.androidestudy.feature.auth.domain.use_case.TextInputValidation
import com.example.androidestudy.feature.data_store.data.repository.DataStoreRepositoryImpl
import com.example.androidestudy.feature.data_store.domain.repository.DataStoreRepository
import com.example.androidestudy.feature.data_store.domain.use_case.ReadOnBoardingState
import com.example.androidestudy.feature.data_store.domain.use_case.SaveOnBoardingState
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import com.example.androidestudy.feature.retrofit.domain.usecase.DeleteUserPostUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.GetAllUserPostsUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.GetUserPostByIdUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.PostUserPostUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.TextInputValidationUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.UpdateUserPostUseCase
import com.example.androidestudy.feature.todoapp.data.local.TodoDatabase
import com.example.androidestudy.feature.todoapp.data.remote.api.TodoWeatherApi
import com.example.androidestudy.feature.todoapp.data.repository.TodoAppDataStoreRepositoryImpl
import com.example.androidestudy.feature.todoapp.data.repository.TodoLocalDBRepositoryImpl
import com.example.androidestudy.feature.todoapp.data.repository.TodoWeatherApiRepositoryImpl
import com.example.androidestudy.feature.todoapp.domain.repository.TodoAppDataStoreRepository
import com.example.androidestudy.feature.todoapp.domain.repository.TodoLocalDBRepository
import com.example.androidestudy.feature.todoapp.domain.repository.TodoWeatherApiRepository
import com.example.androidestudy.feature.todoapp.domain.usecase.onboarding.GetOnBoardingStateUseCase
import com.example.androidestudy.feature.todoapp.domain.usecase.onboarding.SaveOnBoardingStateUseCase
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

    // Retrofit

    @Provides
    @ViewModelScoped
    fun provideTextInputValidationUseCase(): TextInputValidationUseCase = TextInputValidationUseCase()

    @Provides
    @ViewModelScoped
    fun provideGetAllUserPostsUseCase(repository: UserPostRepository): GetAllUserPostsUseCase
        = GetAllUserPostsUseCase(repository = repository)

    @Provides
    @ViewModelScoped
    fun provideGetUserPostByIdUseCase(repository: UserPostRepository): GetUserPostByIdUseCase
        = GetUserPostByIdUseCase(repository = repository)

    @Provides
    @ViewModelScoped
    fun providePostUserPostUseCase(repository: UserPostRepository, textInputValidationUseCase: TextInputValidationUseCase): PostUserPostUseCase
        = PostUserPostUseCase(repository = repository, textInputValidationUseCase = textInputValidationUseCase)

    @Provides
    @ViewModelScoped
    fun provideUpdateUserPostUseCase(repository: UserPostRepository, textInputValidationUseCase: TextInputValidationUseCase): UpdateUserPostUseCase
        = UpdateUserPostUseCase(repository = repository, textInputValidationUseCase = textInputValidationUseCase)

    @Provides
    @ViewModelScoped
    fun provideDeleteUserPostUseCase(repository: UserPostRepository): DeleteUserPostUseCase
        = DeleteUserPostUseCase(repository = repository)

    @Provides
    @ViewModelScoped
    fun provideTodoAppDataStore(
        @ApplicationContext context: Context
    ): TodoAppDataStoreRepository {
        return TodoAppDataStoreRepositoryImpl(context = context)
    }

    @Provides
    @ViewModelScoped
    fun provideGetOnBoardingStateUseCase(
        todoAppDataStoreRepository: TodoAppDataStoreRepository
    ): GetOnBoardingStateUseCase {
        return GetOnBoardingStateUseCase(
            todoAppDataStoreRepository = todoAppDataStoreRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideSaveOnBoardingStateUseCase(
        todoAppDataStoreRepository: TodoAppDataStoreRepository
    ): SaveOnBoardingStateUseCase {
        return SaveOnBoardingStateUseCase(
            todoAppDataStoreRepository = todoAppDataStoreRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideTodoWeatherApiRepository(
        api: TodoWeatherApi
    ): TodoWeatherApiRepository {
        return TodoWeatherApiRepositoryImpl(api = api)
    }

    @Provides
    @ViewModelScoped
    fun provideTodoLocalDBRepository(
        database: TodoDatabase
    ): TodoLocalDBRepository {
        return TodoLocalDBRepositoryImpl(todoDao = database.todoDao())
    }
}