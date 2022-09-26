package com.example.androidestudy.feature.auth.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.example.androidestudy.R
import com.example.androidestudy.ui.theme.MEDIUM_PADDING
import com.example.androidestudy.ui.theme.TOO_SMALL_PADDING

@Composable
fun BottomAreaComponent(
    buttonTitle: String,
    haveAccount: String,
    operateTitle: String,
    onClick: () -> Unit,
    onNavigate: () -> Unit
) {
    Column {
        Button(
            modifier = Modifier
                .padding(MEDIUM_PADDING)
                .fillMaxWidth(),
            onClick = onClick
        ) {
            Text(
                text = buttonTitle,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.or)
        )

        Row(
            modifier = Modifier
                .padding(top = MEDIUM_PADDING)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = haveAccount
            )
            Spacer(modifier = Modifier.width(TOO_SMALL_PADDING))
            Text(
                modifier = Modifier.clickable {
                    // = でつなぐ以外は、ラムダをラムダないでメソッドのように呼び出す必要がある
                    onNavigate()
                },
                text = operateTitle,
                style = TextStyle(
                    textDecoration = TextDecoration.Underline
                ),
                fontWeight = FontWeight.Bold
            )
        }
    }
}