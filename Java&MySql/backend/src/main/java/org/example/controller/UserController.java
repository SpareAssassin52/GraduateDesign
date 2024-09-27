package org.example.controller;

import jakarta.validation.constraints.Pattern;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.pojo.Topics;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.utls.JwtUtil;
import org.example.utls.Md5Util;
import org.example.utls.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")

@Validated  //validation checker which spring boot provides
public class UserController<string> {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password, String role) {
        //非空字符，5到16位
        //System.out.println(username+password+role);
        User u = userService.findByName(username);  //首先在users表中查找有无相同用户名的用户
        if (u == null) {
            //username is not taken
            //start to register
            userService.register(username, password, role);
            return Result.success();
        } else {
            return Result.error("用户名已经被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {    /*登录界面不用选择登录身份，因为登录身份只有一个*/
        //check out username exist or not
        User loginuser = userService.findByName(username);
        if (loginuser == null) {
            return Result.error("用户名不存在");
        }

        //check password by checking 2 encrypted string is the same or not
        if (Md5Util.getMD5String(password).equals(loginuser.getPassword())) {
            //login success
            Map<String, Object> claims = new HashMap<>();   //user's important info will be inserted into the token
            claims.put("user_id", loginuser.getUser_id());
            claims.put("username", loginuser.getUsername());
            claims.put("role", loginuser.getRole());    //用户的身份
            String token = JwtUtil.genToken(claims);

            //store the token into redis
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token, token, 1, TimeUnit.HOURS);
            return Result.success(token);       //token传给前端

        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() { //get users misc info from database
        Map<String, Object> claims = ThreadLocalUtil.get(); //get token from threadlocal
        String username = (String) claims.get("username");      //username不会同名，直接取username查找用户数据也可以。

        User user = userService.findByName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated(User.Update.class) User user) {     //更新用户数据,除了用户头像、密码与身份
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatar_url) {
        userService.updateAvatar(avatar_url);
        return Result.success();
    }

    @PatchMapping("updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        //validate arguments.
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("必须填写每个密码");
        }
        //1. check ioldPwd is the original password. Decrypt token to get username and then get password, then compare.
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User loginUser = userService.findByName(username);

        //encrypt oldPwd then compare the one in database
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码错误");
        }

        //check is teh newPwd is the same as rePwd
        if (!rePwd.equals(newPwd)) {
            return Result.error("新密码与重复密码不一致");
        }

        //2. call service to update password
        userService.updatePwd(newPwd);

        //delete the current user's token from redis
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }

    @PatchMapping("updateRole")
    public Result updateRole(String newRole, Integer user_id) {
//        //管理员修改用户的身份
//        //  the role of the user in the token must be 'admin' 现在已利用拦截器判断
//        Map<String, Object> cliams = ThreadLocalUtil.get();
//        String currentRole= (String) cliams.get("role");
//        if(!currentRole.equals("admin")){
//            return Result.error("用户不是管理员!");
//        }
        userService.updateRole(newRole, user_id);
        return Result.success();
    }

    @GetMapping("/findById")
    public Result<User> findById(/*@RequestParam("user_id")*/ Integer user_id){
        User user = userService.findById(user_id);
        //System.out.println(user);
        return Result.success(user);
    }

    @GetMapping("/findByRole")
    public Result<List<User>> findByRole(String role){
        List<User> user= userService.findByRole(role);
        return Result.success(user);
    }

    @GetMapping("/all")
    public Result<PageBean<User>> listAll(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer user_id,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String userNameKey){
        PageBean<User> pb= userService.listAll(pageNum, pageSize, user_id, role, userNameKey);
        return Result.success(pb);
    }
}
