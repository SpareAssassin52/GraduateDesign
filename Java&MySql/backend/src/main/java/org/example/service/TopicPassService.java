package org.example.service;

import org.example.pojo.PageBean;
import org.example.pojo.TopicPass;

public interface TopicPassService {
    void add(TopicPass topicPass);

    PageBean<TopicPass> list(Integer pageNum, Integer pageSize);

    void update(TopicPass topicPass);

    void delete(Integer passId);
}
