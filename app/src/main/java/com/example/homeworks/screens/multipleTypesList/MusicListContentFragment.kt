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

        val buttonLayoutManager = GridLayoutManager(requireContext(), 3)
        viewBinding.mainRecycler.layoutManager = buttonLayoutManager
        viewBinding.mainRecycler.adapter = musicAdapter
    }

    private fun onButtonClicked(buttonData: ButtonHoldersData) {
        when (buttonData.id) {
            "List" -> {
                currentSpanCount = 1
            }
            "Grid" -> {
                currentSpanCount = 3
            }
        }

        val musicLayoutManager = GridLayoutManager(requireContext(), currentSpanCount)

        viewBinding.mainRecycler.layoutManager = musicLayoutManager

        viewBinding.mainRecycler.adapter?.notifyDataSetChanged()
    }
}