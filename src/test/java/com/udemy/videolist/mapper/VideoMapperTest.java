package com.udemy.videolist.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.udemy.videolist.model.Video;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VideoMapperTest {

  @Autowired
  VideoMapper videoMapper;

  @Test
  @DataSet(value = "videoList.yml")
  @Transactional
  public void 全てのビデオが取得できること() {
    List<Video> videoList = videoMapper.findAllVideos();

    assertThat(videoList)
        .hasSize(2)
        .contains(
            new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000),
            new Video(2, "The Web Developer Bootcamp", "Colt", "English", true, 0)
        );
  }

  @Test
  @DataSet(value = "empty.yml")
  @Transactional
  void ビデオが存在しない場合は空のListを返すこと() {
    List<Video> videoList = videoMapper.findAllVideos();

    assertThat(videoList).isEmpty();
  }

  @Test
  @DataSet(value = "videoList.yml")
  @Transactional
  void 指定したidのビデオを取得できること() {
    Optional<Video> video = videoMapper.findById(1);

    assertThat(video)
        .contains(new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000));
  }

  @Test
  @DataSet(value = "videoList.yml")
  @Transactional
  void 指定したidのビデオが存在しない場合_空のOptionalを返すこと() {
    Optional<Video> video = videoMapper.findById(3);

    assertThat(video).isEmpty();
  }

  @Test
  @DataSet(value = "videoList.yml")
  @Transactional
  void 指定した言語のビデオのみが取得できること() {
    List<Video> video = videoMapper.findByLanguage("English");

    assertThat(video)
        .contains(new Video(2, "The Web Developer Bootcamp", "Colt", "English", true, 0));
  }

  @Test
  @DataSet(value = "videoList.yml")
  @Transactional
  void 指定した有料か無料であるビデオのみが取得できること() {
    List<Video> video = videoMapper.findByIsFree(true);

    assertThat(video)
        .contains(new Video(2, "The Web Developer Bootcamp", "Colt", "English", true, 0));
  }

  @Test
  @DataSet(value = "videoList.yml")
  @Transactional
  void 指定した言語と有料か無料のビデオのみが取得できること() {
    List<Video> video = videoMapper.findByLanguageAndIsFree("English", true);

    assertThat(video)
        .contains(new Video(2, "The Web Developer Bootcamp", "Colt", "English", true, 0));
  }

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  @ExpectedDataSet(value = "autoIncrementID.yml", ignoreCols = "id")
  void DBに新規ビデオが登録された際にidが自動で増加的に採番されること() {
    Video video3 = new Video("もう怖くないLinux", "山浦", "Japanese", true, 0);
    assertThat(video3.getId()).isNull();

    videoMapper.createVideo(video3);
    assertThat(video3.getId()).isNotNull();

    Video video4 = new Video("Mockito", "Mike", "English", true, 0);
    assertThat(video4.getId()).isNull();

    videoMapper.createVideo(video4);
    assertThat(video4.getId()).isNotNull();

    assertThat(video4.getId()).isGreaterThan(video3.getId());
    assertThat(video3.getId()).isLessThan(video4.getId());
    assertTrue(video4.getId() > video3.getId());
  }

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  @ExpectedDataSet(value = "expectedAfterUpdateVideo.yml")
  void 指定したidのビデオ情報が更新できること() {
    Video video = new Video("もう怖くないLinux", "山浦", "Japanese", true, 0);

    videoMapper.updateVideo(1, video);
  }

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  @ExpectedDataSet(value = "expectedAfterDeleteVideo.yml")
  void 指定したidのビデオを削除できること() {
    videoMapper.deleteVideo(1);
  }
}
