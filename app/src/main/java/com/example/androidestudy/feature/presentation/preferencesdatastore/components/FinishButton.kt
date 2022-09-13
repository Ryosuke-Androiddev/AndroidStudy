package com.example.androidestudy.feature.presentation.preferencesdatastore.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.androidestudy.R
import com.example.androidestudy.feature.presentation.preferencesdatastore.components.OnBoardingPage.Companion.LAST_PAGE
import com.example.androidestudy.ui.theme.EXTRA_LARGE_PADDING
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    // modifierの伝播のさせ方を理解する
    Row(
        modifier = modifier
            .padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == LAST_PAGE
        ) {
            Button(
                onClick = onClick
            ) {
                Text(
                    text = stringResource(id = R.string.on_boarding_finish),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}