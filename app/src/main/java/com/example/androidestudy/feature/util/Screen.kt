package com.example.androidestudy.feature.util

// sealed class, 親クラスのプロパティを渡す意味ってこういうことか
sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")

    // Preferences DataStore
    object SplashScreen: Screen("splash_screen")
    object PreferencesDataStore: Screen("preferences_datastore")
    object GoalScreen: Screen("goal_screen")

    // Firebase Authentication
    object SignInScreen: Screen("sign_in_screen")
    object LoginScreen: Screen("login_screen")
    object CompletedScreen: Screen("completed_screen")

    // Retrofit
    object PostListScreen: Screen("post_list_screen")
    object PostUpdateScreen: Screen("post_edit_screen")
    object PostScreen: Screen("post_screen")

    // Notification
    object SelectNotification: Screen("simple_notification")
    object NotificationDetailScreen: Screen("details/{$MY_ARG}") {
        fun passArgument(message: String) = "details/$message"
    }

    // TodoApp
    object TodoAppSplashScreen: Screen("splash_screen")
    object OnBoardingScreen: Screen("on_boarding_screen")
    object HomeScreen: Screen("home_screen")
    object TodoListScreen: Screen("todo_list_screen")
    object DailyScheduleScreen: Screen("daily_schedule_screen")
}

const val MY_URI = "https://stevdza-san.com"
const val MY_ARG = "message"
