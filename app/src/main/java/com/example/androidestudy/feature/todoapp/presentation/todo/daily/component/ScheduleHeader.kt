package com.example.androidestudy.feature.todoapp.presentation.todo.daily.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androidestudy.feature.todoapp.presentation.home.component.Priority
import com.example.androidestudy.feature.util.Screen

@Composable
fun ScheduleHeader(
    navController: NavController,
    month: String
) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .size(60.dp),
                onClick = { 
                    
                }
            ) {
                Icon(imageVector = Icons.Default.List, contentDescription = "Drawer Button")
            }
            
            Text(
                text = month,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        
        IconButton(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onClick = {
                navController.navigate(Screen.TodoListScreen.route + "?priority=${-1}")
            }
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Navigate to Search")
        }
    }
}