package com.maruf.todo.db

import androidx.room.*
import com.maruf.todo.data.local.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPagedList(limit: Int, offset: Int): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo): Long

    @Update
    suspend fun updateTodo(todo: Todo)
}