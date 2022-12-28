package com.example.androidestudy.feature.todoapp.domain.usecase.todo

import com.example.androidestudy.feature.todoapp.domain.mapper.toTodoItemState
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.OrderType
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.TodoPostOrder
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
        todoPostOrder: TodoPostOrder = TodoPostOrder.Date(orderType = OrderType.Descending)
    ): Flow<List<TodoItemState>> {
        val todoList = repository.getAllTodo().map { todoList ->
            todoList.map { todoItem ->
                todoItem.toTodoItemState()
            }
        }

        return todoList.map { todoStateList ->
            when (todoPostOrder.orderType) {
                is OrderType.Ascending -> {
                    when (todoPostOrder) {
                        is TodoPostOrder.Date -> {
                            todoStateList.sortedBy { it.createdAt }
                        }
                        is TodoPostOrder.Priority -> {
                            todoStateList.sortedBy { it.priority.order }
                        }
                        is TodoPostOrder.Title -> {
                            todoStateList.sortedBy { it.title }
                        }
                    }
                }
                is OrderType.Descending -> {
                    when (todoPostOrder) {
                        is TodoPostOrder.Date -> {
                            todoStateList.sortedByDescending { it.createdAt }
                        }
                        is TodoPostOrder.Priority -> {
                            todoStateList.sortedByDescending { it.priority.order }
                        }
                        is TodoPostOrder.Title -> {
                            todoStateList.sortedByDescending { it.title }
                        }
                    }
                }
            }
        }
    }
}