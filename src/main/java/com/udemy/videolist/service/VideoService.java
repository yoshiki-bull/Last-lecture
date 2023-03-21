package com.udemy.videolist.service;

import com.udemy.videolist.model.Video;

import java.util.List;
import java.util.Optional;

public interface VideoService {

    List<Video> findAllVideos();

    Optional<Video> findByVideoId(int id);

    List<Video> findByLanguage(String language);

    List<Video> findByPrice(String price);

    List<Video> findByLanguageAndPrice(String language, String price);
}