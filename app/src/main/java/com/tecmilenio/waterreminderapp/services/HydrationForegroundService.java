package com.tecmilenio.waterreminderapp.services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.utils.NotificationHelper;

public class HydrationForegroundService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificationHelper.createChannel(this);

        Notification notification = new NotificationCompat.Builder(this, NotificationHelper.CHANNEL_ID)
                .setContentTitle("Recordatorio activo")
                .setContentText("La app está monitoreando tu hidratación")
                .setSmallIcon(R.drawable.ic_drop)
                .build();

        startForeground(2001, notification);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }
}
