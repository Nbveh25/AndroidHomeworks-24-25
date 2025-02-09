package com.example.homeworks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.homeworks.data.entities.CarEntity
import com.example.homeworks.databinding.ItemCarBinding
import com.example.homeworks.ui.viewholder.CarViewHolder

class CarsAdapter(
    private val glide: RequestManager,
    private val onItemClick: (CarEntity) -> Unit,
    private val onLongItemClick: (CarEntity) -> Unit
) : ListAdapter<CarEntity, CarViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            ItemCarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide,
            onItemClick,
            onLongItemClick
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<CarEntity>() {
        override fun areItemsTheSame(oldItem: CarEntity, newItem: CarEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CarEntity, newItem: CarEntity): Boolean {
            return oldItem == newItem
        }
    }
} 