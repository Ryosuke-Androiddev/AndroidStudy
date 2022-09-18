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

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override fun createUser(authUserInfo: AuthUserInfo): Flow<ResultState> = callbackFlow {
        trySend(ResultState.Loading)

        // callbackFlow {} で生成されるCold Flowは、SendChannelによって要素を送信
        if (authUserInfo.email != null && authUserInfo.password != null) {
            firebaseAuth.createUserWithEmailAndPassword(
                authUserInfo.email,
                authUserInfo.password
            ).addOnCompleteListener { authResult ->
                if (authResult.isSuccessful) {
                    //Log.d("AuthResult", "${firebaseAuth.currentUser?.uid}")
                    trySend(ResultState.Success)
                }
            }.addOnFailureListener {
                trySend(ResultState.Failure)
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
        } else {
            trySend(ResultState.Failure)

            awaitClose {
                close()
            }
        }
    }

    override fun loginUser(authUserInfo: AuthUserInfo): Flow<ResultState> = callbackFlow {
        trySend(ResultState.Loading)

        if (authUserInfo.email != null && authUserInfo.password != null) {
            firebaseAuth.signInWithEmailAndPassword(
                authUserInfo.email,
                authUserInfo.password
            ).addOnSuccessListener {
                Log.d("AuthResult", "${firebaseAuth.currentUser?.uid}")
                trySend(ResultState.Success)
            }.addOnFailureListener {
                trySend(ResultState.Failure)
            }

            awaitClose {
                close()
            }
        }
    }
}