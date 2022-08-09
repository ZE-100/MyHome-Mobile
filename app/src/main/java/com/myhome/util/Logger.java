package com.myhome.util;

import android.annotation.SuppressLint;

import com.myhome.service.api.constants.ApiConst;
import com.myhome.other.Session;
import com.myhome.util.debug.DebugMode;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author z-100
 * Custom logger used to print to console
 */
public class Logger {

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void log(ApiConst type, Class location, String... messages) {

        if (Session.Factory.debugMode() != DebugMode.DEACTIVATED) {
            String current = sdf3.format(new Timestamp(System.currentTimeMillis()));

            StringBuilder message = new StringBuilder();
            for (String s : messages)
                message.append(s).append(" | ");

            System.out.printf("%s - [%s] at %s\t- %s%n",
                    current, type.value(), location , message.toString());
        }
    }
}
