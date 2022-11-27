package com.example.androidestudy.feature.todoapp.domain.model.onboarding

sealed class TodoAppOnBoardingState {
    data class IsCompleted(val isCompleted: Boolean): TodoAppOnBoardingState()
    object Exception: TodoAppOnBoardingState()
}
