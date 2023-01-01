package com.example.androidestudy.feature.todoapp.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidestudy.feature.todoapp.presentation.home.viewmodel.HomeEvent
import com.example.androidestudy.feature.todoapp.presentation.home.viewmodel.HomeViewModel
import com.example.androidestudy.feature.util.Screen

@Composable
fun TodoTaskItem(
    @DrawableRes
    imageRes: Int,
    color: Color,
    title: String,
    description: String,
    priority: TodoPriority,
    navController: NavController,
    deadLine: String = "DeadLine",
    viewModel: HomeViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                // 画面遷移を行う
                // TodoListViewModelを引数にもってイベント処理するイメージ
                // 選択したプライオリティごとに呼び出す処理を変更 ViewModelでイベントとして管理してここで引数として渡す
                when (priority) {
                    is TodoPriority.High -> {
                        viewModel.onEvent(HomeEvent.GetTodoListByPriority(priority = Priority.High.order))
                        navController.navigate(Screen.TodoListScreen.route)
                    }
                    is TodoPriority.Medium -> {
                        viewModel.onEvent(HomeEvent.GetTodoListByPriority(priority = Priority.Medium.order))
                        navController.navigate(Screen.TodoListScreen.route)
                    }
                    is TodoPriority.Low -> {
                        viewModel.onEvent(HomeEvent.GetTodoListByPriority(priority = Priority.Low.order))
                        navController.navigate(Screen.TodoListScreen.route)
                    }
                }
            },
        elevation = 12.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                    .background(color = color.copy(alpha = 0.15f))
                    .weight(0.75f),
                contentAlignment = Center
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = description
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 13.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    color = Color.Black.copy(alpha = 0.7f),
                    fontSize = 13.sp
                )
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = CenterVertically
                ) {
                    TodoTaskTag(color = color, title = title)
                    Spacer(modifier = Modifier.width(6.dp))
                    TodoTaskTag(color = color, title = deadLine)
                }
            }
        }
    }
}