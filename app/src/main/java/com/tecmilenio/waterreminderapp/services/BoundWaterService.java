package com.tecmilenio.waterreminderapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BoundWaterService extends Service {

    private final IBinder binder = new LocalBinder();
    private int totalAgua = 0;

    public class LocalBinder extends Binder {
        public BoundWaterService getService() { return BoundWaterService.this; }
    }

    public void agregarAgua(int ml) { totalAgua += ml; }
    public int getTotal() { return totalAgua; }

    @Override
    public IBinder onBind(Intent intent) { return binder; }
}
