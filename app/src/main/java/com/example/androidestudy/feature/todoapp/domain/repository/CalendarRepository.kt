package com.example.androidestudy.feature.todoapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface CalendarRepository {
    fun getCurrentMonth(currentMonth: Int): Flow<List<String>>
    fun getNextMonth(nextMonth: Int): Flow<List<String>>
}