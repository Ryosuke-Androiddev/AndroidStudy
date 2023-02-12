package com.example.androidestudy.feature.todoapp.domain.model.onboarding

sealed class TodoAppOnBoardingEvent {
    object IgnoreOnBoarding: TodoAppOnBoardingEvent()
    object ShowOnBoarding: TodoAppOnBoardingEvent()
    object ReloadOnBoardingState: TodoAppOnBoardingEvent()
}
