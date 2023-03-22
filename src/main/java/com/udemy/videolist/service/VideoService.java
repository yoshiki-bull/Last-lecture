package com.udemy.videolist.service;

import com.udemy.videolist.model.Video;

import java.util.List;

public interface VideoService {

    //List<Video> findAllVideos();

    Video findByVideoId(int id);

    List<Video> searchVideos(String language, String price);
}
