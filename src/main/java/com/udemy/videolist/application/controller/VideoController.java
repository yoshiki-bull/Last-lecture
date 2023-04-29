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
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

  private final VideoService videoService;

  @GetMapping("/{id}")
  public Video findById(@PathVariable("id") int id) {
    return videoService.findById(id);
  }

  @GetMapping("/search")
  public List<VideoResponse> searchVideos(
      @RequestParam(value = "lang", required = false) String language,
      @RequestParam(value = "is_free", required = false) Boolean isFree) {
    return videoService.searchVideos(language, isFree).stream().map(VideoResponse::new).toList();
  }

  @PostMapping
  public ResponseEntity<VideoCreateResponse> createVideo(@RequestBody @Validated CreateForm form,
                                                         UriComponentsBuilder builder) {
    videoService.createVideo(form.getTitle(), form.getInstructor(), form.getLanguage(), form.getIsFree(), Integer.parseInt(form.getPrice()));

    URI uri = builder.path("/videos/" + form.getId())
        .build()
        .toUri();
    return ResponseEntity.created(uri).body(new VideoCreateResponse(form));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<VideoUpdateResponse> updateVideo(@PathVariable("id") int id,
                                                         @RequestBody @Validated UpdateForm form) {
    videoService.updateVideo(id, form);
    return ResponseEntity.ok(new VideoUpdateResponse(form));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Video> deleteVideo(@PathVariable("id") int id) {
    videoService.deleteVideo(id);
    return ResponseEntity.noContent().build();
  }
}
