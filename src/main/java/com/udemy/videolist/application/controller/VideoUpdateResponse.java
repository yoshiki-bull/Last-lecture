package com.udemy.videolist.application.controller;

import com.udemy.videolist.model.Video;
import lombok.Getter;

record VideoUpdateResponse(
    String message,
    String title,
    String instructor,
    String language,
    Boolean isFree,
    int price) {

  public VideoUpdateResponse(Video video) {
    this(
        "video successfully updated",
        video.getTitle(),
        video.getInstructor(),
        video.getLanguage(),
        video.getIsFree(),
        video.getPrice()
    );
  }
}
