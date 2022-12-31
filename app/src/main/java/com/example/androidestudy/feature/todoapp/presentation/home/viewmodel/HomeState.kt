package com.example.androidestudy.feature.todoapp.presentation.home.viewmodel

import com.example.androidestudy.feature.todoapp.data.mapper.WeatherData
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.TodoPostOrder
import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.presentation.home.component.todo.TodoItemState

data class HomeState(
    val location: Location = Location.Default,
    val weatherData: WeatherData? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val todoItemList: List<TodoItemState> = emptyList(),
    val todoPostOrder: TodoPostOrder? = null,
    val todoListByPriority: List<TodoItemState> = emptyList()
)
