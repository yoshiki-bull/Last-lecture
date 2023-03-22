package com.udemy.videolist.application;

import com.udemy.videolist.model.Video;
import lombok.Getter;

@Getter
public class VideoResponse {

    private String title;

    private String instructor;

    private String language;

    private int price;

    public VideoResponse(Video video) {
        this.title = video.getTitle();
        this.instructor = video.getInstructor();
        this.language = video.getLanguage();
        this.price = video.getPrice();
    }
}
