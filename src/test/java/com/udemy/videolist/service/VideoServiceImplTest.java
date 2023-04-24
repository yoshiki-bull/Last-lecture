package com.udemy.videolist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

import com.udemy.videolist.application.exception.VideoNotFoundException;
import com.udemy.videolist.model.Video;
import com.udemy.videolist.repository.VideoMapper;
import java.util.List;
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

  List<Video> videoList = List.of(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000),
      new Video(2, "Enjoy Programming", "Jonny", "English", true, 0));

  @Test
  public void 存在するIDのビデオが正常に取得できること() throws Exception {

    doReturn(Optional.of(video)).when(videoMapper).findById(1);

    Video actual = videoServiceimpl.findById(1);
    assertThat(actual).isEqualTo(video);
  }

  @Test
  public void 存在しないビデオ情報のIDを指定した場合VideoNotFoundExceptionがthrowされること() {
    doReturn(Optional.empty()).when(videoMapper).findById(1);

    assertThatThrownBy(() -> videoServiceimpl.findById(1))
        .isInstanceOf(VideoNotFoundException.class)
        .hasMessage("ビデオID:1は見つかりませんでした");
  }

  @Test
  public void ビデオが全件取得できること() {
    doReturn(videoList).when(videoMapper).findAllVideos();

    List<Video> actual = videoServiceimpl.searchVideos(null, null);
    assertThat(actual).isEqualTo(videoList);
  }
}
