package com.rainier.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tm_tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int TaskId;

	@Column(name = "task")
	private String taskName;

	@Column(name = "is_default")
	private Boolean is_default;

	@Column(name = "created_by", updatable = false)
	private int created_by;

	@Column(name = "modified_by")
	private int modified_by;

	@Column(name = "is_active")
	private int is_active;

	@Temporal(TemporalType.DATE)
	@Column(name = "created", updatable = false)
	private Date createdDate;

	@Column(name = "modified")
	private Date modifiedDate;

	public int getTaskId() {
		return TaskId;
	}

	public void setTaskId(int taskId) {
		TaskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Boolean getIs_default() {
		return is_default;
	}

	public void setIs_default(Boolean is_default) {
		this.is_default = is_default;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getModified_by() {
		return modified_by;
	}

	public void setModified_by(int modified_by) {
		this.modified_by = modified_by;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
