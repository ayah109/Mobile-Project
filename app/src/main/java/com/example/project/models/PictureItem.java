package com.example.project.models;

public class PictureItem {
    private String text;
    private String imageUrl;

    public PictureItem(String text, String imageUrl) {
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
