package com.awake.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notification_id;
    private RemoteViews remoteViews;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context =this;
        notificationManager =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(getPackageName(),R.layout.custom_notification);
        remoteViews.setTextViewText(R.id.textView,"You clicked here");
        remoteViews.setProgressBar(R.id.progressBar,100,50,true);

        notification_id = (int) System.currentTimeMillis();
        Intent button_click = new Intent("button_clicked");
        button_click.putExtra("id", notification_id);
        PendingIntent p_button_intent =PendingIntent.getBroadcast(context,123,button_click,0);
        remoteViews.setOnClickPendingIntent(R.id.button,p_button_intent);

        btn = (Button) findViewById(R.id.btn_notify);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notification_click = new Intent(context,MainActivity.class);
                PendingIntent pendingIntent =PendingIntent.getActivity(context,0,notification_click,0);

                builder = new NotificationCompat.Builder(context);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setAutoCancel(true);
                builder.setCustomBigContentView(remoteViews);
                builder.setContentIntent(pendingIntent);
                notificationManager.notify(notification_id,builder.build());
            }
        });
    }
}
