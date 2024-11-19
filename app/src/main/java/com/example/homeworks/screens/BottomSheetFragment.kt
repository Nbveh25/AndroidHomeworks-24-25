package com.example.homeworks.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.homeworks.R
import com.example.homeworks.databinding.DialogBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment(R.layout.dialog_bottom_sheet) {

    private lateinit var viewBinding : DialogBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DialogBottomSheetBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            addElemBtn.setOnClickListener {
                val countOfElements : String =  countElemEt.text.toString()

                if (countOfElements.isNotEmpty()) {
                    val resultBundle = Bundle().apply {
                        putString(MODE, "ADD_ELEMENTS")
                        putInt(COUNT_OF_ELEMENTS, countOfElements.toInt())
                    }
                    parentFragmentManager.setFragmentResult(REQUEST_KEY, resultBundle)
                    dismiss()
                } else {
                    Toast.makeText(context, "Введите корректное кол-во", Toast.LENGTH_SHORT).show()
                }
            }

            deleteElemBtn.setOnClickListener {
                val countOfElements : String = countElemEt.text.toString()

                if (countOfElements.isNotEmpty()) {
                    val resultBundle = Bundle().apply {
                        putString(MODE, "DELETE_ELEMENTS")
                        putInt(COUNT_OF_ELEMENTS, countOfElements.toInt())
                    }
                    parentFragmentManager.setFragmentResult(REQUEST_KEY, resultBundle)
                    dismiss()
                } else {
                    Toast.makeText(context, "Введите корректное кол-во", Toast.LENGTH_SHORT).show()
                }
            }

            addElemOnRanPosBtn.setOnClickListener {
                val resultBundle = Bundle().apply {
                    putString(MODE, "ADD_ELEM_ON_RANDOM_POS")
                }
                parentFragmentManager.setFragmentResult(REQUEST_KEY, resultBundle)
                dismiss()
            }

            deleteElemOnRanPosBtn.setOnClickListener {
                val resultBundle = Bundle().apply {
                    putString(MODE, "DELETE_ELEM_ON_RANDOM_POS")
                }
                parentFragmentManager.setFragmentResult(REQUEST_KEY, resultBundle)
                dismiss()
            }
        }
    }

    companion object {
        const val BOTTOM_SHEET_DIALOG = "BOTTOM_SHEET_DIALOG"
        const val REQUEST_KEY = "REQUEST_KEY"
        const val COUNT_OF_ELEMENTS = "COUNT_OF_ELEMENTS"
        const val MODE = "MODE"
        const val ADD_ELEMENTS = "ADD_ELEMENTS"
        const val DELETE_ELEMENTS = "DELETE_ELEMENTS"
        const val ADD_ELEM_ON_RANDOM_POS = "ADD_ELEM_ON_RANDOM_POS"
        const val DELETE_ELEM_ON_RANDOM_POS = "DELETE_ELEM_ON_RANDOM_POS"
    }

}