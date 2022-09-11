package com.example.androidestudy.feature.presentation.preferencesdatastore.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.androidestudy.R
import com.example.androidestudy.ui.theme.EXTRA_LARGE_PADDING
import com.example.androidestudy.ui.theme.SMALL_PADDING

@Composable
fun OnBoardingComponent(
    onBoardingPage: OnBoardingPage
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(id = R.string.on_boarding_description)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h4.fontSize
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.contentDescription,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
    }
}