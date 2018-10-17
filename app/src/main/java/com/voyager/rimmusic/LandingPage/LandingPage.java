package com.voyager.rimmusic.LandingPage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.voyager.rimmusic.LandingPage.adapter.MusicListAdapter;
import com.voyager.rimmusic.LandingPage.model.MusicDetails;
import com.voyager.rimmusic.LandingPage.presenter.ILandingPresenter;
import com.voyager.rimmusic.LandingPage.presenter.LandingPresenter;
import com.voyager.rimmusic.LandingPage.view.ILandingView;
import com.voyager.rimmusic.R;
import com.voyager.rimmusic.Services.MyService;
import com.voyager.rimmusic.costom.FilePickerConstants;

import java.io.File;
import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * Created by User on 04-Sep-18.
 */

public class LandingPage extends AppCompatActivity implements ILandingView, EasyPermissions.PermissionCallbacks, MusicListAdapter.ClickListener, View.OnClickListener {

    RecyclerView rvSongList;
    ILandingPresenter iLandingPresenter;
    MusicListAdapter musicListAdapter;
    public static final int RC_STORAGE_ACCESS = 321;
    public static final int RA_STORAGE_ACCESS = 322;
    List<MusicDetails> musicDetails;
    MusicDetails musicDetail;
    MediaPlayer mPlayer;
    TextView tvSongTitle;
    TextView tvSongSubTitle;
    ImageView ivAlbum;
    ImageView ivPrev;
    ImageView ivPlay;
    ImageView ivNext;
    private MyService myService;
    private boolean isServiceBound;
    private ServiceConnection serviceConnection;
    Intent serviceIntent;
    boolean isPaused = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        rvSongList = (RecyclerView) findViewById(R.id.rvSongList);
        tvSongTitle = (TextView) findViewById(R.id.tvSongTitle);
        tvSongSubTitle = (TextView) findViewById(R.id.tvSongSubTitle);
        ivAlbum = (ImageView) findViewById(R.id.ivAlbum);
        ivPrev = (ImageView) findViewById(R.id.ivPrev);
        ivPlay = (ImageView) findViewById(R.id.ivPlay);
        ivNext = (ImageView) findViewById(R.id.ivNext);
        getPremission();
        musicListAdapter = new MusicListAdapter(musicDetails, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSongList.setLayoutManager(mLayoutManager);
        rvSongList.setAdapter(musicListAdapter);
        ivPrev.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        tvSongTitle.setOnClickListener(this);
        tvSongSubTitle.setOnClickListener(this);
        ivAlbum.setOnClickListener(this);
        musicListAdapter.setClickListener(this);
       // serviceIntent=new Intent(this,MyService.class);
    }

    @AfterPermissionGranted(RC_STORAGE_ACCESS)
    public void getPremission() {
        if (EasyPermissions.hasPermissions(this, FilePickerConstants.PERMISSIONS_FILE_PICKER)) {
            if (EasyPermissions.hasPermissions(this, FilePickerConstants.PERMISSIONS_FILE_READER)) {
                setPresenter();
            } else {
                EasyPermissions.requestPermissions(this, "Please Get it done ",
                        RA_STORAGE_ACCESS, FilePickerConstants.PERMISSIONS_FILE_READER);
            }

        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "Please Get it done ",
                    RC_STORAGE_ACCESS, FilePickerConstants.PERMISSIONS_FILE_PICKER);
        }
    }

    public void setPresenter() {
        iLandingPresenter = new LandingPresenter(this, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void getMusicFiles(List<MusicDetails> musicDetails) {
        this.musicDetails = musicDetails;
    }

    @Override
    public void itemClicked(View view, int position) {

        if (musicListAdapter.getData().get(position) instanceof MusicDetails) {
            if(mPlayer!=null&&mPlayer.isPlaying()){
                mPlayer.stop();
                mPlayer.release();
                mPlayer = null;
                isPaused = false;
                ivPlay.setImageDrawable(ContextCompat.getDrawable(this, android.R.drawable.ic_media_play));
            }
            System.out.println(" ------------ LandingPage itemClicked position : " + position);
            musicDetail = musicListAdapter.getData().get(position);
            tvSongTitle.setText(musicDetail.getTitle());
            tvSongSubTitle.setText(musicDetail.getArtist());

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivPrev:
                //startService(serviceIntent);
                break;
            case R.id.ivPlay:
                //bindService();
                System.out.println(" Media State : "+isPaused);
                if(mPlayer!=null&& mPlayer.isPlaying()){
                    ivPlay.setImageDrawable(ContextCompat.getDrawable(this, android.R.drawable.ic_media_play));
                    mPlayer.pause();
                    isPaused = true;
                }else if(mPlayer!=null&&isPaused){
                    ivPlay.setImageDrawable(ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause));
                    mPlayer.start();
                    isPaused = false;
                }


                if(mPlayer==null) {

                    mPlayer = MediaPlayer.create(getApplicationContext(), Uri.fromFile(new File(musicDetail.getPath())));

                    try {
                        mPlayer.prepare();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    ivPlay.setImageDrawable(ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause));
                    mPlayer.start();
                }

               // nicholas.setText("Nicholas (Clicked!) ");


            break;
            case R.id.ivNext:
                //stopService(serviceIntent);
                break;
            case R.id.rvSongList:

                break;
            case R.id.tvSongSubTitle:
                //unBindService();
                break;
            case R.id.ivAlbum:
                //setRandomNumber();
                break;

        }
    }

    public void bindService() {
        if (serviceConnection == null) {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder iBinder) {
                    MyService.MyServiceBinder myServiceBinder = (MyService.MyServiceBinder) iBinder;
                    myService = myServiceBinder.getService();
                    isServiceBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    isServiceBound = false;
                }
            };

        }
        bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);

    }

    public void unBindService() {
        if(isServiceBound){
            unbindService(serviceConnection);
            isServiceBound = false;
        }

    }

    public void setRandomNumber() {
        if(isServiceBound){
            tvSongSubTitle.setText("Random Number : "+myService.getRandomNumber());
        }else {
            tvSongSubTitle.setText("Service not Bound");
        }
    }

    private static class Menu {
        public static final int PREVIOUS = 1;
        public static final int PLAY_PAUSE = 2;
        public static final int NEXT = 3;
        public static final int TERMS_AND_CONDITIONS = 4;
        public static final int SETTINGS = 5;
    }

}