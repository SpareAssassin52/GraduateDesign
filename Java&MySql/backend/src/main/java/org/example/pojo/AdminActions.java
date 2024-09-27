package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AdminActions {     //存储admin操作记录的表
    private Integer action_id;
    private Integer admin_id;   //即是用户id
    private String action_type; //操作名，前端传回
    private String Description;
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;   //创建时间
}
////保持透明度：确保所有管理员的操作都是可追踪的，增加系统的透明度。
////审计：在需要时，可以回顾管理员的操作历史，这对于审计和监督管理活动非常重要。
////安全：如果发生安全事件，可以通过这个表格快速确定哪些操作可能与事件有关。
////错误追踪：如果系统出现问题，可以通过检查管理员的操作记录来帮助诊断问题的根源。

//要在 admin_actions 表中记录管理员的操作，前端和后端需要协同工作来确保每次管理员的操作都被适当地记录。以下是可能的操作流程：
//前端操作：
//        1. 当管理员在前端界面执行任何管理操作时（如审核角色变更申请、发布信息、管理用户等），前端应该捕获这些操作的相关信息。
//        2. 操作信息包括操作类型、描述、操作的时间等。
//        3. 前端将这些信息作为请求发送到后端。
//后端操作：
//        1. 后端接收到前端发送的管理员操作信息后，将其存入 admin_actions 表中。
//        2. 后端可以提供一个接口，允许管理员查看历史操作记录，以便于跟踪和审计。
//例如，如果管理员批准了一个角色变更申请，前端可以发送如下信息到后端：
//JSON
//{
//    "admin_id": 1,
//        "action_type": "role_change_approval",
//        "description": "Approved role change request from user to expert for user_id 42"
//}
//后端接收到这个请求后，可以执行如下 SQL 语句来记录这个操作：
//SQL
//INSERT INTO `admin_actions` (admin_id, action_type, description)
//VALUES (1, 'role_change_approval', 'Approved role change request from user to expert for user_id 42');
//这样，每次管理员的操作都会被记录在 admin_actions 表中，可以随时查看和审计。