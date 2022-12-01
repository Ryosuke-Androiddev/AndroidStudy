package com.example.androidestudy.feature.todoapp.presentation.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.todoapp.domain.usecase.onboarding.SaveOnBoardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveOnBoardingStateUseCase: SaveOnBoardingStateUseCase
): ViewModel() {

    // IO スレッドで対応する
    fun saveOnBoardingState(isCompleted: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        saveOnBoardingStateUseCase(isCompleted = isCompleted)
    }
}