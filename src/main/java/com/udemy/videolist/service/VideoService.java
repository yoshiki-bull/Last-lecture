package com.udemy.videolist.service;

import com.udemy.videolist.model.Video;

import java.util.List;

public interface VideoService {

    Video findByVideoId(int id);

    List<Video> searchVideos(String language, Boolean isFree);
}
