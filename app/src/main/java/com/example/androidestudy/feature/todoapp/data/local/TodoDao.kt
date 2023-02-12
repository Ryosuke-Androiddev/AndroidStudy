package com.example.androidestudy.feature.todoapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_app")
    fun getAllTodo(): Flow<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodoItem(todoItem: TodoItem)

    // PriorityをTypeConverterなしで数字で取得する
    @Query("SELECT * FROM todo_app WHERE priority=:priority")
    fun getTodoByPriority(priority: Int): Flow<List<TodoItem>>

    @Update
    suspend fun updateTodoItem(todoItem: TodoItem)

    @Delete
    suspend fun deleteTodoItem(todoItem: TodoItem)

    @Query("DELETE FROM todo_app WHERE priority=:priority")
    suspend fun deleteAllTodoItem(priority: Int)
}