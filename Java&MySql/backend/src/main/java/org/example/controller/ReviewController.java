package org.example.controller;

import org.apache.ibatis.annotations.Delete;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.pojo.Reviews;
import org.example.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Result add(@RequestBody @Validated Reviews reviews) {
        reviewService.add(reviews);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Reviews>> list(
            Integer expert_id,
            Integer pageNum,
            Integer pageSize
            ){
        PageBean<Reviews> pb=reviewService.list(pageNum, pageSize, expert_id);
        return  Result.success(pb);
    }

    @GetMapping("/findByTopic")
    public Result<List<Reviews>> findByTopic(Integer topic_id) {     //通常情况下，一个课题申请表只对应一张审核表
        List<Reviews> reviews = reviewService.findByTopic(topic_id);
        return Result.success(reviews);
    }

    @GetMapping("/findByReview")
    public Result<List<Reviews>> findByReview(Integer review_id){
        List<Reviews> reviews=reviewService.findByReview(review_id);
        return Result.success(reviews);
    }

    @PutMapping
    public Result update(@RequestBody @Validated Reviews reviews){
        reviewService.update(reviews);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(Integer review_id){
        reviewService.delete(review_id);
        return Result.success();
    }
}
