package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AnalysisMapper {
    @Select("select count(*) from topics where created_by=#{userId}")
    Integer totalTopics(Integer userId);

    @Select("select count(*) from topics where created_by=#{userId} and status='pending'")
    Integer pendingTopics(Integer userId);

    @Select("select count(*) from topics where created_by=#{userId} and status = 'approved'")
    Integer passedTopics(Integer userId);

    @Select("SELECT count(*) from topics where created_by=#{userId} and  MONTH(created_at)=MONTH(CURRENT_DATE)")
    Integer thisMonthTopics(Integer userId);

    @Select("select count(*) from category where created_user=#{userId}")
    Integer totalCategorys(Integer userId);
}
