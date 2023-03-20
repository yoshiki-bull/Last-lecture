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

    @GetMapping("/{videoId}")
    public Optional<Video> findByVideoId(@PathVariable("videoId") int videoId) {
        return videoService.findByVideoId(videoId);
    }

    @GetMapping("/search/")
    public List<VideoResponse> findByLanguage(@RequestParam(value = "lang", required = false) String language,
                                              @RequestParam(value = "price", required = false) String price) {
        if (language != null && price == null) {
            return videoService.findByLanguage(language).stream().map(VideoResponse::new).toList();
        } else if (price != null && language == null) {
            return videoService.findByPrice(price).stream().map(VideoResponse::new).toList();
        } else {
            return videoService.findAllVideos().stream().map(VideoResponse::new).toList();
        }
    }
}
