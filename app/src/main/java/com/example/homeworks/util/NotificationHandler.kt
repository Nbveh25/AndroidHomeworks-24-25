package com.example.homeworks.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.homeworks.MainActivity
import com.example.homeworks.R

class NotificationHandler(
    private val appCtx: Context
) {

    private val notificationManager: NotificationManager =
        appCtx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(
        channelId: String,
        notificationId: Int,
        titleNotification: String,
        messageNotification: String,
        importance: Int
    ) {
        createNotificationChannel(channelId, importance)

        val activityIntent = Intent(appCtx, MainActivity::class.java)
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val pendingIntent = PendingIntent.getActivity(
            appCtx,
            0,
            activityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(appCtx, channelId)
            .setSmallIcon(R.drawable.ic_notifications_24)
            .setContentTitle(titleNotification)
            .setContentText(messageNotification)
            .setContentIntent(pendingIntent)

        notificationManager.notify(notificationId, builder.build())
    }


    private fun createNotificationChannel(channelId: String, importance: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                Constants.MESSAGE_CHANNEL,
                when (importance) {
                    0 -> NotificationManager.IMPORTANCE_DEFAULT
                    1 -> NotificationManager.IMPORTANCE_MAX
                    2 -> NotificationManager.IMPORTANCE_HIGH
                    3 -> NotificationManager.IMPORTANCE_LOW
                    else -> NotificationManager.IMPORTANCE_DEFAULT
                }
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}
