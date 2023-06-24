package com.maruf.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.maruf.todo.R
import com.maruf.todo.data.local.Todo
import com.maruf.todo.databinding.ItemTodoBinding
import com.maruf.todo.utils.strike

class TodoAdapter(val todoLongPressListener: TodoLongPressListener) :
    PagingDataAdapter<Todo, TodoAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    interface TodoLongPressListener {
        fun todoCompleted(todo: Todo)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean =
                oldItem == newItem
        }
    }

    inner class ItemViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let { todo ->
            holder.binding.apply {
                title.text = todo.title
                manageCompleted(todo, holder)

                holder.binding.checkeImg.load(R.drawable.circle_shadow)
                holder.binding.title.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.textPrimaryColor
                    )
                )

            }

            holder.itemView.setOnLongClickListener {
                todoLongPressListener.todoCompleted(todo)

               manageCompleted(todo, holder)

                return@setOnLongClickListener true
            }
        }


    }


    private fun manageCompleted(todo: Todo, holder: ItemViewHolder) {
        if (todo.isCompleted) {
            holder.binding.checkeImg.load(R.drawable.ic_checkmark)
            holder.binding.title.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.textSecondaryColor
                )
            )
            holder.binding.title.strike = true
        } else {
            holder.binding.checkeImg.load(R.drawable.circle_shadow)
            holder.binding.title.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.textPrimaryColor
                )
            )
            holder.binding.title.strike = false
        }


    }
}