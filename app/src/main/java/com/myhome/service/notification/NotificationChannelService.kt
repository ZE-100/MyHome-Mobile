package com.myhome.service.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.myhome.R

class NotificationChannelService {

    fun createDebugChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.debug_channel_name)
            val descriptionText = context.getString(R.string.debug_channel_desc)
            val importance = NotificationManager.IMPORTANCE_HIGH

            val dChannel = NotificationChannel(name, name, importance)
            dChannel.description = descriptionText

            val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(dChannel)
        }
    }

    fun createMealChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.meal_channel_name)
            val descriptionText = context.getString(R.string.meal_channel_desc)
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(name, name, importance)
            mChannel.description = descriptionText

            val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}