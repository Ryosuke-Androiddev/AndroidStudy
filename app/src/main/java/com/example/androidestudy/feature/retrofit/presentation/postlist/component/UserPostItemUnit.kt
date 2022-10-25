package com.example.androidestudy.feature.retrofit.presentation.postlist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidestudy.R
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem

@Composable
fun UserPostItemUnit(
    modifier: Modifier = Modifier,
    userPostItem: UserPostItem,
    onDeleteClick: () -> Unit
) {
    Box(modifier = modifier) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 8.dp
        ) {
            // endの値を変更する
            Column(
                modifier = Modifier
                    .background(color = Color.LightGray.copy(alpha = 0.3f))
                    .padding(vertical = 12.dp)
                    .padding(start = 12.dp, end = 32.dp)
            ) {
                Text(
                    text = userPostItem.title,
                    fontWeight = FontWeight.Bold,
                    fontStyle = MaterialTheme.typography.h4.fontStyle
                )
                Spacer(modifier = Modifier.height(16.dp))
                // 改行のタイミングを指定したい
                Text(
                    text = userPostItem.body,
                    fontStyle = MaterialTheme.typography.h6.fontStyle,
                    maxLines = 10
                )
            }
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete note",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Preview(widthDp = 375)
@Composable
private fun UserPostIteUnitPreview() {
    UserPostItemUnit(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        userPostItem = UserPostItem("quia et suscipit nsuscipit recusandae consequuntur expedita et cum", 1, "The First Day", 1)
    ) {

    }
}