package com.example.androidestudy.feature.presentation.preferencesdatastore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidestudy.feature.presentation.preferencesdatastore.components.FinishButton
import com.example.androidestudy.feature.presentation.preferencesdatastore.components.OnBoardingComponent
import com.example.androidestudy.feature.presentation.preferencesdatastore.components.OnBoardingPage
import com.example.androidestudy.feature.presentation.preferencesdatastore.viewmodel.OnBoardingViewModel
import com.example.androidestudy.ui.theme.PAGER_INDICATOR_SPACING
import com.example.androidestudy.ui.theme.PAGER_INDICATOR_WIDTH
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun PreferencesDataStoreScreen(
    navController: NavController,
    viewModel: OnBoardingViewModel = hiltViewModel(),
    onNavigate: () -> Unit
) {
    
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
            modifier = Modifier
                .weight(10f),
            count = pages.size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { page ->
            OnBoardingComponent(onBoardingPage = pages[page])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor = Color.Black,
            inactiveColor = Color.Gray,
            indicatorWidth = PAGER_INDICATOR_WIDTH,
            spacing = PAGER_INDICATOR_SPACING
        )
        FinishButton(
            modifier = Modifier
                .weight(1f),
            pagerState = pagerState,
            onClick = {
                navController.popBackStack()
                onNavigate()
                viewModel.saveOnBoardingStates(isCompleted = true)
            }
        )
    }
}