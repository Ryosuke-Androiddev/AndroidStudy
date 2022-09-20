package com.example.androidestudy.feature.presentation.main.component

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ExplainButton(
    modifier: Modifier = Modifier,
    explain: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        // styleに渡すタイポによって文字のサイズが変わるんや
        Text(
            text = explain,
            style = MaterialTheme.typography.h6
        )
    }
}