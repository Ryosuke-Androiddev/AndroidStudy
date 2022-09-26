package com.example.androidestudy.feature.auth.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.androidestudy.ui.theme.MEDIUM_PADDING
import com.example.androidestudy.ui.theme.SMALL_PADDING

// text, onValueChangeをホイスティング
@Composable
fun StandardTextField(
    @DrawableRes
    imageRes: Int,
    imageDescription: String,
    hint: String,
    text: String,
    maxLen: Int,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit
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
                Icon(
                    painter = painterResource(id = imageRes),
                    contentDescription = imageDescription
                )
            },
            visualTransformation = MaxLengthErrorTransformation(maxLen),
            // imeActionの指定を省略すると、パスワードの文字として改行コードが入力可能
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            singleLine = true
        )
        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING)
                .align(Alignment.End),
            text = "${text.length} / $maxLen",
            textAlign = TextAlign.End,
            color = if (text.length > maxLen) Color.Red else Color.Unspecified,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}