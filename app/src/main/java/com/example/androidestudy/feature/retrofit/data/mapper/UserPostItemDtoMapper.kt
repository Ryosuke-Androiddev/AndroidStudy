package com.example.androidestudy.feature.retrofit.data.mapper

import com.example.androidestudy.feature.retrofit.data.remote.dto.UserPostItemDto
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem

fun UserPostItemDto.toUserPostItem(): UserPostItem {
    return UserPostItem(
        body = body,
        id = id,
        title = title,
        userId = userId
    )
}