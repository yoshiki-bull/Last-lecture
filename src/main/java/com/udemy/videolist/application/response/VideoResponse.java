package com.udemy.videolist.application.response;

import com.udemy.videolist.domain.model.Video;

public record VideoResponse(
    int id,
    String title,
    String instructor,
    String language,
    int price) {

  public VideoResponse(Video video) {
    this(video.getId(),
        video.getTitle(),
        video.getInstructor(),
        video.getLanguage(),
        video.getPrice());
  }
}
