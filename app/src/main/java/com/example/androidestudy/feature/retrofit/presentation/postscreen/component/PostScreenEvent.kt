package com.example.androidestudy.feature.retrofit.presentation.postscreen.component

sealed class PostScreenEvent {
    data class EnterTitleEvent(val title: String): PostScreenEvent()
    data class EnterBodyEvent(val body: String): PostScreenEvent()
    object PostUserPost: PostScreenEvent()
}
