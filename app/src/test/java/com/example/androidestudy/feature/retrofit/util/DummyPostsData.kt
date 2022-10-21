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

fun createIdAscendingData(): List<UserPostItem> {
    val dummy = createDummyData()
    return dummy.sortedBy {
        it.id
    }
}

fun createIdDescendingData(): List<UserPostItem> {
    val dummy = createDummyData()
    return dummy.sortedByDescending {
        it.id
    }
}

fun createTitleAscendingData(): List<UserPostItem> {
    val dummy = createDummyData()
    return dummy.sortedBy {
        it.title
    }
}

fun createTitleDescendingData(): List<UserPostItem> {
    val dummy = createDummyData()
    return dummy.sortedByDescending {
        it.title
    }
}