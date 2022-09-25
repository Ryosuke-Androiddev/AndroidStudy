package com.example.androidestudy.feature.auth.data.repository

import android.app.Activity
import android.os.Parcel
import app.cash.turbine.test
import com.example.androidestudy.feature.auth.domain.model.AuthUserInfo
import com.example.androidestudy.feature.auth.domain.model.ResultState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.AdditionalUserInfo
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.Executor

@ExperimentalCoroutinesApi
class AuthRepositoryImplTest {

    private lateinit var user: FirebaseUser
    private lateinit var successTask: Task<AuthResult>
    private lateinit var failureTask: Task<AuthResult>
    private lateinit var isNotCompleted: Task<AuthResult>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authRepositoryImpl: AuthRepositoryImpl

    @Before
    fun setup() {
        user = mockk(relaxed = true)
        isNotCompleted = mockk(relaxed = true)
        successTask = object : Task<AuthResult>() {
            override fun addOnFailureListener(p0: Activity, p1: OnFailureListener): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun addOnFailureListener(p0: Executor, p1: OnFailureListener): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun addOnSuccessListener(executor: Executor, onSuccessListener: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun addOnSuccessListener(p0: Activity, p1: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                TODO("Not yet implemented")
            }
            override fun getException(): Exception? {
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

            // 任意のユーザーを変換するAuthResultの実装
            override fun getResult(): AuthResult {
                return object : AuthResult {
                    override fun describeContents(): Int {
                        TODO("Not yet implemented")
                    }

                    override fun writeToParcel(p0: Parcel?, p1: Int) {
                        TODO("Not yet implemented")
                    }

                    override fun getAdditionalUserInfo(): AdditionalUserInfo? {
                        TODO("Not yet implemented")
                    }

                    override fun getCredential(): AuthCredential? {
                        TODO("Not yet implemented")
                    }

                    override fun getUser(): FirebaseUser? {
                        return user
                    }
                }
            }

            // 変更した箇所
            override fun addOnSuccessListener(onSuccessListener: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                onSuccessListener.onSuccess(successTask.result)
                return successTask
            }

            override fun addOnCompleteListener(onCompleteListener: OnCompleteListener<AuthResult>): Task<AuthResult> {
                onCompleteListener.onComplete(successTask)
                return successTask
            }

            // successのここでこれが定義されていないことから例外が投げられている
            override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<AuthResult> {
                onFailureListener.onFailure(RuntimeException())
                return failureTask
            }
        }

        failureTask = object : Task<AuthResult>() {
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
                onCompleteListener.onComplete(failureTask)
                return failureTask
            }

            override fun addOnFailureListener(onFailureListener: OnFailureListener): Task<AuthResult> {
                onFailureListener.onFailure(RuntimeException())
                return failureTask
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
        val email = "123456@gmail.com"
        val password = "123456"

        // 処理が成功したと考える(ユーザーの作成)
        // このタスクで何を返すのかが重要な気がする
        // 通信に必要な具体的な処理はここで記述しない
        coEvery {
            firebaseAuth.createUserWithEmailAndPassword(
                "123456@gmail.com",
                "123456"
            )
        } returns successTask

        // ダミーのデータを使って何かをやることが必要な時に、戻り値を明示的に書く必要がある
        authRepositoryImpl.createUser(email, password).test {
            assertThat(awaitItem()).isEqualTo(ResultState.Success)
        }
    }

    @Test
    fun `Create User with Valid Data But Occurred with any problems`() = runTest {

        // ダミーデータを用意
        val email = "123456@gmail.com"
        val password = "123456"

        // 処理が成功したと考える(ユーザーの作成)
        // このタスクで何を返すのかが重要な気がする
        // 通信に必要な具体的な処理はここで記述しない
        coEvery {
            firebaseAuth.createUserWithEmailAndPassword(
                "123456@gmail.com",
                "123456"
            )
        } returns failureTask

        // ダミーのデータを使って何かをやることが必要な時に、戻り値を明示的に書く必要がある
        authRepositoryImpl.createUser(email, password).test {
            assertThat(awaitItem()).isEqualTo(ResultState.Failure)
        }
    }

    // 正常系のテストは間違いなくおかしい
    @Test
    fun `Login User Successfully with Valid Data`() = runTest {

        // ダミーデータを用意
        val email = "123456@gmail.com"
        val password = "123456"

        // 処理が成功したと考える(ユーザーの作成)
        coEvery {
            firebaseAuth.signInWithEmailAndPassword(
                "123456@gmail.com",
                "123456"
            )
        } returns successTask

        authRepositoryImpl.loginUser(email, password).test {
            assertThat(awaitItem()).isEqualTo(ResultState.Success)
        }
    }

    @Test
    fun `Login User with Valid Data But Occurred with any problems`() = runTest {

        // ダミーデータを用意
        val email = "123456@gmail.com"
        val password = "123456"

        // 処理が成功したと考える(ユーザーの作成)
        // このタスクで何を返すのかが重要な気がする
        // 通信に必要な具体的な処理はここで記述しない
        coEvery {
            firebaseAuth.signInWithEmailAndPassword(
                "123456@gmail.com",
                "123456"
            )
        } returns failureTask

        // ダミーのデータを使って何かをやることが必要な時に、戻り値を明示的に書く必要がある
        authRepositoryImpl.loginUser(email, password).test {
            assertThat(awaitItem()).isEqualTo(ResultState.Failure)
        }
    }

    @Test
    fun `Create User with Valid Data But Occurred IOException`() = runTest {

        // ダミーデータを用意
        val email = "123456@gmail.com"
        val password = "123456"

        // 処理が成功したと考える(ユーザーの作成)
        // このタスクで何を返すのかが重要な気がする
        // 通信に必要な具体的な処理はここで記述しない
        coEvery {
            firebaseAuth.createUserWithEmailAndPassword(
                "123456@gmail.com",
                "123456"
            )
        } throws IOException()

        // ダミーのデータを使って何かをやることが必要な時に、戻り値を明示的に書く必要がある
        authRepositoryImpl.createUser(email, password).test {
            assertThat(awaitItem()).isEqualTo(ResultState.Failure)
        }
    }

    @Test
    fun `Login User with Valid Data But Occurred IOException`() = runTest {

        // ダミーデータを用意
        val email = "123456@gmail.com"
        val password = "123456"

        // 処理が成功したと考える(ユーザーの作成)
        // このタスクで何を返すのかが重要な気がする
        // 通信に必要な具体的な処理はここで記述しない
        coEvery {
            firebaseAuth.signInWithEmailAndPassword(
                "123456@gmail.com",
                "123456"
            )
        } throws IOException()

        // ダミーのデータを使って何かをやることが必要な時に、戻り値を明示的に書く必要がある
        authRepositoryImpl.loginUser(email, password).test {
            assertThat(awaitItem()).isEqualTo(ResultState.Failure)
        }
    }
}