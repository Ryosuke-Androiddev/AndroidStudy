package com.example.androidestudy.feature.retrofit.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidestudy.feature.auth.presentation.component.MaxLengthErrorTransformation

@Composable
fun StandardTextField(
    hint: String,
    text: String,
    maxLength: Int,
    errorText: String? = null,
    isSingleLine: Boolean,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = hint,
                    fontSize = MaterialTheme.typography.h6.fontSize
                )
            },
            visualTransformation = MaxLengthErrorTransformation(maxLength),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            singleLine = isSingleLine
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp, vertical = 4.dp),
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
fun ShowStandardTitleTextField() {
    StandardTextField(
        hint = "",
        text = "text",
        maxLength = 10,
        errorText = "please input 10 more character",
        isSingleLine = true,
        keyboardType = KeyboardType.Text,
        onValueChange = {

        }
    )
}

@Preview
@Composable
fun ShowStandardContentTextField() {
    StandardTextField(
        hint = "",
        text = "text",
        maxLength = 10,
        errorText = "please input 10 more character",
        isSingleLine = true,
        keyboardType = KeyboardType.Text,
        onValueChange = {

        }
    )
}

@Preview
@Composable
fun ShowPostScreen() {
    Column {

        Spacer(modifier = Modifier.height(50.dp))

        StandardTextField(
            hint = "",
            text = "text",
            maxLength = 10,
            errorText = "please input 10 more character",
            isSingleLine = true,
            keyboardType = KeyboardType.Text,
            onValueChange = {

            }
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}