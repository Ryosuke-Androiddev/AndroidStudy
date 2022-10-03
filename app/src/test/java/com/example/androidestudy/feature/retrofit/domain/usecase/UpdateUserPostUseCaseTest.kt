package com.example.androidestudy.feature.retrofit.domain.usecase

import com.example.androidestudy.feature.retrofit.data.repository.FakeUserpostRepository
import com.example.androidestudy.feature.retrofit.domain.repository.UserPostRepository
import io.mockk.mockk
import org.junit.Before

class UpdateUserPostUseCaseTest {

    private lateinit var updateUserPostUseCase: UpdateUserPostUseCase
    private lateinit var textInputValidationUseCase: TextInputValidationUseCase
    private lateinit var fakeUserPostRepository: UserPostRepository

    @Before
    fun setup() {
        fakeUserPostRepository = FakeUserpostRepository()
        textInputValidationUseCase = mockk(relaxed = true)
        updateUserPostUseCase = UpdateUserPostUseCase(
            repository = fakeUserPostRepository,
            textInputValidationUseCase = textInputValidationUseCase
        )
    }
}