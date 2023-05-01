package com.udemy.videolist.repository;

import com.udemy.videolist.model.Video;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoMapper {

  List<Video> findAllVideos();

  Optional<Video> findById(Integer id);

  List<Video> findByLanguage(String language);

  List<Video> findByIsFree(Boolean isFree);

  List<Video> findByLanguageAndIsFree(String language, Boolean isFree);

  void createVideo(Video video);

  void updateVideo(Integer id, Video video);

  void deleteVideo(Integer id);
}
