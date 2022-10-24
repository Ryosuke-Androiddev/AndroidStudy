package com.example.androidestudy.feature.retrofit.presentation.component

data class StandardScreenState(
    val title: String,
    val body: String,
    val isLoading: Boolean,
    val showHint: Boolean = false,
    val isTitleError: String? = null,
    val isBodyError: String? = null
)
