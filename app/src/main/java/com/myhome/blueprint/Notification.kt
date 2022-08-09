package com.myhome.blueprint

import android.content.Context
import com.myhome.service.notification.Channel
import com.myhome.service.notification.NotificationService

data class Notification(val title: String, val desc: String, val channel: Channel) {

    fun createNotification(context: Context) {
        val notificationService = NotificationService()
        notificationService.create(context, this)
    }
}
