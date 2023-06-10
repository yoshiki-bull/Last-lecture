package com.udemy.videolist.domain.service;

import com.udemy.videolist.domain.model.Video;
import java.util.List;

public interface VideoService {

  Video findById(int id);

  List<Video> searchVideos(String language, Boolean isFree);

  void createVideo(Video video);

  void updateVideo(int id, Video video);

  void deleteVideo(int id);
}
