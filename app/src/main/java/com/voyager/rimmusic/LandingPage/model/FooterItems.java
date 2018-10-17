package com.voyager.rimmusic.LandingPage.model;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

/**
 * Created by User on 05-Sep-18.
 */

public class FooterItems  {

    public final static String TAG_NAME = "FooterItems";
    private int ID;
    private String Name;
    private String Discription;
    private boolean enabled=true;
    private MaterialDrawableBuilder.IconValue iconDrawPrevious;
    private MaterialDrawableBuilder.IconValue iconDrawNext;
    private MaterialDrawableBuilder.IconValue iconDraw2PlayPause;

    public FooterItems(){
        super();
    }

    public static String getTagName() {
        return TAG_NAME;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public MaterialDrawableBuilder.IconValue getIconDrawPrevious() {
        return iconDrawPrevious;
    }

    public void setIconDrawPrevious(MaterialDrawableBuilder.IconValue iconDrawPrevious) {
        this.iconDrawPrevious = iconDrawPrevious;
    }

    public MaterialDrawableBuilder.IconValue getIconDrawNext() {
        return iconDrawNext;
    }

    public void setIconDrawNext(MaterialDrawableBuilder.IconValue iconDrawNext) {
        this.iconDrawNext = iconDrawNext;
    }

    public MaterialDrawableBuilder.IconValue getIconDraw2PlayPause() {
        return iconDraw2PlayPause;
    }

    public void setIconDraw2PlayPause(MaterialDrawableBuilder.IconValue iconDraw2PlayPause) {
        this.iconDraw2PlayPause = iconDraw2PlayPause;
    }
}

