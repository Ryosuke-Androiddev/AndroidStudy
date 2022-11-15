package com.example.androidestudy.feature.notification.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidestudy.R
import com.example.androidestudy.feature.notification.presentation.viewmodel.NotificationViewModel
import com.example.androidestudy.feature.presentation.main.component.ExplainButton
import com.example.androidestudy.ui.theme.MAIN_SCREEN_BUTTON_WIDTH
import com.example.androidestudy.ui.theme.MEDIUM_PADDING

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ExplainButton(
            modifier = Modifier.width(MAIN_SCREEN_BUTTON_WIDTH),
            explain = stringResource(id = R.string.simple_notification),
            onClick = viewModel::displaySimpleNotification
        )

        Spacer(modifier = Modifier.height(MEDIUM_PADDING))

        // Retrofit
        ExplainButton(
            modifier = Modifier.width(MAIN_SCREEN_BUTTON_WIDTH),
            explain = stringResource(id = R.string.update_notification),
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(MEDIUM_PADDING))

        // Retrofit
        ExplainButton(
            modifier = Modifier.width(MAIN_SCREEN_BUTTON_WIDTH),
            explain = stringResource(id = R.string.cancel_notification),
            onClick = {

            }
        )
    }
}