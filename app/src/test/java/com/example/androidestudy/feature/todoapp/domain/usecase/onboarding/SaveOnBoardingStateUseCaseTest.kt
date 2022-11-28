package com.example.androidestudy.feature.todoapp.domain.usecase.onboarding

import app.cash.turbine.test
import com.example.androidestudy.feature.todoapp.domain.model.onboarding.TodoAppOnBoardingState
import com.example.androidestudy.feature.todoapp.domain.repository.TodoAppDataStoreRepository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveOnBoardingStateUseCaseTest {
    // 書き込みが有効かを調べる時に、GetOnBoardingStateUseCaseで確認するということは
    // ここで書き込みが有効かをテストすることとうまく読み取れるかを判断するテストは同じことをやっているので
    // ここのテストは無意味になる

    private lateinit var todoAppDataStoreRepository: TodoAppDataStoreRepository

    // 値の書き換えにこのUseCaseが必要になる
    private lateinit var saveOnBoardingStateUseCase: SaveOnBoardingStateUseCase

    // 値の書き換えを読み取るのにこのUseCaseが必要になる
    private lateinit var getOnBoardingStateUseCase: GetOnBoardingStateUseCase

    @Before
    fun setup() {
        todoAppDataStoreRepository = mockk(relaxed = true)
        saveOnBoardingStateUseCase = SaveOnBoardingStateUseCase(
            todoAppDataStoreRepository = todoAppDataStoreRepository
        )
        getOnBoardingStateUseCase = GetOnBoardingStateUseCase(
            todoAppDataStoreRepository = todoAppDataStoreRepository
        )
    }

    @Test
    fun `onBoarding State is Completed, return isCompleted = true`() = runTest {
        val expectedState = TodoAppOnBoardingState.IsCompleted(isCompleted = true)

        coEvery {
            todoAppDataStoreRepository.getOnBoardingState()
        } returns flowOf(expectedState)

        getOnBoardingStateUseCase().test {
            Truth.assertThat(awaitItem()).isTrue()
            awaitComplete()
        }
    }

    @Test
    fun `onBoarding State is Not Completed, return isCompleted = false`() = runTest {
        val expectedState = TodoAppOnBoardingState.IsCompleted(isCompleted = false)

        coEvery {
            todoAppDataStoreRepository.getOnBoardingState()
        } returns flowOf(expectedState)

        getOnBoardingStateUseCase().test {
            Truth.assertThat(awaitItem()).isFalse()
            awaitComplete()
        }
    }
}