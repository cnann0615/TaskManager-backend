package com.example.demo.repositories;


import com.example.demo.models.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskItemRepository extends JpaRepository<TaskItem, Long> {

    //    完了タスクを抽出
    @Query(value = "SELECT * FROM task_item " +
            "WHERE is_completed = true", nativeQuery = true)
    List<TaskItem> completedTaskGet();

    //    未完了タスクを抽出
    @Query(value = "SELECT * FROM task_item " +
            "WHERE is_completed = false", nativeQuery = true)
    List<TaskItem> inCompletedTaskGet();

//    カテゴリを指定して未完了タスクを抽出
    @Query(value = "SELECT * FROM task_item " +
            "WHERE category = :category", nativeQuery = true)
    List<TaskItem> findByCategory(@Param("category") String category);

}