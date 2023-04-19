package com.udemy.videolist.application.controller;

import com.udemy.videolist.application.form.CreateForm;
import lombok.Getter;

@Getter
public class VideoCreateResponse {

  private String message;

  private int id;

  private String title;

  private String instructor;

  private String language;

  private Boolean isFree;

  private String price;

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
