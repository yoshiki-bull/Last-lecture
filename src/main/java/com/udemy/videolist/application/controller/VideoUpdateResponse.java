package com.udemy.videolist.application.controller;

import com.udemy.videolist.model.Video;
import lombok.Getter;

@Getter
public class VideoUpdateResponse {

  private final String message;

  private final String title;

  private final String instructor;

  private final String language;

  private final Boolean isFree;

  private final int price;

  protected VideoUpdateResponse(Video video) {
    this.message = "video successfully updated";
    this.title = video.getTitle();
    this.instructor = video.getInstructor();
    this.language = video.getLanguage();
    this.isFree = video.getIsFree();
    this.price = video.getPrice();
  }

  ;
}
