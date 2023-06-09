package com.udemy.videolist.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.udemy.videolist.application.exception.VideoNotFoundException;
import com.udemy.videolist.domain.repository.VideoRepository;
import com.udemy.videolist.domain.model.Video;
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
  VideoRepository videoRepository;
  @InjectMocks
  VideoServiceImpl videoServiceImpl;

  @Test
  public void 存在するIDのビデオが正常に取得できること() throws Exception {
    Video video = new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000);

    doReturn(Optional.of(video)).when(videoRepository).findById(1);

    Video actual = videoServiceImpl.findById(1);

    assertThat(actual).isEqualTo(video);
    verify(videoRepository, times(1)).findById(1);
  }


  @Test
  public void 存在しないビデオ情報のIDを指定した場合VideoNotFoundExceptionがthrowされること() {
    doReturn(Optional.empty()).when(videoRepository).findById(1);

    assertThatThrownBy(() -> videoServiceImpl.findById(1))
        .isInstanceOf(VideoNotFoundException.class)
        .hasMessage("ビデオID:1は見つかりませんでした");
    verify(videoRepository, times(1)).findById(1);
  }

  @Test
  public void ビデオが全件取得できること() {
    List<Video> allVideoList = List.of(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000),
        new Video(2, "Enjoy Programming", "Jonny", "English", true, 0));

    doReturn(allVideoList).when(videoRepository).findAllVideos();

    List<Video> actual = videoServiceImpl.searchVideos(null, null);

    assertThat(actual).isEqualTo(allVideoList);
    verify(videoRepository, times(1)).findAllVideos();
  }

  @Test
  public void 指定した言語と無料のビデオリストが取得できること() {
    List<Video> freeVideoList = List.of(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 0),
        new Video(2, "もう怖くないGit!", "山浦", "Japanese", false, 0));

    doReturn(freeVideoList).when(videoRepository).findByLanguageAndIsFree("Japanese", true);

    List<Video> actual = videoServiceImpl.searchVideos("Japanese", true);

    assertThat(actual).isEqualTo(freeVideoList);
    verify(videoRepository).findByLanguageAndIsFree("Japanese", true);
  }

  @Test
  public void 指定した言語と有料のビデオリストが取得できること() {
    List<Video> notFreeVideoList = List.of(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 10000),
        new Video(2, "もう怖くないGit!", "山浦", "Japanese", false, 10000));

    doReturn(notFreeVideoList).when(videoRepository).findByLanguageAndIsFree("Japanese", false);

    List<Video> actual = videoServiceImpl.searchVideos("Japanese", false);

    assertThat(actual).isEqualTo(notFreeVideoList);
    verify(videoRepository).findByLanguageAndIsFree("Japanese", false);
  }

  @Test
  public void 料金は指定せずに指定した言語のビデオリストが取得できること() {
    List<Video> japaneseVideoList = List.of(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 10000),
        new Video(2, "もう怖くないGit!", "山浦", "Japanese", false, 10000));

    doReturn(japaneseVideoList).when(videoRepository).findByLanguage("Japanese");

    List<Video> actual = videoServiceImpl.searchVideos("Japanese", null);

    assertThat(actual).isEqualTo(japaneseVideoList);
    verify(videoRepository).findByLanguage("Japanese");
  }

  @Test
  public void 言語は指定せずに無料のビデオリストが取得できること() {
    List<Video> freeVideoList = List.of(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 0),
        new Video(2, "もう怖くないGit!", "山浦", "Japanese", false, 0));

    doReturn(freeVideoList).when(videoRepository).findByIsFree(true);

    List<Video> actual = videoServiceImpl.searchVideos(null, true);

    assertThat(actual).isEqualTo(freeVideoList);
    verify(videoRepository).findByIsFree(true);
  }

  @Test
  public void ビデオが登録できること() {
    Video video = new Video("もう怖くないGit!", "山浦", "Japanese", false, 12000);

    doNothing().when(videoRepository).createVideo(video);

    videoServiceImpl.createVideo(video);
    verify(videoRepository).createVideo(video);
  }

  @Test
  public void ビデオが更新できること() throws Exception {
    Video video = new Video( "もう怖くないGit!", "山浦", "Japanese", false, 12000);

    doReturn(Optional.of(video)).when(videoRepository).findById(1);

    videoServiceImpl.updateVideo(1, video);
    verify(videoRepository).findById(1);
    verify(videoRepository).updateVideo(1, video);
  }

  @Test
  public void 更新対象のビデオが存在しない場合VideoNotFoundExceptionがthrowされること() {
    doReturn(Optional.empty()).when(videoRepository).findById(1);
    Video video = new Video("もう怖くないGit!", "山浦", "Japanese", false, 12000);

    assertThatThrownBy(() -> videoServiceImpl.updateVideo(1, video))
        .isInstanceOf(VideoNotFoundException.class)
        .hasMessage("ビデオID:1は見つかりませんでした");
    verify(videoRepository).findById(1);
    verify(videoRepository, never()).updateVideo(1, video);
  }

  @Test
  public void ビデオが削除できること() throws Exception {
    Video video = new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000);

    doReturn(Optional.of(video)).when(videoRepository).findById(1);

    videoServiceImpl.deleteVideo(1);
    verify(videoRepository).findById(1);
    verify(videoRepository).deleteVideo(1);
  }

  @Test
  public void 削除対象のビデオが存在しない場合VideoNotFoundExceptionがthrowされること() {
    doReturn(Optional.empty()).when(videoRepository).findById(1);

    assertThatThrownBy(() -> videoServiceImpl.deleteVideo(1))
        .isInstanceOf(VideoNotFoundException.class)
        .hasMessage("ビデオID:1は見つかりませんでした");
    verify(videoRepository).findById(1);
    verify(videoRepository, never()).deleteVideo(1);
  }
}
