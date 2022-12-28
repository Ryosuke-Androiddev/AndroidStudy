package com.example.androidestudy.feature.todoapp.domain.usecase.todo

import com.example.androidestudy.feature.todoapp.domain.mapper.toTodoItemState
import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.OrderType
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.TodoPostOrder
import com.example.androidestudy.feature.todoapp.domain.repository.TodoLocalDBRepository
import com.example.androidestudy.feature.todoapp.presentation.home.component.Priority
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoPriority
import com.example.androidestudy.feature.todoapp.presentation.home.component.todo.TodoItemState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTodo @Inject constructor(
    private val repository: TodoLocalDBRepository
) {

    // sort 一覧 title, priority, createdAt
    operator fun invoke(
        todoPostOrder: TodoPostOrder = TodoPostOrder.Date(orderType = OrderType.Descending)
    ): Flow<List<TodoItemState>> {
        return repository.getAllTodo().map { todoList ->
            todoList.map { todoItem ->
                todoItem.toTodoItemState()
            }
        }
    }
}