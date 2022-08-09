package com.myhome.service.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.myhome.Application
import com.myhome.R
import com.myhome.blueprint.Notification

class NotificationService {

    fun create(context: Context, notification: Notification) {

        val channel = notification.channel.value()
        val title = notification.title
        val desc = notification.desc

        val intent = Intent(context, Application::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, context.getString(channel))
            .setSmallIcon(R.drawable.ic_home) // House icon. TODO: Make generic
            .setContentTitle(title)
            .setContentText(desc)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(channel, builder.build())
        }
    }
}
