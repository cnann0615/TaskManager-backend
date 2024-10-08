package com.example.demo.repositories;


import com.example.demo.models.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
    //    全カテゴリ取得
    @Query(value = "SELECT * FROM task_category WHERE user_id = :userId order by order_index", nativeQuery = true)
    List<TaskCategory> allCategoryGet(@Param("userId") String userId);

    //    最新のカテゴリ取得
    @Query(value = "SELECT * FROM task_category WHERE id = (SELECT MAX(id) from task_category) AND user_id = :userId", nativeQuery = true)
    TaskCategory latestCategoryGet(@Param("userId") String userId);

    //    カテゴリをidから取得
    @Query(value = "SELECT * FROM task_category WHERE id = :id", nativeQuery = true)
    TaskCategory categoryGetById(@Param("id") Long id);

    //    最大のorderIndexを取得
    @Query(value = "SELECT MAX(order_index) FROM task_category WHERE user_id = :userId", nativeQuery = true)
    Integer maxOrderIndexGet(@Param("userId") String userId);
}