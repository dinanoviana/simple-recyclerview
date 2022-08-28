package com.whitestudio.simplerecyclerview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.whitestudio.simplerecyclerview.databinding.ItemDataBinding
import com.whitestudio.simplerecyclerview.model.Students

class MainAdapter : ListAdapter<Students, MainAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<Students>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    inner class ViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Students) {
            binding.TVCity.text = student.city
            binding.TVName.text = student.name
            binding.TVSchool.text = student.school
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Students>() {
    override fun areItemsTheSame(oldItem: Students, newItem: Students): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Students, newItem: Students): Boolean {
        return oldItem.id == newItem.id
    }
}