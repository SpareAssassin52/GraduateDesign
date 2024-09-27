package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.UserMapper;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.pojo.Topics;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.utls.Md5Util;
import org.example.utls.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public void register(String username, String password, String role) {   //传进整个user结构，时间不用传，数据图添加的时候会默认添加时间
        //encrypt password first
        String md5String = Md5Util.getMD5String(password);
        //call mapper to register
        userMapper.add(username, md5String, role);
    }

    @Override
    public void update(User user) {
        user.setUpdated_at(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatar_url) {
        Map<String, Object> cliams = ThreadLocalUtil.get();
        Integer user_id = (Integer) cliams.get("user_id");
        userMapper.updateAvatar(avatar_url, user_id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer user_id=(Integer) claims.get("user_id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), user_id);
    }

    @Override
    public void updateRole(String newRole, Integer userId) {
        userMapper.updateRole(newRole, userId);
    }

    @Override
    public User findById(Integer userId) {
        return userMapper.findById(userId);
    }

    @Override
    public List<User> findByRole(String role) {
        return userMapper.findByRole(role);
    }

    @Override
    public PageBean<User> listAll(Integer pageNum, Integer pageSize, Integer userId, String role, String userNameKey) {
        //创建pagebean对象
        PageBean<User> pb = new PageBean<>();
        //开启分页查询 pagehelper
        PageHelper.startPage(pageNum, pageSize);
        //调用mapper
        List<User> ts = userMapper.listAll(userId, role, userNameKey);
        //page中提供了方法可以获取pagehelper的分页查询后得到的总记录条数和当前查询。
        Page<User> p = (Page<User>) ts;
        //把page中的数据填充到pagebean中去
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }


}
