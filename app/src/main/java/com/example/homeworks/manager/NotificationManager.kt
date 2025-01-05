package com.example.homeworks.manager

import android.content.Context
import com.example.homeworks.utils.Constants
import com.example.homeworks.utils.NotificationHandler

class NotificationManager(
    private val appCtx: Context
) {

    private var notificationHandler : NotificationHandler ?= null

    fun initNotifications() {
        appCtx.let { appCtx ->
            if (notificationHandler == null) {
                notificationHandler = NotificationHandler(appCtx = appCtx)
            }
        }
    }

    fun showNotification(
        titleNotification: String,
        messageNotification: String,
        importance: Int
    ) {
        notificationHandler?.showNotification(
            channelId = Constants.SAMPLE_CHANNEL,
            notificationId = Constants.NOTIFICATION_ID,
            titleNotification = titleNotification,
            messageNotification = messageNotification,
            importance = importance
        )
    }

}