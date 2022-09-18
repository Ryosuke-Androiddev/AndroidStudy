package com.example.androidestudy.feature.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.presentation.main.component.ExplainButton
import com.example.androidestudy.feature.util.Screen
import com.example.androidestudy.ui.theme.MEDIUM_PADDING
import com.example.androidestudy.ui.theme.SMALL_PADDING

@Composable
fun MainScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Preferences DataStore
        ExplainButton(
            explain = stringResource(id = R.string.preferences_datastore),
            onClick = {
                navController.navigate(Screen.SplashScreen.route)
            }
        )

        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        
        // Firebase Authentication
        ExplainButton(
            explain = stringResource(id = R.string.authentication),
            onClick = {
                navController.navigate(Screen.SignInScreen.route)
            }
        )
    }
}