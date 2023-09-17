package com.rainier.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BenchSalesAddEmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String gender;
	@Column
	private String maritalStatus;
	@Column
	private String officialEmailId;
	@Column
	private String personalEmailId;
	@Column
	private String password;
	@Column
	private String confirmPassword;
	@Column
	private String phoneEmpNo;
    @Column
	private Date dob;
	@Column
	private String phoneEmergencyNo;
	@Column
	private String relation;
	@Column
	private String address;
	@Column
	private String comment;
	@Column
	private Date joiningDate;
	@Column
	private String uploadPic;
	@Column
	private String emergencyName;
	@Column
	private String role;
	@Column
	private String employeeNo;
	@Column
	private short isActive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getOfficialEmailId() {
		return officialEmailId;
	}
	public void setOfficialEmailId(String officialEmailId) {
		this.officialEmailId = officialEmailId;
	}
	public String getPersonalEmailId() {
		return personalEmailId;
	}
	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getPhoneEmpNo() {
		return phoneEmpNo;
	}
	public void setPhoneEmpNo(String phoneEmpNo) {
		this.phoneEmpNo = phoneEmpNo;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhoneEmergencyNo() {
		return phoneEmergencyNo;
	}
	public void setPhoneEmergencyNo(String phoneEmergencyNo) {
		this.phoneEmergencyNo = phoneEmergencyNo;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getUploadPic() {
		return uploadPic;
	}
	public void setUploadPic(String uploadPic) {
		this.uploadPic = uploadPic;
	}
	public String getEmergencyName() {
		return emergencyName;
	}
	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public short getIsActive() {
		return isActive;
	}
	public void setIsActive(short isActive) {
		this.isActive = isActive;
	}

	


}
