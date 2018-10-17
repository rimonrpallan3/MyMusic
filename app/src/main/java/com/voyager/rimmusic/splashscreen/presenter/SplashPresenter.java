package com.voyager.rimmusic.splashscreen.presenter;

import android.os.Handler;
import android.os.Looper;

import com.voyager.rimmusic.splashscreen.view.ISplashView;

/**
 * Created by User on 04-Sep-18.
 */

public class SplashPresenter implements ISplashPresenter {

    ISplashView iSplashView;
    private int SPLASH_DISPLAY_LENGTH = 1000;

    public SplashPresenter(ISplashView iSplashView) {
        this.iSplashView = iSplashView;
        load();
    }

    public void load(){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iSplashView.moveLanding();
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
