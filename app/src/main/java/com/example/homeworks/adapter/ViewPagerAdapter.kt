package com.example.homeworks.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.homeworks.MainActivity
import com.example.homeworks.fragment.QuestionFragment

class ViewPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return if (position < 0 || position > 5) {
            throw IllegalArgumentException("Invalid position: ${position}}")
        } else {
            QuestionFragment.getInstance(position)
        }
    }
}