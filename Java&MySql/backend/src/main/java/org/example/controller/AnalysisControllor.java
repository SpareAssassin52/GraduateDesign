package org.example.controller;

import org.example.pojo.Result;
import org.example.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
public class AnalysisControllor {
    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/totalTopics")
    public Result<Integer> totalTopics(){
        Integer tt= analysisService.totalTopics();
        return Result.success(tt);
    }

    @GetMapping("/pendingTopics")
    public Result<Integer> pendingTopics(){
        Integer pt=analysisService.pendingTopics();
        return Result.success(pt);
    }

    @GetMapping("/passedTopics")
    public Result<Integer> passedTopics(){
        Integer rt=analysisService.passedTopics();
        return Result.success(rt);
    }

    @GetMapping("/thisMonthTopics")
    public Result<Integer> thisMonthTopics(){
        Integer tmt=analysisService.thisMonthTopics();
        return Result.success(tmt);
    }

    @GetMapping("/totalCategorys")
    public Result<Integer> totalCategorys(){
        Integer tc=analysisService.totalCategorys();
        return Result.success(tc);
    }

}
