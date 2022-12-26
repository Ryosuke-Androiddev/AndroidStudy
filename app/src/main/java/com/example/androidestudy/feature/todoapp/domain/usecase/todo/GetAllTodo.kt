package com.example.androidestudy.feature.todoapp.domain.usecase.todo

import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder
import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.OrderType
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.TodoPostOrder
import com.example.androidestudy.feature.todoapp.domain.repository.TodoLocalDBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTodo @Inject constructor(
    private val repository: TodoLocalDBRepository
) {

    // sort 一覧 title, content, priority, createdAt
    operator fun invoke(
        todoPostOrder: TodoPostOrder = TodoPostOrder.Date(orderType = OrderType.Descending)
    ): Flow<List<TodoItem>> {
        return repository.getAllTodo().map { todoList ->
            when (todoPostOrder.orderType) {
                is OrderType.Ascending -> {
                    when (todoPostOrder) {
                        is TodoPostOrder.Date -> {
                            todoList.sortedBy { it.createdAt }
                        }
                        is TodoPostOrder.Priority -> {
                            todoList.sortedBy { it.priority.order }
                        }
                        is TodoPostOrder.Title -> {
                            todoList.sortedBy { it.title }
                        }
                    }
                }
                is OrderType.Descending -> {
                    when (todoPostOrder) {
                        is TodoPostOrder.Date -> {
                            todoList.sortedByDescending { it.createdAt }
                        }
                        is TodoPostOrder.Priority -> {
                            todoList.sortedByDescending { it.priority.order }
                        }
                        is TodoPostOrder.Title -> {
                            todoList.sortedByDescending { it.title }
                        }
                    }
                }
            }
        }
    }
}