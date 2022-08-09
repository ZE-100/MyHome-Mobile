package com.myhome.blueprint

import android.content.Context
import com.myhome.service.notification.Channel
import com.myhome.service.notification.NotificationService

data class Notification(val title: String, val desc: String, val channel: Channel) {

    private val notificationService = NotificationService()

    fun createNotification(context: Context) {
        notificationService.create(context, this)
    }
}
