package com.example.homeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworks.databinding.ActivityMainBinding
import com.example.homeworks.screens.multipleTypesList.MusicListContentFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private val mainContainerId: Int = R.id.main_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(mainContainerId, MusicListContentFragment())
                .commit()
        }

    }
}