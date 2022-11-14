package com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.viewmodel.PostEditScreenViewModel

@Composable
fun UpdateScreenHeader(
    viewModel: PostEditScreenViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(12f),
            text = "Post Screen",
            fontWeight = FontWeight.Bold
        )

        IconButton(
            modifier = Modifier.weight(1f),
            onClick = {
                viewModel.onEvent(PostUpdateScreenEvent.UpdateUserPost)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Sort Icon")
        }
    }
}