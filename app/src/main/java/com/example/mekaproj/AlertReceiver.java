package com.example.mekaproj;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

/**
 *
 * @author Kaspar Tullus
 */
public class AlertReceiver extends BroadcastReceiver  {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String text = bundle.getString("event");
        String date = bundle.getString("date") + "\n " + bundle.getString("time");
        MekaDataBase mekaDataBase;

        //Click on Notification
        Intent intent1 = new Intent(context, Calendar_memory_list.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        //Notification Builder
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "notify_001");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_ONE_SHOT);


        //here we set all the properties for the notification
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        contentView.setImageViewResource(R.id.imageView4, R.drawable.logoremove);
        contentView.setTextViewText(R.id.message, text);
        contentView.setTextViewText(R.id.date, date);
        mBuilder.setSmallIcon(R.drawable.logoremove);
        mBuilder.setAutoCancel(true);
        mBuilder.setOngoing(true);
        mBuilder.setAutoCancel(true);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);
        mBuilder.setOnlyAlertOnce(true);
        mBuilder.build().flags = Notification.FLAG_NO_CLEAR | Notification.PRIORITY_HIGH;
        mBuilder.setContent(contentView);
        mBuilder.setContentIntent(pendingIntent);

        //we have to create notification channel after api level 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "channel name", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        Notification notification = mBuilder.build();
        notificationManager.notify(143, notification);


    }
}

