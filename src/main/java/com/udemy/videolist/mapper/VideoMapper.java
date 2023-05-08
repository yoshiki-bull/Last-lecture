package com.udemy.videolist.mapper;

import com.udemy.videolist.model.Video;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface VideoMapper {

  @Select("SELECT * "
      + "FROM videos")
  List<Video> findAllVideos();

  @Select("SELECT * "
      + "FROM videos "
      + "WHERE "
      + " id = #{id}")
  Optional<Video> findById(Integer id);

  @Select("SELECT * "
      + "FROM videos "
      + " WHERE "
      + " language = #{language}")
  List<Video> findByLanguage(String language);

  @Select("SELECT * "
      + " FROM videos "
      + " WHERE is_free = #{isFree}")
  List<Video> findByIsFree(Boolean isFree);

  @Select("SELECT * "
      + " FROM videos "
      + " WHERE "
      + " language = #{language} "
      + " AND "
      + " is_free = #{isFree}")
  List<Video> findByLanguageAndIsFree(String language, Boolean isFree);

  @Insert("INSERT INTO videos ("
      + "title, "
      + "instructor, "
      + "language, "
      + "is_free, "
      + "price "
      + ") VALUES ("
      + "#{title}, "
      + "#{instructor}, "
      + "#{language}, "
      + "#{isFree}, "
      + "#{price})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void createVideo(Video video);

  @Update("UPDATE videos "
      + "SET "
      + "title = #{video.title}, "
      + "instructor = #{video.instructor}, "
      + "language = #{video.language}, "
      + "is_free = #{video.isFree}, "
      + "price = #{video.price} "
      + "WHERE "
      + "id = #{id}")
  void updateVideo(Integer id, Video video);

  @Delete("DELETE FROM videos "
      + " WHERE "
      + " id = #{id}")
  void deleteVideo(Integer id);
}
