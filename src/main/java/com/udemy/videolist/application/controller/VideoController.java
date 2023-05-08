package com.udemy.videolist.application.controller;

import com.udemy.videolist.application.form.CreateForm;
import com.udemy.videolist.application.form.UpdateForm;
import com.udemy.videolist.model.Video;
import com.udemy.videolist.service.VideoService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;

  @GetMapping("/{id}")
  public VideoResponse findById(@PathVariable("id") Integer id) {
    return new VideoResponse(videoService.findById(id));

  }

  @GetMapping("/search")
  public List<VideoResponse> searchVideos(
      @RequestParam(value = "lang", required = false) String language,
      @RequestParam(value = "is_free", required = false) Boolean isFree) {
    return videoService.searchVideos(language, isFree).stream().map(VideoResponse::new).toList();
  }

  @PostMapping
  public ResponseEntity<VideoCreateResponse> createVideo(
      @RequestBody @Validated CreateForm form, UriComponentsBuilder builder) {
    Video video = new Video(
        form.getTitle(),
        form.getInstructor(),
        form.getLanguage(),
        form.getIsFree(),
        form.getPrice());

    videoService.createVideo(video);

    URI uri = builder
        .path("/api/videos/" + video.getId())
        .build()
        .toUri();

    return ResponseEntity.created(uri).body(new VideoCreateResponse(video));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<VideoUpdateResponse> updateVideo(
      @PathVariable("id") Integer id, @RequestBody @Validated UpdateForm form) {
    Video video = new Video(
        form.getTitle(),
        form.getInstructor(),
        form.getLanguage(),
        form.getIsFree(),
        form.getPrice());

    videoService.updateVideo(id, video);

    return ResponseEntity.ok(new VideoUpdateResponse(video));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Video> deleteVideo(@PathVariable("id") Integer id) {
    videoService.deleteVideo(id);

    return ResponseEntity.noContent().build();
  }
}
