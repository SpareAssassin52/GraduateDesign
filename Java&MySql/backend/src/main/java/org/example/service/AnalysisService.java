package org.example.service;

public interface AnalysisService {
    Integer totalTopics();

    Integer pendingTopics();

    Integer passedTopics();

    Integer thisMonthTopics();

    Integer totalCategorys();
}
