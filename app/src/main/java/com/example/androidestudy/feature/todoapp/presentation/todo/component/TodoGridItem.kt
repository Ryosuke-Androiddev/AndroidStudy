package com.example.androidestudy.feature.todoapp.presentation.todo.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidestudy.feature.todoapp.presentation.home.component.TodoTaskTag

@Composable
fun TodoGridItem() {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .size(width = 55.dp, 150.dp),
        border = BorderStroke(2.dp, Color.Black.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 12.dp, end = 10.dp),
                text = "Title is text text text",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
            ) {
                TodoTaskTag(
                    modifier = Modifier
                        .padding(top = 6.dp),
                    color = Color.Red,
                    title = "Medium",
                    fontSize = 12.sp
                )
                
                Spacer(modifier = Modifier.width(4.dp))
                
                TodoTaskTag(
                    modifier = Modifier
                        .padding(top = 6.dp),
                    color = Color.Red,
                    title = "DeadLine",
                    fontSize = 12.sp
                )
            }

            Text(
                modifier = Modifier
                    .padding(start = 12.dp, end = 10.dp, top = 9.dp),
                text = "Title is title title title title title title title title title title title title title title title title title title title title",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}