package com.example.homeworks

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworks.manager.ImageManager
import com.example.homeworks.screens.MainFragment
import com.example.homeworks.util.Constants
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private var imageManager: ImageManager ?= null
    private var imagePickLauncher: ActivityResultLauncher<Intent>? = null
    private var permissionLauncher: ActivityResultLauncher<String>? = null
    private var currentTheme = R.style.Theme_HomeWorks

    override fun onCreate(savedInstanceState: Bundle?) {
        // Применяем тему
        setTheme(currentTheme)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupImagePicking()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, MainFragment.getInstance())
                .commit()
        }
    }

    private fun setupImagePicking() {
        imagePickLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                (supportFragmentManager.findFragmentById(R.id.main_fragment_container) as? MainFragment)?.handleImageUri(uri)
            }
        }

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                imageManager?.openGallery()
            } else {
                Toast.makeText(this, Constants.PERMISSION_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }

        imagePickLauncher?.let { imageLauncher ->
            permissionLauncher?.let { permissionLauncher ->
                imageManager = ImageManager(this, imageLauncher, permissionLauncher)
            }
        }
    }

    fun pickImage() {
        imageManager?.openGallery()
    }

    fun setAppTheme(theme: Int) {
        currentTheme = theme
        updateTheme(theme)
    }

    private fun updateTheme(themeId: Int) {
        val context = ContextThemeWrapper(this, themeId)
        val window = window

        val backgroundColor = TypedValue().apply {
            context.theme.resolveAttribute(android.R.attr.windowBackground, this, true)
        }.data
        window.decorView.setBackgroundColor(backgroundColor)

        updateViewColors(window.decorView, context.theme)
    }

    private fun updateViewColors(view: View, theme: Resources.Theme) {
        when (view) {
            is ViewGroup -> {
                for (i in 0 until view.childCount) {
                    updateViewColors(view.getChildAt(i), theme)
                }
            }
            is Button -> {
                val colorPrimary = TypedValue().apply {
                    theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, this, true)
                }.data
                view.setBackgroundColor(colorPrimary)
            }
            is TextInputLayout -> {
                val colorPrimary = TypedValue().apply {
                    theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, this, true)
                }.data
                view.boxStrokeColor = colorPrimary
                view.hintTextColor = ColorStateList.valueOf(colorPrimary)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        imagePickLauncher = null
        permissionLauncher = null
        imageManager = null
    }
}