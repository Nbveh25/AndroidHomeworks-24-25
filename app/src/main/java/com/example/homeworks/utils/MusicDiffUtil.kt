package com.example.homeworks.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.homeworks.model.MultipleHoldersData
import com.example.homeworks.model.MusicHoldersData

class MusicDiffUtil(
    private val oldList: List<MultipleHoldersData>,
    private val newList: List<MultipleHoldersData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        if (oldItem is MusicHoldersData && newItem is MusicHoldersData) {
            return oldItem.title == newItem.title && oldItem.singer == newItem.singer && oldItem.desc == newItem.desc && oldItem.imageUrl == newItem.imageUrl
        }
        return false
    }
}