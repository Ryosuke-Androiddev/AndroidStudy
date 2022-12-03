package com.example.androidestudy.feature.todoapp.presentation.home.component.weather

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidestudy.R

@Composable
fun DailyWeatherCardBottomSection(
    modifier: Modifier = Modifier,
    @DrawableRes
    detailWeatherImage: Int,
    contentDescription: String,
    detailWeatherTitle: String,
    amount: Float,
    @StringRes
    stringRes: Int
) {
    Column(
        modifier = modifier
            .padding(vertical = 2.dp, horizontal = 2.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(color = Color.Gray.copy(alpha = 0.7f))
    ) {
        Spacer(
            modifier = Modifier
                .height(25.dp)
        )

        Image(
            modifier = Modifier
                .padding(start = 28.dp)
                .size(20.dp),
            painter = painterResource(id = detailWeatherImage),
            contentDescription = contentDescription
        )
        Text(modifier = Modifier
            .padding(start = 30.dp),
            text = detailWeatherTitle,
            fontSize = 12.sp
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp),
            text = stringResource(id = stringRes, amount),
            fontSize = 12.sp
        )

        Spacer(
            modifier = Modifier
            .height(25.dp)
        )
    }
}

@Preview
@Composable
fun DailyWeatherCardBottomSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.7f),
        horizontalArrangement = Arrangement.Center
    ) {
        DailyWeatherCardBottomSection(
            modifier = Modifier
                .weight(1f),
            detailWeatherImage = R.drawable.windy,
            contentDescription = "",
            detailWeatherTitle = "Wind",
            amount = 8f,
            stringRes = R.string.wind
        )
        DailyWeatherCardBottomSection(
            modifier = Modifier
                .weight(1f),
            detailWeatherImage = R.drawable.windy,
            contentDescription = "",
            detailWeatherTitle = "Pressure",
            amount = 8f,
            stringRes = R.string.pressure
        )
        DailyWeatherCardBottomSection(
            modifier = Modifier
                .weight(1f),
            detailWeatherImage = R.drawable.windy,
            contentDescription = "",
            detailWeatherTitle = "Humidity",
            amount = 8f,
            stringRes = R.string.humidity
        )
    }
}