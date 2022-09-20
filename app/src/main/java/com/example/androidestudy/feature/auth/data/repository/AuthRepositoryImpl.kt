package com.example.androidestudy.feature.auth.data.repository

import android.util.Log
import com.example.androidestudy.feature.auth.domain.model.AuthUserInfo
import com.example.androidestudy.feature.auth.domain.model.ResultState
import com.example.androidestudy.feature.auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

// 例外処理が漏れてる、例外処理を書くとテストが崩れる
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override fun createUser(email: String, password: String): Flow<ResultState> = callbackFlow {
        trySend(ResultState.Loading)

        firebaseAuth.createUserWithEmailAndPassword(
            email,
            password
        ).addOnCompleteListener { authResult ->
            if (authResult.isSuccessful) {
                //Log.d("AuthResult", "${firebaseAuth.currentUser?.uid}")
                trySend(ResultState.Success)
            } else {
                trySend(ResultState.Failure)
            }
        }

        // キャンセル待ち(キャンセルが発生したタイミングで呼ばれる) → キャンセルが発生するまで待機
        // Flowのキャンセル、SendChannel.closeを手動で呼び出された時に自動で実行される
        // コールバックの登録解除などに用いられる(メモリリークを防止する)
        awaitClose {
            // SendChannelをCloseする
            // このFlowがsendできなくなる
            // キャンセルが起こったタイミングでSendChannelを閉じる処理を呼び出す
            close()
        }
    }

    override fun loginUser(email: String, password: String): Flow<ResultState> = callbackFlow {
        trySend(ResultState.Loading)

        firebaseAuth.signInWithEmailAndPassword(
            email,
            password
        ).addOnCompleteListener { authResult ->
            if (authResult.isSuccessful) {
                trySend(ResultState.Success)
            } else {
                trySend(ResultState.Failure)
            }
        }

        awaitClose {
            close()
        }
    }
}