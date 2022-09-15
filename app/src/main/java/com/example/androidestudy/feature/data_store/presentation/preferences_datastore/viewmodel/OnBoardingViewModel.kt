package com.example.androidestudy.feature.data_store.presentation.preferences_datastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.data_store.domain.use_case.SaveOnBoardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveOnBoardingState: SaveOnBoardingState,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    // このViewModelは、値の書き込みだけ
    fun saveOnBoardingStates(isCompleted: Boolean) {
        viewModelScope.launch(dispatcher) {
            saveOnBoardingState(isCompleted = isCompleted)
        }
    }
}