package com.udemy.videolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Video {

  private int id;

  private String title;

  private String instructor;

  private String language;

  private Boolean isFree;

  private int price;
}
