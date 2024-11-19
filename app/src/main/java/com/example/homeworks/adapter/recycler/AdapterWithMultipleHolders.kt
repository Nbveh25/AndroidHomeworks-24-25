package com.example.homeworks.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.homeworks.databinding.ButtonItemListBinding
import com.example.homeworks.databinding.MusicItemListBinding
import com.example.homeworks.model.ButtonHoldersData
import com.example.homeworks.model.MultipleHoldersData
import com.example.homeworks.model.MusicHoldersData
import com.example.homeworks.ui.viewholder.ButtonViewHolder
import com.example.homeworks.ui.viewholder.MusicViewHolder
import com.example.homeworks.utils.MusicDiffUtil

class AdapterWithMultipleHolders(
    private val requestManager: RequestManager,
    private var items: List<MultipleHoldersData>,
    private val onButtonClick: (ButtonHoldersData) -> Unit,
    private val onMusicClick: (MusicHoldersData) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_MUSIC = 0
        const val TYPE_BUTTON = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MusicHoldersData -> TYPE_MUSIC
            is ButtonHoldersData -> TYPE_BUTTON
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_MUSIC -> {
                val binding = MusicItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MusicViewHolder(binding, requestManager, onMusicClick)
            }
            TYPE_BUTTON -> {
                val binding = ButtonItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ButtonViewHolder(binding, onButtonClick)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MusicViewHolder -> holder.bindItem(items[position] as MusicHoldersData)
            is ButtonViewHolder -> holder.bindItem(items[position] as ButtonHoldersData)
        }
    }

    override fun getItemCount(): Int = items.size

    fun getCurrentList() : List<MultipleHoldersData> {
        return items
    }

    fun updateData(newList: List<MultipleHoldersData>) {
        val musicDiffCallback = MusicDiffUtil(
            oldList = items,
            newList = newList
        )
        items = newList
        val diffResult = DiffUtil.calculateDiff(musicDiffCallback)
        diffResult.dispatchUpdatesTo(this)
    }
}