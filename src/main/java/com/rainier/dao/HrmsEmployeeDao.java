package com.rainier.dao;

import com.rainier.beans.EmployeeRequestBean;
import com.rainier.beans.EmployeeResponseBean;
import com.rainier.beans.PersonalDetailsRequestBean;
import com.rainier.entities.*;

import java.util.Date;
import java.util.List;

public interface HrmsEmployeeDao {
	List<EmployeeDetailsEntity> getListOfEmp(int userId, String filter);

	List<EmployeeDetailsEntity> getListOfBUDUEmp(int businessunitId, int departmentId);

	List<Object[]> getAvailableLeaves(int userid);

	String insertEmployeeLeave(AllottedLeavesLogEntity leaveLog);

	List<?> addEmployeeLeaveLimit(AllottedLeavesLogEntity leaveLimit);

	List<EmployeeDetailsEntity> getEmployeeprofile(String employeeId);

	// Add Employee Official Details- stored proc.
	int saveEmployeeOfficialDetails(EmployeeRequestBean bean);

	// Update Employee Official Details- stored proc.
	String updateEmployeeOfficialDetails(EmployeeRequestBean bean);

	// Saving mail log on employee Official info insertion
	void insertMailLog(MailLogEntity mailLog);

	// Save Employee Personal Details
	boolean saveEmpPersonalDetails(PersonalDetailsRequestBean bean);

	// Read Employee Personal Details
	List<EmpPersonalDetailsEntity> getEmpPersonalDetails(int userId);

	// Update Employee Personal Details
	boolean updateEmpPersonalDetails(PersonalDetailsRequestBean entity);

	// Save EmpContact Details
	int saveEmpContactDetails(CommunicationInfoEntity entity);

	// Update EmpContact Details
	int updateEmpContactDetails(CommunicationInfoEntity entity);

	// Read Employee Contact Details
	List<Object[]> getEmpContactDetails(int userId);

	// Fetching Salary Details
	SalaryDetailsEntity getEmpSalaryDetails(int userId);

	// Update Salary Details
	int updateSalaryDetails(SalaryDetailsEntity entity);

	List<Object[]> getAdditionalDetails(int userId);

	// Signature Upload Method
	boolean updateSignaturePath(String filePath, int userId);

	// Hr Manager will get only that no of Employee whoever added by himself.
	List<EmployeeDetailsEntity> getListOfEmpAddedByHrMgr(String hrManagerName);

	// Email Address Duplicate Check Service for Adding Employee.
	EmployeeDetailsEntity getDuplicateEmailAddress(String emailAddress);

	// getService sellsExecutive
	List<User> getListOfSellsExecutive();

	//Empolyee Soft Delete Service 
	public int softDeleteEmployee(int userId);
	//get ReportingManagerId By UserId
	public Integer getReportingMangerIdByUserId(int userId);
	
	public Date getDateOfJoiningOfEmp(int userId);
	
	List<EmployeeDetailsEntity>getAllInActiveEmp();
	

	public List<EmployeeResponseBean>getAllEmpByRepId(int repId);
	
}
