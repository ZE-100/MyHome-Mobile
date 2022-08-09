package com.myhome.service.notification

import com.myhome.R

enum class Channel(private var id: Int) {

    DEBUG_CHANNEL(R.string.debug_channel_name),
    MEAL_CHANNEL(R.string.meal_channel_name);

    fun value(): Int {
        return id
    }
}