package com.example.homeworks.screens.multipleTypesList

import android.os.Bundle
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
import com.example.homeworks.model.MusicHoldersData
import com.example.homeworks.repository.ScreenRepository
import com.example.homeworks.screens.BottomSheetFragment
import com.example.homeworks.screens.DescriptionFragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.repository.DataRepository

class MusicListContentFragment : Fragment(R.layout.fragment_list_content) {
    private lateinit var viewBinding: FragmentListContentBinding
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var multipleAdapter: AdapterWithMultipleHolders

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(BottomSheetFragment.REQUEST_KEY) { key, bundle ->
            val action = bundle.getString(BottomSheetFragment.MODE)
            val countOfElements = bundle.getInt(BottomSheetFragment.COUNT_OF_ELEMENTS)

            when (action) {
                BottomSheetFragment.ADD_ELEMENTS -> {
                    addMusicsOnRandomPos(countOfElements)
                }

                BottomSheetFragment.DELETE_ELEMENTS -> {
                    deleteMusicsOnRandomPos(countOfElements)
                }

                BottomSheetFragment.ADD_ELEM_ON_RANDOM_POS -> {
                    addMusicOnRandomPos()
                }

                BottomSheetFragment.DELETE_ELEM_ON_RANDOM_POS -> {
                    deleteMusicOnRandomPos()
                }
            }
        }

    }

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

        viewBinding.floating.setOnClickListener {
            openBottomSheet()
        }
    }

    private fun initRecyclerView(requestManager: RequestManager) {
        val dataList = ScreenRepository.getListForMultipleTypes(ctx = requireContext())

        multipleAdapter = AdapterWithMultipleHolders(
            requestManager = requestManager,
            items = dataList,
            onButtonClick = { buttonData ->
                onButtonClicked(buttonData = buttonData)
            },
            onMusicClick = { musicData ->
                openMusicDescFragment(musicData = musicData)
            }
        )

        layoutManager = GridLayoutManager(requireContext(), 3)

        viewBinding.mainRecycler.layoutManager = layoutManager
        viewBinding.mainRecycler.adapter = multipleAdapter
    }

    private fun spanLookup(type: Int) = object : SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (type) {
                0 -> when (position) {
                    in 0..2 -> 1
                    else -> 3
                }

                else -> 1
            }
        }
    }

    private fun onButtonClicked(buttonData: ButtonHoldersData) {
        layoutManager.spanCount = 3
        layoutManager.spanSizeLookup = spanLookup(buttonData.id)
        viewBinding.mainRecycler.adapter?.notifyDataSetChanged()
    }

    private fun openMusicDescFragment(musicData: MusicHoldersData) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_fragment_container,
                DescriptionFragment.getInstance(
                    musicData.title,
                    musicData.singer,
                    musicData.imageUrl,
                    musicData.desc
                )
            )
            .addToBackStack(null)
            .commit()
    }

    private fun openBottomSheet() {
        val dialog = BottomSheetFragment().apply {
            isCancelable = true
        }

        dialog.show(parentFragmentManager, BottomSheetFragment.BOTTOM_SHEET_DIALOG)
    }

    private fun addMusicsOnRandomPos(countOfElements: Int) {
        val currentList = multipleAdapter.getCurrentList().toMutableList()

        for (i in 0 until countOfElements) {
            //if (currentList.size >= 3) {
                val randomPos = (3 until currentList.size + 1).random()
                val newElem = DataRepository.getRandomElem()
                currentList.add(randomPos, newElem)
                ScreenRepository.addElement(newElem)
            //}
        }

        multipleAdapter.updateData(currentList)
    }

    private fun deleteMusicsOnRandomPos(countOfElements: Int) {
        val currentList = multipleAdapter.getCurrentList().toMutableList()
        val countForRemove = countOfElements.coerceAtMost(currentList.size - 3)

        for (i in 0 until countForRemove) {
            if (currentList.size > 3) {
                val randomPos = (3 until currentList.size).random()
                val elementToRemove = currentList[randomPos]
                currentList.removeAt(randomPos)
                ScreenRepository.removeElement(elementToRemove)
                multipleAdapter.notifyItemRemoved(randomPos)
            }
        }

        multipleAdapter.updateData(currentList)
    }

    private fun addMusicOnRandomPos() {
        val currentList = multipleAdapter.getCurrentList().toMutableList()

        val randomPos = (3 until currentList.size + 1).random()
        val newElem = DataRepository.getRandomElem()
        currentList.add(randomPos, newElem)
        ScreenRepository.addElement(newElem)

        multipleAdapter.updateData(currentList)
    }

    private fun deleteMusicOnRandomPos() {
        val currentList = multipleAdapter.getCurrentList().toMutableList()

        if (currentList.size > 3) {
            val randomPos = (3 until currentList.size).random()
            val elementToRemove = currentList[randomPos]
            currentList.removeAt(randomPos)
            ScreenRepository.removeElement(elementToRemove)
            multipleAdapter.notifyItemRemoved(randomPos)
        }

        multipleAdapter.updateData(currentList)
    }
}