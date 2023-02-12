package com.example.androidestudy.feature.todoapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface CalendarRepository {
    fun getCurrentMonth(currentMonth: Int): Flow<List<Pair<Int, String>>>
    fun getNextMonth(currentMonth: Int): Flow<List<Pair<Int, String>>>
    fun getPreviousMonth(currentMonth: Int): Flow<List<Pair<Int, String>>>
}