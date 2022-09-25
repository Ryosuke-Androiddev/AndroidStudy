package com.example.androidestudy.feature.auth.presentation.sign_in.component.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidestudy.R
import com.example.androidestudy.feature.auth.presentation.component.BottomAreaComponent
import com.example.androidestudy.feature.auth.presentation.component.StandardTextField

@Preview
@Composable
fun SignInScreenPreView() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign In",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(80.dp))

        StandardTextField(
            imageRes = R.drawable.mail,
            imageDescription = "",
            hint = "Email",
            text = "123456@gmail.com",
            maxLen = 20,
            keyboardType = KeyboardType.Email,
            showText = true,
            onValueChange = {},
            onClick = { /*TODO*/ })

        StandardTextField(
            imageRes = R.drawable.show_password,
            imageDescription = "",
            hint = "Password",
            text = "aaaaaaaaaaaaaaa",
            maxLen = 10,
            keyboardType = KeyboardType.Password,
            showText = false,
            onValueChange = {},
            onClick = { /*TODO*/ })

        BottomAreaComponent(
            buttonTitle = stringResource(id = R.string.sign_in),
            haveAccount = stringResource(id = R.string.have_account),
            operateTitle = stringResource(id = R.string.login),
            onClick = {},
            onNavigate = {}
        )
    }
}