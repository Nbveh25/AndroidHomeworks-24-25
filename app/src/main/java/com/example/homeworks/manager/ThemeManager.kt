package com.example.homeworks.manager

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import com.example.homeworks.R
import com.google.android.material.textfield.TextInputLayout

class ThemeManager(
    private val appCtx: Context
) {
    var currentTheme: Int = R.style.Theme_HomeWorks
        private set

    fun applyColorScheme(window: Window, color: Int) {
        val theme = when (color) {
            0 -> R.style.BlueTheme
            1 -> R.style.RedTheme
            2 -> R.style.GreenTheme
            3 -> R.style.YellowTheme
            else -> R.style.Theme_HomeWorks
        }

        currentTheme = theme

        val themedContext = ContextThemeWrapper(appCtx, theme)
        val backgroundColor = TypedValue().apply {
            themedContext.theme.resolveAttribute(android.R.attr.windowBackground, this, true)
        }.data

        window.decorView.setBackgroundColor(backgroundColor)
        updateViewColors(window.decorView, themedContext.theme)
    }

    fun getThemePosition(): Int {
        return when (currentTheme) {
            R.style.BlueTheme -> 0
            R.style.RedTheme -> 1
            R.style.GreenTheme -> 2
            R.style.YellowTheme -> 3
            else -> 0
        }
    }

    private fun updateViewColors(view: View, theme: Resources.Theme) {
        if (view.id == R.id.deleteImageButton) {
            val colorPrimary = TypedValue().apply {
                theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, this, true)
            }.data
            view.setBackgroundColor(colorPrimary)
        } else {

            when (view) {
                is ViewGroup -> {
                    for (i in 0 until view.childCount) {
                        updateViewColors(view.getChildAt(i), theme)
                    }
                }

                is Button -> {
                    val colorPrimary = TypedValue().apply {
                        theme.resolveAttribute(
                            com.google.android.material.R.attr.colorPrimary,
                            this,
                            true
                        )
                    }.data
                    view.setBackgroundColor(colorPrimary)
                }

                is TextInputLayout -> {
                    val colorPrimary = TypedValue().apply {
                        theme.resolveAttribute(
                            com.google.android.material.R.attr.colorPrimary,
                            this,
                            true
                        )
                    }.data
                    view.boxStrokeColor = colorPrimary
                    view.hintTextColor = ColorStateList.valueOf(colorPrimary)
                }

            }
        }
    }
}