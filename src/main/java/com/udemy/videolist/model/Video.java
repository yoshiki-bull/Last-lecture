package com.udemy.videolist.model;

public class Video {

    private int videoId;

    private String videoTitle;

    private String instructor;

    private String videoLanguage;

    private String price;

    private int videoPrice;

    public Video(int videoId, String videoTitle, String instructor, String videoLanguage, String price, int videoPrice) {
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.instructor = instructor;
        this.videoLanguage = videoLanguage;
        this.price = price;
        this.videoPrice = videoPrice;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getLanguage() {
        return videoLanguage;
    }

    public int getVideoPrice() {
        return videoPrice;
    }
}
