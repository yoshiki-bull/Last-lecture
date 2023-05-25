package com.udemy.videolist.application.controller;

import com.udemy.videolist.model.Video;

record VideoResponse(int id, String title, String instructor, String language, int price) {
  public VideoResponse(Video video) {
    this(video.getId(), video.getTitle(), video.getInstructor(), video.getLanguage(), video.getPrice());
  }
}
