package com.example.homeworks.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.homeworks.MainActivity
import com.example.homeworks.fragment.QuestionFragment
import com.example.homeworks.repository.QuestionRepository

class ViewPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = QuestionRepository.getSize()

    override fun createFragment(position: Int): Fragment {
        return if (position < 0 || position > QuestionRepository.getSize()) {
            throw IllegalArgumentException("Invalid position: ${position}}")
        } else {
            QuestionFragment.getInstance(position)
        }
    }
}