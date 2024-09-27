package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Topics;

import java.util.List;

@Mapper
public interface TopicMapper {

    @Insert("insert into topics(title, description, created_by, category_id, docs) " +
            "values(#{title}, #{description}, #{created_by}, #{category_id}, #{docs})")
    void add(Topics topic);

    @Select("select * from topics where topic_id=#{topicId}")
    Topics detail(Integer topicId);

    @Update("UPDATE topics set " +
            "title=#{title}, description=#{description}, category_id=#{category_id}, status=#{status}, created_by=#{created_by}, assigned_to=#{assigned_to}, updated_at=#{updated_at}, batch_id=#{batch_id}, docs=#{docs} where topic_id=#{topic_id}")
    void update(Topics topics);

    List<Topics> list(Integer categoryId, String status, Integer assignedTo, Integer createdBy, Integer batchId, String titleKey);

    @Update("update topics set docs=#{docs}, updated_at=NOW() where topic_id=#{topicId}")
    void uploadDocs(Integer topicId, String docs);

    @Update("update topics set status=#{status}, updated_at=NOW() where topic_id=#{topicId}")
    void updateStatus(Integer topicId, String status);

    @Update("update topics set assigned_to=#{expertId}, updated_at=now() where topic_id=#{topicId}")
    void addExpert(Integer topicId, Integer expertId);

    @Delete("delete from topics where topic_id=#{topicId}")
    void delete(Integer topicId);

    @Update("update topics set batch_id=#{batchId} where topic_id=#{topicId}")
    void addBatch(Integer topicId, Integer batchId);

    @Select("Select * from topics where batch_id=#{batchId}")
    List<Topics> findByBatch(Integer batchId);

    @Select("SELECT * from topics where status = 'pending' and (assigned_to is null or batch_id is null)")
    List<Topics> listAuto();

    List<Topics> listReview(Integer categoryId, String status, Integer assignedTo, Integer createdBy, Integer batchId, String titleKey);
}
