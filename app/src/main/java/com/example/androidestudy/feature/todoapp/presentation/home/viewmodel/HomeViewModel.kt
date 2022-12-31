package com.example.androidestudy.feature.todoapp.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.data_store.presentation.preferences_datastore.component.TodoOnBoardingState
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.OrderType
import com.example.androidestudy.feature.todoapp.domain.model.todo.order.TodoPostOrder
import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.domain.usecase.todo.GetAllTodo
import com.example.androidestudy.feature.todoapp.domain.usecase.todo.GetTodoItemByPriority
import com.example.androidestudy.feature.todoapp.domain.usecase.weather.GetWeatherData
import com.example.androidestudy.feature.todoapp.domain.usecase.weather.GetWeatherLocation
import com.example.androidestudy.feature.todoapp.domain.usecase.weather.SetWeatherLocation
import com.example.androidestudy.feature.todoapp.presentation.home.component.Priority
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoPriority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val setWeatherLocation: SetWeatherLocation,
    private val getWeatherLocation: GetWeatherLocation,
    private val getWeatherData: GetWeatherData,
    private val getTodoListByPriority: GetTodoItemByPriority
): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private var getAllTodoJob: Job? = null

    init {
        // 以下の処理を async-await で完了させる
        getLocation()
        getWeatherData()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SetWeatherLocation -> {
                setLocation(
                    location = event.location,
                    latitude = event.latitude,
                    longitude = event.longitude
                )
            }
            is HomeEvent.GetWeatherLocation -> {
                getLocation()
            }
            is HomeEvent.GetWeatherData -> {
                getWeatherData()
            }
            is HomeEvent.GetTodoListByPriority -> {
                getTodoListByPriority(event.priority)
            }
        }
    }

    private fun setLocation(
        location: Location,
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            setWeatherLocation(location = location, latitude = latitude, longitude = longitude)
        }
        state = state.copy(
            location = location,
            latitude = latitude,
            longitude = longitude
        )
    }

    private fun getLocation() {
        viewModelScope.launch {
            val weatherLocation = getWeatherLocation().stateIn(viewModelScope).value
            if (weatherLocation.latitude != null && weatherLocation.longitude != null) {
                state = state.copy(
                    location = weatherLocation.location,
                    latitude = weatherLocation.latitude,
                    longitude = weatherLocation.longitude
                )
            }
        }
    }

    private fun getWeatherData() {
        val latitude = state.latitude
        val longitude = state.longitude
        if (latitude != null && longitude != null) {
            viewModelScope.launch(Dispatchers.IO) {
                val weatherData = getWeatherData(
                    latitude = latitude,
                    longitude = longitude
                )
                state = state.copy(
                    weatherData = weatherData
                )
            }
        }
    }

    private fun getTodoListByPriority(priority: Int) {
        getAllTodoJob?.cancel()
        val priorityId = Priority.intToPriority(priority)
        getAllTodoJob = getTodoListByPriority(priority = priorityId)
            .onEach { todoList ->
                state = state.copy(
                    todoListByPriority = todoList
                )
            }
            .launchIn(viewModelScope)
    }
}