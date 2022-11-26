package com.example.androidestudy.feature.todoapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.androidestudy.feature.todoapp.data.repository.TodoAppDataStoreRepositoryImpl.Companion.TODO_PREFERENCES_DATA_STORE_NAME
import com.example.androidestudy.feature.todoapp.domain.repository.TodoAppDataStoreRepository
import kotlinx.coroutines.flow.Flow

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

    override fun getOnBoardingState(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun saveOnBoardingState() {
        TODO("Not yet implemented")
    }

    companion object {
        const val TODO_PREFERENCES_DATA_STORE_NAME = "todo_data_store"
    }
}