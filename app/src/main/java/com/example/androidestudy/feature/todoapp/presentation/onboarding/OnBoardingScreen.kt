package com.example.androidestudy.feature.todoapp.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.todoapp.presentation.onboarding.component.OnBoardingContent
import com.example.androidestudy.feature.todoapp.presentation.onboarding.component.OnBoardingItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val onBoardingContent = listOf(
        OnBoardingContent(
            title = stringResource(id = R.string.todo_list),
            resId = R.raw.todolist
        ),
        OnBoardingContent(
            title = stringResource(id = R.string.weather),
            resId = R.raw.weather
        ),
        OnBoardingContent(
            title = stringResource(id = R.string.schedule),
            resId = R.raw.schedule
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier
                .weight(3f),
            count = onBoardingContent.size,
            state = pagerState
        ) { position ->
            val title = onBoardingContent[position].title
            val resId = onBoardingContent[position].resId
            OnBoardingItem(
                title = title,
                resId = resId
            )
        }

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.8f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = MaterialTheme.colors.primary,
            )

            Row {
                if (pagerState.currentPage > 0) {
                    TextButton(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.next_button))
                    }
                }

                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                ) {
                    Text(text = stringResource(id = R.string.back_button))
                }
            }
        }
    }
}