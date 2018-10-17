package com.voyager.rimmusic.Services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.voyager.rimmusic.R;

/**
 * Created by User on 17-Oct-18.
 */

public class MediaConstants {

    public interface ACTION {
        public static String MAIN_ACTION = "foregroundservice.action.main";
        public static String PREV_ACTION = "foregroundservice.action.prev";
        public static String PLAY_ACTION = "foregroundservice.action.play";
        public static String NEXT_ACTION = "foregroundservice.action.next";
        public static String STARTFOREGROUND_ACTION = "foregroundservice.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "foregroundservice.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }


    public static Bitmap getDefaultAlbumArt(Context context) {
        Bitmap bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            bm = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.default_icon, options);
        } catch (Error ee) {
        } catch (Exception e) {
        }
        return bm;
    }


}
