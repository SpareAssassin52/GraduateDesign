package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.ActiveBatch;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ActiveBatchMapper {
    @Insert("INSERT INTO activebatch(start_time, end_time) VALUES (#{start_time}, #{end_time})")
    void add(ActiveBatch activeBatch);

    @Select("select * from activebatch")
    List<ActiveBatch> list();

    @Update("update activebatch set start_time=#{start_time}, end_time=#{end_time} where active_batch_id=#{active_batch_id}")
    void update(ActiveBatch activeBatch);

    @Delete("delete from activebatch where active_batch_id=#{activeBatchId}")
    void delete(Integer activeBatchId);

    @Select("SELECT * from activebatch where NOW() BETWEEN start_time AND end_time or now() >= start_time")
    List<ActiveBatch> listNowThen();

    @Select("SELECT * from activebatch")
    List<ActiveBatch> listAll();

    @Select("select * from activebatch where NOW() between start_time and end_time")
    List<ActiveBatch> listNow();
}
