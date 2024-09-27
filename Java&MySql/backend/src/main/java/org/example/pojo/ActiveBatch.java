package org.example.pojo;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ActiveBatch {
    private Integer active_batch_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String start_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String end_time;
}
