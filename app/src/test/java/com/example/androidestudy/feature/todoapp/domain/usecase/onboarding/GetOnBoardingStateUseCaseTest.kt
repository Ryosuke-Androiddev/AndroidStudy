package com.example.androidestudy.feature.todoapp.domain.usecase.onboarding

import app.cash.turbine.test
import com.example.androidestudy.feature.todoapp.domain.model.onboarding.TodoAppOnBoardingEvent
import com.example.androidestudy.feature.todoapp.domain.model.onboarding.TodoAppOnBoardingState
import com.example.androidestudy.feature.todoapp.domain.repository.TodoAppDataStoreRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetOnBoardingStateUseCaseTest {
    private lateinit var todoAppDataStoreRepository: TodoAppDataStoreRepository

    // 値の書き換えを読み取るのにこのUseCaseが必要になる
    private lateinit var getOnBoardingStateUseCase: GetOnBoardingStateUseCase

    @Before
    fun setup() {
        todoAppDataStoreRepository = mockk(relaxed = true)
        getOnBoardingStateUseCase = GetOnBoardingStateUseCase(
            todoAppDataStoreRepository = todoAppDataStoreRepository
        )
    }

    @Test
    fun `onBoarding State is Completed, return isCompleted = true`() = runTest {
        val expectedState = TodoAppOnBoardingState.IsCompleted
        val actualState = TodoAppOnBoardingEvent.IgnoreOnBoarding

        coEvery {
            todoAppDataStoreRepository.getOnBoardingState()
        } returns flowOf(expectedState)

        getOnBoardingStateUseCase().test {
            assertThat(awaitItem()).isEqualTo(actualState)
            awaitComplete()
        }
    }

    @Test
    fun `onBoarding State is Not Completed, return isCompleted = false`() = runTest {
        val expectedState = TodoAppOnBoardingState.IsNotCompleted
        val actualState = TodoAppOnBoardingEvent.ShowOnBoarding

        coEvery {
            todoAppDataStoreRepository.getOnBoardingState()
        } returns flowOf(expectedState)

        getOnBoardingStateUseCase().test {
            assertThat(awaitItem()).isEqualTo(actualState)
            awaitComplete()
        }
    }

    @Test
    fun `Can't get onBoarding State, occurred Exception`() = runTest {
        val expectedState = TodoAppOnBoardingState.Exception
        val actualState = TodoAppOnBoardingEvent.ReloadOnBoardingState

        coEvery {
            todoAppDataStoreRepository.getOnBoardingState()
        } returns flowOf(expectedState)

        getOnBoardingStateUseCase().test {
            assertThat(awaitItem()).isEqualTo(actualState)
            awaitComplete()
        }
    }
}