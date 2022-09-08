package com.example.androidestudy.feature.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.androidestudy.feature.domain.repository.AndroidStudyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class AndroidStudyRepositoryImpl(context: Context): AndroidStudyRepository {

    // デリゲートで、PreferencesDataStoreを使用する
    // デリゲートにより、DataStore の単一インスタンスが、その名前でアプリ内に存在するようになる
    // Singletonであるので、Hiltを使ってDIする必要がある
    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(PREFERENCES_DATASTORE_KEY)

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
    override fun readIsCompleted(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    // 永続性を持たないデータを返却する
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preference ->
                val onBoardingState = preference[PreferencesKey.IS_COMPLETED] ?: false
                onBoardingState
            }
    }

    companion object {
        private const val PREFERENCES_DATASTORE_KEY = "PreferencesDataStore"
    }
}