package com.example.bttracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context, "BT Tracker Channel")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Notification from BT Tracker")
                .setContentText("Please log your body temperature now")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // ↑上記の".setSmallIcon"などは、"NotificationCompat.Builder"の部分を省略していて、
        // たとえば、".setSmallIcon"は、NotificationCompat.Builder.setSmallIconである。たぶん。
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());
    }
}
