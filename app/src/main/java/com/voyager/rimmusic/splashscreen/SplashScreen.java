package com.voyager.rimmusic.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.voyager.rimmusic.LandingPage.LandingPage;
import com.voyager.rimmusic.R;
import com.voyager.rimmusic.splashscreen.presenter.ISplashPresenter;
import com.voyager.rimmusic.splashscreen.presenter.SplashPresenter;
import com.voyager.rimmusic.splashscreen.view.ISplashView;

/**
 * Created by User on 04-Sep-18.
 */

public class SplashScreen extends AppCompatActivity implements ISplashView {

    ISplashPresenter iSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iSplashPresenter = new SplashPresenter(this);

    }

    @Override
    public void moveLanding() {
        Intent intent = new Intent(this, LandingPage.class);
        startActivity(intent);
        finish();
    }
}