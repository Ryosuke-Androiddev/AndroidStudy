package com.example.androidestudy.feature.retrofit.presentation.component

sealed class UiEvent {
    object Success: UiEvent()
    object Failure: UiEvent()
    object NavigateUp: UiEvent()
    data class ShowSnackBar(val message: String): UiEvent()
}
