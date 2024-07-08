package com.example.demo.repositories;


import com.example.demo.models.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskItemRepository extends JpaRepository<TaskItem, Long> {

    //    全タスクを取得
    @Query(value = "SELECT * FROM task_item WHERE user_id = :userId order by order_index", nativeQuery = true)
    List<TaskItem> allTaskGet(@Param("userId") String userId);

    //    未完了タスクを取得
    @Query(value = "SELECT * FROM task_item WHERE is_completed = false AND user_id = :userId order by order_index", nativeQuery = true)
    List<TaskItem> inCompletedTaskGet(@Param("userId") String userId);

    //    タスクをカテゴリIDから取得
    @Query(value = "SELECT * FROM task_item WHERE category_id = :categoryId AND user_id = :userId", nativeQuery = true)
    List<TaskItem> taskGetByCategoryId(@Param("categoryId") Long id, @Param("userId") String userId);

    //    タスクをスケジュールIDから取得
    @Query(value = "SELECT * FROM task_item WHERE schedule_id = :scheduleId AND user_id = :userId", nativeQuery = true)
    List<TaskItem> taskGetByScheduleId(@Param("scheduleId") Long id, @Param("userId") String userId);

    //    完了タスクを取得
    @Query(value = "SELECT * FROM task_item WHERE is_completed = true AND user_id = :userId order by order_index", nativeQuery = true)
    List<TaskItem> completedTaskGet(@Param("userId") String userId);

    //    最新のタスク取得
    @Query(value = "SELECT * FROM task_item WHERE id = (SELECT MAX(id) from task_item) AND user_id = :userId", nativeQuery = true)
    TaskItem latestTaskGet(@Param("userId") String userId);

    //    最大のorderIndexを取得
    @Query(value = "SELECT MAX(order_index) FROM task_item WHERE user_id = :userId", nativeQuery = true)
    Integer maxOrderIndexGet(@Param("userId") String userId);

}