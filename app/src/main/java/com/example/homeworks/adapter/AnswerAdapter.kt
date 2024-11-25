package com.example.homeworks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.databinding.ItemAnswerBinding
import com.example.homeworks.model.Answer

class AnswerAdapter(
    private val answers: List<Answer>
) : RecyclerView.Adapter<AnswerViewHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAnswerBinding.inflate(inflater, parent, false)
        return AnswerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val answer = answers[position]
        holder.bindItem(answer, position == selectedPosition) {
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = answers.size
}