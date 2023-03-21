package com.udemy.videolist.application;

import com.udemy.videolist.model.Video;
import lombok.Getter;

@Getter
public class VideoResponse {

    private String title;

    private String instructor;

    private String language;

    private int videoPrice;

    public VideoResponse(Video video) {
        this.title = video.getTitle();
        this.instructor = video.getInstructor();
        this.language = video.getLanguage();
        this.videoPrice = video.getVideoPrice();
    }
}
