package com.example.homeworks.manager

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

class ImageManager(
    private val appCtx: Context,
    private val getImageLauncher: ActivityResultLauncher<Intent>,
    private val requestPermissionLauncher: ActivityResultLauncher<String>
) {

    fun openGallery() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            android.Manifest.permission.READ_MEDIA_IMAGES
        } else {
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(appCtx, permission) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(permission)
            return
        }

        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        getImageLauncher.launch(intent)
    }
}
