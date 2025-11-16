package com.tecmilenio.waterreminderapp.services;

import static android.app.Service.START_NOT_STICKY;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SyncService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            // Operaciones de fondo
            System.out.println("Sincronizando datos...");
            stopSelf();
        }).start();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }
}