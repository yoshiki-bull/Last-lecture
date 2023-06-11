package com.udemy.videolist.application.response;

import com.udemy.videolist.domain.model.Video;

public record VideoCreateResponse(
    String message,
    int id,
    String title,
    String instructor,
    String language,
    Boolean isFree,
    int price) {

  public VideoCreateResponse(Video video) {
    this(
        "video successfully created",
        video.getId(),
        video.getTitle(),
        video.getInstructor(),
        video.getLanguage(),
        video.getIsFree(),
        video.getPrice()
    );
  }
}
