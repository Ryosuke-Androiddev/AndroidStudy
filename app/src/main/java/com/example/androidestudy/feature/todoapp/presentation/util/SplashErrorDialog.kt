package com.example.androidestudy.feature.todoapp.presentation.util

import android.app.Activity
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.util.Screen

@Composable
fun SplashErrorDialog(
    navController: NavController
) {
    val activity = LocalContext.current as Activity
    AlertDialog(
        onDismissRequest = {
            // 画面の外等をクリックした時に処理
            navController.navigate(Screen.TodoAppSplashScreen.route)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    navController.navigate(Screen.TodoAppSplashScreen.route)
                }
            ) {
                Text(text = stringResource(id = R.string.reload))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(id = R.string.finish))
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.unexpected_error),
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(text = stringResource(id = R.string.explain_dialog))
        }
    )
}