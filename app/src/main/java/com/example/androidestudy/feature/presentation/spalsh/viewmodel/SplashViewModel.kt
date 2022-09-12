package com.example.androidestudy.feature.presentation.spalsh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.domain.use_case.datastore.preferences.ReadOnBoardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readOnBoardingState: ReadOnBoardingState,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    // ここStateFlowでやるんや
    private val _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted: StateFlow<Boolean> = _onBoardingCompleted

    init {
        viewModelScope.launch(dispatcher) {
            _onBoardingCompleted.value =
                readOnBoardingState().stateIn(viewModelScope).value
        }
    }
}