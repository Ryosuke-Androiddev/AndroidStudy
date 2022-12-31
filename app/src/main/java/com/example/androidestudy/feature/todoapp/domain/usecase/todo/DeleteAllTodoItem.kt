package com.example.androidestudy.feature.todoapp.domain.usecase.todo

import com.example.androidestudy.feature.todoapp.domain.repository.TodoLocalDBRepository
import com.example.androidestudy.feature.todoapp.presentation.home.component.Priority
import javax.inject.Inject

class DeleteAllTodoItem   @Inject constructor(
    private val repository: TodoLocalDBRepository
) {
    suspend operator fun invoke(priority: Priority) {
        val priorityId = Priority.priorityToInt(priority = priority)
        repository.deleteAllTodoItem(priority = priorityId)
    }
}