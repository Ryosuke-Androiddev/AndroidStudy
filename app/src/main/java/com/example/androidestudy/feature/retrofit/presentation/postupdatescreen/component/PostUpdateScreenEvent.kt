package com.example.androidestudy.feature.retrofit.presentation.postupdatescreen.component

import androidx.compose.ui.focus.FocusState
import com.example.androidestudy.feature.retrofit.presentation.postscreen.component.PostScreenEvent

sealed class PostUpdateScreenEvent {
    data class EnterTitleEvent(val title: String): PostUpdateScreenEvent()
    data class EnterBodyEvent(val body: String): PostUpdateScreenEvent()
    data class ChangeContentFocus(val focusState: FocusState): PostUpdateScreenEvent()
    object UpdateUserPost: PostUpdateScreenEvent()
}
