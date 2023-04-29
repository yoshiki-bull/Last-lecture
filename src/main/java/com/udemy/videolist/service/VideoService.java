package com.udemy.videolist.service;

import com.udemy.videolist.application.form.UpdateForm;
import com.udemy.videolist.model.Video;
import java.util.List;

public interface VideoService {

  Video findById(int id);

  List<Video> searchVideos(String language, Boolean isFree);

  Video createVideo(String title, String instructor, String language, Boolean isFree, int price);

  void updateVideo(int id, UpdateForm form);

  void deleteVideo(int id);
}
