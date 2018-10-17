package com.voyager.rimmusic.LandingPage.presenter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.google.gson.Gson;
import com.voyager.rimmusic.LandingPage.model.FooterItems;
import com.voyager.rimmusic.LandingPage.model.IMusicDetails;
import com.voyager.rimmusic.LandingPage.model.MusicDetails;
import com.voyager.rimmusic.LandingPage.view.ILandingView;
import com.voyager.rimmusic.R;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 04-Sep-18.
 */

public class LandingPresenter implements ILandingPresenter {

    ILandingView iLandingView;
    IMusicDetails iMusicDetails;
    List<MusicDetails> musicDetails = Arrays.asList();
    Context context;


    public LandingPresenter(ILandingView iLandingView, Context context) {
        this.iLandingView = iLandingView;
        this.context = context;
        getData();
    }

    private void getData() {
        iMusicDetails = new MusicDetails();

        musicDetails = new ArrayList<MusicDetails>();
        musicDetails.clear();


        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        Cursor c = context.getContentResolver().query(uri, null, selection, null, null);
        c.moveToFirst();
        while (c.moveToNext()) {
            MusicDetails songData = new MusicDetails();

            String title = c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String artist = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String album = c.getString(c.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            long duration = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.DURATION));
            String time = String.format("%02d:%02d ",
                    TimeUnit.MILLISECONDS.toMinutes(duration),
                    TimeUnit.MILLISECONDS.toSeconds(duration) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
            String data = c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
            long albumId = c.getLong(c.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            String composer = c.getString(c.getColumnIndex(MediaStore.Audio.Media.COMPOSER));

            songData.setTitle(title);
            songData.setAlbum(album);
            songData.setArtist(artist);
            songData.setDuration(time);
            songData.setPath(data);
            songData.setAlbumId(albumId);
            songData.setComposer(composer);
            musicDetails.add(songData);
        }
        c.close();
        Log.d("SIZE", "SIZE: " + musicDetails.size());
        Gson gson = new Gson();
        String json = gson.toJson(musicDetails);
        iLandingView.getMusicFiles(musicDetails);
        System.out.println("-----------LandingPresenter getData musicDetails : " + json);

    }

}
