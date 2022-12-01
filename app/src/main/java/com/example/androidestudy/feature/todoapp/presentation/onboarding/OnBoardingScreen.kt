package com.example.androidestudy.feature.todoapp.presentation.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.todoapp.presentation.onboarding.component.BottomSection
import com.example.androidestudy.feature.todoapp.presentation.onboarding.component.OnBoardingContent
import com.example.androidestudy.feature.todoapp.presentation.onboarding.component.OnBoardingItem
import com.example.androidestudy.feature.todoapp.presentation.onboarding.viewmodel.OnBoardingViewModel
import com.example.androidestudy.feature.util.Screen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
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

        BottomSection(
            modifier = Modifier
                .weight(1f),
            pagerState = pagerState
        )

        AnimatedVisibility(
            modifier = Modifier
                .offset(y = (-50).dp)
                .fillMaxWidth(0.85f),
            visible = pagerState.currentPage == PAGER_LAST_PAGE
        ) {
            Button(
                onClick = {
                    navController.popBackStack()
                    // OnBoardingの最終ページでTrueに変更する
                    viewModel.saveOnBoardingState(isCompleted = true)
                    navController.navigate(Screen.HomeScreen.route)
                }
            ) {
                Text(text = stringResource(id = R.string.finish_button))
            }
        }
    }
}

private const val PAGER_LAST_PAGE = 2