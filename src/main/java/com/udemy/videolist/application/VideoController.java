package com.udemy.videolist.application;

import com.udemy.videolist.model.Video;
import com.udemy.videolist.service.VideoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public List<VideoResponse> findAllVideos() {
        return videoService.findAllVideos().stream().map(VideoResponse::new).toList();
    }

    @GetMapping("/{videoId}")
    public Optional<Video> findByVideoId(@PathVariable("videoId") int videoId) {
        return videoService.findByVideoId(videoId);
    }

    @GetMapping("/search")
    public List<VideoResponse> findByLanguage(@RequestParam(value = "lang", defaultValue = "Japanese") String language) {
        return videoService.findByLanguage(language).stream().map(VideoResponse::new).toList();
    }
}
