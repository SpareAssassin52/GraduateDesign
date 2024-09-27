package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopicPass {
    private Integer pass_id;
    private String title;
    private String description;
    private String created_by;      //创建人的nickname
    private String assigned_to;     //审核专家的nickname
    private Integer category_id;
    private Integer batch_id;      //批次编号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at;

}
