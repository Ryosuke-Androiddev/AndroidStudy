package com.example.androidestudy.feature.retrofit.presentation.postlist.viewmodel

import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import com.example.androidestudy.feature.retrofit.domain.usecase.DeleteUserPostUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.GetAllUserPostsUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.PostUserPostUseCase
import com.example.androidestudy.feature.retrofit.domain.usecase.TextInputValidationUseCase
import io.mockk.mockk
import org.junit.Before

class PostListViewModelTest {

    private lateinit var repository: UserPostRepository
    private lateinit var deleteUserPostUseCase: DeleteUserPostUseCase
    private lateinit var getAllUserPostsUseCase: GetAllUserPostsUseCase
    private lateinit var postUserPostUseCase: PostUserPostUseCase
    private lateinit var textInputValidationUseCase: TextInputValidationUseCase
    private lateinit var viewModel: PostListViewModel

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        deleteUserPostUseCase = DeleteUserPostUseCase(repository = repository)
        getAllUserPostsUseCase = GetAllUserPostsUseCase(repository = repository)
        textInputValidationUseCase = TextInputValidationUseCase()
        postUserPostUseCase = PostUserPostUseCase(repository = repository, textInputValidationUseCase = textInputValidationUseCase)
        viewModel = PostListViewModel(
            deleteUserPostUseCase = deleteUserPostUseCase,
            getAllUserPostsUseCase = getAllUserPostsUseCase,
            postUserPostUseCase = postUserPostUseCase
        )
    }
}