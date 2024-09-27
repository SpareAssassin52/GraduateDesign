package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ReviewMapper;
import org.example.pojo.*;
import org.example.service.ReviewService;
import org.example.service.TopicPassService;
import org.example.service.TopicService;
import org.example.service.UserService;
import org.example.utls.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicPassService topicPassService;
    @Autowired
    private UserService userService;

    @Override
    public void add(Reviews reviews) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer expert_id = (Integer) claims.get("user_id");
        reviews.setExpert_id(expert_id);

        reviewMapper.add(reviews);

        //为topic更改status
        if(reviews.isApproved()){
            topicService.updateStatus(reviews.getTopic_id(), "approved");

            //同时发布到公告中
            //首先获取对应的topic
            Topics topics=topicService.detail(reviews.getTopic_id());
            User topicCreator=userService.findById(topics.getCreated_by());
            User expert=userService.findById(expert_id);
            //拼装
            TopicPass topicPass=new TopicPass();
            topicPass.setTitle(topics.getTitle());
            topicPass.setDescription(topics.getDescription());
            topicPass.setCreated_by(topicCreator.getNickname());
            topicPass.setAssigned_to(expert.getNickname());
            topicPass.setCategory_id(topics.getCategory_id());
            topicPass.setBatch_id(topics.getBatch_id());
            //发布到表中

            topicPassService.add(topicPass);
        }
        else{
            topicService.updateStatus(reviews.getTopic_id(), "rejected");
        }
    }

    @Override
    public List<Reviews> findByTopic(Integer topicId) {
        return reviewMapper.findByTopic(topicId);
    }

    @Override
    public List<Reviews> findByReview(Integer reviewId) {
        return reviewMapper.findByReview(reviewId);
    }

    @Override
    public void update(Reviews reviews) {
        reviews.setUpdated_at(LocalDateTime.now());
        reviewMapper.update(reviews);
        //修改评审表的同时，需要修改课题申请表的结果
        if(reviews.isApproved()) {
            topicService.updateStatus(reviews.getTopic_id(), "approved");
        }
        else{
            topicService.updateStatus(reviews.getTopic_id(), "rejected");
        }
    }

    @Override
    public void delete(Integer reviewId) {
        reviewMapper.delete(reviewId);
    }

    @Override
    public PageBean<Reviews> list(Integer pageNum, Integer pageSize, Integer expertId) {
        //创建pagebean对象
        PageBean<Reviews> pb = new PageBean<>();
        //开启分页查询 pagehelper
        PageHelper.startPage(pageNum, pageSize);
        //调用mapper
        List<Reviews> ts = reviewMapper.list(expertId);
        //page中提供了方法可以获取pagehelper的分页查询后得到的总记录条数和当前查询。
        Page<Reviews> p = (Page<Reviews>) ts;
        //把page中的数据填充到pagebean中去
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
