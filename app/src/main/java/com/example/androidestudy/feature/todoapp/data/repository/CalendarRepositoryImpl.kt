package com.example.androidestudy.feature.todoapp.data.repository

import android.util.Log
import com.example.androidestudy.feature.todoapp.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarRepositoryImpl: CalendarRepository {

    override fun getCurrentMonth(currentMonth: Int): Flow<List<String>> {
        val days = mutableListOf<String>()
        val currentMonth = Calendar.getInstance(Locale.JAPAN)
        currentMonth.set(Calendar.MONTH, 0)
        currentMonth.set(Calendar.DAY_OF_MONTH, 0)
        val daysOfMonth = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
        val simpleDateFormat = SimpleDateFormat("MM-dd")
        for (i in 0 until daysOfMonth) {
            currentMonth.set(Calendar.DAY_OF_MONTH, i + 1)
            Log.d("Calendar", "${simpleDateFormat.format(currentMonth.time)}")
            days.add("${simpleDateFormat.format(currentMonth.time)}")
        }
        return flow {
            emit(days)
        }
    }

    override fun getNextMonth(nextMonth: Int): Flow<List<String>> {
        TODO("Not yet implemented")
    }
}