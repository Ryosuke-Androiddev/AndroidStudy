package com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androidestudy.feature.retrofit.domain.usecase.UpdateUserPostUseCase
import com.example.androidestudy.feature.retrofit.presentation.component.StandardScreenState
import com.example.androidestudy.feature.retrofit.presentation.component.UiEvent
import com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.component.PostUpdateScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class PostEditScreenViewModel @Inject constructor(
    private val updateUserPostUseCase: UpdateUserPostUseCase
): ViewModel() {

    var state = mutableStateOf(StandardScreenState())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: PostUpdateScreenEvent) {
        when (event) {
            is PostUpdateScreenEvent.EnterBodyEvent -> {

            }
            is PostUpdateScreenEvent.EnterTitleEvent -> {

            }
            is PostUpdateScreenEvent.ChangeContentFocus -> {

            }
            is PostUpdateScreenEvent.UpdateUserPost -> {

            }
        }
    }
}