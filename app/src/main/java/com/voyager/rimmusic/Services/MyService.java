package com.voyager.rimmusic.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.Random;

/**
 * Created by User on 28-Sep-18.
 */

public class MyService extends Service {

    static  String TAG = "MyService : ";
    private int mRandomNumber;
    private boolean mIsRandomGenerator;

    private final int MIN = 0;
    private final int MAX = 100;


    public class MyServiceBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    private IBinder mBinder = new MyServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println(TAG+"In onBind");
        return mBinder;
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
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(TAG+"In onStartCommand, Thread id : "+Thread.currentThread().getId());
        mIsRandomGenerator = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                startRandomGenrator();
            }
        }).start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRandomGenrator();
        System.out.println(TAG+"In onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println(TAG+"In onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        System.out.println(TAG+"In onRebind");
    }

    public void startRandomGenrator(){
        while (mIsRandomGenerator){
            try{
                Thread.sleep(1000);
                if(mIsRandomGenerator){
                    mRandomNumber = new Random().nextInt(MAX)+MIN;
                    System.out.println(TAG+"In startRandomGenrator, Thread id : "+Thread.currentThread().getId()+" , mRandomNumber : "+mRandomNumber);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        mIsRandomGenerator = true;
    }

    public void stopRandomGenrator(){
        mIsRandomGenerator = false;
    }

    public int getRandomNumber(){
        return mRandomNumber;
    }
}
