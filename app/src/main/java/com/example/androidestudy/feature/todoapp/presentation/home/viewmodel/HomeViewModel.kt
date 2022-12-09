package com.example.androidestudy.feature.todoapp.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidestudy.feature.todoapp.domain.model.weather.Location
import com.example.androidestudy.feature.todoapp.domain.model.weather.LocationState
import com.example.androidestudy.feature.todoapp.domain.usecase.weather.GetWeatherLocation
import com.example.androidestudy.feature.todoapp.domain.usecase.weather.SetWeatherLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val setWeatherLocation: SetWeatherLocation,
    private val getWeatherLocation: GetWeatherLocation
): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        // Flowから返却される値をコンストラクタで呼び出しておく
        getLocation()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SetWeatherLocation -> {
                setLocation(location = event.location)
            }
            is HomeEvent.GetWeatherLocation -> {
                getLocation()
            }
        }
    }

    private fun setLocation(location: Location) {
        viewModelScope.launch {
            setWeatherLocation(location = location)
        }
        state = state.copy(
            location = location
        )
    }

    private fun getLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            val location = getWeatherLocation().stateIn(viewModelScope).value
            state = state.copy(
                location = location
            )
        }
    }
}