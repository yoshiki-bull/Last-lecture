package com.udemy.videolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Video {

    private int videoId;

    private String title;

    private String instructor;

    private String language;

    private String price;

    private int videoPrice;
}
