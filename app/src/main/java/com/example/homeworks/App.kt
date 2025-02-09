package com.example.homeworks

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.homeworks.data.MyDatabase
import com.example.homeworks.data.SecurePreferences

class App : Application() {
    private lateinit var preferences: SharedPreferences
    private val AUTH = "AUTH"

    private var db: MyDatabase? = null
    private val DB_NAME = "MyDatabase"

    override fun onCreate() {
        super.onCreate()
        preferences = getSharedPreferences(AUTH, Context.MODE_PRIVATE)
        SecurePreferences.init(this)

        if (db == null) {
            db = Room.databaseBuilder(this, MyDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    fun getPreferences(): SharedPreferences = preferences

    fun saveAuthState(isAuth: Boolean) {
        preferences.edit()
            .putBoolean(AUTH_KEY, isAuth)
            .apply()
    }

    fun isUserLoggedIn(): Boolean {
        return preferences.getBoolean(AUTH_KEY, false)
    }

    fun getDatabase(): MyDatabase {
        return db ?: throw IllegalStateException("Database not initialized")
    }

    companion object {
        const val AUTH_KEY = "AUTH"
    }
}