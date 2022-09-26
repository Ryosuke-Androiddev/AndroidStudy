package com.example.androidestudy.feature.auth.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.auth.domain.model.ResultState
import com.example.androidestudy.feature.auth.presentation.component.BottomAreaComponent
import com.example.androidestudy.feature.auth.presentation.component.StandardPasswordTextField
import com.example.androidestudy.feature.auth.presentation.component.StandardTextField
import com.example.androidestudy.feature.auth.presentation.sign_in.component.SignInEvent
import com.example.androidestudy.feature.auth.presentation.sign_in.viewmodel.SignInViewModel
import com.example.androidestudy.feature.util.Constants.TEXT_FIELD_MAX_LENGTH
import com.example.androidestudy.feature.util.Screen
import com.example.androidestudy.ui.theme.SO_MATCH_LARGE_PADDING

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {

    val state = viewModel.signInState
    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.signInResult.collect { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    navController.navigate(Screen.CompletedScreen.route) {
                        popUpTo(Screen.MainScreen.route)
                    }
                }
                is ResultState.Failure -> {
                    Toast.makeText(
                        context,
                        "Error Occurred",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.sign_in),
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(SO_MATCH_LARGE_PADDING))

        StandardTextField(
            imageRes = R.drawable.mail,
            imageDescription = stringResource(id = R.string.sign_in_email_image_description),
            hint = stringResource(id = R.string.email_hint),
            text = state.signInEmail,
            maxLen = TEXT_FIELD_MAX_LENGTH,
            keyboardType = KeyboardType.Email,
            onValueChange = {
                viewModel.onSignInEvent(SignInEvent.SignInEmailChanged(it))
            }
        )

        StandardPasswordTextField(
            imageDescription = "",
            hint = stringResource(id = R.string.password_hint),
            text = state.signInPassword,
            maxLen = TEXT_FIELD_MAX_LENGTH,
            keyboardType = KeyboardType.Password,
            // ここをViewModelのStateで管理する
            showText = state.showText,
            onValueChange = {
                viewModel.onSignInEvent(SignInEvent.SignInPasswordChanged(it))
            },
            onClick = {
                // 反転させる
                viewModel.onSignInEvent(SignInEvent.SignInPasswordVisibility(state.showText))
            }
        )

        BottomAreaComponent(
            buttonTitle = stringResource(id = R.string.sign_in),
            haveAccount = stringResource(id = R.string.have_account),
            operateTitle = stringResource(id = R.string.login),
            onClick = {
                viewModel.onSignInEvent(SignInEvent.SignIn)
            },
            onNavigate = {
                navController.navigate(Screen.LoginScreen.route)
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
        }
    }
}