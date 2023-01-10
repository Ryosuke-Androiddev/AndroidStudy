package com.example.androidestudy.feature.todoapp.presentation.todo.list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.OrderType
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.TodoOrder

@Composable
fun SortOrderSection(
    modifier: Modifier = Modifier,
    todoOrder: TodoOrder,
    onOrderChange: (TodoOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SortRadioButton(
                text = "Title",
                selected = todoOrder is TodoOrder.Title,
                onSelect = {
                    onOrderChange(TodoOrder.Title(todoOrder.orderType))
                }
            )
            SortRadioButton(
                text = "Date",
                selected = todoOrder is TodoOrder.Date,
                onSelect = {
                    onOrderChange(TodoOrder.Date(todoOrder.orderType))
                }
            )
            SortRadioButton(
                text = "Priority",
                selected = todoOrder is TodoOrder.Priority,
                onSelect = {
                    onOrderChange(TodoOrder.Priority(todoOrder.orderType))
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SortRadioButton(
                text = "Ascending",
                selected = todoOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(todoOrder.copy(OrderType.Ascending))
                }
            )

            SortRadioButton(
                text = "Descending",
                selected = todoOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(todoOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}