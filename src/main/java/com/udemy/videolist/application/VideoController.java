package com.udemy.videolist.application;

import com.udemy.videolist.model.Video;
import com.udemy.videolist.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/{id}")
    public Optional<Video> findByVideoId(@PathVariable("id") int videoId) {
        return videoService.findByVideoId(videoId);
    }

    @GetMapping("/search")
    public List<VideoResponse> findByLanguage(@RequestParam(value = "lang", required = false) String language,
                                              @RequestParam(value = "price", required = false) String price) {
        if (language != null && price != null) {
            return videoService.findByLanguageAndPrice(language, price).stream().map(VideoResponse::new).toList();
        } else if (language != null) {
            return videoService.findByLanguage(language).stream().map(VideoResponse::new).toList();
        } else if (price != null) {
            return videoService.findByPrice(price).stream().map(VideoResponse::new).toList();
        } else {
            return videoService.findAllVideos().stream().map(VideoResponse::new).toList();
        }
    }
}
