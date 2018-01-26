package com.example.aqil.database;

import android.graphics.Bitmap;

/**
 * Created by Aqil on 21/10/2017.
 */

public class List_item {
    private String name;
    private int image;
    private String description;
    public List_item(String nam, int img,String descr)
    {
        name=nam;
        image=img;
        description=descr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setImage(int image)
    {
        this.image= image;
    }
    public void setName(String name)
    {
        this.name= name;
    }
    public int getImage()
    {
        return image;
    }
    public String getName()
    {
        return name;
    }
}