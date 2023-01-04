package com.example.androidestudy.feature.todoapp.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.androidestudy.R
import com.example.androidestudy.feature.util.Screen

@Composable
fun BottomBar(
    navController: NavController
) {
    val screens = listOf(
        BottomNavBarModel(
            screen = Screen.HomeScreen.route,
            icon = R.drawable.ic_dashboard
        ),
        BottomNavBarModel(
            screen = Screen.TodoListScreen.route + "?priority={priority}",
            icon = R.drawable.ic_search
        ),
        BottomNavBarModel(
            screen = Screen.DailyScheduleScreen.route,
            icon = R.drawable.ic_schedule
        ),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomNavigation(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxHeight(0.09f)
                .fillMaxWidth(0.5f),
            backgroundColor = Color.Gray
        ) {
            screens.forEach { navModel ->
                BottomNavItem(
                    navBarModel = navModel,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}