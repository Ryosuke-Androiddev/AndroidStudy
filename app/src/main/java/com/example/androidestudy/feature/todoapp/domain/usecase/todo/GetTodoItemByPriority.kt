package com.example.androidestudy.feature.todoapp.domain.usecase.todo

import android.util.Log
import com.example.androidestudy.feature.todoapp.domain.mapper.toTodoItemState
import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem
import com.example.androidestudy.feature.todoapp.domain.repository.TodoLocalDBRepository
import com.example.androidestudy.feature.todoapp.presentation.home.component.Priority
import com.example.androidestudy.feature.todoapp.presentation.home.component.todo.TodoItemState
import com.example.androidestudy.feature.todoapp.presentation.util.fakeTodoList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTodoItemByPriority @Inject constructor(
    private val repository: TodoLocalDBRepository
) {
    // こっちでfilterかけて返してもよさそう
    // operator fun invoke(priority: Priority): Flow<List<TodoItemState>> {
    //     val priorityId = Priority.priorityToInt(priority = priority)
    //     return repository.getTodoItemByPriority(priority = priorityId).map { todoList ->
    //         todoList.map {
    //             it.toTodoItemState()
    //         }
    //     }
    // }

    operator fun invoke(priority: Priority): List<TodoItemState> {

        if (priority == Priority.Ordinal) {
            return fakeTodoList
        }

        val fakeList = fakeTodoList.filter {
            it.priority.order == priority.order
        }

        return fakeList
    }
}