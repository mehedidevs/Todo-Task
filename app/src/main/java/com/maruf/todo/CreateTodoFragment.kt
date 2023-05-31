package com.maruf.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maruf.todo.data.local.Todo
import com.maruf.todo.databinding.FragmentCreateTodoBinding
import com.maruf.todo.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateTodoFragment : BaseFragment<FragmentCreateTodoBinding>() {
    private val viewModel by viewModels<TodoViewModel>()

    override fun getFragmentView(): Int {
        return R.layout.fragment_create_todo
    }

    override fun configUi() {
        binding.addTodoBtn.setOnClickListener {
            viewModel.insertTodoVM(Todo(0, binding.titleTodo.text.toString(), false))
            findNavController().popBackStack()
        }
        binding.cancelBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}