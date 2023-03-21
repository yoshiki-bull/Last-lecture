package com.udemy.videolist.model;

public class Video {

    private int videoId;

    private String title;

    private String instructor;

    private String language;

    private String price;

    private int videoPrice;

    public Video(int videoId, String title, String instructor, String language, String price, int videoPrice) {
        this.videoId = videoId;
        this.title = title;
        this.instructor = instructor;
        this.language = language;
        this.price = price;
        this.videoPrice = videoPrice;
    }

    public String getVideoTitle() {
        return title;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getLanguage() {
        return language;
    }

    public int getVideoPrice() {
        return videoPrice;
    }
}
