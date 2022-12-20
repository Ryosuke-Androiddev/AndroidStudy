package com.example.androidestudy.feature.todoapp.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_app")
    fun getAllTodo(): Flow<List<TodoItem>>
}