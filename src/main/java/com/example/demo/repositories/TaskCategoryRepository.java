package com.example.demo.repositories;


import com.example.demo.models.TaskCategory;
import com.example.demo.models.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
    //    最新のカテゴリ取得
    @Query(value = "SELECT * FROM task_category WHERE id = (SELECT MAX(id) from task_category)", nativeQuery = true)
    TaskCategory latestCategoryGet();

    //    IDを指定してカテゴリ取得
    @Query(value = "SELECT * FROM task_category WHERE id = :id", nativeQuery = true)
    TaskCategory categoryGetById(@Param("id") Long id);
}