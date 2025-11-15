package com.tecmilenio.waterreminderapp.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.tecmilenio.waterreminderapp.receivers.WaterReminderReceiver;

public class AlarmScheduler {

    public static void schedule(Context context) {

        Intent intent = new Intent(context, WaterReminderReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        long interval = 2 * 60 * 60 * 1000;
        long start = System.currentTimeMillis() + interval;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                start,
                interval,
                pendingIntent
        );
    }
}
