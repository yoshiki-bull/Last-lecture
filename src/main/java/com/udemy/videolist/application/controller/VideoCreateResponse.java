package com.udemy.videolist.application.controller;

import com.udemy.videolist.application.form.CreateForm;
import lombok.Getter;

@Getter
public class VideoCreateResponse {

  private final String message;

  private final int id;

  private final String title;

  private final String instructor;

  private final String language;

  private final Boolean isFree;

  private final String price;

  protected VideoCreateResponse(CreateForm form) {
    this.message = "video successfully created";
    this.id = form.getId();
    this.title = form.getTitle();
    this.instructor = form.getInstructor();
    this.language = form.getLanguage();
    this.isFree = form.getIsFree();
    this.price = form.getPrice();
  }

  ;
}
