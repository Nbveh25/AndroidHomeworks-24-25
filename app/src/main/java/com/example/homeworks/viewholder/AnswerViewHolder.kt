package com.example.homeworks.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.R
import com.example.homeworks.databinding.ItemAnswerBinding
import com.example.homeworks.model.Answer

class AnswerViewHolder(
    private val viewBinding: ItemAnswerBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bindItem(answer: Answer, isSelected: Boolean, onClick: () -> Unit) {
        viewBinding.radioButton.text = answer.answer
        viewBinding.radioButton.isChecked = isSelected

        viewBinding.cardView.setCardBackgroundColor(
            if (isSelected) {
                viewBinding.root.context.resources.getColor(R.color.colorSelected, null)
            } else {
                viewBinding.root.context.resources.getColor(R.color.white, null)
            }
        )

        viewBinding.radioButton.setOnClickListener {
            onClick()
        }
    }
}