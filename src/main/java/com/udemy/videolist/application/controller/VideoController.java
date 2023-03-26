package com.udemy.videolist.application.controller;

import com.udemy.videolist.application.form.CreateForm;
import com.udemy.videolist.model.Video;
import com.udemy.videolist.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/{id}")
    public Video findById(@PathVariable("id") int id) {
        return videoService.findById(id);
    }

    @GetMapping("/search")
    public List<VideoResponse> searchVideos(@RequestParam(value = "lang", required = false) String language,
                                            @RequestParam(value = "is_free", required = false) Boolean isFree) {
        return videoService.searchVideos(language, isFree).stream().map(VideoResponse::new).toList();
    }

    @PostMapping
    public ResponseEntity<VideoCreateResponse> createVideo(@RequestBody @Validated CreateForm form, UriComponentsBuilder builder) {
        videoService.createVideo(form);
        URI uri = builder.path("/videos/" + form.getId())
                .build()
                .toUri();
        return ResponseEntity.created(uri).body(new VideoCreateResponse(form));
    }
}
