package com.example.androidestudy.feature.retrofit.presentation.postlist

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidestudy.R
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.OrderSection
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.PostListEvent
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.UserPostItemUnit
import com.example.androidestudy.feature.retrofit.presentation.postlist.viewmodel.PostListViewModel
import com.example.androidestudy.feature.util.Screen
import kotlinx.coroutines.launch

@Composable
fun PostListScreen(
    navController: NavController,
    viewModel: PostListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.PostScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Post")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column {
            Log.d("PostState", "$state")
            if (state.isLoading) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.primary)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "UserPost",
                    fontWeight = FontWeight.Bold
                )

                IconButton(
                    onClick = {
                        viewModel.onEvent(PostListEvent.ToggleOrderSection)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sort),
                        contentDescription = "Sort Icon")
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
            ) {
                AnimatedVisibility(
                    visible = state.isOrderSectionVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    OrderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        postOrder = state.postOrder,
                        onOrderChange = {
                            viewModel.onEvent(PostListEvent.Order(it))
                        }
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.postList) { post ->
                        UserPostItemUnit(
                            userPostItem = post,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(Screen.PostUpdateScreen.route)
                                },
                            onDeleteClick = {
                                viewModel.onEvent(PostListEvent.DeletePost(post))
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Post Deleted",
                                        actionLabel = "Undo"
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(PostListEvent.RestorePost)
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                    }
                }
            }
        }
    }
}