package com.udemy.videolist.application.controller;

import com.udemy.videolist.model.Video;

record VideoCreateResponse(
    String message,
    int id,
    String title,
    String instructor,
    String language,
    Boolean isFree,
    int price) {

  public VideoCreateResponse(Video video) {
    this("video successfully created",
        video.getId(),
        video.getTitle(),
        video.getInstructor(),
        video.getLanguage(),
        video.getIsFree(),
        video.getPrice()
    );
  }
}
