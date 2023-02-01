package com.example.androidestudy.feature.todoapp.presentation.todo.daily.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidestudy.feature.todoapp.domain.usecase.todo.GetCalendarDays
import com.example.androidestudy.feature.todoapp.presentation.todo.list.component.DailyState
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class DailyViewModel(
    private val getCalendarDays: GetCalendarDays
): ViewModel() {

    var state by mutableStateOf(DailyState())
        private set
}