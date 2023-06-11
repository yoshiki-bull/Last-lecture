package com.udemy.videolist.domain.service;

import com.udemy.videolist.application.exception.VideoNotFoundException;
import com.udemy.videolist.domain.model.Video;
import com.udemy.videolist.domain.repository.VideoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

  private final VideoRepository videoRepository;

  @Override
  public Video findById(int videoId) {
    return this.videoRepository
        .findById(videoId)
        .orElseThrow(() -> new VideoNotFoundException(videoId));
  }

  @Override
  public List<Video> searchVideos(String language, Boolean isFree) {
    if (language != null && isFree != null) {
      return videoRepository.findByLanguageAndIsFree(language, isFree);
    } else if (language != null) {
      return videoRepository.findByLanguage(language);
    } else if (isFree != null) {
      return videoRepository.findByIsFree(isFree);
    } else {
      return videoRepository.findAllVideos();
    }
  }

  @Override
  public void createVideo(Video video) {
    videoRepository.createVideo(video);
  }

  @Override
  public void updateVideo(int id, Video video) {
    videoRepository.findById(id).orElseThrow(() -> new VideoNotFoundException(id));

    videoRepository.updateVideo(id, video);
  }

  @Override
  public void deleteVideo(int id) {
    videoRepository.findById(id).orElseThrow(() -> new VideoNotFoundException(id));

    videoRepository.deleteVideo(id);
  }
}
