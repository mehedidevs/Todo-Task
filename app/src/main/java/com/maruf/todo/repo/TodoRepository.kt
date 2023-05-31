package com.maruf.todo.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.maruf.todo.data.local.Todo
import com.maruf.todo.db.TodoDao
import com.maruf.todo.paging.TodoPagingSource
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDao){
    val data = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
    ) {
        TodoPagingSource(todoDao)
    }.flow

    suspend fun insertTodoRepo(item: Todo){
        todoDao.insert(item)

    }
    suspend fun updateTodoRepo(item: Todo){
        todoDao.insert(item)

    }

}