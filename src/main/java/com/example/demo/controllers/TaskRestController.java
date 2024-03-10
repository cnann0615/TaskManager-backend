package com.example.demo.controllers;

import com.example.demo.models.TaskCategory;
import com.example.demo.models.TaskItem;
import com.example.demo.repositories.TaskCategoryRepository;
import com.example.demo.repositories.TaskItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/taskAPI")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskRestController {
    @Autowired
    private TaskItemRepository taskItemRepository;
    @Autowired
    private TaskCategoryRepository taskCategoryRepository;


//    取得
//    全タスク取得
    @GetMapping("/task")
    public List<TaskItem> taskGet () {
        List<TaskItem> taskItems = taskItemRepository.findAll();
        return taskItems;
    }
//    未完了タスク取得
    @GetMapping("/inCompletedTask")
    public List<TaskItem> inCompletedTaskGet () {
        List<TaskItem> taskItems = taskItemRepository.inCompletedTaskGet();
        return taskItems;
    }
//    カテゴリIDから未完了タスク取得
    @GetMapping("inCompletedTask/{categoryId}")
    public List<TaskItem> inCompletedTaskGetByCategoryId (@PathVariable Long categoryId) {
        List<TaskItem> taskItems = taskItemRepository.inCompletedTaskGetByCategoryId(categoryId);
        return taskItems;
    }
//    完了タスク取得
    @GetMapping("/completedTask")
    public List<TaskItem> completedTaskGet () {
        List<TaskItem> taskItems = taskItemRepository.completedTaskGet();
        return taskItems;
    }
//    最新のタスク取得
    @GetMapping("/latestTask")
    public TaskItem latestTaskGet () {
        TaskItem taskItem = taskItemRepository.latestTaskGet();
        return taskItem;
    }
//    全カテゴリ取得
    @GetMapping("/category")
    public List<TaskCategory> categoryGetAll() {
        List<TaskCategory> taskCategories = taskCategoryRepository.findAll();
        return taskCategories;
    }
//    最新のカテゴリ取得
    @GetMapping("/latestCategory")
    public TaskCategory latestCategoryGet () {
        TaskCategory taskCategory = taskCategoryRepository.latestCategoryGet();
        return taskCategory;
    }
//    IDを指定してカテゴリ取得
    @GetMapping("/category/{id}")
    public TaskCategory categoryGetById (@PathVariable Long id) {
        TaskCategory taskCategory = taskCategoryRepository.categoryGetById(id);
        return taskCategory;
    }

//    追加
//    タスク追加
    @PostMapping("/task")
    public List<TaskItem> taskAdd (@RequestBody TaskItem taskItem) {
        taskItemRepository.save(taskItem);
        List<TaskItem> taskItems = taskItemRepository.findAll();
        return taskItems;
    }
    //    カテゴリ追加
    @PostMapping("/category")
    public  List<TaskCategory> taskCategories (@RequestBody TaskCategory newTaskCategory) {
        List<TaskCategory> taskCategories = taskCategoryRepository.findAll();
        for (TaskCategory taskCategory : taskCategories) {
            if (taskCategory.getName().equals(newTaskCategory.getName())){
                return null;
            }
        }
        taskCategoryRepository.save(newTaskCategory);
        taskCategories = taskCategoryRepository.findAll();
        return taskCategories;
    }

//    削除
    @DeleteMapping("/task/{id}")
    public List<TaskItem> taskDelete (@PathVariable Long id) {
        taskItemRepository.deleteById(id);
        List<TaskItem> taskItems = taskItemRepository.findAll();
        return taskItems;
    }

//    更新
//    タスク完了フラグ切り替え
    @PutMapping("/switchIsCompleted/{id}")
    public void switchIsCompleted (@PathVariable Long id) {
        TaskItem taskItem = taskItemRepository.findById(id).orElseThrow();
        if (taskItem.isCompleted() == false) {
            taskItem.setCompleted(true);
        }else {
            taskItem.setCompleted(false);
        }
        taskItemRepository.save(taskItem);
    }


}























