package com.udemy.videolist.service;

import com.udemy.videolist.model.Video;
import com.udemy.videolist.repository.VideoMapper;
import com.udemy.videolist.application.exception.VideoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
