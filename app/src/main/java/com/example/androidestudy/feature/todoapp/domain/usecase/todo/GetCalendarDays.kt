package com.example.androidestudy.feature.todoapp.domain.usecase.todo

import com.example.androidestudy.feature.todoapp.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetCalendarDays @Inject constructor(
    private val repository: CalendarRepository
) {
    // 曜日で先月の取得したい日を取得する
    // 同じように月末のにひちも取得したいところを取得する
    operator fun invoke(currentMonth: Int): Flow<List<String>> = flow {

        val prevCalendar = mutableListOf<String>()
        val currentCalendar = mutableListOf<String>()
        val nextCalendar = mutableListOf<String>()

        val calendar = mutableListOf<String>()

        // 3つ同時に参照した場合、特に処理は必要ない
        repository.getPreviousMonth(currentMonth = currentMonth).onEach { prevMonthList ->
            prevMonthList.forEach { datePair ->
                // 最後のキリ良く終わっていない部分を追加する
                prevCalendar.add(datePair.second)
            }
        }

        repository.getCurrentMonth(currentMonth = currentMonth).onEach { currentMonthList ->
            currentMonthList.forEach { datePair ->
                currentCalendar.add(datePair.second)
            }
        }

        // ここは,全体で何個あるか確認して、残った分だけを追加する
        // prev, current Calendarの大きさから総グリッド数を引い多分だけをリストに追加する
        repository.getNextMonth(currentMonth = currentMonth).onEach { prevMonthList ->
            prevMonthList.forEach { datePair ->
                nextCalendar.add(datePair.second)
            }
        }

        // 表示したい内容を持ったリストこここで作成する
        calendar.addAll(prevCalendar)
        calendar.addAll(currentCalendar)
        calendar.addAll(nextCalendar)

        emit(calendar.toList())
    }
}