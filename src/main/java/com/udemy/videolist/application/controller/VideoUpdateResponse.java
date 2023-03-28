package com.udemy.videolist.application.controller;

import com.udemy.videolist.application.form.UpdateForm;
import lombok.Getter;

@Getter
public class VideoUpdateResponse {

    private String message;

    private String title;

    private String instructor;

    private String language;

    private Boolean isFree;

    private String price;

    protected VideoUpdateResponse(UpdateForm form) {
        this.message = "video successfully updated";
        this.title = form.getTitle();
        this.instructor = form.getInstructor();
        this.language = form.getLanguage();
        this.isFree = form.getIsFree();
        this.price = form.getPrice();
    };
}
