package org.example.controller;

import org.example.pojo.*;
import org.example.service.TopicPassService;
import org.example.service.TopicService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicPass")
public class TopicPassController {
    @Autowired
    private TopicPassService topicPassService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;

//    public Result add(Integer topic_id){
//        //通过topicid获得主体
//        Topics topics=topicService.detail(topic_id);
//        //通过user_id找出user的nickname填入响应字段中
//
//
//    }

    @GetMapping
    public Result<PageBean<TopicPass>> list(Integer pageNum, Integer pageSize){
        PageBean<TopicPass> pb=topicPassService.list(pageNum,pageSize);
        return Result.success(pb);
    }

    @PutMapping
    public Result update(@RequestBody TopicPass topicPass){
        topicPassService.update(topicPass);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer pass_id){
        topicPassService.delete(pass_id);
        return Result.success();
    }

}
