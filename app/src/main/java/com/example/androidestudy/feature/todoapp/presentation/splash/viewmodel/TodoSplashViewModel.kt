package com.example.androidestudy.feature.todoapp.presentation.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.data_store.presentation.preferences_datastore.component.TodoOnBoardingState
import com.example.androidestudy.feature.todoapp.domain.model.onboarding.TodoAppOnBoardingEvent
import com.example.androidestudy.feature.todoapp.domain.usecase.onboarding.GetOnBoardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoSplashViewModel @Inject constructor(
    private val getOnBoardingStateUseCase: GetOnBoardingStateUseCase,
    dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _onBoardingState = MutableStateFlow(value = TodoOnBoardingState())
    val onBoardingState: StateFlow<TodoOnBoardingState> = _onBoardingState

    // getOnBoardingStateUseCaseは、Cold FlowなのでHot Flowに変更する
    // StateFlowで管理できるようにする(Cold → Hot)
    init {
        // IO DispatcherをModuleからInjectする
        viewModelScope.launch(dispatcher) {
            when (getOnBoardingStateUseCase().stateIn(viewModelScope).value) {
                is TodoAppOnBoardingEvent.IgnoreOnBoarding -> {
                    _onBoardingState.value = _onBoardingState.value.copy(
                        isCompleted = true
                    )
                }
                is TodoAppOnBoardingEvent.ShowOnBoarding -> {
                    _onBoardingState.value = _onBoardingState.value.copy(
                        isCompleted = false
                    )
                }
                is TodoAppOnBoardingEvent.ReloadOnBoardingState -> {
                    _onBoardingState.value = _onBoardingState.value.copy(
                        hasError = true
                    )
                }
            }
        }
    }
}