package com.example.demo.controllers;

import com.example.demo.models.TaskItem;
import com.example.demo.models.TaskCategory;
import com.example.demo.models.TaskSchedule;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taskAPI")
@CrossOrigin(origins = {"https://task-manager-frontend-14.vercel.app/", "http://localhost:3000", "http://localhost:3001"})
public class TaskRestController {

	@Autowired
	private TaskService taskService;

	// 取得 //////////
	// 全タスク取得
	@GetMapping("/task")
	public List<TaskItem> taskGet(@RequestParam String userId) {
		return taskService.getAllTasks(userId);
	}

	// 未完了タスク取得
	@GetMapping("/inCompletedTask")
	public List<TaskItem> inCompletedTaskGet(@RequestParam String userId) {
		return taskService.getIncompleteTasks(userId);
	}

	// カテゴリIDからタスクを取得
	@GetMapping("task/{categoryId}")
	public List<TaskItem> taskGetByCategoryId(@PathVariable Long categoryId, @RequestParam String userId) {
		return taskService.getTasksByCategoryId(categoryId, userId);
	}

	// スケジュールIDからタスクを取得
	@GetMapping("task/{scheduleId}")
	public List<TaskItem> taskGetByScheduleId(@PathVariable Long scheduleId, @RequestParam String userId) {
		return taskService.getTasksByScheduleId(scheduleId, userId);
	}

	// 完了タスク取得
	@GetMapping("/completedTask")
	public List<TaskItem> completedTaskGet(@RequestParam String userId) {
		return taskService.getCompletedTasks(userId);
	}

	// 最新のタスク取得
	@GetMapping("/latestTask")
	public TaskItem latestTaskGet(@RequestParam String userId) {
		return taskService.getLatestTask(userId);
	}

	// 最大のorderIndex（タスク）取得
	@GetMapping("/maxTaskOrderIndex")
	public Integer maxOrderIndexGet(@RequestParam String userId) {
		return taskService.getMaxTaskOrderIndex(userId);
	}

	// 全カテゴリ取得
	@GetMapping("/category")
	public List<TaskCategory> categoryGetAll(@RequestParam String userId) {
		return taskService.getAllCategories(userId);
	}

	// 最新のカテゴリ取得
	@GetMapping("/latestCategory")
	public TaskCategory latestCategoryGet(@RequestParam String userId) {
		return taskService.getLatestCategory(userId);
	}

	// IDを指定してカテゴリ取得
	@GetMapping("/category/{id}")
	public TaskCategory categoryGetById(@PathVariable Long id) {
		return taskService.getCategoryById(id);
	}

	// 最大のorderIndex（カテゴリ）取得
	@GetMapping("/maxCategoryOrderIndex")
	public Integer maxCategoryOrderIndexGet(@RequestParam String userId) {
		return taskService.getMaxCategoryOrderIndex(userId);
	}

	// 全スケジュール取得
	@GetMapping("/schedule")
	public List<TaskSchedule> scheduleGetAll(@RequestParam String userId) {
		return taskService.getAllSchedules(userId);
	}

	// 最新のスケジュール取得
	@GetMapping("/latestSchedule")
	public TaskSchedule latestScheduleGet(@RequestParam String userId) {
		return taskService.getLatestSchedule(userId);
	}

	// IDを指定してスケジュール取得
	@GetMapping("/schedule/{id}")
	public TaskSchedule scheduleGetById(@PathVariable Long id) {
		return taskService.getScheduleById(id);
	}

	// 最大のorderIndex（スケジュール）取得
	@GetMapping("/maxScheduleOrderIndex")
	public Integer maxScheduleOrderIndexGet(@RequestParam String userId) {
		return taskService.getMaxScheduleOrderIndex(userId);
	}


	// 追加 //////////
	// タスク追加
	@PostMapping("/task")
	public List<TaskItem> taskAdd(@RequestBody TaskItem taskItem) {
		return taskService.addTask(taskItem);
	}

	// カテゴリ追加
	@PostMapping("/category")
	public List<TaskCategory> taskCategories(@RequestBody TaskCategory newTaskCategory) {
		return taskService.addCategory(newTaskCategory);
	}

	// スケジュール追加
	@PostMapping("/schedule")
	public List<TaskSchedule> taskSchedules(@RequestBody TaskSchedule newTaskSchedule) {
		return taskService.addSchedule(newTaskSchedule);
	}

	// 削除 //////////
	// タスク削除
	@DeleteMapping("/task/{id}")
	public List<TaskItem> taskDelete(@PathVariable Long id) {
		return taskService.deleteTask(id);
	}

	// カテゴリ削除
	@DeleteMapping("/category/{id}")
	public List<TaskCategory> categoryDelete(@PathVariable Long id) {
		return taskService.deleteCategory(id);
	}

	// スケジュール削除
	@DeleteMapping("/schedule/{id}")
	public List<TaskSchedule> scheduleDelete(@PathVariable Long id) {
		return taskService.deleteSchedule(id);
	}

	// 更新 //////////
	// タスク更新
	@PutMapping("/updateTask")
	public void updateTask(@RequestBody TaskItem taskItem) {
		taskService.updateTask(taskItem);
	}

	// カテゴリ更新
	@PutMapping("/updateCategory")
	public void updateCategory(@RequestBody TaskCategory category) {
		taskService.updateCategory(category);
	}

	// スケジュール更新
	@PutMapping("/updateSchedule")
	public void updateSchedule(@RequestBody TaskSchedule schedule) {
		taskService.updateSchedule(schedule);
	}
}
