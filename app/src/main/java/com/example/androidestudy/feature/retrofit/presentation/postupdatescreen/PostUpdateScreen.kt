package com.example.androidestudy.feature.retrofit.presentation.postupdatescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidestudy.feature.retrofit.presentation.component.StandardContentTextField
import com.example.androidestudy.feature.retrofit.presentation.component.StandardTitleTextField
import com.example.androidestudy.feature.retrofit.presentation.component.UiEvent
import com.example.androidestudy.feature.retrofit.presentation.postscreen.component.PostScreenEvent
import com.example.androidestudy.feature.retrofit.presentation.postscreen.component.StandardHeader
import com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.component.PostUpdateScreenEvent
import com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.component.UpdateScreenHeader
import com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.viewmodel.PostEditScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostUpdateScreen(
    navController: NavController,
    viewModel: PostEditScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
                UpdateScreenHeader()

                Spacer(modifier = Modifier.height(25.dp))

                StandardTitleTextField(
                    hint = "title",
                    text = state.title,
                    maxLength = 20,
                    isSingleLine = true,
                    errorText = state.errorMessage,
                    keyboardType = KeyboardType.Text,
                    onValueChange = {
                        viewModel.onEvent(PostUpdateScreenEvent.EnterTitleEvent(it))
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
                        viewModel.onEvent(PostUpdateScreenEvent.EnterBodyEvent(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(PostUpdateScreenEvent.ChangeContentFocus(it))
                    }
                )
            }
        }
    }
}