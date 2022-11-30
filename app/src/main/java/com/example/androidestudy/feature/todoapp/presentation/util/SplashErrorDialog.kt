package com.example.androidestudy.feature.todoapp.presentation.util

import android.app.Activity
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
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
                Text(text = "Reload")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = "Finish")
            }
        },
        title = {
            Text(
                text = "予想外のエラー",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(text = "アプリを再呼び込みする場合は、Reloadボタンを押してください。アプリを終了する場合は、Finishボタンを押してください")
        }
    )
}