package com.example.androidestudy.feature.todoapp.presentation.todo.daily.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MonthGridLine() {

    val day = listOf(
        "月",
        "火",
        "水",
        "木",
        "金",
        "土",
        "日",
    )
    val calendar = listOf(
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14,
        15,
        16,
        17,
        18,
        19,
        20,
        21,
        22,
        23,
        24,
        25,
        26,
        27,
        28,
        29,
        30,
        31
    )
    // 曜日を均等に並べる処理を描く
    Layout(
        modifier = Modifier
            .drawBehind {
                repeat(1) {
                    drawLine(
                        start = Offset(x = 0f, y = (it + 1) * 70f),
                        end = Offset(x = size.width, y = (it + 1) * 70f),
                        color = Color.Gray.copy(alpha = 0.3f),
                        strokeWidth = 3f
                    )
                }
                repeat(1) {
                    drawLine(
                        start = Offset(x = 0f, y = (it + 3.3f) * 100f + 72.5f),
                        end = Offset(x = size.width, y = (it + 3.3f) * 100f + 72.5f),
                        color = Color.Gray.copy(alpha = 0.3f),
                        strokeWidth = 3f
                    )
                }
                repeat(1) {
                    drawLine(
                        start = Offset(x = 0f, y = (it + 6.6f) * 100f + 72.5f),
                        end = Offset(x = size.width, y = (it + 6.6f) * 100f + 72.5f),
                        color = Color.Gray.copy(alpha = 0.3f),
                        strokeWidth = 3f
                    )
                }
                repeat(1) {
                    drawLine(
                        start = Offset(x = 0f, y = (it + 9.9f) * 100f + 72.5f),
                        end = Offset(x = size.width, y = (it + 9.9f) * 100f + 72.5f),
                        color = Color.Gray.copy(alpha = 0.3f),
                        strokeWidth = 3f
                    )
                }
                repeat(1) {
                    drawLine(
                        start = Offset(x = 0f, y = (it + 13.1f) * 100f + 72.5f),
                        end = Offset(x = size.width, y = (it + 13.1f) * 100f + 72.5f),
                        color = Color.Gray.copy(alpha = 0.3f),
                        strokeWidth = 3f
                    )
                }
                repeat(1) {
                    drawLine(
                        start = Offset(x = 0f, y = (it + 16.2f) * 100f + 72.5f),
                        end = Offset(x = size.width, y = (it + 16.2f) * 100f + 72.5f),
                        color = Color.Gray.copy(alpha = 0.3f),
                        strokeWidth = 3f
                    )
                }

                // vertical
                drawLine(
                    start = Offset(x = 160f, y = 40f),
                    end = Offset(x = 160f, y = 2130f),
                    color = Color.Gray.copy(alpha = 0.3f),
                    strokeWidth = 3f
                )
                drawLine(
                    start = Offset(x = 315f, y = 40f),
                    end = Offset(x = 315f, y = 2130f),
                    color = Color.Gray.copy(alpha = 0.3f),
                    strokeWidth = 3f
                )
                drawLine(
                    start = Offset(x = 470f, y = 40f),
                    end = Offset(x = 470f, y = 2130f),
                    color = Color.Gray.copy(alpha = 0.3f),
                    strokeWidth = 3f
                )
                drawLine(
                    start = Offset(x = 625f, y = 40f),
                    end = Offset(x = 625f, y = 2130f),
                    color = Color.Gray.copy(alpha = 0.3f),
                    strokeWidth = 3f
                )
                drawLine(
                    start = Offset(x = 780f, y = 40f),
                    end = Offset(x = 780f, y = 2130f),
                    color = Color.Gray.copy(alpha = 0.3f),
                    strokeWidth = 3f
                )
                drawLine(
                    start = Offset(x = 935f, y = 40f),
                    end = Offset(x = 935f, y = 2130f),
                    color = Color.Gray.copy(alpha = 0.3f),
                    strokeWidth = 3f
                )
            }
            .padding(horizontal = 12.dp),
        content = {
            day.forEachIndexed { index, s ->
                Text(
                    modifier = Modifier.layoutId("$index"),
                    text = s,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            calendar.forEachIndexed { index, s ->
                val layoutId = index + 10
                Text(
                    modifier = Modifier.layoutId("$layoutId"),
                    text = "1",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        measurePolicy = { measurables, constraints ->

            // indexはおそらく0から始まる
            val mondayMeasurable = measurables.find { it.layoutId == "0" }
                ?: error("title not found")
            val tuesdayMeasurable = measurables.find { it.layoutId == "1" }
                ?: error("title not found")
            val wednesMeasurable = measurables.find { it.layoutId == "2" }
                ?: error("title not found")
            val thursdayMeasurable = measurables.find { it.layoutId == "3" }
                ?: error("title not found")
            val fridayMeasurable = measurables.find { it.layoutId == "4" }
                ?: error("title not found")
            val saturdayMeasurable = measurables.find { it.layoutId == "5" }
                ?: error("title not found")
            val sundayMeasurable = measurables.find { it.layoutId == "6" }
                ?: error("title not found")

            val dayOneMeasurable = measurables.find { it.layoutId == "10" }
                ?: error("not found")
            val dayTwoMeasurable = measurables.find { it.layoutId == "11" }
                ?: error("not found")
            val dayThreeMeasurable = measurables.find { it.layoutId == "12" }
                ?: error("not found")
            val dayFourMeasurable = measurables.find { it.layoutId == "13" }
                ?: error("not found")
            val dayFiveMeasurable = measurables.find { it.layoutId == "14" }
                ?: error("not found")
            val daySixMeasurable = measurables.find { it.layoutId == "15" }
                ?: error("not found")
            val daySevenMeasurable = measurables.find { it.layoutId == "16" }
                ?: error("not found")
            val dayEightMeasurable = measurables.find { it.layoutId == "17" }
                ?: error("not found")
            val dayNineMeasurable = measurables.find { it.layoutId == "18" }
                ?: error("not found")
            val dayTenMeasurable = measurables.find { it.layoutId == "19" }
                ?: error("not found")
            val dayElevenMeasurable = measurables.find { it.layoutId == "20" }
                ?: error("not found")
            val dayTwelveMeasurable = measurables.find { it.layoutId == "21" }
                ?: error("not found")
            val dayThirteenMeasurable = measurables.find { it.layoutId == "22" }
                ?: error("not found")
            val dayFourteenMeasurable = measurables.find { it.layoutId == "23" }
                ?: error("not found")
            val dayFifteenMeasurable = measurables.find { it.layoutId == "24" }
                ?: error("not found")
            val daySixteenMeasurable = measurables.find { it.layoutId == "25" }
                ?: error("not found")
            val daySeventeenMeasurable = measurables.find { it.layoutId == "26" }
                ?: error("not found")
            val dayEighteenMeasurable = measurables.find { it.layoutId == "27" }
                ?: error("not found")
            val dayNineteenMeasurable = measurables.find { it.layoutId == "28" }
                ?: error("not found")
            val dayTwentyMeasurable = measurables.find { it.layoutId == "29" }
                ?: error("not found")
            val dayTwentyOneMeasurable = measurables.find { it.layoutId == "30" }
                ?: error("not found")
            val dayTwentyTwoMeasurable = measurables.find { it.layoutId == "31" }
                ?: error("not found")
            val dayTwentyThreeMeasurable = measurables.find { it.layoutId == "32" }
                ?: error("not found")
            val dayTwentyFourMeasurable = measurables.find { it.layoutId == "33" }
                ?: error("not found")
            val dayTwentyFiveMeasurable = measurables.find { it.layoutId == "34" }
                ?: error("not found")
            val dayTwentySixMeasurable = measurables.find { it.layoutId == "35" }
                ?: error("not found")
            val dayTwentySevenMeasurable = measurables.find { it.layoutId == "36" }
                ?: error("not found")
            val dayTwentyEightMeasurable = measurables.find { it.layoutId == "37" }
                ?: error("not found")
            val dayTwentyNineMeasurable = measurables.find { it.layoutId == "38" }
                ?: error("not found")
            val dayThirtyMeasurable = measurables.find { it.layoutId == "39" }
                ?: error("not found")
            val dayThirtyOneMeasurable = measurables.find { it.layoutId == "40" }
                ?: error("not found")

            val mondayConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val tuesdayConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val wednesdayConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val thursdayConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val fridayConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val saturdayConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val sundayConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )

            val dayOneConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwoConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayThreeConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayFourConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayFiveConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val daySixConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val daySevenConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayEightConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayNineConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTenConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayElevenConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwelveConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayThirteenConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayFourteenConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayFifteenConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val daySixteenConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val daySeventeenConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayEighteenConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayNineteenConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwentyConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwentyOneConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwentyTwoConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwentyThreeConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwentyFourConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwentyFiveConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwentySixConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwentySevenConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwentyEightConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayTwentyNineConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayThirtyConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )
            val dayThirtyOneConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )

            val mondayPlaceable = mondayMeasurable.measure(mondayConstraints)
            val tuesdayPlaceable = tuesdayMeasurable.measure(tuesdayConstraints)
            val wednesdayPlaceable = wednesMeasurable.measure(wednesdayConstraints)
            val thursdayPlaceable = thursdayMeasurable.measure(thursdayConstraints)
            val fridayPlaceable = fridayMeasurable.measure(fridayConstraints)
            val saturdayPlaceable = saturdayMeasurable.measure(saturdayConstraints)
            val sundayPlaceable = sundayMeasurable.measure(sundayConstraints)

            val dayOnePlaceable = dayOneMeasurable.measure(dayOneConstraints)
            val dayTwoPlaceable = dayTwoMeasurable.measure(dayTwoConstraints)
            val dayThreePlaceable = dayThreeMeasurable.measure(dayThreeConstraints)
            val dayFourPlaceable = dayFourMeasurable.measure(dayFourConstraints)
            val dayFivePlaceable = dayFiveMeasurable.measure(dayFiveConstraints)
            val daySixPlaceable = daySixMeasurable.measure(daySixConstraints)
            val daySevenPlaceable = daySevenMeasurable.measure(daySevenConstraints)
            val dayEightPlaceable = dayEightMeasurable.measure(dayEightConstraints)
            val dayNinePlaceable = dayNineMeasurable.measure(dayNineConstraints)
            val dayTenPlaceable = dayTenMeasurable.measure(dayTenConstraints)
            val dayElevenPlaceable = dayElevenMeasurable.measure(dayElevenConstraints)
            val dayTwelvePlaceable = dayTwelveMeasurable.measure(dayTwelveConstraints)
            val dayThirteenPlaceable = dayThirteenMeasurable.measure(dayThirteenConstraints)
            val dayFourteenPlaceable = dayFourteenMeasurable.measure(dayFourteenConstraints)
            val dayFifteenPlaceable = dayFifteenMeasurable.measure(dayFifteenConstraints)
            val daySixteenPlaceable = daySixteenMeasurable.measure(daySixteenConstraints)
            val daySeventeenPlaceable = daySeventeenMeasurable.measure(daySeventeenConstraints)
            val dayEighteenPlaceable = dayEighteenMeasurable.measure(dayEighteenConstraints)
            val dayNineteenPlaceable = dayNineteenMeasurable.measure(dayNineteenConstraints)
            val dayTwentyPlaceable = dayTwentyMeasurable.measure(dayTwentyConstraints)
            val dayTwentyOnePlaceable = dayTwentyOneMeasurable.measure(dayTwentyOneConstraints)
            val dayTwentyTwoPlaceable = dayTwentyTwoMeasurable.measure(dayTwentyTwoConstraints)
            val dayTwentyThreePlaceable = dayTwentyThreeMeasurable.measure(dayTwentyThreeConstraints)
            val dayTwentyFourPlaceable = dayTwentyFourMeasurable.measure(dayTwentyFourConstraints)
            val dayTwentyFivePlaceable = dayTwentyFiveMeasurable.measure(dayTwentyFiveConstraints)
            val dayTwentySixPlaceable = dayTwentySixMeasurable.measure(dayTwentySixConstraints)
            val dayTwentySevenPlaceable = dayTwentySevenMeasurable.measure(dayTwentySevenConstraints)
            val dayTwentyEightPlaceable = dayTwentyEightMeasurable.measure(dayTwentyEightConstraints)
            val dayTwentyNinePlaceable = dayTwentyNineMeasurable.measure(dayTwentyNineConstraints)
            val dayThirtyPlaceable = dayThirtyMeasurable.measure(dayThirtyConstraints)
            val dayThirtyOnePlaceable = dayThirtyOneMeasurable.measure(dayThirtyOneConstraints)

            val width = constraints.maxWidth
            val height = maxOf(
                mondayPlaceable.height,
                tuesdayPlaceable.height,
                wednesdayPlaceable.height,
                thursdayPlaceable.height,
                fridayPlaceable.height,
                saturdayPlaceable.height,
                sundayPlaceable.height
            )

            // val dayWidth = constraints.maxWidth
            // val dayHeight = dayOnePlaceable.height

            layout(width, height) {

                val sundayOffset = IntOffset(30, 0)
                val mondayOffsets = IntOffset(189, 0)
                val tuesdayOffsets = IntOffset(344, 0)
                val wednesdayOffsets = IntOffset(500, 0)
                val thursdayOffsets = IntOffset(656, 0)
                val fridayOffsets = IntOffset(805, 0)
                val saturdayOffsets = IntOffset(955, 0)

                sundayPlaceable.place(sundayOffset)
                mondayPlaceable.place(mondayOffsets)
                tuesdayPlaceable.place(tuesdayOffsets)
                wednesdayPlaceable.place(wednesdayOffsets)
                thursdayPlaceable.place(thursdayOffsets)
                fridayPlaceable.place(fridayOffsets)
                saturdayPlaceable.place(saturdayOffsets)

                val dayOneOffset = IntOffset(35, 100)
                val dayTwoOffset = IntOffset(197, 100)
                val dayThreeOffset = IntOffset(351, 100)
                val dayFourOffset = IntOffset(508, 100)
                val dayFiveOffset = IntOffset(662, 100)
                val daySixOffset = IntOffset(812, 100)
                val daySevenOffset = IntOffset(965, 100)
                val dayEightOffset = IntOffset(35, 430)
                val dayNineOffset = IntOffset(197, 430)
                val dayTenOffset = IntOffset(351, 430)
                val dayElevenOffset = IntOffset(508, 430)
                val dayTwelveOffset = IntOffset(662, 430)
                val dayThirteenOffset = IntOffset(812, 430)
                val dayFourteenOffset = IntOffset(965, 430)
                val dayFifteenOffset = IntOffset(35, 760)
                val daySixteenOffset = IntOffset(197, 760)
                val daySeventeenOffset = IntOffset(351, 760)
                val dayEighteenOffset = IntOffset(508, 760)
                val dayNineteenOffset = IntOffset(662, 760)
                val dayTwentyOffset = IntOffset(812, 760)
                val dayTwentyOneOffset = IntOffset(965, 760)
                val dayTwentyTwoOffset = IntOffset(35, 1090)
                val dayTwentyThreeOffset = IntOffset(197, 1090)
                val dayTwentyFourOffset = IntOffset(351, 1090)
                val dayTwentyFiveOffset = IntOffset(508, 1090)
                val dayTwentySixOffset = IntOffset(662, 1090)
                val dayTwentySevenOffset = IntOffset(812, 1090)
                val dayTwentyEightOffset = IntOffset(965, 1090)
                val dayTwentyNineOffset = IntOffset(35, 1410)
                val dayThirtyOffset = IntOffset(197, 1410)
                val dayThirtyOneOffset = IntOffset(351, 1410)

                dayOnePlaceable.place(dayOneOffset)
                dayTwoPlaceable.place(dayTwoOffset)
                dayThreePlaceable.place(dayThreeOffset)
                dayFourPlaceable.place(dayFourOffset)
                dayFivePlaceable.place(dayFiveOffset)
                daySixPlaceable.place(daySixOffset)
                daySevenPlaceable.place(daySevenOffset)
                dayEightPlaceable.place(dayEightOffset)
                dayNinePlaceable.place(dayNineOffset)
                dayTenPlaceable.place(dayTenOffset)
                dayElevenPlaceable.place(dayElevenOffset)
                dayTwelvePlaceable.place(dayTwelveOffset)
                dayThirteenPlaceable.place(dayThirteenOffset)
                dayFourteenPlaceable.place(dayFourteenOffset)
                dayFifteenPlaceable.place(dayFifteenOffset)
                daySixteenPlaceable.place(daySixteenOffset)
                daySeventeenPlaceable.place(daySeventeenOffset)
                dayEighteenPlaceable.place(dayEighteenOffset)
                dayNineteenPlaceable.place(dayNineteenOffset)
                dayTwentyPlaceable.place(dayTwentyOffset)
                dayTwentyOnePlaceable.place(dayTwentyOneOffset)
                dayTwentyTwoPlaceable.place(dayTwentyTwoOffset)
                dayTwentyThreePlaceable.place(dayTwentyThreeOffset)
                dayTwentyFourPlaceable.place(dayTwentyFourOffset)
                dayTwentyFivePlaceable.place(dayTwentyFiveOffset)
                dayTwentySixPlaceable.place(dayTwentySixOffset)
                dayTwentySevenPlaceable.place(dayTwentySevenOffset)
                dayTwentyEightPlaceable.place(dayTwentyEightOffset)
                dayTwentyNinePlaceable.place(dayTwentyNineOffset)
                dayThirtyPlaceable.place(dayThirtyOffset)
                dayThirtyOnePlaceable.place(dayThirtyOneOffset)
            }
        }
    )
}