package com.example.androidestudy.feature.auth.data.repository

import android.app.Activity
import app.cash.turbine.test
import com.example.androidestudy.feature.auth.domain.model.AuthUserInfo
import com.example.androidestudy.feature.auth.domain.model.ResultState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.lang.Exception
import java.util.concurrent.Executor

@ExperimentalCoroutinesApi
class AuthRepositoryImplTest {

    private lateinit var successTask: Task<AuthResult>
    private lateinit var failureTask: Task<AuthResult>
    private lateinit var isNotCompleted: Task<AuthResult>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authRepositoryImpl: AuthRepositoryImpl

    @Before
    fun setup() {
        isNotCompleted = mockk(relaxed = true)
        successTask = object : Task<AuthResult>() {
            override fun addOnFailureListener(p0: Activity, p1: OnFailureListener): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun addOnFailureListener(p0: Executor, p1: OnFailureListener): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun addOnSuccessListener(p0: Activity, p1: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun getException(): Exception? {
                TODO("Not yet implemented")
            }
            override fun getResult(): AuthResult {
                TODO("Not yet implemented")
            }
            override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult {
                TODO("Not yet implemented")
            }
            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            // 変更した箇所
            override fun isComplete(): Boolean = true

            // 変更した箇所
            override fun isSuccessful(): Boolean = true

            // 変更した箇所
            override fun addOnSuccessListener(executor: Executor, onSuccessListener: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("Not yet implemented")
            }

            override fun addOnCompleteListener(onCompleteListener: OnCompleteListener<AuthResult>): Task<AuthResult> {
                onCompleteListener.onComplete(successTask)
                return successTask
            }

            // failureTask終わってないのでここで定義するの良くないかも
            // かといってsuccessTaskを返すと意味のないテストになる
            override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<AuthResult> {
                onFailureListener.onFailure(RuntimeException())
                return isNotCompleted
            }
        }

        failureTask = object : Task<AuthResult>() {
            override fun addOnFailureListener(p0: OnFailureListener): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun addOnFailureListener(p0: Activity, p1: OnFailureListener): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun addOnFailureListener(p0: Executor, p1: OnFailureListener): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun addOnSuccessListener(p0: Activity, p1: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun addOnSuccessListener(p0: Executor, p1: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun getException(): Exception? {
                TODO("Not yet implemented")
            }
            override fun getResult(): AuthResult {
                TODO("Not yet implemented")
            }
            override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult {
                TODO("Not yet implemented")
            }
            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun isComplete(): Boolean = true

            override fun isSuccessful(): Boolean = false

            // これっている??
            override fun addOnCompleteListener(onCompleteListener: OnCompleteListener<AuthResult>): Task<AuthResult> {
                onCompleteListener.onComplete(isNotCompleted)
                return isNotCompleted
            }
        }

        firebaseAuth = mockk(relaxed = true)
        authRepositoryImpl = AuthRepositoryImpl(
            firebaseAuth = firebaseAuth
        )
    }

    @Test
    fun `Create User Successfully with Valid Data`() = runTest {

        // ダミーデータを用意
        val authUserInfo = AuthUserInfo(
            email = "123456@gmail.com",
            password = "123456"
        )

        // 処理が成功したと考える(ユーザーの作成)
        coEvery {
            firebaseAuth.createUserWithEmailAndPassword(
                "123456@gmail.com",
                "123456"
            )
        } returns successTask

        authRepositoryImpl.createUser(authUserInfo = authUserInfo).test(timeoutMs = 9_000) {
            assertThat(awaitItem()).isEqualTo(ResultState.Loading)
            assertThat(awaitItem()).isEqualTo(ResultState.Success)
            assertThat(awaitItem()).isEqualTo(ResultState.Failure)

            // このコールバックでは処理がキャンセルされるまでSendChannelがキャンセルされないから
            // 常にHotStreamなのかもしれない
            // 例外が発生したタイミングでのテストケースを追加
            // awaitComplete()
        }
    }

    @Test
    fun `Create User with Invalid Data`() = runTest {
        // ダミーデータを用意
        val authUserInfo = AuthUserInfo(
            email = "123456@gmail.com",
            password = null
        )

        // 処理が成功したと考える(ユーザーの作成)
        coEvery {
            firebaseAuth.createUserWithEmailAndPassword(
                "123456@gmail.com",
                ""
            )
        } throws IllegalStateException()

        authRepositoryImpl.createUser(authUserInfo = authUserInfo).test {
            assertThat(awaitItem()).isEqualTo(ResultState.Loading)
            // IllegalStateExceptionが発生しているので、例外処理をする必要がある
            // 例外をハンドリングする時も、SendChannelをキャンセルするハンドリングが必要
            assertThat(awaitItem()).isEqualTo(ResultState.Failure)
        }
    }

    @Test
    fun `Login User Successfully with Valid Data`() = runTest {

    }

    @Test
    fun `Login User with Invalid Data`() = runTest {

    }

    // Coroutineをキャンセル、SendChannelをCloseしたタイミングで予想通り動くかを確認する
    @Test
    fun `Create User with Exception`() = runTest {

    }

    @Test
    fun `Login User with Exception`() = runTest {

    }
}