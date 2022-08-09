package com.myhome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myhome.blueprint.Notification;
import com.myhome.databinding.ActivityMainBinding;
import com.myhome.other.Session;
import com.myhome.other.SharedPref;
import com.myhome.service.data.DataHandlingService;
import com.myhome.service.notification.Channel;
import com.myhome.service.notification.NotificationChannelService;
import com.myhome.service.notification.NotificationService;
import com.myhome.util.function.TriConsumer;

public class Application extends AppCompatActivity {

    public static SharedPreferences sp;
    public static RequestQueue requestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = this.getSharedPreferences(SharedPref.GENERAL, Context.MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        loadDataOnLogin();
        createNotificationChannels();

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Must be called after content is set
        debugModeButton();
    }

    private void loadDataOnLogin() {
        final DataHandlingService dataHandler = new DataHandlingService();
        dataHandler.loadData();
    }

    private void createNotificationChannels() {
        NotificationChannelService channelService = new NotificationChannelService();
        channelService.createDebugChannel(this);
        channelService.createMealChannel(this);
    }

    private void debugModeButton() {

        final Runnable restartActivity = () -> {
            Intent intent = this.getIntent();
            finish();
            startActivity(intent);
        };

        final TriConsumer<String, String, Channel> createNotification = (title, desc, channel) -> {
            Notification notification = new Notification(title, desc, channel);
            notification.createNotification(this);
        };

        FloatingActionButton fab = findViewById(R.id.fab);

        Session.Factory.switchDebug();

        fab.setOnClickListener(e -> {
            Session.Factory.switchDebug();

            restartActivity.run();

            createNotification.accept("DEBUG-MODE: " + Session.Factory.debugMode().value(),
                    getString(R.string.debug_channel_text), Channel.DEBUG_CHANNEL);
        });

        fab.setOnLongClickListener(e -> {
            Session.Factory.destroyDebugMockSession();
            restartActivity.run();
            createNotification.accept("DEBUG-MODE: " + Session.Factory.debugMode().value(),
                    getString(R.string.debug_channel_text), Channel.DEBUG_CHANNEL);
            return true;
        });
    }


}
