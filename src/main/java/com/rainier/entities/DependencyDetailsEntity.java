
package com.rainier.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_empdependencydetails")
public class DependencyDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "dependent_name")
	private String dependentName;

	@Column(name = "dependent_relation")
	private String dependentRelation;

	@Column(name = "dependent_custody")
	private String dependentCustodyCode;

	@Column(name = "dependent_dob")
	private String dependentDOB;

	@Column(name = "dependent_age")
	private int dependentAge;

	@Column(name = "createddate")
	private Timestamp createdDate;

	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@Column(name = "isactive")
	private int isactive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDependentName() {
		return dependentName;
	}

	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}

	public String getDependentRelation() {
		return dependentRelation;
	}

	public void setDependentRelation(String dependentRelation) {
		this.dependentRelation = dependentRelation;
	}

	public String getDependentCustodyCode() {
		return dependentCustodyCode;
	}

	public void setDependentCustodyCode(String dependentCustodyCode) {
		this.dependentCustodyCode = dependentCustodyCode;
	}

	public String getDependentDOB() {
		return dependentDOB;
	}

	public void setDependentDOB(String dependentDOB) {
		this.dependentDOB = dependentDOB;
	}

	public int getDependentAge() {
		return dependentAge;
	}

	public void setDependentAge(int dependentAge) {
		this.dependentAge = dependentAge;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

}
