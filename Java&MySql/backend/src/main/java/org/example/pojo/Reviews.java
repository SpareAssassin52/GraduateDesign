package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Reviews {  //专家审核表。 前端为一个card中显示。 审批过后，与topics表格同步更新。
    @NotNull(groups = update.class)
    private Integer review_id;
    @NotNull
    private Integer topic_id;   //从前端自动填充从后端拿取的当前row的数据，然后copy过来，最后发到后端新增条目
    private Integer expert_id;  //与上属性同理
    private String comments;

    private boolean approved;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated_at;

    public interface update extends Default{}

}
