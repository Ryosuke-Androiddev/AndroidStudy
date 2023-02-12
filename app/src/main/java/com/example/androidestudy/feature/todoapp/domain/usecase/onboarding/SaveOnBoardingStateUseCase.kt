package com.example.androidestudy.feature.todoapp.domain.usecase.onboarding

import com.example.androidestudy.feature.todoapp.domain.repository.TodoAppDataStoreRepository

class SaveOnBoardingStateUseCase(
    private val todoAppDataStoreRepository: TodoAppDataStoreRepository
) {
    suspend operator fun invoke(isCompleted: Boolean) {
        todoAppDataStoreRepository.saveOnBoardingState(isCompleted = isCompleted)
    }
}