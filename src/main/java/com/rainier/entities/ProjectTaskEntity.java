package com.rainier.entities;

import javax.persistence.*;

@Entity
@Table(name = "tm_project_tasks")
public class ProjectTaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "project_id")
	private Integer projectId;

	@Column(name = "task_id")
	private Integer taskId;

	@Column(name = "estimated_hrs")
	private Integer estimatedHrs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getEstimatedHrs() {
		return estimatedHrs;
	}

	public void setEstimatedHrs(Integer estimatedHrs) {
		this.estimatedHrs = estimatedHrs;
	}

}
