package com.tecmilenio.waterreminderapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.tecmilenio.waterreminderapp.R;
import com.tecmilenio.waterreminderapp.utils.NotificationHelper;

public class WaterReminderReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationHelper.createChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationHelper.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_drop)
                .setContentTitle("¡Hora de beber agua! 💧")
                .setContentText("Mantén tu cuerpo hidratado.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);

        try {
            manager.notify(1001, builder.build());
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
