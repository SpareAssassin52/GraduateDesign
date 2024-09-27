package org.example.controller;

import net.sf.jsqlparser.statement.select.Top;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.pojo.Reviews;
import org.example.pojo.Topics;
import org.example.service.ReviewService;
import org.example.service.TopicService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.data.annotation.AccessType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Result add(@RequestBody @Validated Topics topic){
        topicService.add(topic);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Topics.update.class) Topics topics){
        //判断status是pending
        if(!topics.getStatus().equals("pending")){
            return Result.error("不能修改已经审核过的课题申请");
        }
        topicService.update(topics);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Topics> detail(Integer topic_id){
        Topics tpc=topicService.detail(topic_id);
        return Result.success(tpc);
    }

    @GetMapping
    public Result<PageBean<Topics>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer category_id,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer assigned_to,
            @RequestParam(required = false) Integer created_by,
            @RequestParam(required = false) Integer batch_id,
            @RequestParam(required = false) String titleKey
    ){
        PageBean<Topics> pb= topicService.list(pageNum, pageSize, category_id, status, assigned_to, created_by, batch_id, titleKey);
        return Result.success(pb);
    }

    @PatchMapping("/docs")
    public Result uploadDocs(Integer topic_id, @URL String docs){
        topicService.uploadDocs(topic_id, docs);
        return Result.success();
    }

    @PutMapping("/addExpert")
    public Result addExpert(Integer topic_id, Integer expert_id){
        topicService.addExpert(topic_id,expert_id);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer topic_id){
        //首先查找本课题表有没有对应的评审结果表
        List<Reviews> reviews = reviewService.findByTopic(topic_id);
        if (reviews == null) {
            //直接删除即可
            topicService.delete(topic_id);
        } else {//需要先删除评审结果表
            for (Reviews review : reviews) {
                Integer review_id = review.getReview_id();
                reviewService.delete(review_id);
            }
            topicService.delete(topic_id);
        }
        return Result.success();
    }

    @PatchMapping("/addBatch")
    public Result addBatch(Integer topic_id, Integer batch_id){
        topicService.addBatch(topic_id, batch_id);
        return Result.success();
    }

    @GetMapping("/findByBatch")
    public Result<List<Topics>> findByBatch(Integer batch_id){
        List<Topics> lp= topicService.findByBatch(batch_id);
        return Result.success(lp);
    }

    @GetMapping("/auto")
    public Result<List<Topics>> listAuto(){
        List<Topics> tp=topicService.listAuto();
        return Result.success(tp);
    }

    @GetMapping("/review")
    public Result<PageBean<Topics>> listReview(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer category_id,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer assigned_to,
            @RequestParam(required = false) Integer created_by,
            @RequestParam(required = false) Integer batch_id,
            @RequestParam(required = false) String titleKey
    ){
        PageBean<Topics> pb= topicService.listReview(pageNum, pageSize, category_id, status, assigned_to, created_by, batch_id, titleKey);
        return Result.success(pb);
    }
}
