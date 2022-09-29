package com.example.androidestudy.feature.retrofit.data.remote.dto

import com.squareup.moshi.Json

data class UserPostItem(
    @field:Json(name = "body") // キーと一致している場合は省略することが可能
    val body: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "userId") // キャメルケースの時にこのアノテーションをつける
    val userId: Int
)