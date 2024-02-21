package com.example.demo.repositories;


import com.example.demo.models.TaskCategory;
import com.example.demo.models.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
    //    最新のカテゴリ取得
    @Query(value = "SELECT * FROM task_category WHERE id = (SELECT MAX(id) from task_category)", nativeQuery = true)
    TaskCategory latestCategoryGet();
    
} 