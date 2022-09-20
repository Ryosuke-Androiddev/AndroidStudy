package com.example.androidestudy.feature.auth.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidestudy.R
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
    showText: Boolean = false,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // labelがない時どうなるかを確認する
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MEDIUM_PADDING),
            value = text,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = hint)
            },
            label = {
                Text(text = hint)
            },
            trailingIcon = {
                // onClickは、ViewModelごとにSealed Classの実装を投げて処理を行う
                // passwordの方はもう一回Composableを書いてもいいかも
                IconButton(onClick = onClick) {
                    Icon(
                        painter = painterResource(id = imageRes),
                        contentDescription = imageDescription
                    )
                }
            },
            visualTransformation = if (showText) VisualTransformation.None
            else PasswordVisualTransformation(),
            // imeActionの指定を省略すると、パスワードの文字として改行コードが入力可能
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            )
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MEDIUM_PADDING)
                .padding(top = SMALL_PADDING),
            text = "${text.length} / $maxLen",
            textAlign = TextAlign.End
        )
    }
}

@Preview
@Composable
fun PreviewStandardTextField() {

    StandardTextField(
        imageRes = R.drawable.show_password,
        imageDescription = "",
        hint = "password",
        text = "",
        maxLen = 10,
        keyboardType = KeyboardType.Password,
        onValueChange = {},
        onClick = { /*TODO*/ })
}