package com.example.androidestudy.feature.todoapp.domain.usecase.todo

import com.example.androidestudy.feature.todoapp.domain.mapper.toTodoItemState
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.OrderType
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.TodoOrder
import com.example.androidestudy.feature.todoapp.domain.repository.TodoLocalDBRepository
import com.example.androidestudy.feature.todoapp.presentation.home.component.todo.TodoItemState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTodo @Inject constructor(
    private val repository: TodoLocalDBRepository
) {

    // sort 一覧 title, priority, createdAt
    operator fun invoke(
        todoOrder: TodoOrder = TodoOrder.Date(orderType = OrderType.Descending)
    ): Flow<List<TodoItemState>> {
        val todoList = repository.getAllTodo().map { todoList ->
            todoList.map { todoItem ->
                todoItem.toTodoItemState()
            }
        }

        return todoList.map { todoStateList ->
            when (todoOrder.orderType) {
                is OrderType.Ascending -> {
                    when (todoOrder) {
                        is TodoOrder.Date -> {
                            todoStateList.sortedBy { it.createdAt }
                        }
                        is TodoOrder.Priority -> {
                            todoStateList.sortedBy { it.priority.order }
                        }
                        is TodoOrder.Title -> {
                            todoStateList.sortedBy { it.title }
                        }
                    }
                }
                is OrderType.Descending -> {
                    when (todoOrder) {
                        is TodoOrder.Date -> {
                            todoStateList.sortedByDescending { it.createdAt }
                        }
                        is TodoOrder.Priority -> {
                            todoStateList.sortedByDescending { it.priority.order }
                        }
                        is TodoOrder.Title -> {
                            todoStateList.sortedByDescending { it.title }
                        }
                    }
                }
            }
        }
    }
}