package com.tecmilenio.waterreminderapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DateChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_DATE_CHANGED.equals(intent.getAction())) {
            Toast.makeText(context, "Nuevo día detectado. Estadísticas reiniciadas.", Toast.LENGTH_LONG).show();
        }
    }
}
