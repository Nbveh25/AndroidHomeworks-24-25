package com.example.homeworks.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.homeworks.data.entities.CarEntity
import com.example.homeworks.databinding.ItemCarBinding

class CarViewHolder(
    private val binding: ItemCarBinding,
    private val glide: RequestManager,
    private val onItemClick: (CarEntity) -> Unit,
    private val onLongItemClick: (CarEntity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            car?.let(onItemClick)
        }
        binding.root.setOnLongClickListener { 
            car?.let(onLongItemClick)
            true
        }
    }

    private var car: CarEntity? = null

    fun bind(item: CarEntity) {
        car = item
        with(binding) {
            tvBrand.text = item.brand
            tvModel.text = item.model

            glide.load(item.imageUrl)
                .centerCrop()
                .into(ivCar)
        }
    }
} 