package com.udemy.videolist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.udemy.videolist.application.exception.VideoNotFoundException;
import com.udemy.videolist.application.form.CreateForm;
import com.udemy.videolist.application.form.UpdateForm;
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
  VideoServiceImpl videoServiceImpl;

  @Test
  public void 存在するIDのビデオが正常に取得できること() throws Exception {
    Video video = new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000);

    doReturn(Optional.of(video)).when(videoMapper).findById(1);

    Video actual = videoServiceImpl.findById(1);

    assertThat(actual).isEqualTo(video);
    verify(videoMapper, times(1)).findById(1);
  }

  @Test
  public void 存在しないビデオ情報のIDを指定した場合VideoNotFoundExceptionがthrowされること() {
    doReturn(Optional.empty()).when(videoMapper).findById(1);

    assertThatThrownBy(() -> videoServiceImpl.findById(1))
        .isInstanceOf(VideoNotFoundException.class)
        .hasMessage("ビデオID:1は見つかりませんでした");
    verify(videoMapper, times(1)).findById(1);
  }

  @Test
  public void ビデオが全件取得できること() {
    List<Video> allVideoList = List.of(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000),
        new Video(2, "Enjoy Programming", "Jonny", "English", true, 0));

    doReturn(allVideoList).when(videoMapper).findAllVideos();

    List<Video> actual = videoServiceImpl.searchVideos(null, null);

    assertThat(actual).isEqualTo(allVideoList);
    verify(videoMapper, times(1)).findAllVideos();
  }

  @Test
  public void 指定した言語と無料のビデオリストが取得できること() {
    List<Video> freeVideoList = List.of(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 0),
        new Video(2, "もう怖くないGit!", "山浦", "Japanese", false, 0));

    doReturn(freeVideoList).when(videoMapper).findByLanguageAndIsFree("Japanese", true);

    List<Video> actual = videoServiceImpl.searchVideos("Japanese", true);

    assertThat(actual).isEqualTo(freeVideoList);
    verify(videoMapper).findByLanguageAndIsFree("Japanese", true);
  }

  @Test
  public void 指定した言語と有料のビデオリストが取得できること() {
    List<Video> notFreeVideoList = List.of(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 10000),
        new Video(2, "もう怖くないGit!", "山浦", "Japanese", false, 10000));

    doReturn(notFreeVideoList).when(videoMapper).findByLanguageAndIsFree("Japanese", false);

    List<Video> actual = videoServiceImpl.searchVideos("Japanese", false);

    assertThat(actual).isEqualTo(notFreeVideoList);
    verify(videoMapper).findByLanguageAndIsFree("Japanese", false);
  }

  @Test
  public void 料金は指定せずに指定した言語のビデオリストが取得できること() {
    List<Video> japaneseVideoList = List.of(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 10000),
        new Video(2, "もう怖くないGit!", "山浦", "Japanese", false, 10000));

    doReturn(japaneseVideoList).when(videoMapper).findByLanguage("Japanese");

    List<Video> actual = videoServiceImpl.searchVideos("Japanese", null);

    assertThat(actual).isEqualTo(japaneseVideoList);
    verify(videoMapper).findByLanguage("Japanese");
  }

  @Test
  public void 言語は指定せずに無料のビデオリストが取得できること() {
    List<Video> freeVideoList = List.of(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 0),
        new Video(2, "もう怖くないGit!", "山浦", "Japanese", false, 0));

    doReturn(freeVideoList).when(videoMapper).findByIsFree(true);

    List<Video> actual = videoServiceImpl.searchVideos(null, true);

    assertThat(actual).isEqualTo(freeVideoList);
    verify(videoMapper).findByIsFree(true);
  }

  @Test
  public void ビデオが登録できること() {
    CreateForm createForm = new CreateForm();

    doNothing().when(videoMapper).createVideo(createForm);

    videoServiceImpl.createVideo(createForm);
    verify(videoMapper).createVideo(createForm);
  }

  @Test
  public void ビデオが更新できること() throws Exception {
    Video video = new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000);
    UpdateForm updateForm = new UpdateForm();

    doReturn(Optional.of(video)).when(videoMapper).findById(1);

    videoServiceImpl.updateVideo(1, updateForm);
    verify(videoMapper).findById(1);
    verify(videoMapper).updateVideo(1, updateForm);
  }

  @Test
  public void 更新対象のビデオが存在しない場合VideoNotFoundExceptionがthrowされること() {
    doReturn(Optional.empty()).when(videoMapper).findById(1);
    UpdateForm updateForm = new UpdateForm();

    assertThatThrownBy(() -> videoServiceImpl.updateVideo(1, updateForm))
        .isInstanceOf(VideoNotFoundException.class)
        .hasMessage("ビデオID:1は見つかりませんでした");
    verify(videoMapper).findById(1);
    verify(videoMapper, never()).updateVideo(1, updateForm);
  }

  @Test
  public void ビデオが削除できること() throws Exception {
    Video video = new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000);

    doReturn(Optional.of(video)).when(videoMapper).findById(1);

    videoServiceImpl.deleteVideo(1);
    verify(videoMapper).findById(1);
    verify(videoMapper).deleteVideo(1);
  }

  @Test
  public void 削除対象のビデオが存在しない場合VideoNotFoundExceptionがthrowされること() {
    doReturn(Optional.empty()).when(videoMapper).findById(1);

    assertThatThrownBy(() -> videoServiceImpl.deleteVideo(1))
        .isInstanceOf(VideoNotFoundException.class)
        .hasMessage("ビデオID:1は見つかりませんでした");
    verify(videoMapper).findById(1);
    verify(videoMapper, never()).deleteVideo(1);
  }
}
