package com.example.androidestudy.feature.retrofit.presentation.postscreen.component

import androidx.compose.ui.focus.FocusState

sealed class PostScreenEvent {
    data class EnterTitleEvent(val title: String): PostScreenEvent()
    data class EnterBodyEvent(val body: String): PostScreenEvent()
    data class ChangeContentFocus(val focusState: FocusState): PostScreenEvent()
    object PostUserPost: PostScreenEvent()
}
