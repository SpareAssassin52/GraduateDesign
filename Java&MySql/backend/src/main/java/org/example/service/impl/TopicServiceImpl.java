package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.bytebuddy.asm.Advice;
import net.sf.jsqlparser.statement.select.Top;
import org.example.mapper.TopicMapper;
import org.example.pojo.PageBean;
import org.example.pojo.Reviews;
import org.example.pojo.Topics;
import org.example.service.ReviewService;
import org.example.service.TopicService;
import org.example.utls.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

//    @Autowired
//    private ReviewService reviewService;

    @Override
    public void add(Topics topic) {
        //no need to add created_at or updated_at because I made SQL to automatically write it for me

        //get user_id from redis
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer user_id = (Integer) claims.get("user_id");
        topic.setCreated_by(user_id);
        topicMapper.add(topic);
    }

    @Override
    public void update(Topics topics) {
        topics.setUpdated_at(LocalDateTime.now());
        topicMapper.update(topics);
    }

    @Override
    public Topics detail(Integer topicId) {
        return topicMapper.detail(topicId);
    }

    @Override
    public PageBean<Topics> list(Integer pageNum, Integer pageSize, Integer categoryId, String status, Integer assignedTo, Integer createdBy, Integer batchId, String titleKey) {
        //创建pagebean对象
        PageBean<Topics> pb = new PageBean<>();
        //开启分页查询 pagehelper
        PageHelper.startPage(pageNum, pageSize);
        //调用mapper
        List<Topics> ts = topicMapper.list(categoryId, status, assignedTo, createdBy, batchId, titleKey);
        //page中提供了方法可以获取pagehelper的分页查询后得到的总记录条数和当前查询。
        Page<Topics> p = (Page<Topics>) ts;
        //把page中的数据填充到pagebean中去
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void uploadDocs(Integer topicId, String docs) {
        topicMapper.uploadDocs(topicId, docs);
    }

    @Override
    public void updateStatus(Integer topic_id, String status) {
        topicMapper.updateStatus(topic_id, status);
    }

    @Override
    public void addExpert(Integer topicId, Integer expertId) {
        topicMapper.addExpert(topicId, expertId);
    }

    @Override
    public void delete(Integer topicId) {
        topicMapper.delete(topicId);
    }

    @Override
    public void addBatch(Integer topicId, Integer batchId) {
        topicMapper.addBatch(topicId, batchId);
    }

    @Override
    public List<Topics> findByBatch(Integer batchId) {
        return topicMapper.findByBatch(batchId);
    }

    @Override
    public List<Topics> listAuto() {
        return topicMapper.listAuto();
    }

    @Override
    public PageBean<Topics> listReview(Integer pageNum, Integer pageSize, Integer categoryId, String status, Integer assignedTo, Integer createdBy, Integer batchId, String titleKey) {
        //创建pagebean对象
        PageBean<Topics> pb = new PageBean<>();
        //开启分页查询 pagehelper
        PageHelper.startPage(pageNum, pageSize);
        //调用mapper
        List<Topics> ts = topicMapper.listReview(categoryId, status, assignedTo, createdBy, batchId, titleKey);
        //page中提供了方法可以获取pagehelper的分页查询后得到的总记录条数和当前查询。
        Page<Topics> p = (Page<Topics>) ts;
        //把page中的数据填充到pagebean中去
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
