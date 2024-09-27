package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.AdminActions;

import java.util.List;

@Mapper
public interface AdminActionsMapper {

    @Insert("insert into admin_actions(admin_id, action_type, description) " +
            "values (#{admin_id}, #{action_type}, #{description})")
    void add(AdminActions adminActions);

    @Select("select * from admin_actions " +
            "order by created_at desc")
    List<AdminActions> list();
}
