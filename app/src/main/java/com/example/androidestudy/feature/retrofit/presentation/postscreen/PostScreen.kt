package com.example.androidestudy.feature.retrofit.presentation.postscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidestudy.feature.retrofit.presentation.component.StandardContentTextField
import com.example.androidestudy.feature.retrofit.presentation.component.StandardHeader
import com.example.androidestudy.feature.retrofit.presentation.component.StandardTitleTextField
import com.example.androidestudy.feature.retrofit.presentation.postscreen.viewmodel.PostScreenViewModel

@Composable
fun PostScreen(
    viewModel: PostScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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