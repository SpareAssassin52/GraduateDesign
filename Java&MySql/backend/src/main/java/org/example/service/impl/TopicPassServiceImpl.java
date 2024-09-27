package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.TopicPassMapper;
import org.example.pojo.PageBean;
import org.example.pojo.TopicPass;
import org.example.pojo.Topics;
import org.example.service.TopicPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicPassServiceImpl implements TopicPassService {
    @Autowired
    private TopicPassMapper topicPassMapper;

    @Override
    public void add(TopicPass topicPass) {
        topicPassMapper.add(topicPass);
    }

    @Override
    public PageBean<TopicPass> list(Integer pageNum, Integer pageSize) {
        //创建pagebean对象
        PageBean<TopicPass> pb = new PageBean<>();
        //开启分页查询 pagehelper
        PageHelper.startPage(pageNum, pageSize);
        //调用mapper
        List<TopicPass> ts = topicPassMapper.list();
        //page中提供了方法可以获取pagehelper的分页查询后得到的总记录条数和当前查询。
        Page<TopicPass> p = (Page<TopicPass>) ts;
        //把page中的数据填充到pagebean中去
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void update(TopicPass topicPass) {
        topicPassMapper.update(topicPass);
    }

    @Override
    public void delete(Integer passId) {
        topicPassMapper.delete(passId);
    }
}
