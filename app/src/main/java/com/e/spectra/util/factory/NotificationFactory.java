package com.e.spectra.util.factory;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class NotificationFactory extends ContextWrapper {
    private NotificationManager notificationManager;
    private static NotificationFactory channelFactory = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationFactory() {
        super(null);
    }

    public static NotificationFactory getInstance(Context context) {
        channelFactory = new NotificationFactory();
        channelFactory.attachBaseContext(context);
        return channelFactory;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationChannel createChannel(String channelId, String channelName, int channelImportance) {
        NotificationChannel androidChannel = new NotificationChannel(channelId,
                channelName, channelImportance);
        androidChannel.enableLights(true);
        androidChannel.enableVibration(true);
        androidChannel.setLightColor(Color.GREEN);
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(androidChannel);
        return androidChannel;
    }

    public NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void publishNotification(int notificationId, String title, String body, String channelId) {
        Notification.Builder builder = new Notification.Builder(channelFactory.getApplicationContext(), channelId)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
        getManager().notify(notificationId, builder.build());
    }
}
