package com.example.demo.controllers;

import com.example.demo.models.TaskCategory;
import com.example.demo.models.TaskItem;
import com.example.demo.repositories.TaskCategoryRepository;
import com.example.demo.repositories.TaskItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/taskGetAll")
    public List<TaskItem> taskGet () {
        List<TaskItem> taskItems = taskItemRepository.findAll();
        return taskItems;
    }
//    完了タスク取得
    @GetMapping("/completedTaskGet")
    public List<TaskItem> completedTaskGet () {
        List<TaskItem> taskItems = taskItemRepository.completedTaskGet();
        return taskItems;
    }
//    未完了タスク取得
    @GetMapping("/inCompletedTaskGet")
    public List<TaskItem> inCompletedTaskGet () {
        List<TaskItem> taskItems = taskItemRepository.inCompletedTaskGet();
        return taskItems;
    }
//    最新のタスク取得
    @GetMapping("/latestTaskGet")
    public TaskItem latestTaskGet () {
        TaskItem taskItem = taskItemRepository.latestTaskGet();
        return taskItem;
    }

//    追加
//    タスク追加
    @PostMapping("/taskAdd")
    public List<TaskItem> taskAdd (@RequestBody TaskItem taskItem) {
        taskItemRepository.save(taskItem);
        List<TaskItem> taskItems = taskItemRepository.findAll();
        return taskItems;
    }
    //    カテゴリ追加
    @PostMapping("/categoryAdd")
    public  List<TaskCategory> taskCategories (@RequestBody TaskCategory newTaskCategory) {
        List<TaskCategory> taskCategories = taskCategoryRepository.findAll();
        for (TaskCategory taskCategory : taskCategories) {
            if (taskCategory.getName().equals(newTaskCategory.getName())){
                return taskCategories;
            }
        }
        taskCategoryRepository.save(newTaskCategory);
        return taskCategories;
    }

//    タスク削除
    @DeleteMapping("/taskDelete/{id}")
    public List<TaskItem> taskDelete (@PathVariable Long id) {
        taskItemRepository.deleteById(id);
        List<TaskItem> taskItems = taskItemRepository.findAll();
        return taskItems;
    }

//    タスク更新
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























