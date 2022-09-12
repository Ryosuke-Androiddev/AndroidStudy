package com.example.androidestudy.feature.domain.use_case.datastore.preferences

import androidx.datastore.preferences.core.emptyPreferences
import app.cash.turbine.test
import com.example.androidestudy.feature.domain.model.OnBoardingState
import com.example.androidestudy.feature.domain.repository.datastore.preferences.DataStoreRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
@Suppress("UNUSED_EXPRESSION")
class ReadOnBoardingStateTest {

    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var readOnBoardingState: ReadOnBoardingState

    @Before
    fun setup() {
        // 詳細を決めることなくオブジェクトの生成が可能に
        dataStoreRepository = mockk<DataStoreRepository>(relaxed = true)
        readOnBoardingState = ReadOnBoardingState(dataStoreRepository = dataStoreRepository)
    }

    // これはTestに助けられた
    // 成功して加工するだけじゃダメやもんな
    // 参照したSealed classの結果を返すように変更する
    @Test
    fun `If OnBoarding State is not Completed`() = runTest {
        // flowOfで何が返ってくるかを明示的に示す
        coEvery {
            dataStoreRepository.readOnBoardingState()
        } returns flowOf(OnBoardingState.Success(isCompleted = false))

        // 実際の呼び出しを行う
        // この呼び出しと、何を比較するんだろう
        readOnBoardingState().test {
            assertThat(awaitItem()).isFalse()
            awaitComplete()
        }
    }

    @Test
    fun `If OnBoarding State is Completed`() = runTest {
        coEvery {
            dataStoreRepository.readOnBoardingState()
        } returns flowOf(OnBoardingState.Success(isCompleted = true))

        // 実際の呼び出しを行う
        // この呼び出しと、何を比較するんだろう
        readOnBoardingState().test {
            assertThat(awaitItem()).isTrue()
            awaitComplete()
        }
    }

    @Test
    fun `If OnBoarding State is Invalid with Exception`() = runTest {

        coEvery {
            dataStoreRepository.readOnBoardingState()
        } returns flowOf(OnBoardingState.Failure)

        // 実際の呼び出しを行う
        // この呼び出しと、何を比較するんだろう
        // turbineじゃなくて単に、キャッチするだけでいい気がする
        // emitするだけでのテスト方法があるはず
        readOnBoardingState().test {
            assertThat(awaitItem()).isFalse()
            awaitComplete()
        }
    }
}