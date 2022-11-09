package com.example.androidestudy.feature.retrofit.presentation.component

import androidx.compose.foundation.border
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
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidestudy.feature.auth.presentation.component.MaxLengthErrorTransformation
import com.example.androidestudy.feature.retrofit.presentation.postscreen.component.StandardHeader

@Composable
fun StandardTitleTextField(
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
                .fillMaxWidth()
                .border(2.dp, Color.Black),
            value = text,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            },
            visualTransformation = MaxLengthErrorTransformation(maxLength),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            singleLine = isSingleLine,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp)
                .padding(top = 13.dp)
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
fun ShowStandardTitleTextField() {
    StandardTitleTextField(
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

        StandardHeader()

        Spacer(modifier = Modifier.height(25.dp))

        StandardTitleTextField(
            hint = "",
            text = "text",
            maxLength = 10,
            isSingleLine = true,
            keyboardType = KeyboardType.Text,
            onValueChange = {

            }
        )

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
}