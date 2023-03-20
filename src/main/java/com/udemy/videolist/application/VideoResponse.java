package com.udemy.videolist.application;

import com.udemy.videolist.model.Video;

public class VideoResponse {

    private String videoTitle;

    private String instructor;

    private String videoLanguage;

    private int videoPrice;

    public VideoResponse(Video video) {
        this.videoTitle = video.getVideoTitle();
        this.instructor = video.getInstructor();
        this.videoLanguage = video.getLanguage();
        this.videoPrice = video.getVideoPrice();
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getVideoLanguage() {
        return videoLanguage;
    }

    public int getVideoPrice() {
        return videoPrice;
    }
}
