package com.example.homeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworks.screens.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, MainFragment.getInstance())
                .commit()
        }
    }

}