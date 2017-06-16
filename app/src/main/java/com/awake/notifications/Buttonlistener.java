package com.awake.notifications;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Administrator on 12/06/2017.
 */

public class Buttonlistener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       NotificationManager manager =(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(intent.getExtras().getInt("id"));
        Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show();
    }
}
