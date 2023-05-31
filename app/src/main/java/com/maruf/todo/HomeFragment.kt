package com.maruf.todo

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.maruf.todo.adapter.TodoAdapter
import com.maruf.todo.adapter.LoaderAdapter
import com.maruf.todo.data.local.Todo
import com.maruf.todo.databinding.FragmentHomeBinding
import com.maruf.todo.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), TodoAdapter.TodoLongPressListener {
    private val viewModel by viewModels<TodoViewModel>()
    override fun getFragmentView(): Int {
        return R.layout.fragment_home
    }


    override fun configUi() {
        for (i in 1..100) {
            val todo: Todo = Todo(0, "Item- $i", false)
            viewModel.insertTodoVM(todo)
        }

        val adapter = TodoAdapter(this)
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            LoaderAdapter()
        )


        lifecycleScope.launch {
            viewModel.todoData.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun todoCompleted(todo: Todo) {
        todo.isCompleted = !todo.isCompleted

        viewModel.updateTodoVM(todo)
        Toast.makeText(requireActivity(), "${todo.title} is Updated", Toast.LENGTH_SHORT).show()
    }
}


