package com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.example.androidestudy.feature.retrofit.domain.model.result.GetUserPostByIdState
import com.example.androidestudy.feature.retrofit.domain.model.util.ScreenState
import com.example.androidestudy.feature.retrofit.domain.usecase.GetUserPostByIdUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.UpdateUserPostUseCase
import com.example.androidestudy.feature.retrofit.presentation.component.StandardScreenState
import com.example.androidestudy.feature.retrofit.presentation.component.UiEvent
import com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.component.PostUpdateScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostEditScreenViewModel @Inject constructor(
    private val getUserPostByIdUseCase: GetUserPostByIdUseCase,
    private val updateUserPostUseCase: UpdateUserPostUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(StandardScreenState())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentUserPostId: Int = -1

    init {
        // SavedStateHandleを使って、取得まで済ませる
        savedStateHandle.get<Int>("id")?.let { id ->
            if (id != -1) {
                viewModelScope.launch {
                    getUserPostByIdUseCase(id = id).also {
                        currentUserPostId = id
                        state = when (it) {
                            is GetUserPostByIdState.GetUserPostById -> {
                                state.copy(
                                    title = it.userPost.title,
                                    body = it.userPost.body
                                )
                            }
                            is GetUserPostByIdState.Failure -> {
                                state.copy(
                                    errorMessage = "Un Exception Occurred"
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: PostUpdateScreenEvent) {
        when (event) {
            is PostUpdateScreenEvent.EnterBodyEvent -> {
                state = state.copy(
                    body = event.body
                )
            }
            is PostUpdateScreenEvent.EnterTitleEvent -> {
                state = state.copy(
                    title = event.title
                )
            }
            is PostUpdateScreenEvent.ChangeContentFocus -> {
                state = state.copy(
                    isHintVisible = !event.focusState.isFocused
                        && state.body.isNotEmpty()
                )
            }
            is PostUpdateScreenEvent.UpdateUserPost -> {
                onUseCaseEvent()
            }
        }
    }

    private fun onUseCaseEvent() = viewModelScope.launch {

        if (currentUserPostId != -1) {
            val screenState = updateUserPostUseCase(
                userPostItem = UserPostItem(
                    body = state.body,
                    id = currentUserPostId,
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
}