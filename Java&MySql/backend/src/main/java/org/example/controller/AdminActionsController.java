package org.example.controller;

import org.example.pojo.AdminActions;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.service.AdminActionsService;
import org.example.utls.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/adminActions")
public class AdminActionsController {
    @Autowired
    private AdminActionsService adminActionsService;

    @PostMapping
    public Result add(@RequestBody @Validated AdminActions adminActions){
        Map<String, Object> claims= ThreadLocalUtil.get();
        Integer userId=(Integer) claims.get("user_id");
        adminActions.setAdmin_id(userId);
        adminActionsService.add(adminActions);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<AdminActions>> list(Integer pageNum, Integer pageSize){
        PageBean<AdminActions> aa=adminActionsService.list(pageNum,pageSize);
        return Result.success(aa);
    }

}
