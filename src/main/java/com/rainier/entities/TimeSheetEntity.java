package com.rainier.entities;

import javax.persistence.*;

@Entity
@Table(name = "timeSheetEntity")

public class TimeSheetEntity {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")
	 private int id;
	@Column(name = "user_id")
	private int userid;
	
	@Column(name = " time")
	private String time;
	
	@Column(name = "projectName")
	private String projectName;
	
	@Column(name = "task")
	private String task;
	
	@Column(name = "description")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
