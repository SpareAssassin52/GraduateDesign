package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.example.anno.Role;


import java.time.LocalDateTime;
@Data
public class User {
    @NotNull(groups = Update.class)
    private Integer user_id;
    //@Pattern(regexp = "^\\S{1,10}$", groups = Add.class)
    private String username;
    @NotEmpty
    @Pattern(regexp = "^\\S{1,16}$", groups = Update.class)
    private String nickname;
    @NotEmpty
    @Email
    private String email;
    private String avatar_url;
    @JsonIgnore
    //@Pattern(regexp = "^\\S{1,10}$", groups = Add.class)
    private String password;

    @Role(groups = {Add.class, UpdateRole.class})
    private String role;    //user, expert, admin
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at;

    public interface Add extends Default{
        //分组校验，在注册账号时用到
    }
    public interface Update extends Default{
        //分组校验，在更新账号信息时用到
    }

    public interface UpdateRole extends Default{

    }

}
