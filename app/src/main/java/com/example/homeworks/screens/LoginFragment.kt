package com.example.homeworks.screens

import NavigationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.homeworks.App
import com.example.homeworks.R
import com.example.homeworks.data.repository.UserRepository
import com.example.homeworks.data.SecurePreferences
import com.example.homeworks.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var viewBinding: FragmentLoginBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentLoginBinding.bind(view)

        viewBinding?.run {
            btnLogin.setOnClickListener {
                val name = etName.text.toString()
                val password = etPassword.text.toString()

                if (name.isBlank() || password.isBlank()) {
                    Toast.makeText(requireContext(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                loginUser(name, password)
            }

            tvRegister.setOnClickListener {
                NavigationManager.navigateToFragment(
                    fragmentManager = requireActivity().supportFragmentManager,
                    fragment = RegisterFragment.getInstance(),
                    addToBackStack = true
                )
            }
        }
    }

    private fun loginUser(name: String, password: String) {
        lifecycleScope.launch {
            try {
                val db = (requireActivity().application as App).getDatabase()
                val userRepository = UserRepository(db.userDao)
                
                val user = userRepository.getUserByNameAndPass(name, password)

                (requireActivity().application as App).saveAuthState(true)
                SecurePreferences.setCurrentUserId(user.id)

                NavigationManager.navigateToFragment(
                    fragmentManager = requireActivity().supportFragmentManager,
                    fragment = MainFragment.getInstance(),
                    addToBackStack = false
                )
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    e.message ?: R.string.error_signin.toString(),
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
        fun getInstance(): LoginFragment = LoginFragment()
    }
}