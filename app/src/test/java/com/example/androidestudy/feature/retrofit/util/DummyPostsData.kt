package com.example.androidestudy.feature.retrofit.util

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import kotlinx.coroutines.runBlocking

fun createDummyData(): List<UserPostItem> {
    val dummyPost = mutableListOf<UserPostItem>()
    ('a'..'z').forEachIndexed { index, c ->
        dummyPost.add(
            UserPostItem(
                body = c.toString(),
                id = index,
                title = c.toString(),
                userId = index
            )
        )
    }
    dummyPost.shuffle()

    return dummyPost
}