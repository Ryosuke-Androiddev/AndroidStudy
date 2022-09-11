package com.example.androidestudy.feature.presentation.preferencesdatastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.domain.use_case.datastore.preferences.SaveOnBoardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesDataStoreViewModel @Inject constructor(
    private val saveOnBoardingState: SaveOnBoardingState
): ViewModel() {

    // このViewModelは、値の書き込みだけ
    fun saveOnBoardingStates(isCompleted: Boolean) {
        viewModelScope.launch {
            saveOnBoardingState(isCompleted = isCompleted)
        }
    }
}