package com.udemy.videolist.service;

import com.udemy.videolist.application.form.CreateForm;
import com.udemy.videolist.model.Video;

import java.util.List;

public interface VideoService {

    Video findById(int id);

    List<Video> searchVideos(String language, Boolean isFree);

    void createVideo(CreateForm form);
}
