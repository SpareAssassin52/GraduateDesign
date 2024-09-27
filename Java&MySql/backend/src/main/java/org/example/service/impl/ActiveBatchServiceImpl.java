package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ActiveBatchMapper;
import org.example.pojo.ActiveBatch;
import org.example.pojo.PageBean;
import org.example.pojo.Topics;
import org.example.service.ActiveBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActiveBatchServiceImpl implements ActiveBatchService {
    @Autowired
    private ActiveBatchMapper activeBatchMapper;

    @Override
    public void add(ActiveBatch activeBatch) {
        activeBatchMapper.add(activeBatch);
    }

    @Override
    public PageBean<ActiveBatch> list(Integer pageNum, Integer pageSize) {
        //创建pagebean对象
        PageBean<ActiveBatch> pb = new PageBean<>();
        //开启分页查询 pagehelper
        PageHelper.startPage(pageNum, pageSize);
        //调用mapper
        List<ActiveBatch> ts = activeBatchMapper.list();
        //page中提供了方法可以获取pagehelper的分页查询后得到的总记录条数和当前查询。
        Page<ActiveBatch> p = (Page<ActiveBatch>) ts;
        //把page中的数据填充到pagebean中去
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void update(ActiveBatch activeBatch) {
        activeBatchMapper.update(activeBatch);
    }

    @Override
    public void delete(Integer activeBatchId) {
        activeBatchMapper.delete(activeBatchId);
    }

    @Override
    public List<ActiveBatch> listNowThen() {
        return activeBatchMapper.listNowThen();
    }

    @Override
    public List<ActiveBatch> listAll() {
        return activeBatchMapper.listAll();
    }

    @Override
    public List<ActiveBatch> listNow() {
        return activeBatchMapper.listNow();
    }
}
