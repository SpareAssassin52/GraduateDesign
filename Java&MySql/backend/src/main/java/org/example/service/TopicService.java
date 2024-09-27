package org.example.service;

import org.example.pojo.PageBean;
import org.example.pojo.Topics;

import java.util.List;

public interface TopicService {
    void add(Topics topic);

    void update(Topics topics);

    Topics detail(Integer topicId);

    PageBean<Topics> list(Integer pageNum, Integer pageSize, Integer categoryId, String status, Integer assignedTo, Integer createdBy, Integer batchId, String titleKey);

    void uploadDocs(Integer topicId, String docs);

    void updateStatus(Integer topic_id, String status);

    void addExpert(Integer topicId, Integer expertId);

    void delete(Integer topicId);

    void addBatch(Integer topicId, Integer batchId);

    List<Topics> findByBatch(Integer batchId);

    List<Topics> listAuto();

    PageBean<Topics> listReview(Integer pageNum, Integer pageSize, Integer categoryId, String status, Integer assignedTo, Integer createdBy, Integer batchId, String titleKey);
}
