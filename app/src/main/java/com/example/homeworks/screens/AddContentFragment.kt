package com.example.homeworks.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.homeworks.App
import com.example.homeworks.R
import com.example.homeworks.data.entities.CarEntity
import com.example.homeworks.data.repository.CarRepository
import com.example.homeworks.data.SecurePreferences
import com.example.homeworks.databinding.FragmentAddContentBinding
import kotlinx.coroutines.launch

class AddContentFragment : Fragment(R.layout.fragment_add_content) {
    private var viewBinding: FragmentAddContentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentAddContentBinding.bind(view)

        viewBinding?.run {
            btnSave.setOnClickListener {
                val brand = etBrand.text.toString()
                val model = etModel.text.toString()
                val imageUrl = etImageUrl.text.toString()

                if (brand.isBlank() || model.isBlank() || imageUrl.isBlank()) {
                    Toast.makeText(requireContext(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                saveCar(brand, model, imageUrl)
            }
        }
    }

    private fun saveCar(brand: String, model: String, imageUrl: String) {
        lifecycleScope.launch {
            try {
                val db = (requireActivity().application as App).getDatabase()
                val carRepository = CarRepository(db.carDao)

                val userId = SecurePreferences.getCurrentUserId()
                if (userId == -1L) {
                    Toast.makeText(requireContext(), R.string.error_user_not_found, Toast.LENGTH_SHORT).show()
                    return@launch
                }

                val car = CarEntity(
                    brand = brand,
                    model = model,
                    imageUrl = imageUrl,
                    userId = userId
                )

                carRepository.addCar(car)

                Toast.makeText(requireContext(), R.string.car_added, Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.popBackStack()
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    e.message ?: R.string.error_save.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    companion object {
        fun getInstance(): AddContentFragment = AddContentFragment()
    }
}