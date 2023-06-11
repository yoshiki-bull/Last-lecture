package com.udemy.videolist.domain.repository;

import com.udemy.videolist.domain.model.Video;
import java.util.List;
import java.util.Optional;

public interface VideoRepository {

  List<Video> findAllVideos();

  Optional<Video> findById(Integer id);

  List<Video> findByLanguage(String language);

  List<Video> findByIsFree(Boolean isFree);

  List<Video> findByLanguageAndIsFree(String language, Boolean isFree);

  void createVideo(Video video);

  void updateVideo(Integer id, Video video);

  void deleteVideo(Integer id);
}
