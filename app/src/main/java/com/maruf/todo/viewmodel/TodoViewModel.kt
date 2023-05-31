package com.maruf.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruf.todo.data.local.Todo
import com.maruf.todo.repo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepo: TodoRepository
) : ViewModel() {
    val todoData = todoRepo.data

    fun insertTodoVM(todo: Todo) {
        viewModelScope.launch {
            todoRepo.insertTodoRepo(todo)
        }

    }

    fun updateTodoVM(todo: Todo) {
        viewModelScope.launch {
            todoRepo.updateTodoRepo(todo)
        }
    }


}

