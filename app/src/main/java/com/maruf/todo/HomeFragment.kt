package com.maruf.todo

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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

        val adapter = TodoAdapter(this)
        binding.recyclerView.adapter = adapter.withLoadStateFooter(LoaderAdapter())
        lifecycleScope.launch {
            viewModel.todoData.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun setupNavigation() {
        binding.insertBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createTodoFragment)
        }
    }




    override fun todoCompleted(todo: Todo) {
        todo.isCompleted = !todo.isCompleted
        viewModel.updateTodoVM(todo)
        Toast.makeText(requireActivity(), "${todo.title} is Updated", Toast.LENGTH_SHORT).show()
    }
}


