package com.example.androidestudy.feature.retrofit.presentation.postscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidestudy.feature.retrofit.presentation.component.StandardContentTextField
import com.example.androidestudy.feature.retrofit.presentation.postscreen.component.StandardHeader
import com.example.androidestudy.feature.retrofit.presentation.component.StandardTitleTextField
import com.example.androidestudy.feature.retrofit.presentation.component.UiEvent
import com.example.androidestudy.feature.retrofit.presentation.postscreen.component.PostScreenEvent
import com.example.androidestudy.feature.retrofit.presentation.postscreen.viewmodel.PostScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostScreen(
    navController: NavController,
    viewModel: PostScreenViewModel = hiltViewModel(),
    id: Int
) {
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Success -> {
                    navController.navigateUp()
                }
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UiEvent.Failure -> {
                    // Nothing TO DO
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            StandardHeader()

            Spacer(modifier = Modifier.height(25.dp))

            StandardTitleTextField(
                hint = "title",
                text = state.title,
                maxLength = 20,
                isSingleLine = true,
                errorText = state.errorMessage,
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    viewModel.onEvent(PostScreenEvent.EnterTitleEvent(it))
                }
            )

            StandardContentTextField(
                hint = "content",
                text = state.body,
                maxLength = 300,
                isHintVisible = state.isHintVisible,
                textStyle = MaterialTheme.typography.body2,
                errorText = state.errorMessage,
                onValueChange = {
                    viewModel.onEvent(PostScreenEvent.EnterBodyEvent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(PostScreenEvent.ChangeContentFocus(it))
                }
            )
        }
    }
}