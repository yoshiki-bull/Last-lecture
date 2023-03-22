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
    public Video findByVideoId(int id) {
        return this.videoMapper.findByVideoId(id).orElseThrow(() -> new VideoNotFoundException("video not found"));
    }

    @Override
    public List<Video> searchVideos(String language, String priceState) {
        if (language != null && priceState != null) {
            return videoMapper.findByLanguageAndPrice(language, priceState);
        } else if (language != null) {
            return videoMapper.findByLanguage(language);
        } else if (priceState != null) {
            return videoMapper.findByPrice(priceState);
        } else {
            return videoMapper.findAllVideos();
        }
    }
}
