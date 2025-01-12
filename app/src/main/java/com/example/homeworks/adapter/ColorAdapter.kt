package com.example.homeworks.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.databinding.ItemColorBinding
import com.example.homeworks.holder.ColorViewHolder

class ColorAdapter(
    private val context: Context,
    private val onColorSelected: (Int) -> Unit
) : RecyclerView.Adapter<ColorViewHolder>() {

    private val colors = arrayOf(
        Color.BLUE,
        Color.YELLOW,
        Color.RED
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorViewHolder(binding, onColorSelected)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int = colors.size
}