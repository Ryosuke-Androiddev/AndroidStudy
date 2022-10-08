package com.example.androidestudy.feature.retrofit.presentation.postscreen.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidestudy.feature.retrofit.domain.usecase.PostUserPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostScreenViewModel @Inject constructor(
    private val postUserPostUseCase: PostUserPostUseCase
): ViewModel() {
}