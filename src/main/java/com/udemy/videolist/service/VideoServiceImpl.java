package com.udemy.videolist.service;

import com.udemy.videolist.model.Video;
import com.udemy.videolist.repository.VideoMapper;
import com.udemy.videolist.service.exception.VideoNotFoundException;
import com.udemy.videolist.service.exception.VideoNotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl extends VideoNotFoundExceptionHandler implements VideoService {

    private final VideoMapper videoMapper;

    @Override
    public List<Video> findAllVideos() {
        return videoMapper.findAllVideos();
    }

    @Override
    public Video findByVideoId(int id) {
        return this.videoMapper.findByVideoId(id).orElseThrow(() -> new VideoNotFoundException("video not found"));
    }

    @Override
    public List<Video> findByLanguage(String language) {
        return videoMapper.findByLanguage(language);
    }

    @Override
    public List<Video> findByPrice(String price) {
        return videoMapper.findByPrice(price);
    }

    @Override
    public List<Video> findByLanguageAndPrice(String language, String price) {
        return videoMapper.findByLanguageAndPrice(language, price);
    }
}
