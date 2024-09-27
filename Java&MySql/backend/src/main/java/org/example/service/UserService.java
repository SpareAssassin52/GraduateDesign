package org.example.service;

import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.pojo.User;

import java.util.List;

public interface UserService {
    User findByName(String username);

    void register(String username, String password, String role);

    void update(User user);

    void updateAvatar(String avatar_url);

    void updatePwd(String newPwd);

    void updateRole(String newRole, Integer userId);

    User findById(Integer userId);

    List<User> findByRole(String role);

    PageBean<User> listAll(Integer pageNum, Integer pageSize, Integer userId, String role, String userNameKey);
}
