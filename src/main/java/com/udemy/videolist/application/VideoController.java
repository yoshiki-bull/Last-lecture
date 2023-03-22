package com.udemy.videolist.application;

import com.udemy.videolist.model.Video;
import com.udemy.videolist.service.VideoService;
import com.udemy.videolist.service.exception.VideoNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/{id}")
    public Video findByVideoId(@PathVariable("id") int videoId) {
        return videoService.findByVideoId(videoId);
    }

    @ExceptionHandler(value = VideoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlerNoResourceFound(VideoNotFoundException e, HttpServletRequest request) {

        Map<String, String> body = Map.of(
        "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
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
