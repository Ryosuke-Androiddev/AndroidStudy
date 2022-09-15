package com.example.androidestudy.feature.main_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidestudy.feature.presentation.goal.GoalScreen
import com.example.androidestudy.feature.presentation.main.MainScreen
import com.example.androidestudy.feature.presentation.preferencesdatastore.PreferencesDataStoreScreen
import com.example.androidestudy.feature.util.Screen
import com.example.androidestudy.feature.presentation.spalsh.SplashScreen
import com.example.androidestudy.ui.theme.AndroideStudyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
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
                                navController.navigate(Screen.SplashScreen.route)
                            }
                        )
                    }
                    composable(route = Screen.SplashScreen.route) {
                        SplashScreen(navController = navController)
                    }
                    composable(route = Screen.PreferencesDataStore.route) {
                        PreferencesDataStoreScreen(
                            navController = navController,
                            onNavigate = {
                                navController.navigate(Screen.GoalScreen.route)
                            }
                        )
                    }
                    composable(route = Screen.GoalScreen.route) {
                        GoalScreen()
                    }
                }
            }
        }
    }
}