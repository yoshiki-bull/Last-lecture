package com.udemy.videolist.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.udemy.videolist.model.Video;
import java.util.List;
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

  /*@Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-videos.sql", "classpath:/sqlannotation/insert-videos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void 全てのビデオが取得できること() {
    List<Video> videoList = videoMapper.findAllVideos();

    assertThat(videoList)
        .hasSize(2)
        .contains(
            new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000),
            new Video(2, "The Web Developer Bootcamp", "Colt", "English", false, 12000)
        );
  }*/


  @Test
  @DataSet(value = "videoList.yml")
  @Transactional
  void 全てのビデオが取得できること() {
    List<Video> videoList = videoMapper.findAllVideos();

    assertThat(videoList)
        .hasSize(2)
        .contains(
            new Video(1, "もう怖くないGit!", "山浦", "Japanese", false, 12000),
            new Video(2, "The Web Developer Bootcamp", "Colt", "English", false, 12000)
        );
  }
}
