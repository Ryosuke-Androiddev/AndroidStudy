package com.example.androidestudy.feature.todoapp.component

import androidx.annotation.DrawableRes
import com.example.androidestudy.feature.util.Screen

data class BottomNavBarModel(
    val screen: String,
    @DrawableRes
    val icon: Int
)
