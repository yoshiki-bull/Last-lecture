package com.udemy.videolist.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Video {

  private int id;

  private String title;

  private String instructor;

  private String language;

  private Boolean isFree;

  private int price;
}
