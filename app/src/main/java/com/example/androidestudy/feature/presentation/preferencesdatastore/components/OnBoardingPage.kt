package com.example.androidestudy.feature.presentation.preferencesdatastore.components

import androidx.annotation.DrawableRes
import com.example.androidestudy.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val contentDescription: String
) {
    object FirstPage: OnBoardingPage(
        image = R.drawable.first_page,
        title = FIRST_PAGE,
        contentDescription = FIRST_PAGE_DESCRIPTION
    )
    object SecondPage: OnBoardingPage(
        image = R.drawable.second_page,
        title = SECOND_PAGE,
        contentDescription = SECOND_PAGE_DESCRIPTION
    )
    object ThirdPage: OnBoardingPage(
        image = R.drawable.third_page,
        title = THIRD_PAGE,
        contentDescription = THIRD_PAGE_DESCRIPTION
    )

    companion object {

        // Page title
        private const val FIRST_PAGE = "First Page"
        private const val SECOND_PAGE = "Second Page"
        private const val THIRD_PAGE = "Third Page"

        // Page description
        private const val FIRST_PAGE_DESCRIPTION = "Welcome to First Page, this is a page one third please go ahead next page"
        private const val SECOND_PAGE_DESCRIPTION = "Welcome to Second Page, this is a page second third please go ahead next page"
        private const val THIRD_PAGE_DESCRIPTION = "Welcome to Last Page, this is a page one third please click Button"
    }
}
