package com.example.androidestudy.feature.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.presentation.main.component.ExplainButton
import com.example.androidestudy.feature.util.Screen
import com.example.androidestudy.ui.theme.MAIN_SCREEN_BUTTON_WIDTH
import com.example.androidestudy.ui.theme.MEDIUM_PADDING

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
            modifier = Modifier.width(MAIN_SCREEN_BUTTON_WIDTH),
            explain = stringResource(id = R.string.preferences_datastore),
            onClick = {
                navController.navigate(Screen.SplashScreen.route)
            }
        )

        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        
        // Firebase Authentication
        ExplainButton(
            modifier = Modifier.width(MAIN_SCREEN_BUTTON_WIDTH),
            explain = stringResource(id = R.string.authentication),
            onClick = {
                navController.navigate(Screen.SignInScreen.route)
            }
        )
    }
}