package com.udemy.videolist.service;

import com.udemy.videolist.model.Video;
import com.udemy.videolist.repository.VideoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    private VideoMapper videoMapper;

    public VideoServiceImpl(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Override
    public List<Video> findAllVideos() {
        return videoMapper.findAllVideos();
    }

    @Override
    public Optional<Video> findByVideoId(int videoId) {
        return videoMapper.findByVideoId(videoId);
    }
}
