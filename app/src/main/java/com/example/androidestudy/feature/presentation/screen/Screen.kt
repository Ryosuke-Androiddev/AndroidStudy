package com.example.androidestudy.feature.presentation.screen

// sealed class, 親クラスのプロパティを渡す意味ってこういうことか
sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object SplashScreen: Screen("splash_screen")
    object PreferencesDataStore: Screen("preferences_datastore")
    object GoalScreen: Screen("goal_screen")
}
