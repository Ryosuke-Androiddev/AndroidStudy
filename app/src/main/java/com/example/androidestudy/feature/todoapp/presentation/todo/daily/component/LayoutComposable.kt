package com.example.androidestudy.feature.todoapp.presentation.todo.daily.component

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize

@Composable
fun LearnLayout() {
    Layout(
        content = {
            Text(
                modifier = Modifier.layoutId("title"),
                text = "Title Title Title Title Title Title Title Title",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            TextButton(
                modifier = Modifier.layoutId("button"),
                onClick = { /* ... */ }
            ) {
                Text(text = "Button")
            }
        },
        measurePolicy = { measurables, constraints ->
            // contentスロットに入れたやつをMeasurableなリストで管理する
            val titleMeasurable = measurables.find { it.layoutId == "title" }
                ?: error("title not found")
            val buttonMeasurable = measurables.find { it.layoutId == "button" }
                ?: error("button not found")

            val buttonConstraints = constraints.copy(
                minHeight = 0,
                minWidth = 0
            )

            val buttonPlaceable = buttonMeasurable.measure(buttonConstraints)

            val titleConstraints = constraints.copy(
                maxWidth = maxOf(constraints.maxWidth - buttonPlaceable.width * 2, 0),
                minHeight = 0,
                minWidth = 0
            )

            val titlePlaceable = titleMeasurable.measure(titleConstraints)

            val width = constraints.maxWidth
            val height = maxOf(
                constraints.minHeight,
                titlePlaceable.height,
                buttonPlaceable.height
            )

            layout(width = width, height = height) {
                val space = IntSize(width, height)
                val titleSize = IntSize(titlePlaceable.width, titlePlaceable.height)
                val buttonSize = IntSize(buttonPlaceable.width, buttonPlaceable.height)
                val titleOffset = Alignment.Center.align(titleSize, space, layoutDirection)
                val buttonOffset = Alignment.CenterEnd.align(buttonSize, space, layoutDirection)

                titlePlaceable.place(titleOffset)
                buttonPlaceable.place(buttonOffset)
            }
        }
    )
}