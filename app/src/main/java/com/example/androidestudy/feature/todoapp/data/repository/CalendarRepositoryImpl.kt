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
        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, currentMonth)
        val daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val simpleDateFormat = SimpleDateFormat("MM-dd")
        for (i in 0 until daysOfMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1)
            Log.d("Calendar", "${simpleDateFormat.format(calendar.time)}")
            days.add("${simpleDateFormat.format(calendar.time)}")
        }
        return flow {
            emit(days)
        }
    }

    override fun getNextMonth(currentMonth: Int): Flow<List<String>> {
        val days = mutableListOf<String>()
        val calendar = Calendar.getInstance(Locale.JAPAN)
        val nextMonth = currentMonth + 1
        calendar.set(Calendar.MONTH, nextMonth)
        calendar.set(Calendar.DAY_OF_MONTH, nextMonth)

        // ループを止める範囲を変更する
        val daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val simpleDateFormat = SimpleDateFormat("MM-dd")
        for (i in 0 until daysOfMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1)
            Log.d("Calendar", "${simpleDateFormat.format(calendar.time)}")
            days.add("${simpleDateFormat.format(calendar.time)}")
        }
        return flow {
            emit(days)
        }
    }

    override fun getPreviousMonth(currentMonth: Int): Flow<List<String>> {
        val days = mutableListOf<String>()
        val calendar = Calendar.getInstance(Locale.JAPAN)
        val nextMonth = currentMonth - 1
        calendar.set(Calendar.MONTH, nextMonth)
        calendar.set(Calendar.DAY_OF_MONTH, nextMonth)

        // ループを止める範囲を変更する
        val daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val simpleDateFormat = SimpleDateFormat("MM-dd")
        for (i in 0 until daysOfMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1)
            Log.d("Calendar", "${simpleDateFormat.format(calendar.time)}")
            days.add("${simpleDateFormat.format(calendar.time)}")
        }
        return flow {
            emit(days)
        }
    }
}