package com.example.androidestudy.feature.todoapp.presentation.home.viewmodel

import com.example.androidestudy.feature.todoapp.domain.model.weather.Location

data class HomeState(
    val location: Location = Location.Tokyo
)
