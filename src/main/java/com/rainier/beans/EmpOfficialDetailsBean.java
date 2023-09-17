package com.rainier.beans;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Column;

public class EmpOfficialDetailsBean {

    private int id;
    private Integer userId;
    private String employeeName;
    private String designation;
    private String email;
    private String employeeId;
    private String contactNumber;
    private Integer departmentId;
    private String departmentName;
    private String prefix;
    private String firstName;
    private String lastName;
    private String modeOfEmployment;
    private Integer emprole;
    private String role;
    private Integer businessunitId;
    private String businessunitName;
    private Integer reportingManagerId;
    private String reportingManager;
    private Integer jobTitleId;
    private String jobTitleName;
    private Integer employmentStatusId;
    private String employmentStatus;
    private Integer positionId;
    private String positionName;
    private Date dateOfJoining;
    private Date dateOfleaving;
    private Integer yearOfExp;
    private String WorkTelephoneNo;
    private long extensionNo;
    private String faxNo;
    private String profileImg;
    private String signature;
    private String avilableCasualLeaves;
    private String avilableSickLeaves;
    private Integer currencyId;
    private String currencyName;
    private Integer salaryType;
    private String frequencyType;
    private String salary;
    private String bankname;
    private Integer visaId;
    private String visaName;
    private ArrayList<Integer> documentIds;
    private boolean isActive;
    private String employeeStatus;
    private Integer prefixId;
    private Integer roleId;
    private int immManagerId;
    private int hrManagerId;
    private String immManagerName;
    private String hrManagerName;

    public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public Integer getPrefixId() {
		return prefixId;
	}

	public void setPrefixId(Integer prefixId) {
		this.prefixId = prefixId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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

    public String getModeOfEmployment() {
        return modeOfEmployment;
    }

    public void setModeOfEmployment(String modeOfEmployment) {
        this.modeOfEmployment = modeOfEmployment;
    }
    

    public Integer getEmprole() {
		return emprole;
	}

	public void setEmprole(Integer emprole) {
		this.emprole = emprole;
	}

	public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getBusinessunitId() {
        return businessunitId;
    }

    public void setBusinessunitId(Integer businessunitId) {
        this.businessunitId = businessunitId;
    }

    public String getBusinessunitName() {
        return businessunitName;
    }

    public void setBusinessunitName(String businessunitName) {
        this.businessunitName = businessunitName;
    }

    public Integer getReportingManagerId() {
        return reportingManagerId;
    }

    public void setReportingManagerId(Integer reportingManagerId) {
        this.reportingManagerId = reportingManagerId;
    }

    public String getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }

    public Integer getJobTitleId() {
        return jobTitleId;
    }

    public void setJobTitleId(Integer jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public Integer getEmploymentStatusId() {
        return employmentStatusId;
    }

    public void setEmploymentStatusId(Integer employmentStatusId) {
        this.employmentStatusId = employmentStatusId;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Date getDateOfleaving() {
        return dateOfleaving;
    }

    public void setDateOfleaving(Date dateOfleaving) {
        this.dateOfleaving = dateOfleaving;
    }

    public Integer getYearOfExp() {
        return yearOfExp;
    }

    public void setYearOfExp(Integer yearOfExp) {
        this.yearOfExp = yearOfExp;
    }

    public String getWorkTelephoneNo() {
        return WorkTelephoneNo;
    }

    public void setWorkTelephoneNo(String workTelephoneNo) {
        WorkTelephoneNo = workTelephoneNo;
    }

    public long getExtensionNo() {
        return extensionNo;
    }

    public void setExtensionNo(long extensionNo) {
        this.extensionNo = extensionNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAvilableCasualLeaves() {
        return avilableCasualLeaves;
    }

    public void setAvilableCasualLeaves(String avilableCasualLeaves) {
        this.avilableCasualLeaves = avilableCasualLeaves;
    }

    public String getAvilableSickLeaves() {
        return avilableSickLeaves;
    }

    public void setAvilableSickLeaves(String avilableSickLeaves) {
        this.avilableSickLeaves = avilableSickLeaves;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Integer getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(Integer salaryType) {
        this.salaryType = salaryType;
    }

    public String getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(String frequencyType) {
        this.frequencyType = frequencyType;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public Integer getVisaId() {
        return visaId;
    }

    public void setVisaId(Integer visaId) {
        this.visaId = visaId;
    }

    public String getVisaName() {
        return visaName;
    }

    public void setVisaName(String visaName) {
        this.visaName = visaName;
    }

    public ArrayList<Integer> getDocumentIds() {
        return documentIds;
    }

    public void setDocumentIds(ArrayList<Integer> documentIds) {
        this.documentIds = documentIds;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

	public String getImmManagerName() {
		return immManagerName;
	}

	public void setImmManagerName(String immManagerName) {
		this.immManagerName = immManagerName;
	}

	public String getHrManagerName() {
		return hrManagerName;
	}

	public void setHrManagerName(String hrManagerName) {
		this.hrManagerName = hrManagerName;
	}
    
}
