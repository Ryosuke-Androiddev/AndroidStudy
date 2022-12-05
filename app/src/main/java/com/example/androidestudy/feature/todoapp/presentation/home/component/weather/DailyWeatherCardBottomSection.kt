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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidestudy.R
import com.example.androidestudy.ui.theme.Purple500
import com.example.androidestudy.ui.theme.Purple700
import com.example.androidestudy.ui.theme.TransparentBlack
import com.example.androidestudy.ui.theme.TransparentGray

@Composable
fun DailyWeatherCardBottomSection(
    modifier: Modifier = Modifier,
    @DrawableRes
    detailWeatherImage: Int,
    contentDescription: String,
    detailWeatherTitle: String,
    amount: Float,
    @StringRes
    stringRes: Int,
    color: Color = Color.White
) {
    Column(
        modifier = modifier
            .padding(vertical = 2.dp, horizontal = 2.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(Brush.linearGradient(
                listOf(
                    TransparentBlack,
                    TransparentGray.copy(alpha = 0.9f)
                ),
                start = Offset(x = 15f, y = Float.POSITIVE_INFINITY),
                end = Offset(x = Float.POSITIVE_INFINITY, y = 55f),
            )
            )
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
            fontSize = 12.sp,
            color = color
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp),
            text = stringResource(id = stringRes, amount),
            fontSize = 12.sp,
            color = color
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
            detailWeatherImage = R.drawable.ic_windy,
            contentDescription = "",
            detailWeatherTitle = "Wind",
            amount = 8f,
            stringRes = R.string.wind
        )
        DailyWeatherCardBottomSection(
            modifier = Modifier
                .weight(1f),
            detailWeatherImage = R.drawable.ic_windy,
            contentDescription = "",
            detailWeatherTitle = "Pressure",
            amount = 8f,
            stringRes = R.string.pressure
        )
        DailyWeatherCardBottomSection(
            modifier = Modifier
                .weight(1f),
            detailWeatherImage = R.drawable.ic_windy,
            contentDescription = "",
            detailWeatherTitle = "Humidity",
            amount = 8f,
            stringRes = R.string.humidity
        )
    }
}