package com.example.homeworks.screens

import NavigationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.homeworks.App
import com.example.homeworks.R
import com.example.homeworks.data.repository.CarRepository
import com.example.homeworks.databinding.FragmentMainBinding
import com.example.homeworks.ui.adapter.CarsAdapter
import kotlinx.coroutines.launch
import com.example.homeworks.data.SecurePreferences
import com.example.homeworks.data.entities.CarEntity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.appcompat.widget.SearchView


class MainFragment : Fragment(R.layout.fragment_main) {
    private var viewBinding: FragmentMainBinding? = null
    private var originalList: List<CarEntity> = emptyList()
    private var adapter: CarsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentMainBinding.bind(view)


        viewBinding?.run {
            btnExit.setOnClickListener {
                (requireActivity().application as App).saveAuthState(false)
                SecurePreferences.clearSession()

                NavigationManager.navigateToFragment(
                    fragmentManager = requireActivity().supportFragmentManager,
                    fragment = LoginFragment.getInstance()
                )
            }

            btnAdd.setOnClickListener {
                NavigationManager.navigateToFragment(
                    fragmentManager = requireActivity().supportFragmentManager,
                    fragment = AddContentFragment.getInstance(),
                    addToBackStack = true
                )
            }

            adapter = CarsAdapter(
                Glide.with(this@MainFragment),
                onItemClick = { car ->
                    Toast.makeText(
                        requireContext(),
                        "${car.brand} ${car.model}",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onLongItemClick = { car ->
                    showDeleteDialog(car)
                }
            )

            rvCars.adapter = adapter
            rvCars.layoutManager = GridLayoutManager(requireContext(), 2)

            setupSearchView()

            val userId = SecurePreferences.getCurrentUserId()
            if (userId == -1L) {
                Toast.makeText(requireContext(), R.string.error_user_not_found, Toast.LENGTH_SHORT).show()
                return
            }

            lifecycleScope.launch {
                try {
                    val db = (requireActivity().application as App).getDatabase()
                    val carRepository = CarRepository(db.carDao)

                    carRepository.getCarsByUserId(userId)
                        .collect { cars ->
                            originalList = cars
                            adapter?.submitList(cars)
                        }
                } catch (e: Exception) {
                    Toast.makeText(
                        requireContext(),
                        e.message ?: R.string.error_loading_data.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setupSearchView() {
        viewBinding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterCars(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterCars(newText)
                return true
            }
        })
    }

    private fun filterCars(query: String?) {
        if (query.isNullOrBlank()) {
            adapter?.submitList(originalList)
            return
        }

        val filteredList = originalList.filter { car ->
            car.brand.contains(query, ignoreCase = true) ||
                    car.model.contains(query, ignoreCase = true)
        }
        adapter?.submitList(filteredList)
    }

    private fun showDeleteDialog(car: CarEntity) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.car_deleting)
            .setMessage(getString(R.string.delete_car_confirmation, car.brand, car.model))
            .setPositiveButton(R.string.delete) { _, _ ->
                deleteCar(car)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun deleteCar(car: CarEntity) {
        lifecycleScope.launch {
            try {
                val db = (requireActivity().application as App).getDatabase()
                val carRepository = CarRepository(db.carDao)
                carRepository.deleteCar(car)

                Toast.makeText(
                    requireContext(),
                    R.string.car_deleted,
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    e.message ?: R.string.error_delete.toString(),
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
        fun getInstance(): MainFragment = MainFragment()
    }
}