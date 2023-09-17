package com.rainier.entities;

import javax.persistence.*;

@Entity
@Table(name = "tm_project_employees")
public class ProjectEmployeesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	private Project projectId;

	@ManyToOne
	@JoinColumn(name = "emp_id", referencedColumnName = "id")
	private User empId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}

	public User getEmpId() {
		return empId;
	}

	public void setEmpId(User empId) {
		this.empId = empId;
	}

}
