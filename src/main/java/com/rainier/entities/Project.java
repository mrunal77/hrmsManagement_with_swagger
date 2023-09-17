package com.rainier.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "tm_projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int projectId;

	@Column(name = "project_Name")
	private String project_Name;

	@Column(name = "project_status")
	private String project_status;

	/*
	 * @Column(name = "base_project") private int baseProject;
	 */
	@Column(name = "description")
	private String desc;

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private ClientsEntity client;

	@ManyToOne
	@JoinColumn(name = "currency_id", referencedColumnName = "id")
	private Currency currencyId;

	@Column(name = "project_type")
	private String project_type;

	@Column(name = "lead_approve_ts")
	private Boolean lead_approve_ts;

	@Column(name = "estimated_hrs")
	private int estimated_hrs;

	@Column(name = "start_date")
	private Date start_date;

	@Column(name = "end_date")
	private Date end_date;

	/*
	 * @JsonIgnore
	 * 
	 * @Column(name = "initiated_date") private Timestamp initiated_date;
	 * 
	 * @JsonIgnore
	 * 
	 * @Column(name = "hold_date") private Timestamp hold_date;
	 * 
	 * @JsonIgnore
	 * 
	 * @Column(name = "completed_date") private Timestamp completed_date;
	 */

	@JsonIgnore
	@Column(name = "created_by")
	private int created_by;

	@JsonIgnore
	@Column(name = "modified_by")
	private int modified_by;

	@JsonIgnore
	@Column(name = "is_active")
	private Boolean is_active;

	@JsonIgnore
	@Column(name = "created")
	private Timestamp created_date;

	@JsonIgnore
	@Column(name = "modified")
	private Timestamp modifiedDate;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProject_Name() {
		return project_Name;
	}

	public void setProject_Name(String project_Name) {
		this.project_Name = project_Name;
	}

	public String getProject_status() {
		return project_status;
	}

	public void setProject_status(String project_status) {
		this.project_status = project_status;
	}

	/*
	 * public int getBaseProject() { return baseProject; }
	 * 
	 * public void setBaseProject(int baseProject) { this.baseProject = baseProject;
	 * }
	 */

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ClientsEntity getClient() {
		return client;
	}

	public void setClient(ClientsEntity client) {
		this.client = client;
	}

	public Currency getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Currency currencyId) {
		this.currencyId = currencyId;
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public Boolean getLead_approve_ts() {
		return lead_approve_ts;
	}

	public void setLead_approve_ts(Boolean lead_approve_ts) {
		this.lead_approve_ts = lead_approve_ts;
	}

	public int getEstimated_hrs() {
		return estimated_hrs;
	}

	public void setEstimated_hrs(int estimated_hrs) {
		this.estimated_hrs = estimated_hrs;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	/*
	 * public Timestamp getInitiated_date() { return initiated_date; }
	 * 
	 * public void setInitiated_date(Timestamp initiated_date) { this.initiated_date
	 * = initiated_date; }
	 * 
	 * public Timestamp getHold_date() { return hold_date; }
	 * 
	 * public void setHold_date(Timestamp hold_date) { this.hold_date = hold_date; }
	 * 
	 * public Timestamp getCompleted_date() { return completed_date; }
	 * 
	 * public void setCompleted_date(Timestamp completed_date) { this.completed_date
	 * = completed_date; }
	 */

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

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Timestamp getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
