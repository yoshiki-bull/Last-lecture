package com.udemy.videolist.repository;

import com.udemy.videolist.model.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface VideoMapper {

    @Select("SELECT * FROM videos")
    List<Video> findAllVideos();

    @Select("SELECT * FROM videos WHERE id = #{id}")
    Optional<Video> findByVideoId(int id);

    @Select("SELECT * FROM videos WHERE language = #{language}")
    List<Video> findByLanguage(String language);

    @Select("SELECT * FROM videos WHERE price = #{price}")
    List<Video> findByPrice(String price);

    @Select("SELECT * FROM videos WHERE language = #{language} AND price = #{price}")
    List<Video> findByLanguageAndPrice(String language, String price);
}
