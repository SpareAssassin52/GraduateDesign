package org.example.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.example.anno.Status;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
@Data
public class Topics {
    private Integer topic_id;
    @NotEmpty
    @Pattern(regexp = "^[\\S\\s]{1,100}")
    private String title;
    @NotEmpty
    private String description;
    @Status(groups = update.class)
    private String status;  //pending, approved, rejected
    @NotNull(groups = update.class)
    private Integer created_by; //创建人ID

    private Integer assigned_to;    //专家ID 由专家自行配置，或管理员强行分配专家, 默认为空
    @NotNull
    private Integer category_id;    //课题类别的id
    private Integer batch_id;   //课题审核批次id
    @URL(groups = upload.class)
    private String docs;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public interface update extends Default { }
    public interface upload extends Default { }
}
