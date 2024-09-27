package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer category_id;
    @NotEmpty
    private String category_name;
    private String description;
    private Integer created_user;   //创建课题类别的用户
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at;

    public interface Add extends Default{   //validation group

    }
    public interface Update extends Default{

    }
}
