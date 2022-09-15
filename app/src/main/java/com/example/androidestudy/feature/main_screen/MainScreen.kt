package com.example.androidestudy.feature.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.presentation.main.component.ExplainButton

@Composable
fun MainScreen(
    onNextClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ExplainButton(
            modifier = Modifier.align(Alignment.Center),
            explain = stringResource(id = R.string.preferences_datastore),
            onClick = onNextClick
        )
    }
}