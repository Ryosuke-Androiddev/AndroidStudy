package com.example.androidestudy.feature.data_store.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.androidestudy.feature.data_store.domain.model.OnBoardingState
import com.example.androidestudy.feature.data_store.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

// デリゲートで、PreferencesDataStoreを使用する
// デリゲートにより、DataStore の単一インスタンスが、その名前でアプリ内に存在するようになる
// Singletonであるので、Hiltを使ってDIする必要がある
// トップレベルで宣言+委譲することで参照するだけでオブジェクトが生成される
val Context.dataStore : DataStore<Preferences> by preferencesDataStore(DataStoreRepositoryImpl.PREFERENCES_DATASTORE_KEY)

class DataStoreRepositoryImpl(context: Context): DataStoreRepository {

    private val dataStore = context.dataStore

    // Flowで管理したいオブジェクトのキーを決定する
    private object PreferencesKey {
        val IS_COMPLETED = booleanPreferencesKey("is_completed")
    }

    override suspend fun saveOnBoardingState(isCompleted: Boolean) {
        dataStore.edit { preference ->
            preference[PreferencesKey.IS_COMPLETED] = isCompleted
        }
    }

    // 保存されたデータをFlowを使って公開する
    override fun readOnBoardingState(): Flow<OnBoardingState> {
        return dataStore.data
            .catch {
                // どういうExceptionなのかがわからない
                OnBoardingState.Failure
            }
            .map { preference ->
                val onBoardingState = preference[PreferencesKey.IS_COMPLETED] ?: false
                OnBoardingState.Success(isCompleted = onBoardingState)
            }
    }

    companion object {
        const val PREFERENCES_DATASTORE_KEY = "PreferencesDataStore"
    }
}