package com.example.androidestudy.feature.auth.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.androidestudy.R
import com.example.androidestudy.ui.theme.MEDIUM_PADDING
import com.example.androidestudy.ui.theme.SMALL_PADDING

@Composable
fun StandardPasswordTextField(
    imageDescription: String,
    hint: String,
    text: String,
    maxLen: Int,
    keyboardType: KeyboardType,
    showText: Boolean = false,
    errorText: String?,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit
) {
    // labelがない時どうなるかを確認する
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = MEDIUM_PADDING),
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
            label = {
                Text(text = hint)
            },
            trailingIcon = {
                // onClickは、ViewModelごとにSealed Classの実装を投げて処理を行う
                // passwordの方はもう一回Composableを書いてもいいかも
                IconButton(onClick = onClick) {
                    if (showText) {
                        Icon(
                            painter = painterResource(id = R.drawable.show_password),
                            contentDescription = imageDescription
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.hide_password),
                            contentDescription = imageDescription
                        )
                    }
                }
            },
            visualTransformation = if (showText) {
                // ここはこの処理で変更が加わるかを確認する
                MaxLengthErrorTransformation(maxLen)
                // VisualTransformation.None
            } else {
                CustomPasswordVisualTransformation(maxLength = maxLen)
            },
            // imeActionの指定を省略すると、パスワードの文字として改行コードが入力可能
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            singleLine = true
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SMALL_PADDING)
        ) {
            if (errorText != null) {
                Text(
                    modifier = Modifier
                        .weight(2f),
                    text = errorText,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                modifier = Modifier
                    .weight(1f),
                text = "${text.length} / $maxLen",
                textAlign = TextAlign.End,
                color = if (text.length > maxLen) Color.Red else Color.Unspecified,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }
    }
}