package com.example.androidestudy.feature.main_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.androidestudy.feature.auth.presentation.complete.CompletedScreen
import com.example.androidestudy.feature.auth.presentation.login.LoginScreen
import com.example.androidestudy.feature.auth.presentation.sign_in.SignInScreen
import com.example.androidestudy.feature.notification.presentation.NotificationScreen
import com.example.androidestudy.feature.notification.presentation.detail.NotificationDetailScreen
import com.example.androidestudy.feature.presentation.goal.GoalScreen
import com.example.androidestudy.feature.presentation.preferencesdatastore.PreferencesDataStoreScreen
import com.example.androidestudy.feature.util.Screen
import com.example.androidestudy.feature.presentation.spalsh.SplashScreen
import com.example.androidestudy.feature.retrofit.presentation.postlist.PostListScreen
import com.example.androidestudy.feature.retrofit.presentation.postscreen.PostScreen
import com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.PostUpdateScreen
import com.example.androidestudy.feature.todoapp.component.BottomBar
import com.example.androidestudy.feature.todoapp.presentation.home.HomeScreen
import com.example.androidestudy.feature.todoapp.presentation.onboarding.OnBoardingScreen
import com.example.androidestudy.feature.todoapp.presentation.splash.TodoAppSplashScreen
import com.example.androidestudy.feature.todoapp.presentation.splash.error.SplashErrorScreen
import com.example.androidestudy.feature.util.MY_ARG
import com.example.androidestudy.feature.util.MY_URI
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

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                //  エントリの追跡、スタックの前進、バックスタック操作の有効化、デスティネーション状態間のナビゲーション
                Screen(
                    showBottomNavBar = navBackStackEntry?.destination?.route in listOf(
                        Screen.HomeScreen.route,
                        Screen.TodoListScreen.route,
                        Screen.DailyScheduleScreen.route
                    ),
                    navController = navController
                )

                //  NavController をナビゲーション グラフ（NavGraph）にリンクする
                // NavHost(
                //     navController = navController,
                //     startDestination = Screen.MainScreen.route
                // ) {
                //     // Preferences DataStore
                //     composable(route = Screen.MainScreen.route) {
                //         MainScreen(
                //             navController = navController
                //         )
                //     }
                //     composable(route = Screen.SplashScreen.route) {
                //         SplashScreen(navController = navController)
                //     }
                //     composable(route = Screen.PreferencesDataStore.route) {
                //         PreferencesDataStoreScreen(
                //             navController = navController,
                //             onNavigate = {
                //                 navController.navigate(Screen.GoalScreen.route)
                //             }
                //         )
                //     }
                //     composable(route = Screen.GoalScreen.route) {
                //         GoalScreen()
                //     }
                //
                //     // Firebase Authentication
                //     composable(route = Screen.SignInScreen.route) {
                //         SignInScreen(
                //             navController = navController
                //         )
                //     }
                //     composable(route = Screen.LoginScreen.route) {
                //         LoginScreen(
                //             navController = navController
                //         )
                //     }
                //     composable(route = Screen.CompletedScreen.route) {
                //         CompletedScreen()
                //     }
                //
                //     // Retrofit
                //     composable(route = Screen.PostListScreen.route) {
                //         PostListScreen(navController = navController)
                //     }
                //     composable(
                //         route = Screen.PostUpdateScreen.route +
                //             "?id={id}",
                //         arguments = listOf(
                //             navArgument(
                //                 name = "id"
                //             ) {
                //                 type = NavType.IntType
                //                 defaultValue = -1
                //             }
                //         )
                //     ) {
                //         PostUpdateScreen(
                //             navController = navController
                //         )
                //     }
                //     composable(route = Screen.PostScreen.route) {
                //         PostScreen(
                //             navController = navController
                //         )
                //     }
                //
                //     // Notification
                //     composable(route = Screen.SelectNotification.route) {
                //         NotificationScreen(navController = navController)
                //     }
                //
                //     composable(
                //         route = Screen.NotificationDetailScreen.route,
                //         arguments = listOf(navArgument(MY_ARG) { type = NavType.StringType }),
                //         deepLinks = listOf(navDeepLink { uriPattern = "$MY_URI/$MY_ARG={$MY_ARG}" })
                //     ) {
                //         val arguments = it.arguments
                //         arguments?.getString(MY_ARG)?.let {
                //             NotificationDetailScreen(message = it)
                //         }
                //     }
                // }
            }
        }
    }
}

@Composable
fun Screen(
    showBottomNavBar: Boolean = true,
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (showBottomNavBar) {
                BottomBar(navController = navController)
            }
        }
    ) {
        TodoScreen(
            navController = navController
        )
    }
}

@Composable
fun TodoScreen(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        // TodoApp
        composable(route = Screen.TodoAppSplashScreen.route) {
            TodoAppSplashScreen(navController = navController)
        }
        composable(route = Screen.SplashErrorScreen.route) {
            SplashErrorScreen(navController = navController)
        }
        composable(route = Screen.OnBoardingScreen.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.TodoListScreen.route) {

        }
        composable(route = Screen.DailyScheduleScreen.route) {

        }
    }
}