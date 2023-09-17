package com.rainier.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "main_employees_summary")
public class EmployeeDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "date_of_joining")
    private Date dateOfJoining;

    @Column(name = "date_of_leaving")
    private Date dateOfleaving;

    @Column(name = "reporting_manager")
    private Integer reportingManagerId;

    @Column(name = "reporting_manager_name")
    private String reportingManager;

    @Column(name = "emp_status_id")
    private String emp_status_id;

    @Column(name = "emp_status_name")
    private String employmentStatus;

    @Column(name = "businessunit_name")
    private String businessunitName;

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "jobtitle_id")
    private Integer jobtitle_id;

    @Column(name = "jobtitle_name")
    private String jobTitleName;

    @Column(name = "position_id")
    private Integer position_id;

    @Column(name = "position_name")
    private String designation;

    @Column(name = "years_exp")
    private Integer yearOfExp;

    @Column(name = "holiday_group")
    private String holiday_group;

    @Column(name = "holiday_group_name")
    private String holiday_group_name;

    @Column(name = "prefix_id")
    private Integer prefix_id;

    @Column(name = "prefix_name")
    private String prefix;

    @Column(name = "extension_number")
    private Long extensionNo;

    @Column(name = "office_number")
    private String WorkTelephoneNo;

    @Column(name = "office_faxnumber")
    private String faxNo;

    @Column(name = "emprole")
    private Integer emprole;

    @Column(name = "emprole_name")
    private String role;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "userfullname")
    private String employeeName;

    @Column(name = "emailaddress")
    private String email;

    @Column(name = "contactnumber")
    private String contactnumber;

    @Column(name = "backgroundchk_status")
    private String backgroundchk_status;

    @Column(name = "employeeId")
    private String employeeId;

    @Column(name = "modeofentry")
    private String modeOfEmployment;

    @Column(name = "visa_id")
    private int visaId;

    @Column(name = "selected_documents")
    private String selectedDocumentsIds;

    @Column(name = "other_modeofentry")
    private String other_modeofentry;

    @Column(name = "selecteddate")
    private Date selecteddate;

    @Column(name = "candidatereferredby")
    private String candidatereferredby;

    @Column(name = "referer_name")
    private String referer_name;

    @Column(name = "profileimg")
    private String profileImg;

    @Column(name = "signature")
    private String signature;

    @Column(name = "createdby")
    private Integer createdby;

    @Column(name = "createdby_name")
    private String createdby_name;

    @Column(name = "modifiedby")
    private Integer modifiedBy;

    @Column(name = "modifieddate")
    private Date modifieddate;

    @Column(name = "createddate")
    private Date createddate;

    @Column(name = "isactive")
    private int isactive;

    @Column(name = "businessunit_id")
    private Integer businessunitId;
    
    @Column(name = "imm_manager_id")
    private int immManagerId;

    @Column(name = "hr_manager_id")
	private int hrManagerId;
    
    @Column(name = "imm_manager_name")
    private String immManagerName;

    @Column(name = "hr_manager_name")
	private String hrManagerName;
    @Column(name="employee_status")
    private String employeeStatus;
    

    public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
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

    public String getEmp_status_id() {
        return emp_status_id;
    }

    public void setEmp_status_id(String emp_status_id) {
        this.emp_status_id = emp_status_id;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getBusinessunitName() {
        return businessunitName;
    }

    public void setBusinessunitName(String businessunitName) {
        this.businessunitName = businessunitName;
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

    public Integer getJobtitle_id() {
        return jobtitle_id;
    }

    public void setJobtitle_id(Integer jobtitle_id) {
        this.jobtitle_id = jobtitle_id;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getYearOfExp() {
        return yearOfExp;
    }

    public void setYearOfExp(Integer yearOfExp) {
        this.yearOfExp = yearOfExp;
    }

    public String getHoliday_group() {
        return holiday_group;
    }

    public void setHoliday_group(String holiday_group) {
        this.holiday_group = holiday_group;
    }

    public String getHoliday_group_name() {
        return holiday_group_name;
    }

    public void setHoliday_group_name(String holiday_group_name) {
        this.holiday_group_name = holiday_group_name;
    }

    public Integer getPrefix_id() {
        return prefix_id;
    }

    public void setPrefix_id(Integer prefix_id) {
        this.prefix_id = prefix_id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getExtensionNo() {
        return extensionNo;
    }

    public void setExtensionNo(Long extensionNo) {
        this.extensionNo = extensionNo;
    }

    public String getWorkTelephoneNo() {
        return WorkTelephoneNo;
    }

    public void setWorkTelephoneNo(String workTelephoneNo) {
        WorkTelephoneNo = workTelephoneNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getBackgroundchk_status() {
        return backgroundchk_status;
    }

    public void setBackgroundchk_status(String backgroundchk_status) {
        this.backgroundchk_status = backgroundchk_status;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getModeOfEmployment() {
        return modeOfEmployment;
    }

    public void setModeOfEmployment(String modeOfEmployment) {
        this.modeOfEmployment = modeOfEmployment;
    }

    public int getVisaId() {
        return visaId;
    }

    public void setVisaId(int visaId) {
        this.visaId = visaId;
    }

    public String getSelectedDocumentsIds() {
        return selectedDocumentsIds;
    }

    public void setSelectedDocumentsIds(String selectedDocumentsIds) {
        this.selectedDocumentsIds = selectedDocumentsIds;
    }

    public String getOther_modeofentry() {
        return other_modeofentry;
    }

    public void setOther_modeofentry(String other_modeofentry) {
        this.other_modeofentry = other_modeofentry;
    }

    public Date getSelecteddate() {
        return selecteddate;
    }

    public void setSelecteddate(Date selecteddate) {
        this.selecteddate = selecteddate;
    }

    public String getCandidatereferredby() {
        return candidatereferredby;
    }

    public void setCandidatereferredby(String candidatereferredby) {
        this.candidatereferredby = candidatereferredby;
    }

    public String getReferer_name() {
        return referer_name;
    }

    public void setReferer_name(String referer_name) {
        this.referer_name = referer_name;
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

    public Integer getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    public String getCreatedby_name() {
        return createdby_name;
    }

    public void setCreatedby_name(String createdby_name) {
        this.createdby_name = createdby_name;
    }

    public Integer getModifiedby() {
        return modifiedBy;
    }

    public void setModifiedby(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public Integer getBusinessunitId() {
        return businessunitId;
    }

    public void setBusinessunitId(Integer businessunitId) {
        this.businessunitId = businessunitId;
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
