package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("INSERT INTO category(category_name, description, created_user) " +
            "VALUES (#{category_name}, #{description}, #{created_user})")
    void add(Category category);

    @Select("select * from category where created_user =#{userId}")
    List<Category> list(Integer userId);

    @Select("SELECT * from category where category_id=#{categoryId}")
    Category findById(Integer categoryId);

    @Update("UPDATE category set " +
            "category_name=#{category_name}, description=#{description}, updated_at=#{updated_at} " +
            "where category_id=#{category_id}")
    void update(Category category);

    @Delete("delete from category where category_id=#{categoryId}")
    void delete(Integer categoryId);

    @Select("select * from category")
    List<Category> listAll();
}
