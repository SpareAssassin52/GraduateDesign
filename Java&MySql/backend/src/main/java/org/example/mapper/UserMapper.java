package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.Result;
import org.example.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from users where username=#{username}")
    User findByName(String username);

    @Insert("insert into users(username, password, role) " +
            "values(#{username}, #{password}, #{role})")  //前端需要校验数据
    void add(String username, String password, String role);

    @Update("update users set username=#{username}, nickname=#{nickname}, email=#{email}, avatar_url=#{avatar_url}, role=#{role}, updated_at=#{updated_at} where user_id=#{user_id}")
    void update(User user);
    @Update("update users set avatar_url=#{avatar_url}, updated_at=now() where user_id=#{user_id}")
    void updateAvatar(String avatar_url, Integer user_id);
    @Update("update users set password=#{newPwd}, updated_at=now() where user_id=#{userId}")
    void updatePwd(String newPwd, Integer userId);

    @Update("update users set role=#{newRole} where user_id=#{userId}")
    void updateRole(String newRole, Integer userId);


    @Select("select * from users where user_id=#{userId}")
    User findById(Integer userId);

    @Select("select * from users where role=#{role}")
    List<User> findByRole(String role);

    List<User> listAll(Integer userId, String role, String userNameKey);
}
