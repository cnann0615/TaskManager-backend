package com.example.demo.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.demo.models.TaskItem;
import com.example.demo.models.TaskCategory;
import com.example.demo.models.TaskSchedule;
import com.example.demo.repositories.TaskCategoryRepository;
import com.example.demo.repositories.TaskItemRepository;
import com.example.demo.repositories.TaskScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/taskAPI")
@CrossOrigin(origins = {"https://task-manager-frontend-14.vercel.app/", "http://localhost:3000", "http://localhost:3001"})
public class TaskRestController {
    @Autowired
    private TaskItemRepository taskItemRepository;
    @Autowired
    private TaskCategoryRepository taskCategoryRepository;
    @Autowired
    private TaskScheduleRepository taskScheduleRepository;


//    取得
//    全タスク取得
    @GetMapping("/task")
    public List<TaskItem> taskGet (@RequestParam String userId) {
        List<TaskItem> taskItems = taskItemRepository.allTaskGet(userId);
        return taskItems;
    }
//    未完了タスク取得
    @GetMapping("/inCompletedTask")
    public List<TaskItem> inCompletedTaskGet (@RequestParam String userId) {
        List<TaskItem> taskItems = taskItemRepository.inCompletedTaskGet(userId);
        return taskItems;
    }
//    カテゴリIDからタスクを取得
    @GetMapping("task/{categoryId}")
    public List<TaskItem> taskGetByCategoryId (@PathVariable Long categoryId, @RequestParam String userId) {
        List<TaskItem> taskItems = taskItemRepository.taskGetByCategoryId(categoryId, userId);
        return taskItems;
    }
//    スケジュールIDからタスクを取得
    @GetMapping("task/{scheduleId}")
    public List<TaskItem> taskGetByScheduleId (@PathVariable Long scheduleId, @RequestParam String userId) {
        List<TaskItem> taskItems = taskItemRepository.taskGetByScheduleId(scheduleId, userId);
        return taskItems;
    }
//    完了タスク取得
    @GetMapping("/completedTask")
    public List<TaskItem> completedTaskGet (@RequestParam String userId) {
        List<TaskItem> taskItems = taskItemRepository.completedTaskGet(userId);
        return taskItems;
    }
//    最新のタスク取得
    @GetMapping("/latestTask")
    public TaskItem latestTaskGet (@RequestParam String userId) {
        TaskItem taskItem = taskItemRepository.latestTaskGet(userId);
        return taskItem;
    }
//    最大のorderIndex（タスク）取得
    @GetMapping("/maxTaskOrderIndex")
    public Integer maxOrderIndexGet (@RequestParam String userId) {
        Integer orderIndex = taskItemRepository.maxOrderIndexGet(userId);
        return orderIndex;
    }
//    全カテゴリ取得
    @GetMapping("/category")
    public List<TaskCategory> categoryGetAll(@RequestParam String userId) {
        List<TaskCategory> taskCategories = taskCategoryRepository.allCategoryGet(userId);
        return taskCategories;
    }
//    最新のカテゴリ取得
    @GetMapping("/latestCategory")
    public TaskCategory latestCategoryGet (@RequestParam String userId) {
        TaskCategory taskCategory = taskCategoryRepository.latestCategoryGet(userId);
        return taskCategory;
    }
//    IDを指定してカテゴリ取得
    @GetMapping("/category/{id}")
    public TaskCategory categoryGetById (@PathVariable Long id) {
        TaskCategory taskCategory = taskCategoryRepository.categoryGetById(id);
        return taskCategory;
    }
    //    最大のorderIndex（カテゴリ）取得
    @GetMapping("/maxCategoryOrderIndex")
    public Integer maxCategoryOrderIndexGet (@RequestParam String userId) {
        Integer orderIndex = taskCategoryRepository.maxOrderIndexGet(userId);
        return orderIndex;
    }
    //    全スケジュール取得
    @GetMapping("/schedule")
    public List<TaskSchedule> scheduleGetAll(@RequestParam String userId) {
        List<TaskSchedule> taskSchedules = taskScheduleRepository.allScheduleGet(userId);
        return taskSchedules;
    }
    //    最新のスケジュール取得
    @GetMapping("/latestSchedule")
    public TaskSchedule latestScheduleGet (@RequestParam String userId) {
        TaskSchedule taskSchedule = taskScheduleRepository.latestScheduleGet(userId);
        return taskSchedule;
    }
    //    IDを指定してスケジュール取得
    @GetMapping("/schedule/{id}")
    public TaskSchedule scheduleGetById (@PathVariable Long id) {
        TaskSchedule taskSchedule = taskScheduleRepository.scheduleGetById(id);
        return taskSchedule;
    }
    //    最大のorderIndex（スケジュール）取得
    @GetMapping("/maxScheduleOrderIndex")
    public Integer maxScheduleOrderIndexGet (@RequestParam String userId) {
        Integer orderIndex = taskScheduleRepository.maxOrderIndexGet(userId);
        return orderIndex;
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
    //    スケジュール追加
    @PostMapping("/schedule")
    public  List<TaskSchedule> taskSchedules (@RequestBody TaskSchedule newTaskSchedule) {
        List<TaskSchedule> taskSchedules = taskScheduleRepository.findAll();
        for (TaskSchedule taskSchedule : taskSchedules) {
            if (taskSchedule.getName().equals(newTaskSchedule.getName())){
                return null;
            }
        }
        taskScheduleRepository.save(newTaskSchedule);
        taskSchedules = taskScheduleRepository.findAll();
        return taskSchedules;
    }

//    削除
//    タスク削除
    @DeleteMapping("/task/{id}")
    public List<TaskItem> taskDelete (@PathVariable Long id) {
        taskItemRepository.deleteById(id);
        List<TaskItem> taskItems = taskItemRepository.findAll();
        return taskItems;
    }
//    カテゴリ削除
    @DeleteMapping("/category/{id}")
    public List<TaskCategory> categoryDelete (@PathVariable Long id) {
        taskCategoryRepository.deleteById(id);
        System.out.println("削除");
        List<TaskCategory> categories = taskCategoryRepository.findAll();
        return  categories;
    }
//    スケジュール削除
    @DeleteMapping("/schedule/{id}")
    public List<TaskSchedule> scheduleDelete (@PathVariable Long id) {
        taskScheduleRepository.deleteById(id);
        List<TaskSchedule> schedules = taskScheduleRepository.findAll();
        return  schedules;
    }

//    更新
//    タスクの更新
    @PutMapping("/updateTask")
    public void updateTask (@RequestBody TaskItem taskItem) {
        TaskItem updateTask = taskItemRepository.findById(taskItem.getId()).orElseThrow();
        updateTask.setTitle(taskItem.getTitle());
        updateTask.setIsCompleted(taskItem.getIsCompleted());
        updateTask.setDeadLine(taskItem.getDeadLine());
        updateTask.setCategory(taskItem.getCategory());
        updateTask.setSchedule(taskItem.getSchedule());
        updateTask.setMemo(taskItem.getMemo());
        taskItemRepository.save(updateTask);
    }

//    カテゴリの編集
    @PutMapping("/updateCategory")
    public void updateCategory (@RequestBody TaskCategory category) {
        TaskCategory updateCategory = taskCategoryRepository.findById(category.getId()).orElseThrow();
        updateCategory.setName(category.getName());
        taskCategoryRepository.save(updateCategory);
    }
//    スケジュールの編集
    @PutMapping("/updateSchedule")
    public void updateSchedule (@RequestBody TaskSchedule schedule) {
        TaskSchedule updateSchedule = taskScheduleRepository.findById(schedule.getId()).orElseThrow();
        updateSchedule.setName(schedule.getName());
        taskScheduleRepository.save(updateSchedule);
    }
}























