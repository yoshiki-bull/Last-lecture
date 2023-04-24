package com.udemy.videolist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import com.udemy.videolist.model.Video;
import com.udemy.videolist.repository.VideoMapper;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VideoServiceImplTest {

  @Mock
  VideoMapper videoMapper;

  @InjectMocks
  VideoServiceImpl videoServiceimpl;

  Video video = new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000);

  @Test
  void 指定したIDのビデオが正常に取得できること() throws Exception {

    doReturn(Optional.of(video)).when(videoMapper).findById(1);

    Video actual = videoServiceimpl.findById(1);
    assertThat(actual).isEqualTo(video);
  }
}
