package com.example.androidestudy.feature.retrofit.presentation.postlist.component

import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem

data class PostListScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val postList: List<UserPostItem> = emptyList()
)
