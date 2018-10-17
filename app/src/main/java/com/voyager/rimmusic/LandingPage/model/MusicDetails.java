package com.voyager.rimmusic.LandingPage.model;

import android.os.Parcel;
import android.os.Parcelable;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

/**
 * Created by User on 04-Sep-18.
 */

public class MusicDetails implements Parcelable,IMusicDetails {

    String title;
    String artist;
    String album;
    String path;
    String duration;
    long albumId;
    String composer;
    protected String type;
    private int notify;

    public final static String TAG_NAME = "MusicDetails";
    public MusicDetails() {
            this.type=TAG_NAME;
    }

    public MusicDetails(String title, String artist, String album, String path, String  duration, long albumId, String composer) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.path = path;
        this.duration = duration;
        this.albumId = albumId;
        this.composer = composer;
    }

    public int getNotify() {
        return notify;
    }

    public void setNotify(int notify) {
        this.notify = notify;
    }

    public static String getTagName() {
        return TAG_NAME;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Creator<MusicDetails> getCREATOR() {
        return CREATOR;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.artist);
        dest.writeString(this.album);
        dest.writeString(this.path);
        dest.writeString(this.duration);
        dest.writeLong(this.albumId);
        dest.writeString(this.composer);
    }

    protected MusicDetails(Parcel in) {
        this.title = in.readString();
        this.artist = in.readString();
        this.album = in.readString();
        this.path = in.readString();
        this.duration = in.readString();
        this.albumId = in.readLong();
        this.composer = in.readString();
    }

    public static final Parcelable.Creator<MusicDetails> CREATOR = new Parcelable.Creator<MusicDetails>() {
        @Override
        public MusicDetails createFromParcel(Parcel source) {
            return new MusicDetails(source);
        }

        @Override
        public MusicDetails[] newArray(int size) {
            return new MusicDetails[size];
        }
    };
}
