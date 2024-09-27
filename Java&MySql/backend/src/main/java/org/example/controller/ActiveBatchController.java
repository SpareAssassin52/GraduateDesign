package org.example.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.annotations.Delete;
import org.example.pojo.ActiveBatch;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.pojo.Topics;
import org.example.service.ActiveBatchService;
import org.example.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/activeBatch")
public class ActiveBatchController {
    @Autowired
    private ActiveBatchService activeBatchService;
    @Autowired
    private TopicService topicService;


    @PostMapping
    public Result add(@RequestBody ActiveBatch activeBatch) {
        activeBatchService.add(activeBatch);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<ActiveBatch>> list(
            Integer pageNum,
            Integer pageSize) {
        PageBean<ActiveBatch> pb = activeBatchService.list(pageNum, pageSize);
        return Result.success(pb);
    }

    @PutMapping
    public Result update(@RequestBody ActiveBatch activeBatch) {
        activeBatchService.update(activeBatch);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer active_batch_id) {
        //首先检查 batch 表中批次id有无存在的记录
        List<Topics> lt = topicService.findByBatch(active_batch_id);
        if (lt.isEmpty()) {
            activeBatchService.delete(active_batch_id);
            return Result.success();
        }
        return Result.error("当前批次中已经含有课题正在申请");
    }

    @GetMapping("/nowThen")
    public Result<List<ActiveBatch>> listNowThen(){
        List<ActiveBatch> ab=activeBatchService.listNowThen();
        return Result.success(ab);
    }

    @GetMapping("/all")
    public Result<List<ActiveBatch>> listAll(){
        List<ActiveBatch> ab=activeBatchService.listAll();
        return Result.success(ab);
    }

    @GetMapping("/now")
    public Result<List<ActiveBatch>> listNow(){
        List<ActiveBatch> ab=activeBatchService.listNow();
        return Result.success(ab);
    }
}
