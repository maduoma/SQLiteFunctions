package com.dodemy.android.sqlitefunctions;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class SingletonExample {
    public static final String ANDROID_CHANNEL_ID = "com.dodemy.android.sqlitefunctions";
    private static SingletonExample ourInstance = new SingletonExample();

    private Context appContext;

    private SingletonExample() {
    }

    public static Context get() {
        return getInstance().getContext();
    }

    public static synchronized SingletonExample getInstance() {

        return ourInstance;
    }

    public void init(Context context) {
        if (appContext == null) {
            this.appContext = context;
        }
    }

    private Context getContext() {
        return appContext;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notification(final MainActivity mainActivity) {
        NotificationManager notif=(NotificationManager)mainActivity.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID, "Notification Channel", NotificationManager.IMPORTANCE_DEFAULT);
        androidChannel.enableLights(true);
        androidChannel.enableVibration(true);
        androidChannel.setLightColor(Color.GREEN);
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notif.createNotificationChannel(androidChannel);

        Notification notify= null;
        notify = new Notification.Builder(mainActivity,ANDROID_CHANNEL_ID).setContentTitle("Notification").setContentText("Sample Text").
                setContentTitle("Sample Subject").setSmallIcon(R.mipmap.ic_launcher).build();
        notif.notify(0, notify);
    }
}
