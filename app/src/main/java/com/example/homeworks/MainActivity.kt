package com.example.homeworks

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.homeworks.adapter.ViewPagerAdapter
import com.example.homeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupViewPager()
        initButtons()
    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter(this)
        viewBinding.viewPager2.adapter = viewPagerAdapter

        viewBinding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateQuestionCounter(position)
                updateButtonsState(position)
            }
        })
    }

    private fun initButtons() {
        with(viewBinding) {
            prevBtn.setOnClickListener {
                val currentItem = viewPager2.currentItem
                if (currentItem > 0) {
                    viewPager2.currentItem = currentItem - 1
                }
            }

            nextBtn.setOnClickListener {
                val currentItem = viewPager2.currentItem
                if (currentItem < viewPagerAdapter.itemCount - 1) {
                    viewPager2.currentItem = currentItem + 1
                } else {
                    finishTest()
                }
            }
        }
    }

    private fun updateQuestionCounter(position: Int) {
        with(viewBinding) {
            curQuestion.text = (position + 1).toString()
            maxCountQuestion.text = viewPagerAdapter.itemCount.toString()
        }
    }

    private fun updateButtonsState(position: Int) {
        with(viewBinding) {
            prevBtn.isEnabled = position > 0

            if (position == viewPagerAdapter.itemCount - 1) {
                nextBtn.text = getString(R.string.complete)
                nextBtn.setBackgroundColor(Color.GREEN)
            } else {
                nextBtn.text = getString(R.string.next)
                nextBtn.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.blue))
            }
        }
    }

    private fun finishTest() {
        Toast.makeText(this, getString(R.string.test_completed), Toast.LENGTH_SHORT).show()
    }
}