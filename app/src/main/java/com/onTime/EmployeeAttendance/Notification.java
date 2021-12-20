package com.onTime.EmployeeAttendance;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import static androidx.core.content.ContextCompat.getSystemService;

public class Notification extends Application {

    private static final String CHANNEL_1_ID = "channel1";

    private void createNotificationChannels(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel1=new NotificationChannel(
                    CHANNEL_1_ID,
                    "Notification Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );

            channel1.setDescription("Notification");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }


}

// NotificationHelper.displayNotification(this, "Company Attendance","You leaved the office");
              /*  NotificationCompat.Builder mbuilder=(NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.app_logo)
                        .setContentTitle("Notification")
                        .setContentText(" You leave the office your checkout Time is recorded");*/

/*

                Notification notification = new Notification.Builder(Attendance.this)
                        .setContentTitle("Notification")
                        .setContentText("You leave the office your checkout Time is recorded. ")
                        .setSmallIcon(R.drawable.app_logo)
                        .setChannelId(CHANNEL_1_ID)
                        .build();

                NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
*/
// notificationManager.notify(0,mbuilder.build());

//NotificationHelper.displayNotification(this, "Company Attendance","Your attendance is recorded");
                   /* NotificationCompat.Builder mbuilder=(NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.app_logo)
                            .setContentTitle("Notification")
                            .setContentText(" Welcome your checkIn time is recorded");

                            NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                            notificationManager.notify(1,mbuilder.build());*/
