package com.example.androidestudy.feature.retrofit.presentation.component

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StandardContentTextField(
    hint: String,
    isHintVisible: Boolean = true,
    text: String,
    maxLength: Int,
    errorText: String? = null,
    isSingleLine: Boolean = false,
    textStyle: TextStyle,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(12.dp)
                .border(width = 2.dp, color = Color.Black)
        ) {
            BasicTextField(
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxWidth()
                    .onFocusChanged {
                        onFocusChange(it)
                    },
                value = text,
                onValueChange = onValueChange,
                singleLine = isSingleLine,
                textStyle = textStyle
            )
            if (isHintVisible) {
                Text(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth(),
                    text = hint,
                    style = textStyle,
                    color = Color.Black
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 13.dp)
        ) {
            if (errorText != null) {
                Text(
                    modifier = Modifier
                        .weight(3f),
                    text = errorText,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.End,
                text = "${text.length} / $maxLength",
                color = if (text.length > maxLength) Color.Red else Color.Black
            )
        }
    }
}

@Preview
@Composable
fun ShowStandardContentTextField() {
    StandardContentTextField(
        hint = "placeholder",
        text = "",
        maxLength = 10,
        textStyle = MaterialTheme.typography.body2,
        onValueChange = {

        },
        onFocusChange = {

        }
    )
}