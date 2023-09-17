package com.rainier.dao;

import com.rainier.entities.DefaultTaskEntity;
import com.rainier.entities.Task;

import java.util.List;

public interface ProjectTaskDao {

	List<Task> getProjectDetails(int projectId);

	// Add task Name.
    boolean addTask(DefaultTaskEntity entity);
	
	// update Task Name
    boolean updateTask(DefaultTaskEntity entity);
	
	// Delete Task name.
	
	String deleteTaskname(int id);
	
	// fetch Default task
    List<DefaultTaskEntity> getDefaultTask();
}
