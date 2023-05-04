package com.udemy.videolist.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VideoRestApiIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @DataSet(value = "datasets/videoList.yml")
  void 全てのビデオを取得できること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders
        .get("/api/videos/search"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        [{
            "id": 1,
            "title": "もう怖くないGit!",
            "instructor": "山浦",
            "language": "Japanese",
            "price": 12000
        },
        {
            "id": 2,
            "title": "The Web Developer Bootcamp",
            "instructor": "Colt",
            "language": "English",
            "price": 0
        }]
        """, response, JSONCompareMode.STRICT);
  }
}
