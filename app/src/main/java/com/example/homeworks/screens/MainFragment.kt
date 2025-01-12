package com.example.homeworks.screens

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.homeworks.MainActivity
import com.example.homeworks.R
import com.example.homeworks.adapter.ColorAdapter
import com.example.homeworks.databinding.FragmentMainBinding
import com.example.homeworks.util.Constants
import com.example.homeworks.util.NotificationHandler

class MainFragment : Fragment(R.layout.fragment_main) {

    private var viewBinding: FragmentMainBinding? = null
    private var notificationHandler: NotificationHandler? = null

    private var isColorPickerVisible = false
    private var currentImportance = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentMainBinding.bind(view)

        setupColorPicker()
        setupPrioritySpinner()


        notificationHandler = NotificationHandler(requireContext())
        initViews()
    }

    private fun initViews() {

        with(viewBinding) {

            viewBinding?.deleteImageButton?.setOnClickListener {
                handleImageUri(null)
            }

            viewBinding?.circularImageView?.setOnClickListener {
                (requireActivity() as MainActivity).pickImage()
            }


            viewBinding?.colorSchemeSpinner?.setOnClickListener {
                toggleColorPicker()
            }

            viewBinding?.showNotificationButton?.setOnClickListener {
                val title = this?.titleEditText?.text?.toString()
                val message = this?.messageEditText?.text?.toString()

                if (title.isNullOrEmpty() || message.isNullOrEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        Constants.EMPTY_FIELDS_MESSAGE,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    notificationHandler?.showNotification(
                        Constants.SAMPLE_CHANNEL,
                        Constants.NOTIFICATION_ID,
                        titleNotification = title,
                        messageNotification = message,
                        importance = currentImportance
                    )
                }
            }

            viewBinding?.resetColorButton?.setOnClickListener {
                (requireActivity() as MainActivity).setAppTheme(R.style.Theme_HomeWorks)
            }
        }
    }

    fun handleImageUri(uri: Uri?) {
        viewBinding?.circularImageView?.let {
            Glide.with(this)
                .load(uri)
                .circleCrop()
                .into(it)
        }
    }

    private fun setupColorPicker() {
        viewBinding?.colorRecyclerView?.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val adapter = ColorAdapter(requireContext()) { position ->
            val theme = when (position) {
                0 -> R.style.BlueTheme
                1 -> R.style.YellowTheme
                2 -> R.style.RedTheme
                else -> R.style.GreenTheme
            }

            (requireActivity() as MainActivity).setAppTheme(theme)

            toggleColorPicker()
        }

        viewBinding?.colorRecyclerView?.adapter = adapter
    }

    private fun toggleColorPicker() {
        isColorPickerVisible = !isColorPickerVisible
        viewBinding?.colorPickerCard?.visibility =
            if (isColorPickerVisible) View.VISIBLE else View.GONE
    }

    private fun setupPrioritySpinner() {
        val spinner: Spinner = viewBinding?.prioritySpinner ?: return

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.priority_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentImportance = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                return
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
        notificationHandler = null
    }

    companion object {
        fun getInstance(): MainFragment = MainFragment()
    }
}