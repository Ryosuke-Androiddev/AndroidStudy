package com.example.androidestudy.feature.todoapp.presentation.todo.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidestudy.R
import com.example.androidestudy.feature.retrofit.presentation.postlist.component.PostListEvent
import com.example.androidestudy.feature.todoapp.presentation.todo.component.TodoGridItem
import com.example.androidestudy.feature.todoapp.presentation.todo.list.component.SearchBar
import com.example.androidestudy.feature.todoapp.presentation.todo.list.component.TodoListEvent
import com.example.androidestudy.feature.todoapp.presentation.todo.list.viewmodel.TodoListViewModel
import com.example.androidestudy.feature.todoapp.presentation.util.fakeTodoList

// 最初のViewModelがここに入ってるからリソースが共有できていない
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoListScreen(
    priority: Int,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                modifier = Modifier
                    .weight(2f),
                text = state.query,
                onValueChange = {
                    viewModel.onEvent(TodoListEvent.EnterSearchQuery(it))
                },
                onClear = {
                    viewModel.onEvent(TodoListEvent.ClearSearchQuery)
                }
            )

            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sort),
                    contentDescription = "Sort Icon")
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        if (priority != -1) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(),
                cells = GridCells.Fixed(2),
                content = {
                    // state.todoListByPriority へ変更
                    items(fakeTodoList) {
                        TodoGridItem(title = it.title, content = it.content, priority = it.priority.name)
                    }
                }
            )
        } else {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(),
                cells = GridCells.Fixed(2),
                content = {
                    // state.todoItemListへ変更
                    items(fakeTodoList) {
                        TodoGridItem(title = it.title, content = it.content, priority = it.priority.name)
                    }
                }
            )
        }
    }
}