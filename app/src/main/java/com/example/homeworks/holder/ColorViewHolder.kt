package com.example.homeworks.holder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.databinding.ItemColorBinding

class ColorViewHolder(
    private val binding: ItemColorBinding,
    onColorSelected: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onColorSelected(adapterPosition) // Передаем позицию элемента
        }
    }

    fun bind(color: Int) {
        binding.colorSquare.setBackgroundColor(color) // Устанавливаем цвет
    }
}