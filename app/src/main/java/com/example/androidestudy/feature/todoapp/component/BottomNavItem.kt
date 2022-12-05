package com.example.androidestudy.feature.todoapp.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun RowScope.BottomNavItem(
    navBarModel: BottomNavBarModel,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        icon = {
            Icon(
                modifier = Modifier
                    .size(30.dp),
                painter = painterResource(id = navBarModel.icon),
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == navBarModel.screen.route
        } == true,
        unselectedContentColor = Color.White.copy(alpha = 0.6f),
        onClick = {
            navController.navigate(navBarModel.screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}