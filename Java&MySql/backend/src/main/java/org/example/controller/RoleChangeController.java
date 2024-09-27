package org.example.controller;

import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.pojo.RoleChangeRequest;
import org.example.service.RoleChangeService;
import org.example.service.UserService;
import org.example.utls.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roleChange")
public class RoleChangeController {
    @Autowired
    private RoleChangeService roleChangeService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Result add(@RequestBody @Validated RoleChangeRequest roleChangeRequest) {
        Map<String, Object> claims= ThreadLocalUtil.get();
        Integer user_id=(Integer) claims.get("user_id");
        String currentRole=(String) claims.get("role");
        roleChangeRequest.setUser_id(user_id);
        roleChangeRequest.setCurrent_role(currentRole);

        if(roleChangeRequest.getRequested_role().equals(roleChangeRequest.getCurrent_role())){
            return Result.error("不能申请一样的身份");
        }

        //no need to update "updated_at" because the column attribute is set to update when update;
        roleChangeService.add(roleChangeRequest);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<RoleChangeRequest>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer user_id,
            @RequestParam(required = false) String status
    ) {
        PageBean<RoleChangeRequest> rc = roleChangeService.list(pageNum, pageSize, user_id, status);
        return Result.success(rc);
    }

    @PutMapping("/change")
    public Result update(@RequestBody @Validated RoleChangeRequest roleChangeRequest){
        if(roleChangeRequest.getStatus().equals("approved")){
            //管理员调用用户操作接口修改用户身份
            userService.updateRole(roleChangeRequest.getRequested_role(), roleChangeRequest.getUser_id());
        }
        roleChangeService.update(roleChangeRequest);
        return Result.success();
    }
}
