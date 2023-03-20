package com.udemy.videolist.service;

import com.udemy.videolist.model.Video;

import java.util.List;
import java.util.Optional;

public interface VideoService {

    List<Video> findAllVideos();

    Optional<Video> findByVideoId(int videoId);
}
