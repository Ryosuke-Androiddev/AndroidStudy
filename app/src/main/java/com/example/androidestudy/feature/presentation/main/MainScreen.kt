package com.example.androidestudy.feature.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.androidestudy.R

@Composable
fun MainScreen(
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = onNextClick
        ) {
            Text(
                text = stringResource(id = R.string.preferences_datastore),
                style = MaterialTheme.typography.h1
            )
        }
    }
}