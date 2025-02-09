package com.example.homeworks

import NavigationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworks.screens.LoginFragment
import com.example.homeworks.screens.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val isLoggedIn = (application as App).isUserLoggedIn()
            
            if (isLoggedIn) {
                NavigationManager.navigateToFragment(
                    fragmentManager = supportFragmentManager,
                    fragment = MainFragment.getInstance()
                )
            } else {
                NavigationManager.navigateToFragment(
                    fragmentManager = supportFragmentManager,
                    fragment = LoginFragment.getInstance()
                )
            }
        }
    }
}