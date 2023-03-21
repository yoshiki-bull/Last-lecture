package com.udemy.videolist.application;

import com.udemy.videolist.model.Video;

public class VideoResponse {

    private String title;

    private String instructor;

    private String language;

    private int videoPrice;

    public VideoResponse(Video video) {
        this.title = video.getVideoTitle();
        this.instructor = video.getInstructor();
        this.language = video.getLanguage();
        this.videoPrice = video.getVideoPrice();
    }

    public String getVideoTitle() {
        return title;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getVideoLanguage() {
        return language;
    }

    public int getVideoPrice() {
        return videoPrice;
    }
}
