package com.example.androidestudy.feature.todoapp.presentation.todo.list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidestudy.feature.todoapp.presentation.home.viewmodel.HomeViewModel
import com.example.androidestudy.feature.todoapp.presentation.todo.list.viewmodel.TodoListViewModel

// 最初のViewModelがここに入ってるからリソースが共有できていない
@Composable
fun TodoListScreen(
    priority: Int,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Log.d("TodoList", "${state.todoListByPriority}")
    Log.d("TodoList", "${state.todoListByPriority.size == 5}")
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.todoListByPriority.forEach {
            Text(text = it.title)
        }
    }
}