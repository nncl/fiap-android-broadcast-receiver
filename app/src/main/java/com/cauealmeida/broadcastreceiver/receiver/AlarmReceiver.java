package com.cauealmeida.broadcastreceiver.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.cauealmeida.broadcastreceiver.AlarmActivity;
import com.cauealmeida.broadcastreceiver.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm done", Toast.LENGTH_SHORT).show();
        sendNotification(context);
    }

    private void sendNotification(Context c) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(c);

        // Open Alarm Activity
        builder.setContentIntent(PendingIntent.getActivity(c, 0, new Intent(c, AlarmActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT));
        builder.setContentTitle("Alarm shooted");
        builder.setContentText("Your alarm has been shooted successfully");
        builder.setTicker("New Message Alert");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true); // https://developer.android.com/reference/android/app/Notification.Builder.html

        // Show message
        NotificationManager notificationManager = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(100, builder.build());
    }
}
