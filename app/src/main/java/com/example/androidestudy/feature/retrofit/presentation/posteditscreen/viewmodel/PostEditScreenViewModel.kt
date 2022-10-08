package com.example.androidestudy.feature.retrofit.presentation.posteditscreen.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidestudy.feature.retrofit.domain.usecase.UpdateUserPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostEditScreenViewModel @Inject constructor(
    private val updateUserPostUseCase: UpdateUserPostUseCase
): ViewModel() {
}