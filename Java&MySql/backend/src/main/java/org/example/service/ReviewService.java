package org.example.service;

import org.example.pojo.PageBean;
import org.example.pojo.Reviews;

import java.util.List;

public interface ReviewService {
    void add(Reviews reviews);

    List<Reviews> findByTopic(Integer topicId);

    List<Reviews> findByReview(Integer reviewId);

    void update(Reviews reviews);

    void delete(Integer reviewId);

    PageBean<Reviews> list(Integer pageNum, Integer pageSize, Integer expertId);
}
