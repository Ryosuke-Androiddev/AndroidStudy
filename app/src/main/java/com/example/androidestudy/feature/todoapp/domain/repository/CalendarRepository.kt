package com.example.androidestudy.feature.todoapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface CalendarRepository {
    fun getCurrentMonth(currentMonth: Int): Flow<Map<String, String>>
    fun getNextMonth(currentMonth: Int): Flow<Map<String, String>>
    fun getPreviousMonth(currentMonth: Int): Flow<Map<String, String>>
}