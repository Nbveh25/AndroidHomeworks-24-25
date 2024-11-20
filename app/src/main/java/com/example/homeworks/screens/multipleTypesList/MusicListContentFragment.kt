package com.example.homeworks.screens.multipleTypesList

import android.app.AlertDialog
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
import com.example.homeworks.enums.ListType
import com.example.homeworks.repository.DataRepository

class MusicListContentFragment : Fragment(R.layout.fragment_list_content) {
    private lateinit var viewBinding: FragmentListContentBinding
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var multipleAdapter: AdapterWithMultipleHolders
    private var itemTouchHelper: ItemTouchHelper? = null
    private var selectedPosition = -1
    private var listType: ListType = ListType.GRID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBottomSheetListener()

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
            },
            onLongClick = { position ->
                if (position >= 3 && listType == ListType.GRID) {
                    selectedPosition = position
                    showDeleteConfirmationDialog()
                    true
                } else {
                    false
                }
            }
        )

        layoutManager = GridLayoutManager(requireContext(), 6)
        layoutManager.spanSizeLookup = spanLookup(0)

        viewBinding.mainRecycler.layoutManager = layoutManager
        viewBinding.mainRecycler.adapter = multipleAdapter
    }

    private fun spanLookup(type: Int) = object : SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (type) {
                0 -> when (position) {
                    in 0..2 -> 2
                    else -> 6
                }

                1 -> 2
                else -> {
                    when {
                        position in 0..2 -> 2
                        position % 4 == 1 || position % 4 == 0 -> 3
                        else -> 6
                    }
                }
            }
        }
    }

    private fun onButtonClicked(buttonData: ButtonHoldersData) {
        layoutManager.spanSizeLookup = spanLookup(buttonData.id)

        listType = when (buttonData.id) {
            0 -> { // List mode
                setupSwipeToDelete()
                ListType.LIST
            }

            1 -> { // Grid mode
                itemTouchHelper?.attachToRecyclerView(null) // Detach swipe functionality
                ListType.GRID
            }

            else -> {
                ListType.OPTIONAL
            }
        }

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
            val randomPos = (3 until currentList.size + 1).random()
            val newElem = DataRepository.getRandomElem()
            currentList.add(randomPos, newElem)
            ScreenRepository.addElement(newElem)
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

    private fun setupSwipeToDelete() {
        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun getSwipeDirs(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ): Int {
                    return if (viewHolder.adapterPosition >= 3) {
                        super.getSwipeDirs(recyclerView, viewHolder)
                    } else {
                        0
                    }
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val currentList = multipleAdapter.getCurrentList().toMutableList()

                    if (position >= 3) {
                        val elementToRemove = currentList[position]
                        currentList.removeAt(position)
                        ScreenRepository.removeElement(elementToRemove)
                        multipleAdapter.updateData(currentList)

                        multipleAdapter.notifyDataSetChanged()
                    } else {
                        multipleAdapter.notifyItemChanged(position)
                    }
                }

                override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
                    return 0.66f
                }
            }

        itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper?.attachToRecyclerView(viewBinding.mainRecycler)
    }

    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.delete_confirmation_title)
            .setMessage(R.string.delete_confirmation_message)
            .setPositiveButton(R.string.delete) { _, _ ->
                deleteSelectedItem()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun deleteSelectedItem() {
        if (selectedPosition >= 3) {
            val currentList = multipleAdapter.getCurrentList().toMutableList()
            val elementToRemove = currentList[selectedPosition]
            currentList.removeAt(selectedPosition)
            ScreenRepository.removeElement(elementToRemove)
            multipleAdapter.updateData(currentList)
            multipleAdapter.notifyDataSetChanged()
        }
        selectedPosition = -1
    }

    fun setBottomSheetListener() {
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

}