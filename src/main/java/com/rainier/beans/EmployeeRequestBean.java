package com.rainier.beans;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;

public class EmployeeRequestBean {
	private String employeeId;
	private int userId;
	private int prefixId;
	private String firstName;
	private String lastName;
	private String fullName;
	private String modeOfEmployment;
	private int roleId;
	private int businessunitId;
	private int departmentId;
	private int reportingManagerId;
	private int jobTitleId;
	private int employmentStatusId;
	private int positionId;
	private String yearOfExp;
	private String dateOfJoining;
	private String workTelephoneNo;
	private String extensionNo;
	private String faxNo;
	private String email;
	private String password;
	private int createdBy;
	private int currencyId;
	private int modifiedBy;
	private int payFrequency;
	private String salary;
	private String bankName;
	private int visaId;
	private int[] visaDocumentId;
	private int immManagerId;
    private int hrManagerId;
    private String employeeStatus;
    private Timestamp modifieddate;
    
     

	public Timestamp getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	

	
	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(int prefixId) {
		this.prefixId = prefixId;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getModeOfEmployment() {
		return modeOfEmployment;
	}

	public void setModeOfEmployment(String modeOfEmployment) {
		this.modeOfEmployment = modeOfEmployment;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getBusinessunitId() {
		return businessunitId;
	}

	public void setBusinessunitId(int businessunitId) {
		this.businessunitId = businessunitId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getReportingManagerId() {
		return reportingManagerId;
	}

	public void setReportingManagerId(int reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}

	public int getJobTitleId() {
		return jobTitleId;
	}

	public void setJobTitleId(int jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	public int getEmploymentStatusId() {
		return employmentStatusId;
	}

	public void setEmploymentStatusId(int employmentStatusId) {
		this.employmentStatusId = employmentStatusId;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public String getYearOfExp() {
		return yearOfExp;
	}

	public void setYearOfExp(String yearOfExp) {
		this.yearOfExp = yearOfExp;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getWorkTelephoneNo() {
		return workTelephoneNo;
	}

	public void setWorkTelephoneNo(String workTelephoneNo) {
		this.workTelephoneNo = workTelephoneNo;
	}

	public String getExtensionNo() {
		return extensionNo;
	}

	public void setExtensionNo(String extensionNo) {
		this.extensionNo = extensionNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public int getPayFrequency() {
		return payFrequency;
	}

	public void setPayFrequency(int payFrequency) {
		this.payFrequency = payFrequency;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getVisaId() {
		return visaId;
	}

	public void setVisaId(int visaId) {
		this.visaId = visaId;
	}

	public int[] getVisaDocumentId() {
		return visaDocumentId;
	}

	public void setVisaDocumentId(int[] visaDocumentId) {
		this.visaDocumentId = visaDocumentId;
	}

	public int getImmManagerId() {
		return immManagerId;
	}

	public void setImmManagerId(int immManagerId) {
		this.immManagerId = immManagerId;
	}

	public int getHrManagerId() {
		return hrManagerId;
	}

	public void setHrManagerId(int hrManagerId) {
		this.hrManagerId = hrManagerId;
	}

	
}
