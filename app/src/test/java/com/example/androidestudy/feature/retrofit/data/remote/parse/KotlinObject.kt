package com.example.androidestudy.feature.retrofit.data.remote.parse

import com.example.androidestudy.feature.retrofit.data.mapper.toUserPostItem
import com.example.androidestudy.feature.retrofit.data.remote.dto.UserPostItemDto
import com.example.androidestudy.feature.retrofit.data.remote.validUserPostResponse
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun createUserPosts(): MutableList<UserPostItem> {

    // 最終的にこれを返したい
    val dummyPost = mutableListOf<UserPostItem>()

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val type = Types.newParameterizedType(List::class.java, UserPostItem::class.java)

    val fakeList = moshi.adapter<List<UserPostItem>>(type).fromJson(validUserPostResponse)

    //println(fakeList)

    fakeList?.map {
        dummyPost.add(it)
    }

    //dummyPost.shuffle()
    return dummyPost
}

fun createUserPostsDto(): MutableList<UserPostItemDto> {

    // 最終的にこれを返したい
    val dummyPost = mutableListOf<UserPostItemDto>()

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val type = Types.newParameterizedType(List::class.java, UserPostItemDto::class.java)

    val fakeList = moshi.adapter<List<UserPostItemDto>>(type).fromJson(validUserPostResponse)

    //println(fakeList)

    fakeList?.map {
        dummyPost.add(it)
    }

    //dummyPost.shuffle()
    return dummyPost
}

fun getPostById(): UserPostItem {
    return UserPostItem(
        "",
        1,
        "",
        1
    )
}

fun getPostDtoById(): UserPostItemDto {
    return UserPostItemDto(
        "",
        1,
        "",
        1
    )
}