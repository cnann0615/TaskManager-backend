package com.example.demo.repositories;


import com.example.demo.models.TaskSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskScheduleRepository extends JpaRepository<TaskSchedule, Long> {
    //    全カテゴリ取得
    @Query(value = "SELECT * FROM task_schedule WHERE user_id = :userId order by order_index", nativeQuery = true)
    List<TaskSchedule> allScheduleGet(@Param("userId") String userId);

    //    最新のカテゴリ取得
    @Query(value = "SELECT * FROM task_schedule WHERE id = (SELECT MAX(id) from task_schedule) AND user_id = :userId", nativeQuery = true)
    TaskSchedule latestScheduleGet(@Param("userId") String userId);

    //    カテゴリをidから取得
    @Query(value = "SELECT * FROM task_schedule WHERE id = :id", nativeQuery = true)
    TaskSchedule scheduleGetById(@Param("id") Long id);

    //    最大のorderIndexを取得
    @Query(value = "SELECT MAX(order_index) FROM task_schedule WHERE user_id = :userId", nativeQuery = true)
    Integer maxOrderIndexGet(@Param("userId") String userId);
}