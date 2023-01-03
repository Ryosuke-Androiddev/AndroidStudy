package com.example.androidestudy.feature.todoapp.presentation.todo.list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.androidestudy.feature.todoapp.domain.usecase.todo.GetTodoItemByPriority
import com.example.androidestudy.feature.todoapp.presentation.home.component.Priority
import com.example.androidestudy.feature.todoapp.presentation.home.viewmodel.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodoListByPriority: GetTodoItemByPriority,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        savedStateHandle.get<Int>("priority")?.let { priority ->
            if (priority != -1) {
                // 置き換える
                // getTodoListByPriority(priority = priority)
                getTodoList(priorityOrder = priority)
            }
        }
    }

    private fun getTodoListByPriority(priority: Int) {
        // getAllTodoJob?.cancel()
        // val priorityId = Priority.intToPriority(priority)
        // getAllTodoJob = getTodoListByPriority(priority = priorityId)
        //     .onEach { todoList ->
        //         state = state.copy(
        //             todoListByPriority = todoList
        //         )
        //     }
        //     .launchIn(viewModelScope)
    }

    private fun getTodoList(priorityOrder: Int) {
        val priority = Priority.intToPriority(priorityOrder)
        val todoList = getTodoListByPriority(priority = priority)
        state = state.copy(
            todoListByPriority = todoList
        )
    }
}