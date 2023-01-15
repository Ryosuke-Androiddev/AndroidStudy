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

            val mondayPlaceable = mondayMeasurable.measure(mondayConstraints)
            val tuesdayPlaceable = tuesdayMeasurable.measure(tuesdayConstraints)
            val wednesdayPlaceable = wednesMeasurable.measure(wednesdayConstraints)
            val thursdayPlaceable = thursdayMeasurable.measure(thursdayConstraints)
            val fridayPlaceable = fridayMeasurable.measure(fridayConstraints)
            val saturdayPlaceable = saturdayMeasurable.measure(saturdayConstraints)
            val sundayPlaceable = sundayMeasurable.measure(sundayConstraints)

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

            layout(width, height) {
                val space = IntSize(width, height)
                val mondaySize = IntSize(mondayPlaceable.width, mondayPlaceable.height)
                val tuesdaySize = IntSize(tuesdayPlaceable.width, tuesdayPlaceable.height)
                val wednesdaySize = IntSize(wednesdayPlaceable.width, wednesdayPlaceable.height)
                val thursdaySize = IntSize(thursdayPlaceable.width, thursdayPlaceable.height)
                val fridaySize = IntSize(fridayPlaceable.width, fridayPlaceable.height)
                val saturdaySize = IntSize(saturdayPlaceable.width, saturdayPlaceable.height)
                val sundaySize = IntSize(sundayPlaceable.width, sundayPlaceable.height)

                // val mondayOffset = Alignment.Center.align(mondaySize, space, layoutDirection)
                // val tuesdayOffset = Alignment.Center.align(tuesdaySize, space, layoutDirection)
                // val wednesdayOffset = Alignment.Center.align(wednesdaySize, space, layoutDirection)
                // val thursdayOffset = Alignment.Center.align(thursdaySize, space, layoutDirection)
                // val fridayOffset = Alignment.Center.align(fridaySize, space, layoutDirection)
                // val saturdayOffset = Alignment.Center.align(saturdaySize, space, layoutDirection)
                // val sundayOffset = Alignment.Center.align(sundaySize, space, layoutDirection)

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

                // mondayPlaceable.place(mondayOffset)
                // tuesdayPlaceable.place(tuesdayOffset)
                // wednesdayPlaceable.place(wednesdayOffset)
                // thursdayPlaceable.place(thursdayOffset)
                // fridayPlaceable.place(fridayOffset)
                // saturdayPlaceable.place(saturdayOffset)
                // sundayPlaceable.place(sundayOffset)
            }
        }
    )
}