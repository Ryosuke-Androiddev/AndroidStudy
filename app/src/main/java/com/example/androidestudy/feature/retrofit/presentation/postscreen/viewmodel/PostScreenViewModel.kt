package com.example.androidestudy.feature.retrofit.presentation.postscreen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.util.ScreenState
import com.example.androidestudy.feature.retrofit.domain.usecase.PostUserPostUseCase
import com.example.androidestudy.feature.retrofit.presentation.component.StandardScreenState
import com.example.androidestudy.feature.retrofit.presentation.component.UiEvent
import com.example.androidestudy.feature.retrofit.presentation.postscreen.component.PostScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostScreenViewModel @Inject constructor(
    private val postUserPostUseCase: PostUserPostUseCase
): ViewModel() {

    var state by mutableStateOf(StandardScreenState())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: PostScreenEvent) {
        when (event) {
            // ネストが深くなるからsealed classで処理書くのあんまり良くなさそう
            is PostScreenEvent.EnterTitleEvent -> {
                // TextInputValidationこれは、ViewModelでやるべき
                // ui側の引数で渡す
                state = state.copy(
                    title = event.title
                )
                onUseCaseEvent()
            }
            is PostScreenEvent.EnterBodyEvent -> {
                // ui側の引数で渡す
                state = state.copy(
                    body = event.body
                )
                onUseCaseEvent()
            }
            is PostScreenEvent.ChangeContentFocus -> {
                state = state.copy(
                    isHintVisible = !event.focusState.isFocused
                )
            }
            is PostScreenEvent.PostUserPost -> {
                // _eventFlowを更新する → ここでsealed classを通知する
                onUseCaseEvent()
            }
        }
    }

    private fun onUseCaseEvent() = viewModelScope.launch {
        val screenState = postUserPostUseCase(
            userPostItem = UserPostItem(
                body = state.body,
                id = 1,
                title = state.title,
                userId = 1
            )
        )
        when (screenState) {
            is ScreenState.TextInputError -> {
                state = state.copy(
                    errorMessage = "Please Check Text Input Field"
                )
            }
            is ScreenState.Success -> {
                _eventFlow.emit(UiEvent.Success)
            }
            is ScreenState.Failure -> {
                _eventFlow.emit(UiEvent.ShowSnackBar(message = "UnExpected Error Occurred"))
            }
        }
    }
}