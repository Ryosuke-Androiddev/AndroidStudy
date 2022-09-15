package com.example.androidestudy.feature.domain.use_case.datastore.preferences

import app.cash.turbine.test
import com.example.androidestudy.feature.domain.model.onboarding.OnBoardingState
import com.example.androidestudy.feature.domain.repository.datastore.preferences.DataStoreRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveOnBoardingStateTest {

    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var readOnBoardingState: ReadOnBoardingState
    private lateinit var saveOnBoardingState: SaveOnBoardingState

    @Before
    fun setup() {
        dataStoreRepository = mockk<DataStoreRepository>(relaxed = true)
        readOnBoardingState = ReadOnBoardingState(dataStoreRepository = dataStoreRepository)
        saveOnBoardingState = SaveOnBoardingState(dataStoreRepository = dataStoreRepository)
    }

    // このテストに意味があるのか
    @Test
    fun `save isCompleted as false, return false`() = runTest {
        coEvery {
            dataStoreRepository.readOnBoardingState()
        } returns flowOf(OnBoardingState.Success(isCompleted = true))

        saveOnBoardingState(isCompleted = true)
        readOnBoardingState().test {
            assertThat(awaitItem()).isEqualTo(OnBoardingState.Success(isCompleted = true))
            awaitComplete()
        }
        coVerify(exactly = 1) {
            dataStoreRepository.saveOnBoardingState(any())
        }
    }

    @Test
    fun `save isCompleted as true, return true`() = runTest {
        coEvery {
            dataStoreRepository.readOnBoardingState()
        } returns flowOf(OnBoardingState.Success(isCompleted = false))

        saveOnBoardingState(isCompleted = false)
        readOnBoardingState().test {
            assertThat(awaitItem()).isEqualTo(OnBoardingState.Success(isCompleted = false))
            awaitComplete()
        }
        coVerify(exactly = 1) {
            dataStoreRepository.saveOnBoardingState(any())
        }
    }
}