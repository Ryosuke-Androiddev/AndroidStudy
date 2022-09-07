package com.example.androidestudy.feature.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidestudy.feature.presentation.screen.Screen
import com.example.androidestudy.ui.theme.AndroideStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroideStudyTheme {
                //  エントリの追跡、スタックの前進、バックスタック操作の有効化、デスティネーション状態間のナビゲーション
                val navController = rememberNavController()

                //  NavController をナビゲーション グラフ（NavGraph）にリンクする
                NavHost(
                    navController = navController,
                    startDestination = Screen.MainScreen.route
                ) {
                    composable(route = Screen.MainScreen.route) {
                        MainScreen(
                            onNextClick = {
                                navController.navigate(Screen.PreferencesDataStore.route)
                            }
                        )
                    }
                    composable(route = Screen.PreferencesDataStore.route) {

                    }
                }
            }
        }
    }
}