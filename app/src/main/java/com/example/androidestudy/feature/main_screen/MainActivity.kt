package com.example.androidestudy.feature.main_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidestudy.feature.auth.presentation.complete.CompletedScreen
import com.example.androidestudy.feature.auth.presentation.login.LoginScreen
import com.example.androidestudy.feature.auth.presentation.sign_in.SignInScreen
import com.example.androidestudy.feature.presentation.goal.GoalScreen
import com.example.androidestudy.feature.presentation.preferencesdatastore.PreferencesDataStoreScreen
import com.example.androidestudy.feature.util.Screen
import com.example.androidestudy.feature.presentation.spalsh.SplashScreen
import com.example.androidestudy.feature.retrofit.presentation.postlist.PostListScreen
import com.example.androidestudy.feature.retrofit.presentation.postscreen.PostScreen
import com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.PostUpdateScreen
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
                    // Preferences DataStore
                    composable(route = Screen.MainScreen.route) {
                        MainScreen(
                            navController = navController
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

                    // Firebase Authentication
                    composable(route = Screen.SignInScreen.route) {
                        SignInScreen(
                            navController = navController
                        )
                    }
                    composable(route = Screen.LoginScreen.route) {
                        LoginScreen(
                            navController = navController
                        )
                    }
                    composable(route = Screen.CompletedScreen.route) {
                        CompletedScreen()
                    }

                    // Retrofit
                    composable(route = Screen.PostListScreen.route) {
                        PostListScreen(navController = navController)
                    }
                    composable(
                        route = Screen.PostUpdateScreen.route +
                            "?id={id}",
                        arguments = listOf(
                            navArgument(
                                name = "id"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        PostUpdateScreen(
                            navController = navController
                        )
                    }
                    composable(route = Screen.PostScreen.route) {
                        PostScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}