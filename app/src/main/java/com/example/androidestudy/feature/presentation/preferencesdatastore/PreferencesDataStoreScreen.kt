package com.example.androidestudy.feature.presentation.preferencesdatastore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.androidestudy.feature.presentation.preferencesdatastore.components.OnBoardingComponent
import com.example.androidestudy.feature.presentation.preferencesdatastore.components.OnBoardingPage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun PreferencesDataStoreScreen() {
    
    val pages = listOf(
        OnBoardingPage.FirstPage,
        OnBoardingPage.SecondPage,
        OnBoardingPage.ThirdPage
    )
    
    val pagerState = rememberPagerState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            OnBoardingComponent(onBoardingPage = pages[page])
        }
    }
}