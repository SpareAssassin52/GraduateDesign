package org.example.service;

import org.example.pojo.ActiveBatch;
import org.example.pojo.PageBean;

import java.time.LocalDateTime;
import java.util.List;

public interface ActiveBatchService {
    void add(ActiveBatch activeBatch);

    PageBean<ActiveBatch> list(Integer pageNum, Integer pageSize);

    void update(ActiveBatch activeBatch);

    void delete(Integer activeBatchId);

    List<ActiveBatch> listNowThen();

    List<ActiveBatch> listAll();

    List<ActiveBatch> listNow();
}
