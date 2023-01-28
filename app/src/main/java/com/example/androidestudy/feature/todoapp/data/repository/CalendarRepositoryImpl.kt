package com.example.androidestudy.feature.todoapp.data.repository

import android.util.Log
import com.example.androidestudy.feature.todoapp.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarRepositoryImpl: CalendarRepository {

    // TODO 詳細は以下
    // List<Map<String, String>>これで管理して戻り値とすればよさそう

    // ここで最低限の処理しか返さないようにすれば問題なくね??
    override fun getCurrentMonth(currentMonth: Int): Flow<List<Pair<Int, String>>> {
        val days = mutableListOf<String>()
        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_WEEK, currentMonth)
        val daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val simpleDateFormat = SimpleDateFormat("MM-dd")
        for (i in 0 until daysOfMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1)
            Log.d("Calendar1", "${simpleDateFormat.format(calendar.time)}")
            days.add("${simpleDateFormat.format(calendar.time)}")
        }
        return flow {

        }
    }

    override fun getNextMonth(currentMonth: Int): Flow<List<Pair<Int, String>>> {
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
            Log.d("Calendar2", "${simpleDateFormat.format(calendar.time)}")
            days.add("${simpleDateFormat.format(calendar.time)}")
        }
        return flow {

        }
    }

    override fun getPreviousMonth(currentMonth: Int): Flow<List<Pair<Int, String>>> {
        val days = mutableListOf<String>()
        val calendar = Calendar.getInstance(Locale.JAPAN)
        val prevMonth = currentMonth - 1
        calendar.set(Calendar.MONTH, prevMonth)
        calendar.set(Calendar.DAY_OF_MONTH, prevMonth)

        // ループを止める範囲を変更する
        val daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val simpleDateFormat = SimpleDateFormat("MM-dd")
        for (i in 0 until daysOfMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1)
            Log.d("Calendar3", "${simpleDateFormat.format(calendar.time)}")
            days.add("${simpleDateFormat.format(calendar.time)}")
        }
        return flow {

        }
    }
}