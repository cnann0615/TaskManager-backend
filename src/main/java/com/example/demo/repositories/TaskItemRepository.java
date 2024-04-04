package com.example.demo.repositories;


import com.example.demo.models.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskItemRepository extends JpaRepository<TaskItem, Long> {

    //    未完了タスクを取得
    @Query(value = "SELECT * FROM task_item WHERE is_completed = false", nativeQuery = true)
    List<TaskItem> inCompletedTaskGet();

    //    タスクをカテゴリIDから取得
    @Query(value = "SELECT * FROM task_item WHERE category_id = :categoryId", nativeQuery = true)
    List<TaskItem> taskGetByCategoryId(@Param("categoryId") Long id);

    //    完了タスクを取得
    @Query(value = "SELECT * FROM task_item WHERE is_completed = true", nativeQuery = true)
    List<TaskItem> completedTaskGet();

    //    最新のタスク取得
    @Query(value = "SELECT * FROM task_item WHERE id = (SELECT MAX(id) from task_item)", nativeQuery = true)
    TaskItem latestTaskGet();

}