package com.futurebrains.retrofitnoteexample.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.futurebrains.retrofitnoteexample.databinding.NoteDesignBinding
import com.futurebrains.retrofitnoteexample.utils.models.Model

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(val items : NoteDesignBinding) : RecyclerView.ViewHolder(items.root)

    private val diffcallback  = object : DiffUtil.ItemCallback<Model>(){
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem == newItem
        }
    }



    private val differ = AsyncListDiffer(this,diffcallback)

    var TodoItems : List<Model>
    get() = differ.currentList
    set(value) { differ.submitList(value)}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(NoteDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.items.apply {

            val todolist = TodoItems[position]
            tvTitle.text = todolist.title
            cbCheck.isChecked = todolist.completed
        }
    }

    override fun getItemCount(): Int = TodoItems.size
}