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
import androidx.compose.ui.unit.dp
import com.example.androidestudy.R

@Composable
fun OnBoardingComponent(
    @DrawableRes
    image: Int,
    title: String,
    contentDescription: String
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
            painter = painterResource(id = image),
            contentDescription = stringResource(id = R.string.on_boarding_description)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h4.fontSize
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .padding(top = 10.dp),
            text = contentDescription,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
    }
}