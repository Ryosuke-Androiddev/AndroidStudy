package com.example.androidestudy.feature.auth.presentation.component

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog

@Composable
fun StandardCircularProgressIndicator() {
    Dialog(
        onDismissRequest = {
            // ダイアログの外側をタップした時や、端末の戻る操作をした時に呼び出されるコールバック
        }
    ) {
        CircularProgressIndicator(
            color = Color.Green
        )
    }
}