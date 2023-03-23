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

    @Select("SELECT * FROM videos WHERE is_free = #{isFree}")
    List<Video> findByPrice(Boolean isFree);

    @Select("SELECT * FROM videos WHERE language = #{language} AND is_free = #{isFree}")
    List<Video> findByLanguageAndPrice(String language, Boolean isFree);
}
