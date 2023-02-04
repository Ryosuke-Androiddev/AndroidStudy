package com.example.androidestudy.feature.todoapp.presentation.todo.daily.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.todoapp.domain.usecase.todo.GetCalendarDays
import com.example.androidestudy.feature.todoapp.presentation.todo.list.component.DailyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getCalendarDays: GetCalendarDays
): ViewModel() {

    init {
        getCurrentCalendar()
    }

    var state by mutableStateOf(DailyState())
        private set

    private fun getCurrentCalendar() {
        viewModelScope.launch {
            getCalendarDays().onEach { calendar ->
                Log.d("CalendarViewModel", "$calendar")
                state = state.copy(
                    calendar = calendar
                )
            }.launchIn(viewModelScope)
        }
    }
}