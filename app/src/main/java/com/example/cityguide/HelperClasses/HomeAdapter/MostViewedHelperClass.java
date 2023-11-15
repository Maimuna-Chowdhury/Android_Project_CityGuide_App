package com.example.cityguide.HelperClasses.HomeAdapter;

import android.widget.TextView;

public class MostViewedHelperClass {
    int image;
    String title;
    String description;
    public MostViewedHelperClass(int image,String title,String description)
    {
        this.image = image;
        this.title=title;
        this.description=description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //public int getTextView() {
        //return description;
    //}

    //public String getDescription() {
        //return description;
    //}
}
