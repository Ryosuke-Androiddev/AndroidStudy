package com.example.androidestudy.feature.presentation.preferencesdatastore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.androidestudy.R

@Composable
fun PreferencesDataStoreScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.preferences_datastore),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}