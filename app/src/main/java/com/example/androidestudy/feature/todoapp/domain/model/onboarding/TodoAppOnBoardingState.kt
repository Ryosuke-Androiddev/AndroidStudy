package com.example.androidestudy.feature.todoapp.domain.model.onboarding

sealed class TodoAppOnBoardingState {
    object IsCompleted: TodoAppOnBoardingState()
    object IsNotCompleted: TodoAppOnBoardingState()
    object Exception: TodoAppOnBoardingState()
}
