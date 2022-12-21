package com.example.androidestudy.feature.todoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidestudy.feature.todoapp.domain.model.todo.TodoItem

@Database(
    entities = [TodoItem::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}