package com.example.homeworks.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import com.example.homeworks.R
import com.example.homeworks.screens.component.CoroutineScreen

class MainFragment : BaseFragment(R.layout.fragment_main) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        composeView = ComposeView(requireContext())
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        (view as? FrameLayout)?.addView(composeView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeView?.setContent {
            CoroutineScreen(requireContext())
        }
    }



    override fun onDestroy() {
        composeView = null
        super.onDestroy()
    }

    companion object {
        fun getInstance(): MainFragment = MainFragment()
    }
}