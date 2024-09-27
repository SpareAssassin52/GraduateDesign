package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.RoleChangeRequest;

import java.util.List;

@Mapper
public interface RoleChangeMapper {

    @Insert("insert into role_change_requests(user_id, current_role, requested_role, reason) " +
            "values(#{user_id}, #{current_role}, #{requested_role}, #{reason})")
    void add(RoleChangeRequest roleChangeRequest);

    List<RoleChangeRequest> list(Integer userId, String status);

    @Update("update role_change_requests set user_id=#{user_id}, current_role=#{current_role}, requested_role=#{requested_role}, status=#{status}, reason=#{reason} where request_id=#{request_id}")
    void update(RoleChangeRequest roleChangeRequest);
}
