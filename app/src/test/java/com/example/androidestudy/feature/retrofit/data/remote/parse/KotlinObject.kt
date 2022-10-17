package com.example.androidestudy.feature.retrofit.data.remote.parse

import com.example.androidestudy.feature.retrofit.data.remote.validUserPostResponse
import com.example.androidestudy.feature.retrofit.domain.model.UserPostItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun createUserPosts(): MutableList<UserPostItem> {

    // 最終的にこれを返したい
    val dummyPost = mutableListOf<UserPostItem>()

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val type = Types.newParameterizedType(List::class.java, UserPostItem::class.java)

    val fakeList = moshi.adapter<List<UserPostItem>>(type).fromJson(validUserPostResponse)

    // println(fakeList)

    fakeList?.forEach {
        dummyPost.add(it)
    }

    // dummyPost.shuffle()
    return dummyPost
}