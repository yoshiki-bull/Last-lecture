package com.udemy.videolist.integrationtest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.jayway.jsonpath.JsonPath;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VideoRestApiIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  void 全てのビデオを取得できること() throws Exception {
    String jsonResponse = mockMvc.perform(MockMvcRequestBuilders
            .get("/api/videos/search"))
            .andExpect(status().isOk())
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
        """, jsonResponse, JSONCompareMode.STRICT);
  }

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  void 指定したIDのビデオが取得できること() throws Exception {
    String url = "/api/videos/1";
    String jsonResponse = mockMvc.perform(MockMvcRequestBuilders
            .get(url)
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals(""" 
        {
            "id": 1,
            "title": "もう怖くないGit!",
            "instructor": "山浦",
            "language": "Japanese",
            "price": 12000
        }""", jsonResponse, true);
  }

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  void 存在しないIDを指定してビデオを取得しようとした場合に404エラーとなり独自例外がスローされること() throws Exception {
    String url = "/api/videos/0";
    ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 5, 5, 17, 0, 0, 0, ZoneId.of("Asia/Tokyo"));

    // try-with-resources モック化されたstaticメソッドを開放
    try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
      zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDateTime);

      String jsonResponse = mockMvc.perform(MockMvcRequestBuilders
              .get(url)
              .accept(MediaType.APPLICATION_JSON_VALUE)) //perform
              .andExpect(status().isNotFound())
              .andReturn()
              .getResponse()
              .getContentAsString(StandardCharsets.UTF_8);

      JSONAssert.assertEquals("""
          {
              "status": "404",
              "path": "/api/videos/0",
              "error": "Not Found",
              "timestamp": "2023-05-05T17:00+09:00[Asia/Tokyo]",
              "message": "ビデオID:0は見つかりませんでした"
          }""", jsonResponse, true);
    }
  }

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  void 言語のみを指定した場合に指定した言語のビデオのみがJSON配列形式で取得できること() throws Exception {
    String url = "/api/videos/search?lang=Japanese";
    String jsonResponse = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        [{
            "id": 1,
            "title": "もう怖くないGit!",
            "instructor": "山浦",
            "language": "Japanese",
            "price": 12000
        }]
        """, jsonResponse, true);
  }

  @Test
  @Transactional
  @DataSet("videoList.yml")
  void 指定した無料状態であるビデオのみがJSON配列形式で取得できること() throws Exception {
    String url = "/api/videos/search?is_free=true";
    String jsonResponse = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        [{
            "id": 2,
            "title": "The Web Developer Bootcamp",
            "instructor": "Colt",
            "language": "English",
            "price": 0
        }]
        """, jsonResponse, true);
  }

  @Test
  @Transactional
  @DataSet("videoList.yml")
  void 指定した言語かつ無料状態であるビデオのみがJSON配列形式で取得できること() throws Exception {
    String url = "/api/videos/search?lang=English&is_free=true";
    String jsonResponse = mockMvc.perform(MockMvcRequestBuilders
            .get(url)
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        [{
            "id": 2,
            "title": "The Web Developer Bootcamp",
            "instructor": "Colt",
            "language": "English",
            "price": 0
        }]
        """, jsonResponse, true);
  }

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  @ExpectedDataSet(value = "expectedAfterInsertVideo.yml", ignoreCols = "id")
  void 要件を満たしていれば正常にビデオが登録できること() throws Exception {
    String url = "/api/videos";
    String jsonResponse = mockMvc.perform(MockMvcRequestBuilders
            .post(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("""
                {
                    "title": "もう怖くないLinux",
                    "instructor": "山浦",
                    "language": "Japanese",
                    "isFree": true,
                    "price": "0"
                }
                """)).andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    int generatedId = JsonPath.read(jsonResponse, "$.id");

    String expectedJson = String.format("""
        {
            "message": "video successfully created",
            "id": %d,
            "title": "もう怖くないLinux",
            "instructor": "山浦",
            "language": "Japanese",
            "isFree": true,
            "price": 0
        }
        """, generatedId);

    JSONAssert.assertEquals(expectedJson, jsonResponse, true);
  }

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  @ExpectedDataSet(value = "expectedAfterUpdateVideo.yml")
  void 要件を満たしていれば正常にビデオ情報を更新できること() throws Exception {
    String url = "/api/videos/1";
    String jsonResponse = mockMvc.perform(MockMvcRequestBuilders
        .patch(url)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content("""
                {
                    "title": "もう怖くないLinux",
                    "instructor": "山浦",
                    "language": "Japanese",
                    "isFree": true,
                    "price": "0"
                }
                """))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
        {
            "message": "video successfully updated",
            "title": "もう怖くないLinux",
            "instructor": "山浦",
            "language": "Japanese",
            "isFree": true,
            "price": 0
        }
        """, jsonResponse, true);
  }

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  void 存在しないIDを指定してビデオ情報を更新しようとした場合に404エラーとなり独自例外がスローされること() throws Exception {
    String url = "/api/videos/0";
    ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 5, 5, 17, 0, 0, 0, ZoneId.of("Asia/Tokyo"));

    try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
      zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDateTime);

      String jsonResponse = mockMvc.perform(MockMvcRequestBuilders
              .patch(url)
              .contentType(MediaType.APPLICATION_JSON_VALUE)
              .content("""
                {
                    "title": "もう怖くないLinux",
                    "instructor": "山浦",
                    "language": "Japanese",
                    "isFree": true,
                    "price": "0"
                }
                """))
          .andExpect(status().isNotFound())
          .andReturn()
          .getResponse()
          .getContentAsString(StandardCharsets.UTF_8);

      JSONAssert.assertEquals("""
          {
              "status": "404",
              "path": "/api/videos/0",
              "error": "Not Found",
              "timestamp": "2023-05-05T17:00+09:00[Asia/Tokyo]",
              "message": "ビデオID:0は見つかりませんでした"
          }""", jsonResponse, true);
    }
  }

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  @ExpectedDataSet(value = "expectedAfterDeleteVideo.yml")
  void 指定したIDのビデオ情報が正常に削除できること() throws Exception {
    String url = "/api/videos/1";
    mockMvc.perform(MockMvcRequestBuilders
        .delete(url))
        .andExpect(status().isNoContent());
  }

  @Test
  @Transactional
  @DataSet(value = "videoList.yml")
  void 存在しないビデオを指定してビデオ情報を削除しようとした場合に404エラーが発生し独自例外がスローされること() throws Exception {
    String url = "/api/videos/0";
    ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 5, 5, 17, 0, 0, 0, ZoneId.of("Asia/Tokyo"));

    try (MockedStatic<ZonedDateTime> zonedDateTimeMockedStatic = Mockito.mockStatic(ZonedDateTime.class)) {
      zonedDateTimeMockedStatic.when(ZonedDateTime::now).thenReturn(zonedDateTime);

      mockMvc.perform(MockMvcRequestBuilders
              .delete(url))
              .andExpect(status().isNotFound());

      String jsonResponse = """
          {
              "status": "404",
              "path": "/api/videos/0",
              "error": "Not Found",
              "timestamp": "2023-05-05T17:00+09:00[Asia/Tokyo]",
              "message": "ビデオID:0は見つかりませんでした"
          }
          """;

      JSONAssert.assertEquals("""
          {
              "status": "404",
              "path": "/api/videos/0",
              "error": "Not Found",
              "timestamp": "2023-05-05T17:00+09:00[Asia/Tokyo]",
              "message": "ビデオID:0は見つかりませんでした"
          }""", jsonResponse, true);
    }
  }
}
