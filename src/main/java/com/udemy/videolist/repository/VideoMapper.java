package com.udemy.videolist.repository;

import com.udemy.videolist.application.form.CreateForm;
import com.udemy.videolist.model.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface VideoMapper {

    @Select("SELECT * FROM videos")
    List<Video> findAllVideos();

    @Select("SELECT * FROM videos WHERE id = #{id}")
    Optional<Video> findById(int id);

    @Select("SELECT * FROM videos WHERE language = #{language}")
    List<Video> findByLanguage(String language);

    @Select("SELECT * FROM videos WHERE is_free = #{isFree}")
    List<Video> findByIsFree(Boolean isFree);

    @Select("SELECT * FROM videos WHERE language = #{language} AND is_free = #{isFree}")
    List<Video> findByLanguageAndIsFree(String language, Boolean isFree);

    @Insert("INSERT INTO videos (title, instructor, language, is_free, price) VALUES (#{title}, #{instructor}, #{language}, #{isFree}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createVideo(CreateForm form);
}
