package com.example.homeworks.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.homeworks.databinding.MusicItemListBinding
import com.example.homeworks.model.MusicHoldersData
import com.example.homeworks.model.MusicListItemModel

class MusicViewHolder(
    private val viewBinding: MusicItemListBinding,
    private val requestManager: RequestManager
): RecyclerView.ViewHolder(viewBinding.root) {

    fun bindItem(itemData : MusicHoldersData) {
        requestManager
            .load(itemData.imageUrl)
            .into(viewBinding.itemListIv)
        viewBinding.titleItemListTv.text = itemData.title
        viewBinding.singerItemListTv.text = itemData.singer
    }
}