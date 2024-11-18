package com.example.homeworks.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.databinding.ButtonItemListBinding
import com.example.homeworks.model.ButtonHoldersData

class ButtonViewHolder(
    private val viewBinding: ButtonItemListBinding,
    private val onClickAction: (ButtonHoldersData) -> Unit
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bindItem(itemData: ButtonHoldersData) {
        viewBinding.btnItemList.text = itemData.text

        viewBinding.btnItemList.setOnClickListener {
            onClickAction(itemData)
        }
    }
}