package com.example.homeworks

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.homeworks.databinding.ActivityMainBinding
import com.example.homeworks.manager.ImageManager
import com.example.homeworks.manager.NotificationManager
import com.example.homeworks.manager.ThemeManager
import com.example.homeworks.utils.Constants

class MainActivity : AppCompatActivity() {

    private var viewBinding: ActivityMainBinding? = null
    private var currentImportance = 0
    private var notificationManager: NotificationManager? = null
    private var themeManager: ThemeManager? = null
    private var imageManager: ImageManager? = null
    private var getImageLauncher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        themeManager = ThemeManager(this)

        themeManager?.currentTheme?.let {
            setTheme(it)
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        getImageLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data: Intent? = result.data
                    val uri: Uri? = data?.data

                    uri?.let {
                        viewBinding?.circularImageView?.let { it1 ->
                            Glide.with(this)
                                .load(it)
                                .circleCrop()
                                .into(it1)
                        }
                    }
                }
            }

        notificationManager = NotificationManager(this)
        imageManager = ImageManager(this, getImageLauncher!!)

        setupColorSchemeSpinner()
        setupPrioritySpinner()
        notificationManager?.initNotifications()
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            this?.circularImageView?.setOnClickListener {
                imageManager?.openGallery()
            }

            this?.deleteImageButton?.setOnClickListener {
                Glide.with(this@MainActivity)
                    .load(R.drawable.ic_person_24)
                    .into(this.circularImageView)
            }

            this?.colorSchemeSpinner?.setSelection(themeManager?.getThemePosition() ?: return)

            this?.resetColorButton?.setOnClickListener {
                colorSchemeSpinner.setSelection(0)
                themeManager?.applyColorScheme(window, 0)
            }

            this?.showNotificationButton?.setOnClickListener {
                if (titleEditText.text?.isEmpty() ?: return@setOnClickListener
                    || messageEditText.text?.isEmpty() ?: return@setOnClickListener
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        Constants.EMPTY_FIELDS_MESSAGE,
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    notificationManager?.showNotification(
                        titleNotification = titleEditText.text.toString(),
                        messageNotification = messageEditText.text.toString(),
                        importance = currentImportance
                    )
                }
            }
        }
    }

    private fun setupColorSchemeSpinner() {
        val spinner: Spinner = viewBinding?.colorSchemeSpinner ?: return

        ArrayAdapter.createFromResource(
            this,
            R.array.colors_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                themeManager?.applyColorScheme(window, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                return
            }
        }
    }

    private fun setupPrioritySpinner() {
        val spinner: Spinner = viewBinding?.prioritySpinner ?: return

        ArrayAdapter.createFromResource(
            this,
            R.array.priority_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentImportance = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                return
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            imageManager?.openGallery()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
        notificationManager = null
        themeManager = null
        imageManager = null
        getImageLauncher = null
    }
}
