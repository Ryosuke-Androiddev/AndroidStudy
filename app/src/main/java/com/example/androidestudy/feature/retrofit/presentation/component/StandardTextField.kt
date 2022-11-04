package com.example.androidestudy.feature.retrofit.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            .padding(12.dp)
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
    }
}

@Preview
@Composable
fun ShowStandardTextField() {
    StandardTextField(
        hint = "",
        text = "text",
        maxLength = 20,
        isSingleLine = true,
        keyboardType = KeyboardType.Text,
        onValueChange = {

        }
    )
}