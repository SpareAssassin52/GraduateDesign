package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Reviews;

import java.util.List;

@Mapper
public interface ReviewMapper {
    @Insert("insert into reviews(topic_id, expert_id, comments, approved) " +
            "VALUES(#{topic_id}, #{expert_id}, #{comments}, #{approved}) ")
    void add(Reviews reviews);

    @Select("select * from reviews where topic_id=#{topicId}")
    List<Reviews> findByTopic(Integer topicId);

    @Select("select * from reviews where review_id=#{reviewId}")
    List<Reviews> findByReview(Integer reviewId);

    @Update("update reviews set topic_id=#{topic_id}, expert_id=#{expert_id}, comments=#{comments}, approved=#{approved}, updated_at=#{updated_at} where review_id=#{review_id}")
    void update(Reviews reviews);

    @Delete("delete from reviews  where review_id=#{reviewId}")
    void delete(Integer reviewId);

    @Select("select * from reviews where expert_id=#{expertId}")
    List<Reviews> list(Integer expertId);
}
