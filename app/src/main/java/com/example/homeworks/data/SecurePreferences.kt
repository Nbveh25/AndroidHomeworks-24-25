package com.example.homeworks.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object SecurePreferences {
    private const val SECURE_PREFS_FILE = "secure_prefs"
    private const val KEY_USER_ID = "user_id"
    private var prefs: SharedPreferences? = null

    fun init(context: Context) {
        if (prefs == null) {
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            prefs = EncryptedSharedPreferences.create(
                context,
                SECURE_PREFS_FILE,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }

    fun setCurrentUserId(userId: Long) {
        prefs?.edit()?.putLong(KEY_USER_ID, userId)?.apply()
    }

    fun getCurrentUserId(): Long {
        return prefs?.getLong(KEY_USER_ID, -1) ?: -1
    }

    fun clearSession() {
        prefs?.edit()?.remove(KEY_USER_ID)?.apply()
    }
} 