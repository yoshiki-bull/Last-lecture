package com.udemy.videolist.service;

import com.udemy.videolist.application.exception.VideoNotFoundException;
import com.udemy.videolist.application.form.UpdateForm;
import com.udemy.videolist.model.Video;
import com.udemy.videolist.repository.VideoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

  private final VideoMapper videoMapper;

  @Override
  public Video findById(int id) {
    return this.videoMapper.findById(id).orElseThrow(() -> new VideoNotFoundException(id));
  }

  @Override
  public List<Video> searchVideos(String language, Boolean isFree) {
    if (language != null && isFree != null) {
      return videoMapper.findByLanguageAndIsFree(language, isFree);
    } else if (language != null) {
      return videoMapper.findByLanguage(language);
    } else if (isFree != null) {
      return videoMapper.findByIsFree(isFree);
    } else {
      return videoMapper.findAllVideos();
    }
  }

  @Override
  public void createVideo(String title, String instructor, String language, Boolean isFree, int price) {
    Video video = new Video(title, instructor, language, isFree, price);
    videoMapper.createVideo(video);
  }

  @Override
  public void updateVideo(int id, UpdateForm form) {
    videoMapper.findById(id).orElseThrow(() -> new VideoNotFoundException(id));
    videoMapper.updateVideo(id, form);
  }

  @Override
  public void deleteVideo(int id) {
    videoMapper.findById(id).orElseThrow(() -> new VideoNotFoundException(id));
    videoMapper.deleteVideo(id);
  }
}
