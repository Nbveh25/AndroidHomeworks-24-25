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

    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewPagerAdapter = ViewPagerAdapter(this)

        viewPager = viewBinding.viewPager2
        viewPager.adapter = viewPagerAdapter

        initButtons()
        setupViewPager()
    }


    private fun initButtons() {
        with(viewBinding) {
            prevBtn.setOnClickListener {
                if (viewPager2.currentItem > 0) {
                    viewPager2.currentItem -= 1
                }
            }

            nextBtn.setOnClickListener {
                if (viewPager2.currentItem < viewPagerAdapter.itemCount - 1) {
                    viewPager2.currentItem += 1
                } else {
                    // Обработка завершения теста
                    finishTest()
                }
            }
        }
    }

    private fun setupViewPager() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewBinding.curQuestion.text = (position+1).toString()
                updateButtonsState()
            }
        })

    }

    private fun updateButtonsState() {
        with(viewBinding) {
            if (viewPager2.currentItem == 0) {
                prevBtn.isEnabled = false
            } else {
                prevBtn.isEnabled = true
            }

            if (viewPager2.currentItem == 4) {
                nextBtn.text = getText(R.string.complete)
                nextBtn.setBackgroundColor(Color.GREEN)
            } else {
                nextBtn.text = getText(R.string.next)
                nextBtn.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.blue))
            }
        }
    }

    private fun finishTest() {
        Toast.makeText(this, "Тест завершен", Toast.LENGTH_SHORT).show()
    }
}