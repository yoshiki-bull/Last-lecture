package com.udemy.videolist.repository;

import com.github.database.rider.junit5.api.DBRider;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VideoMapperTest {

  @Autowired
  VideoMapper videoMapper;
}
