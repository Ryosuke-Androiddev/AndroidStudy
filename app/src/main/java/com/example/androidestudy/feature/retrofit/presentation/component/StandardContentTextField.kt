package com.example.androidestudy.feature.retrofit.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StandardContentTextField(
    hint: String,
    text: String,
    maxLength: Int,
    errorText: String? = null,
    isSingleLine: Boolean = false,
    textStyle: TextStyle,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
                .padding(12.dp)
                .border(width = 2.dp, color = Color.Black)
        ) {

            BasicTextField(
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxWidth(),
                value = text,
                onValueChange = onValueChange,
                singleLine = isSingleLine,
                textStyle = textStyle
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 13.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (errorText != null) {
                Text(
                    text = errorText,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
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
        hint = "",
        text = "text",
        maxLength = 10,
        textStyle = MaterialTheme.typography.h5,
        onValueChange = {

        }
    )
}