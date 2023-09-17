package com.rainier.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tm_project_tasks")
public class TmProjectTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int projectTaskId;

	@ManyToOne
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "task_id", referencedColumnName = "id")
	private Task task;

	@Column(name = "estimated_hrs")
	private int estimated_hrs;

	@Column(name = "is_billable")
	private Boolean is_billable;

	@Column(name = "billable_rate")
	private Double billable_rate;

	@Column(name = "created_by", updatable = false)
	private int created_by;

	@Column(name = "modified_by")
	private int modified_by;

	@Column(name = "is_active")
	private int is_active;

	@Column(name = "created", updatable = false)
	private Date createdDate;

	@Column(name = "modified")
	private Date modifiedDate;

	public int getProjectTaskId() {
		return projectTaskId;
	}

	public void setProjectTaskId(int projectTaskId) {
		this.projectTaskId = projectTaskId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public int getEstimated_hrs() {
		return estimated_hrs;
	}

	public void setEstimated_hrs(int estimated_hrs) {
		this.estimated_hrs = estimated_hrs;
	}

	public Boolean getIs_billable() {
		return is_billable;
	}

	public void setIs_billable(Boolean is_billable) {
		this.is_billable = is_billable;
	}

	public Double getBillable_rate() {
		return billable_rate;
	}

	public void setBillable_rate(Double billable_rate) {
		this.billable_rate = billable_rate;
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
