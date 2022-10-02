package com.example.androidestudy.feature.retrofit.data.mapper

import com.example.androidestudy.feature.retrofit.data.remote.dto.UserPostItemDto
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem

fun UserPostItem.toUserPostItemDto(): UserPostItemDto {
    return UserPostItemDto(
        body = body,
        id = id,
        title = title,
        userId = userId
    )
}