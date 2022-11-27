package com.example.androidestudy.feature.todoapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.androidestudy.feature.todoapp.data.repository.TodoAppDataStoreRepositoryImpl.Companion.TODO_PREFERENCES_DATA_STORE_NAME
import com.example.androidestudy.feature.todoapp.domain.model.onboarding.TodoAppOnBoardingState
import com.example.androidestudy.feature.todoapp.domain.repository.TodoAppDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

// トップレベルかつContext経由のDelegateで、Singleton ProcessなDataStoreを提供できる
// DI経由で渡すだけで、SingletonなDataStoreを参照できる
val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = TODO_PREFERENCES_DATA_STORE_NAME)

class TodoAppDataStoreRepositoryImpl(
    context: Context
): TodoAppDataStoreRepository {

    // Delegateして、DataStoreを保持するとSingletonなDataStoreとなって、Hiltと相性がいい
    // レシーバーとしてContext経由でDataStoreを取得する
    // キーを使ってプリミティブ型で保持、編集できる
    // キーはこのクラス内でカプセル化して利用する
    private val dataStore = context.dataStore

    private object TodoAppPreferencesKey {
        val IS_COMPLETED = booleanPreferencesKey(name = "on_boarding_state")
    }

    override fun getOnBoardingState(): Flow<TodoAppOnBoardingState> {
        return dataStore.data
            .catch { 
                TodoAppOnBoardingState.Exception
            }
            .map { preferences ->
                val todoAppOnBoardingState = preferences[TodoAppPreferencesKey.IS_COMPLETED] ?: false
                TodoAppOnBoardingState.IsCompleted(isCompleted = todoAppOnBoardingState)
            }
    }

    override suspend fun saveOnBoardingState(isCompleted: Boolean) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[TodoAppPreferencesKey.IS_COMPLETED] = isCompleted
        }
    }

    companion object {
        const val TODO_PREFERENCES_DATA_STORE_NAME = "todo_data_store"
    }
}