package com.udemy.videolist.infrastructure.repository;

import com.udemy.videolist.domain.model.Video;
import com.udemy.videolist.domain.repository.VideoRepository;
import com.udemy.videolist.infrastructure.mapper.VideoMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VideoRepositoryImpl implements VideoRepository {

  private final VideoMapper videoMapper;

  @Override
  public List<Video> findAllVideos() {
    return videoMapper.findAllVideos();
  }

  @Override
  public Optional<Video> findById(Integer id) {
    return videoMapper.findById(id);
  }

  @Override
  public List<Video> findByLanguage(String language) {
    return videoMapper.findByLanguage(language);
  }

  @Override
  public List<Video> findByIsFree(Boolean isFree) {
    return videoMapper.findByIsFree(isFree);
  }

  @Override
  public List<Video> findByLanguageAndIsFree(String language, Boolean isFree) {
    return videoMapper.findByLanguageAndIsFree(language, isFree);
  }

  @Override
  public void createVideo(Video video) {
    videoMapper.createVideo(video);
  }

  @Override
  public void updateVideo(Integer id, Video video) {
    videoMapper.updateVideo(id, video);
  }

  @Override
  public void deleteVideo(Integer id) {
    videoMapper.deleteVideo(id);
  }
}
