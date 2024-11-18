package com.example.homeworks.screens.multipleTypesList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.homeworks.R
import com.example.homeworks.adapter.recycler.AdapterWithMultipleHolders
import com.example.homeworks.databinding.FragmentListContentBinding
import com.example.homeworks.model.ButtonHoldersData
import com.example.homeworks.repository.ScreenRepository

class MusicListContentFragment : Fragment(R.layout.fragment_list_content) {
    private lateinit var viewBinding: FragmentListContentBinding
    private var currentSpanCount = 3

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentListContentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val glide = Glide.with(requireContext())
        initRecyclerView(requestManager = glide)
    }

    private fun initRecyclerView(requestManager: RequestManager) {
        val dataList = ScreenRepository.getListForMultipleTypes(ctx = requireContext())

        val musicAdapter = AdapterWithMultipleHolders(
            requestManager = requestManager,
            items = dataList,
        ) { buttonData ->
            onButtonClicked(buttonData)
        }

        val layoutManager = GridLayoutManager(requireContext(), currentSpanCount)

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val type = musicAdapter.getItemViewType(position)
                return when (type) {
                    AdapterWithMultipleHolders.TYPE_BUTTON -> 1
                    AdapterWithMultipleHolders.TYPE_MUSIC -> currentSpanCount
                    else -> 1
                }
            }
        }

        with(viewBinding) {
            mainRecycler.adapter = musicAdapter
            mainRecycler.layoutManager = layoutManager
        }
    }

    private fun onButtonClicked(buttonData: ButtonHoldersData) {
        when (buttonData.id) {
            "List" -> {
                currentSpanCount = 1
                Log.e("MusicListContentFragment", "Changed to List view")
            }
            "Grid" -> {
                currentSpanCount = 3
                Log.e("MusicListContentFragment", "Changed to Grid view")
            }
        }

        val layoutManager = viewBinding.mainRecycler.layoutManager as GridLayoutManager
        layoutManager.spanCount = currentSpanCount

        viewBinding.mainRecycler.adapter?.notifyDataSetChanged()
    }
}