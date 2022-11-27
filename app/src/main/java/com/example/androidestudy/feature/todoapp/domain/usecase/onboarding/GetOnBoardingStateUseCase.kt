package com.example.androidestudy.feature.todoapp.domain.usecase.onboarding

import com.example.androidestudy.feature.todoapp.domain.model.onboarding.TodoAppOnBoardingState
import com.example.androidestudy.feature.todoapp.domain.repository.TodoAppDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetOnBoardingStateUseCase(
    private val todoAppDataStoreRepository: TodoAppDataStoreRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return todoAppDataStoreRepository.getOnBoardingState().map { todoAppOnBoardingState ->
            when (todoAppOnBoardingState) {
                is TodoAppOnBoardingState.IsCompleted -> {
                    todoAppOnBoardingState.isCompleted
                }
                is TodoAppOnBoardingState.Exception -> {
                    false
                }
            }
        }
    }
}