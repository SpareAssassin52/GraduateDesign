package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.example.anno.Role;
import org.example.anno.Status;

import java.time.LocalDateTime;
@Data
public class RoleChangeRequest {    //普通、专家用户可以提交修改身份请求，供所有管理员查看
    @NotEmpty(groups = update.class)
    private Integer request_id;
    @NotEmpty(groups = update.class)
    private Integer user_id;
    @Role(groups = update.class)
    private String current_role;
    @Role
    private String requested_role;
    @Status(groups = update.class)
    private String status;
    private String reason;
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at;

    public interface update extends Default{}
}


//role_change_requests 表用于存储用户提交的角色变更申请。每个申请包含用户ID、当前角色、请求的新角色、申请状态、申请理由和申请时间。
//前端操作：
//        1. 用户在前端界面填写角色变更申请，包括希望变更到的角色和变更理由。
//        2. 前端将申请数据发送到后端服务器。
//后端操作：
//        1. 后端接收到角色变更申请后，将申请信息存入 role_change_requests 表中。
//        2. 管理员在后台管理界面可以查看所有待处理的角色变更申请。
//        3. 管理员审核申请后，可以更新申请的状态为 approved 或 rejected，并在必要时更新 users 表中的 role 字段。