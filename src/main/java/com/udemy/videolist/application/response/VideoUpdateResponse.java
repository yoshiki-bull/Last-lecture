package com.udemy.videolist.application.response;

import com.udemy.videolist.domain.model.Video;

public record VideoUpdateResponse(
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
