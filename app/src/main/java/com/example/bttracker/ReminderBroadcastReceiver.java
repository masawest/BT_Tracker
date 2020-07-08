package com.example.bttracker;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
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

        // ↓　Create an explicit intent for LogActivity
        // ↓ 以下のIntent(context, LogActivity.class)について、これはディベロッパーのサイトを
        // を見て自分で書いたのだが、最初"context"ではなく、"this"と書いた。しかしエラーがでた。
        // そこで、この後にでてくるコードの中のここから5行目に
        // NotificationCompat.Builder(context, "BT Tracker Channel")というのがあったので、
        // もしかして、と思い、"context"を入れたところうまく動くことがわかった。このことにについて
        // 先生に質問したところ、以下の回答があり、なるほど、と思った！
        // In Android apps, there are different ways to get context: within an Activity itself
        // use "this"; for BroadcastReceiver use the receiver's own context.
        // In line 15 (直前のコード), did you see
        // onReceive(Context context, Intent intent) <-- that "context" is the receiver's
        // own context.
        Intent toLog = new Intent(context, LogActivity.class);
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(toLog);
        // バックスタック全体を含むPendingIntentを取得する
        PendingIntent pendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context, "BT Tracker Channel")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Notification from BT Tracker")
                .setContentText("Please log your body temperature now")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        // ↓下記の意味は、Set the intent that will fire when the user taps the notification.
                .setContentIntent(pendingIntent);
        // ↑上記の".setSmallIcon"などは、"NotificationCompat.Builder"の部分を省略していて、
        // たとえば、".setSmallIcon"は、NotificationCompat.Builder.setSmallIconである。たぶん。
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());
    }
}
