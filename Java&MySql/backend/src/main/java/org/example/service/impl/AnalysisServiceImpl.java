package org.example.service.impl;

import org.example.mapper.AnalysisMapper;
import org.example.service.AnalysisService;
import org.example.utls.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public Integer totalTopics() {
        Map<String, Object> claims= ThreadLocalUtil.get();
        Integer userId=(Integer) claims.get("user_id");
        return analysisMapper.totalTopics(userId);
    }

    @Override
    public Integer pendingTopics() {
        Map<String, Object> claims= ThreadLocalUtil.get();
        Integer userId=(Integer) claims.get("user_id");
        return analysisMapper.pendingTopics(userId);
    }

    @Override
    public Integer passedTopics() {
        Map<String, Object> claims= ThreadLocalUtil.get();
        Integer userId=(Integer) claims.get("user_id");
        return analysisMapper.passedTopics(userId);
    }

    @Override
    public Integer thisMonthTopics() {
        Map<String, Object> claims= ThreadLocalUtil.get();
        Integer userId=(Integer) claims.get("user_id");
        return analysisMapper.thisMonthTopics(userId);
    }

    @Override
    public Integer totalCategorys() {
        Map<String, Object> claims= ThreadLocalUtil.get();
        Integer userId=(Integer) claims.get("user_id");
        return analysisMapper.totalCategorys(userId);
    }
}
