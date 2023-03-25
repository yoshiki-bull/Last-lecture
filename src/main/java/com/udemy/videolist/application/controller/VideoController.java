package com.udemy.videolist.application.controller;

import com.udemy.videolist.model.Video;
import com.udemy.videolist.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/{id}")
    public Video findByVideoId(@PathVariable("id") int videoId) {
        return videoService.findByVideoId(videoId);
    }

    @GetMapping("/search")
    public List<VideoResponse> searchVideos(@RequestParam(value = "lang", required = false) String language,
                                            @RequestParam(value = "is_free", required = false) Boolean isFree) {
        return videoService.searchVideos(language, isFree).stream().map(VideoResponse::new).toList();
    }
}
