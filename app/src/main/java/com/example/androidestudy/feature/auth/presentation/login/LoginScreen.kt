package com.example.androidestudy.feature.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.androidestudy.feature.auth.presentation.component.StandardCircularProgressIndicator
import com.example.androidestudy.feature.auth.presentation.component.StandardPasswordTextField
import com.example.androidestudy.feature.auth.presentation.component.StandardTextField
import com.example.androidestudy.feature.auth.presentation.login.component.LoginEvent
import com.example.androidestudy.feature.auth.presentation.login.viewmodel.LoginViewModel
import com.example.androidestudy.feature.util.Screen
import com.example.androidestudy.ui.theme.SO_MATCH_LARGE_PADDING

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state = viewModel.loginState
    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.loginResult.collect { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    // 溜まってるStackをこの状態になった時に削除する
                    // 認証が成功したらスタックを全て削除したい
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
            text = stringResource(id = R.string.login),
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(SO_MATCH_LARGE_PADDING))

        StandardTextField(
            imageRes = R.drawable.mail,
            imageDescription = stringResource(id = R.string.sign_in_email_image_description),
            hint = stringResource(id = R.string.email_hint),
            text = state.loginEmail,
            maxLen = 20,
            errorText = state.loginEmailError,
            keyboardType = KeyboardType.Email,
            onValueChange = {
                viewModel.onLoginEvent(LoginEvent.LoginEmailChanged(it))
            }
        )

        StandardPasswordTextField(
            imageDescription = "",
            hint = stringResource(id = R.string.password_hint),
            text = state.loginPassword,
            maxLen = 20,
            keyboardType = KeyboardType.Password,
            // ここをViewModelのStateで管理する
            showText = state.showText,
            errorText = state.loginPasswordError,
            onValueChange = {
                viewModel.onLoginEvent(LoginEvent.LoginUserPasswordChanged(it))
            },
            onClick = {
                // 反転させる
                viewModel.onLoginEvent(LoginEvent.LoginPasswordVisibility(state.showText))
            }
        )

        BottomAreaComponent(
            buttonTitle = stringResource(id = R.string.login),
            haveAccount = stringResource(id = R.string.do_not_have_account),
            operateTitle = stringResource(id = R.string.text_sign_in),
            onClick = {
                viewModel.onLoginEvent(LoginEvent.Login)
            },
            onNavigate = {
                navController.navigate(Screen.SignInScreen.route)
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> StandardCircularProgressIndicator()
        }
    }
}