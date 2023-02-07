package com.example.androidestudy.feature.todoapp.domain.usecase.todo

import com.example.androidestudy.feature.todoapp.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetCalendarDays @Inject constructor(
    private val repository: CalendarRepository
) {
    // 曜日で先月の取得したい日を取得する
    // 同じように月末のにひちも取得したいところを取得する
    suspend operator fun invoke(): Flow<List<String>> {

        val calendarInstance = Calendar.getInstance(Locale.JAPAN)
        val currentMonth = calendarInstance.get(Calendar.MONTH)

        val prevCalendar = mutableListOf<String>()
        val currentCalendar = mutableListOf<String>()
        val nextCalendar = mutableListOf<String>()

        val calendar = mutableListOf<String>()

        // 3つ同時に参照した場合、特に処理は必要ない
        val prevCalendarFlow = repository.getPreviousMonth(currentMonth = currentMonth).onEach { prevMonthList ->
            prevMonthList.forEach { datePair ->
                // 最後のキリ良く終わっていない部分を追加する
                prevCalendar.add(datePair.second)
            }
        }

        val currentCalendarFlow = repository.getCurrentMonth(currentMonth = currentMonth).onEach { currentMonthList ->
            currentMonthList.forEach { datePair ->
                currentCalendar.add(datePair.second)
            }
        }

        // ここは,全体で何個あるか確認して、残った分だけを追加する
        // prev, current Calendarの大きさから総グリッド数を引い多分だけをリストに追加する
        val nextCalendarFlow = repository.getNextMonth(currentMonth = currentMonth).onEach { nextMonthList ->
            nextMonthList.forEach { datePair ->
                nextCalendar.add(datePair.second)
            }
        }

        // suspendないで呼び込む
        combine(prevCalendarFlow, currentCalendarFlow, nextCalendarFlow) { f1, f2, f3 ->
            f1 + f2 + f3
        }.collect {
            it.forEach {
                calendar.add(it.second)
            }
        }

        return flow {
            emit(calendar.toList())
        }
    }
}