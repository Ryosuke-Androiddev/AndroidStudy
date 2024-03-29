package com.example.androidestudy.feature.todoapp.data.repository

import android.util.Log
import com.example.androidestudy.feature.todoapp.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.time.Duration.Companion.days

class CalendarRepositoryImpl: CalendarRepository {

    override fun getCurrentMonth(currentMonth: Int): Flow<List<Pair<Int, String>>> {

        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, currentMonth)

        val daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val simpleDateFormat = SimpleDateFormat("dd")
        val currentMonthList = mutableListOf<Pair<Int, String>>()
        for (i in 0 until daysOfMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1)
            currentMonthList.add(Pair(calendar.get(Calendar.DAY_OF_WEEK), simpleDateFormat.format(calendar.time)))
        }
        return flow {
            emit(currentMonthList.toList())
        }
    }

    // 総グリッド数は42個ある
    override fun getNextMonth(currentMonth: Int): Flow<List<Pair<Int, String>>> {

        // prev
        val prevCalendar = Calendar.getInstance(Locale.JAPAN)
        val prevMonth = currentMonth - 1
        prevCalendar.set(Calendar.MONTH, prevMonth)
        prevCalendar.set(Calendar.DAY_OF_MONTH, prevMonth)
        val daysOfMonth = prevCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val lastDayCalendarFlag = Calendar.getInstance(Locale.JAPAN)
        lastDayCalendarFlag.set(Calendar.MONTH, prevMonth)
        lastDayCalendarFlag.set(Calendar.DAY_OF_MONTH, daysOfMonth)

        val prevCount = when (lastDayCalendarFlag.get(Calendar.DAY_OF_WEEK)) {
            1 -> {
                1
            }
            2 -> {
                2
            }
            3 -> {
                3
            }
            4 -> {
                4
            }
            5 -> {
                5
            }
            6 -> {
                6
            }
            7 -> {
                0
            }
            else -> {
                0
                // Nothing To Implement Here
            }
        }

        // current
        val currentCalendar = Calendar.getInstance(Locale.JAPAN)
        currentCalendar.set(Calendar.MONTH, currentMonth)
        currentCalendar.set(Calendar.DAY_OF_MONTH, currentMonth)
        val currentDaysOfMonth = currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // next
        val last = 42 - (prevCount + currentDaysOfMonth)

        val calendar = Calendar.getInstance(Locale.JAPAN)
        val nextMonth = currentMonth + 1
        calendar.set(Calendar.MONTH, nextMonth)
        calendar.set(Calendar.DAY_OF_MONTH, nextMonth)
        val simpleDateFormat = SimpleDateFormat("dd")

        // ループを止める範囲を変更する
        val currentMonthList = mutableListOf<Pair<Int, String>>()

        // last + 1にする必要ある??
        for (i in 0 until last) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1)
            currentMonthList.add(Pair(calendar.get(Calendar.DAY_OF_WEEK), simpleDateFormat.format(calendar.time)))
        }

        return flow {
            emit(currentMonthList.toList())
        }
    }

    override fun getPreviousMonth(currentMonth: Int): Flow<List<Pair<Int, String>>> {
        val calendar = Calendar.getInstance(Locale.JAPAN)
        val prevMonth = currentMonth - 1
        calendar.set(Calendar.MONTH, prevMonth)
        calendar.set(Calendar.DAY_OF_MONTH, prevMonth)
        val daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val simpleDateFormat = SimpleDateFormat("dd")

        val lastDayCalendarFlag = Calendar.getInstance(Locale.JAPAN)
        lastDayCalendarFlag.set(Calendar.MONTH, prevMonth)
        lastDayCalendarFlag.set(Calendar.DAY_OF_MONTH, daysOfMonth)

        val start = when (lastDayCalendarFlag.get(Calendar.DAY_OF_WEEK)) {
            1 -> {
                daysOfMonth - 1
            }
            2 -> {
                daysOfMonth - 2
            }
            3 -> {
                daysOfMonth - 3
            }
            4 -> {
                daysOfMonth - 4
            }
            5 -> {
                daysOfMonth - 5
            }
            6 -> {
                daysOfMonth - 6
            }
            7 -> {
                daysOfMonth - 0
            }
            else -> {
                daysOfMonth - 0
                // Nothing To Implement Here
            }
        }

        // ループを止める範囲を変更する
        val currentMonthList = mutableListOf<Pair<Int, String>>()
        if (start != daysOfMonth) {
            for (i in start until daysOfMonth) {
                calendar.set(Calendar.DAY_OF_MONTH, i + 1)
                currentMonthList.add(Pair(calendar.get(Calendar.DAY_OF_WEEK), simpleDateFormat.format(calendar.time)))
            }
        }

        return flow {
            emit(currentMonthList.toList())
        }
    }
}