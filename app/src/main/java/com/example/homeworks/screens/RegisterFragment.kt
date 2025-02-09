package com.example.homeworks.screens

import NavigationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.homeworks.App
import com.example.homeworks.R
import com.example.homeworks.data.entities.UserEntity
import com.example.homeworks.data.repository.UserRepository
import com.example.homeworks.data.SecurePreferences
import com.example.homeworks.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var viewBinding: FragmentRegisterBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentRegisterBinding.bind(view)

        viewBinding?.run {
            btnRegister.setOnClickListener {
                val name = etName.text.toString()
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                if (name.isBlank() || email.isBlank() || password.isBlank()) {
                    Toast.makeText(requireContext(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                registerUser(name, email, password)
            }
        }
    }

    private fun registerUser(name: String, email: String, password: String) {
        lifecycleScope.launch {
            try {
                val db = (requireActivity().application as App).getDatabase()
                val userRepository = UserRepository(db.userDao)

                try {
                    userRepository.getUserByName(name)
                    Toast.makeText(
                        requireContext(),
                        R.string.user_with_same_name_exist,
                        Toast.LENGTH_SHORT
                    ).show()
                    return@launch
                } catch (e: Exception) {
                    userRepository.register(name, email, password)
                    val user = userRepository.getUserByNameAndPass(name, password)

                    (requireActivity().application as App).run {
                        saveAuthState(true)
                        SecurePreferences.setCurrentUserId(user.id)
                    }

                    NavigationManager.navigateToFragment(
                        fragmentManager = requireActivity().supportFragmentManager,
                        fragment = MainFragment.getInstance(),
                        addToBackStack = false
                    )

                    Toast.makeText(requireContext(), R.string.registration_success, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    e.message ?: R.string.error_registration.toString(),
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
        fun getInstance(): RegisterFragment = RegisterFragment()
    }
}