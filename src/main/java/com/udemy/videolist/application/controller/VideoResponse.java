package com.udemy.videolist.application.controller;

import com.udemy.videolist.model.Video;
import lombok.Getter;

@Getter
public class VideoResponse {

  private final int id;

  private final String title;

  private final String instructor;

  private final String language;

  private final Boolean isFree;

  private final int price;

  public VideoResponse(Video video) {
    this.id = video.getId();
    this.title = video.getTitle();
    this.instructor = video.getInstructor();
    this.language = video.getLanguage();
    this.isFree = video.getIsFree();
    this.price = video.getPrice();
  }
}
