package com.udemy.videolist.service;

import com.udemy.videolist.model.Video;
import java.util.List;

public interface VideoService {

  Video findById(int id);

  List<Video> searchVideos(String language, Boolean isFree);

  Video createVideo(Video video);

  void updateVideo(int id, Video video);

  void deleteVideo(int id);
}
