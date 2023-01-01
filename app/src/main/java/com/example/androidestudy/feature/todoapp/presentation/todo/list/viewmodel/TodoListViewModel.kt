package com.example.androidestudy.feature.todoapp.presentation.todo.list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidestudy.feature.todoapp.domain.usecase.todo.GetTodoItemByPriority
import com.example.androidestudy.feature.todoapp.presentation.home.viewmodel.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodoListByPriority: GetTodoItemByPriority
): ViewModel() {

    init {

    }
}