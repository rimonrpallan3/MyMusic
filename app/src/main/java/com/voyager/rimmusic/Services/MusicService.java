package com.voyager.rimmusic.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RemoteViews;

import com.voyager.rimmusic.LandingPage.LandingPage;
import com.voyager.rimmusic.R;

/**
 * Created by User on 16-Oct-18.
 */

public class MusicService extends Service {

    static  String TAG = "MusicService : ";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopRandomGenrator();
        System.out.println( TAG + "In onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println( TAG + "In onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        System.out.println( TAG + " In onRebind");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println( TAG+"In onStartCommand, Thread id : "+Thread.currentThread().getId());
        //mIsRandomGenerator = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
               // startRandomGenrator();
            }
        }).start();
        return START_STICKY;
    }


    public void showNotification(int stat) {
       // startForeground(MediaConstants.NOTIFICATION_ID.FOREGROUND_SERVICE, createNotification(stat));

    }

    private void updateNotification(int stat) {
       // Notification notification = createNotification(stat);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //mNotificationManager.notify(MediaConstants.NOTIFICATION_ID.FOREGROUND_SERVICE, notification);
    }

 /*   private Notification createNotification(int stat){
        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews smallViews = new RemoteViews(getPackageName(), R.layout.status_bar);
        RemoteViews bigViews = new RemoteViews(getPackageName(), R.layout.status_bar_expanded);
        // showing default album image
        smallViews.setViewVisibility(R.id.status_bar_icon, View.VISIBLE);
        smallViews.setViewVisibility(R.id.status_bar_album_art, View.GONE);
        bigViews.setImageViewBitmap(R.id.status_bar_album_art, MediaConstants.getDefaultAlbumArt(this));

        Intent notificationIntent = new Intent(this, LandingPage.class);
        notificationIntent.setAction(MediaConstants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Intent previousIntent = new Intent(this, MusicService.class);
        previousIntent.setAction(MediaConstants.ACTION.PREV_ACTION);
        PendingIntent _previousIntent = PendingIntent.getService(this, 0, previousIntent, 0);

        Intent playIntent = new Intent(this, MusicService.class);
        playIntent.setAction(MediaConstants.ACTION.PLAY_ACTION);
        PendingIntent _playIntent = PendingIntent.getService(this, 0, playIntent, 0);

        Intent nextIntent = new Intent(this, MusicService.class);
        nextIntent.setAction(MediaConstants.ACTION.NEXT_ACTION);
        PendingIntent _nextIntent = PendingIntent.getService(this, 0, nextIntent, 0);

        Intent closeIntent = new Intent(this, MusicService.class);
        closeIntent.setAction(MediaConstants.ACTION.STOPFOREGROUND_ACTION);
        PendingIntent _closeIntent = PendingIntent.getService(this, 0, closeIntent, 0);

        smallViews.setOnClickPendingIntent(R.id.status_bar_play, _playIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_play, _playIntent);

        smallViews.setOnClickPendingIntent(R.id.status_bar_next, _nextIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_next, _nextIntent);

        smallViews.setOnClickPendingIntent(R.id.status_bar_prev, _previousIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_prev, _previousIntent);

        smallViews.setOnClickPendingIntent(R.id.status_bar_collapse, _closeIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_collapse, _closeIntent);

        if(stat == PLAY){
            smallViews.setImageViewResource(R.id.status_bar_play, R.drawable.ic_pause);
            bigViews.setImageViewResource(R.id.status_bar_play, R.drawable.ic_pause);
        }else{
            smallViews.setImageViewResource(R.id.status_bar_play, R.drawable.ic_play_arrow_black_24dp);
            bigViews.setImageViewResource(R.id.status_bar_play, R.drawable.ic_play_arrow_black_24dp);
        }


        smallViews.setTextViewText(R.id.status_bar_track_name, myRadioList.get(currentSongIndex).getTitle());
        bigViews.setTextViewText(R.id.status_bar_track_name, myRadioList.get(currentSongIndex).getTitle());


        Notification status = new Notification.Builder(this).build();
        status.contentView = smallViews;
        status.bigContentView = bigViews;
        status.flags = Notification.FLAG_ONGOING_EVENT;
        status.icon = getNotificationIcon();
        status.contentIntent = pendingIntent;
        bigViews.setOnClickPendingIntent(R.id.status_bar_play, _playIntent);
        smallViews.setOnClickPendingIntent(R.id.status_bar_play, _playIntent);

        return status;
    }

    protected int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.faith_notification_lollipop : R.drawable.faith_app_icon1;
    }*/
}
