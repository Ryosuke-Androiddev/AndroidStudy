package com.example.androidestudy.feature.retrofit.presentation.postscreen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidestudy.feature.retrofit.domain.usecase.PostUserPostUseCase
import com.example.androidestudy.feature.retrofit.presentation.component.StandardScreenState
import com.example.androidestudy.feature.retrofit.presentation.component.UiEvent
import com.example.androidestudy.feature.retrofit.presentation.postscreen.component.PostScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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
            is PostScreenEvent.EnterTitleEvent -> {

            }
            is PostScreenEvent.EnterBodyEvent -> {

            }
            is PostScreenEvent.PostUserPost -> {
                // _eventFlowを更新する
            }
        }
    }
}