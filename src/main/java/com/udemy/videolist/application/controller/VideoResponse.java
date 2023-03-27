package com.udemy.videolist.application.controller;

import com.udemy.videolist.model.Video;
import lombok.Getter;

@Getter
public class VideoResponse {

    private int id;

    private String title;

    private String instructor;

    private String language;

    private int price;

    public VideoResponse(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.instructor = video.getInstructor();
        this.language = video.getLanguage();
        this.price = video.getPrice();
    }
}
