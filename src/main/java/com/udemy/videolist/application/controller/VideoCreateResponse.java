package com.udemy.videolist.application.controller;

import com.udemy.videolist.model.Video;
import lombok.Getter;

@Getter
public class VideoCreateResponse {

  private final String message;

  private final int id;

  private final String title;

  private final String instructor;

  private final String language;

  private final Boolean isFree;

  private final int price;

  public VideoCreateResponse(Video video) {
    this.message = "video successfully created";
    this.id = video.getId();
    this.title = video.getTitle();
    this.instructor = video.getInstructor();
    this.language = video.getLanguage();
    this.isFree = video.getIsFree();
    this.price = video.getPrice();
  }

  ;
}
