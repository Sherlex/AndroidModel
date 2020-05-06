package com.example.eco;

import java.security.Key;

public class Model {

    public Model() { }

    String Title;
    String Description;
    String Image;
    String Content;
    String Key;

    public Model(String image, String title, String description, String content, String key) {
        Title = title;
        Description = description;
        Image = image;
        Content = content;
        Key = key;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }


    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
