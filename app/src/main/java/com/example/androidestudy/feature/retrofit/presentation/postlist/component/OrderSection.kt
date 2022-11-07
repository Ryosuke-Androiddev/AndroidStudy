package com.example.androidestudy.feature.retrofit.presentation.postlist.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidestudy.feature.retrofit.domain.model.order.OrderType
import com.example.androidestudy.feature.retrofit.domain.model.order.PostOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    postOrder: PostOrder = PostOrder.Id(OrderType.Descending),
    onOrderChange: (PostOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            StandardRadioButton(
                text = "Id",
                selected = postOrder is PostOrder.Id,
                onSelect = {
                    onOrderChange(PostOrder.Id(postOrder.orderType))
                }
            )
            StandardRadioButton(
                text = "Title",
                selected = postOrder is PostOrder.Title,
                onSelect = {
                    onOrderChange(PostOrder.Title(postOrder.orderType))
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            StandardRadioButton(
                text = "Ascending",
                selected = postOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(postOrder.copy(OrderType.Ascending))
                }
            )
            StandardRadioButton(
                text = "Descending",
                selected = postOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(postOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}

@Preview
@Composable
fun ShowOrderSection() {
    OrderSection(onOrderChange = {})
}