package com.example.androidestudy.feature.presentation.preferencesdatastore.components

import androidx.annotation.DrawableRes
import com.example.androidestudy.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String
) {
    object FirstPage: OnBoardingPage(
        image = R.drawable.first_page,
        title = FIRST_PAGE
    )
    object SecondPage: OnBoardingPage(
        image = R.drawable.second_page,
        title = SECOND_PAGE
    )
    object ThirdPage: OnBoardingPage(
        image = R.drawable.third_page,
        title = THIRD_PAGE
    )

    companion object {

        // Page title
        private const val FIRST_PAGE = "First Page"
        private const val SECOND_PAGE = "Second Page"
        private const val THIRD_PAGE = "Third Page"
    }
}
