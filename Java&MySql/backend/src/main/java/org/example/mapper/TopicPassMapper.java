package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.TopicPass;

import java.util.List;

@Mapper
public interface TopicPassMapper {
    @Insert("insert into topic_passed(title, description, created_by, assigned_to, category_id, batch_id) " +
            "values (#{title}, #{description}, #{created_by}, #{assigned_to}, #{category_id}, #{batch_id})")
    void add(TopicPass topicPass);

    @Select("SELECT * from topic_passed " +
            "order by created_at desc ")
    List<TopicPass> list();

    @Update("UPDATE topic_passed set title=#{title}, description=#{description}, created_by=#{created_by}, assigned_to=#{assigned_to}, category_id=#{category_id}, batch_id=#{batch_id} where pass_id=#{pass_id}")
    void update(TopicPass topicPass);

    @Delete("delete from topic_passed where pass_id=#{passId}")
    void delete(Integer passId);
}
