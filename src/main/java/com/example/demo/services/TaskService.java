package com.example.demo.services;

import com.example.demo.models.TaskItem;
import com.example.demo.models.TaskCategory;
import com.example.demo.models.TaskSchedule;
import com.example.demo.repositories.TaskCategoryRepository;
import com.example.demo.repositories.TaskItemRepository;
import com.example.demo.repositories.TaskScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

	@Autowired
	private TaskItemRepository taskItemRepository;
	@Autowired
	private TaskCategoryRepository taskCategoryRepository;
	@Autowired
	private TaskScheduleRepository taskScheduleRepository;

	// 取得 //////////
	// 全タスク取得
	public List<TaskItem> getAllTasks(String userId) {
		return taskItemRepository.allTaskGet(userId);
	}

	// 未完了タスク取得
	public List<TaskItem> getIncompleteTasks(String userId) {
		return taskItemRepository.inCompletedTaskGet(userId);
	}

	// カテゴリIDからタスクを取得
	public List<TaskItem> getTasksByCategoryId(Long categoryId, String userId) {
		return taskItemRepository.taskGetByCategoryId(categoryId, userId);
	}

	// スケジュールIDからタスクを取得
	public List<TaskItem> getTasksByScheduleId(Long scheduleId, String userId) {
		return taskItemRepository.taskGetByScheduleId(scheduleId, userId);
	}

	// 完了タスク取得
	public List<TaskItem> getCompletedTasks(String userId) {
		return taskItemRepository.completedTaskGet(userId);
	}

	// 最新のタスク取得
	public TaskItem getLatestTask(String userId) {
		return taskItemRepository.latestTaskGet(userId);
	}

	// 最大のorderIndex（タスク）取得
	public Integer getMaxTaskOrderIndex(String userId) {
		return taskItemRepository.maxOrderIndexGet(userId);
	}

	// 全カテゴリ取得
	public List<TaskCategory> getAllCategories(String userId) {
		return taskCategoryRepository.allCategoryGet(userId);
	}

	// 最新のカテゴリ取得
	public TaskCategory getLatestCategory(String userId) {
		return taskCategoryRepository.latestCategoryGet(userId);
	}

	// IDを指定してカテゴリ取得
	public TaskCategory getCategoryById(Long id) {
		return taskCategoryRepository.categoryGetById(id);
	}

	// 最大のorderIndex（カテゴリ）取得
	public Integer getMaxCategoryOrderIndex(String userId) {
		return taskCategoryRepository.maxOrderIndexGet(userId);
	}

	// 全スケジュール取得
	public List<TaskSchedule> getAllSchedules(String userId) {
		return taskScheduleRepository.allScheduleGet(userId);
	}

	// 最新のスケジュール取得
	public TaskSchedule getLatestSchedule(String userId) {
		return taskScheduleRepository.latestScheduleGet(userId);
	}

	// IDを指定してスケジュール取得
	public TaskSchedule getScheduleById(Long id) {
		return taskScheduleRepository.scheduleGetById(id);
	}

	// 最大のorderIndex（スケジュール）取得
	public Integer getMaxScheduleOrderIndex(String userId) {
		return taskScheduleRepository.maxOrderIndexGet(userId);
	}


	// 追加 //////////
	// タスク追加
	public List<TaskItem> addTask(TaskItem taskItem) {
		taskItemRepository.save(taskItem);
		return taskItemRepository.findAll();
	}

	// カテゴリ追加
	public List<TaskCategory> addCategory(TaskCategory newTaskCategory) {
		List<TaskCategory> taskCategories = taskCategoryRepository.findAll();
		for (TaskCategory taskCategory : taskCategories) {
			if (taskCategory.getName().equals(newTaskCategory.getName()) &&
				taskCategory.getUserId().equals(newTaskCategory.getUserId())) {
				return null;
			}
		}
		taskCategoryRepository.save(newTaskCategory);
		return taskCategoryRepository.findAll();
	}

	// スケジュール追加
	public List<TaskSchedule> addSchedule(TaskSchedule newTaskSchedule) {
		List<TaskSchedule> taskSchedules = taskScheduleRepository.findAll();
		for (TaskSchedule taskSchedule : taskSchedules) {
			if (taskSchedule.getName().equals(newTaskSchedule.getName()) &&
				taskSchedule.getUserId().equals(newTaskSchedule.getUserId())) {
				return null;
			}
		}
		taskScheduleRepository.save(newTaskSchedule);
		return taskScheduleRepository.findAll();
	}

	// 削除 //////////
	// タスク削除
	public List<TaskItem> deleteTask(Long id) {
		taskItemRepository.deleteById(id);
		return taskItemRepository.findAll();
	}

	// カテゴリ削除
	public List<TaskCategory> deleteCategory(Long id) {
		taskCategoryRepository.deleteById(id);
		return taskCategoryRepository.findAll();
	}

	// スケジュール削除
	public List<TaskSchedule> deleteSchedule(Long id) {
		taskScheduleRepository.deleteById(id);
		return taskScheduleRepository.findAll();
	}


	// 更新 //////////
	// タスク更新
	public void updateTask(TaskItem taskItem) {
		TaskItem updateTask = taskItemRepository.findById(taskItem.getId()).orElseThrow();
		updateTask.setTitle(taskItem.getTitle());
		updateTask.setIsCompleted(taskItem.getIsCompleted());
		updateTask.setDeadLine(taskItem.getDeadLine());
		updateTask.setCategory(taskItem.getCategory());
		updateTask.setSchedule(taskItem.getSchedule());
		updateTask.setMemo(taskItem.getMemo());
		taskItemRepository.save(updateTask);
	}

	// カテゴリ更新
	public void updateCategory(TaskCategory category) {
		TaskCategory updateCategory = taskCategoryRepository.findById(category.getId()).orElseThrow();
		updateCategory.setName(category.getName());
		taskCategoryRepository.save(updateCategory);
	}

	// スケジュール更新
	public void updateSchedule(TaskSchedule schedule) {
		TaskSchedule updateSchedule = taskScheduleRepository.findById(schedule.getId()).orElseThrow();
		updateSchedule.setName(schedule.getName());
		taskScheduleRepository.save(updateSchedule);
	}
}
