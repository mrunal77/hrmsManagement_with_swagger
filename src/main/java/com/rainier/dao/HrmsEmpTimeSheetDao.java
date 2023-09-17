package com.rainier.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rainier.beans.EmpTaskResponse;
import com.rainier.beans.TimeSheetAddCalWeekMonthBean;
import com.rainier.dto.requestBean.TimeSheetApprovalEntity;
import com.rainier.dto.responseBean.SaveTimeSheetResponseClass;
import com.rainier.dto.responseBean.TimeSheetWeekResponse;
import com.rainier.entities.EmployeeDetailsEntity;
import com.rainier.entities.EmployeeWorkStatusEntity;
import com.rainier.entities.ProjectTaskEntity;
import com.rainier.entities.SavingTimeSheet;
import com.rainier.entities.Task;
import com.rainier.entities.TimeSheetApprovalStatusEntity;
import com.rainier.response.LeaveDateResponseInTimeSheet;
import com.rainier.response.TimeSheetStatusCountResponse;

public interface HrmsEmpTimeSheetDao {
	HashMap<String, Object> getEmplTimeSheetDetails(int year, int month, int userId);

	ArrayList<SavingTimeSheet> getLeaveStatusByUserId(int userId, String status);

	public List<SavingTimeSheet> getTSDetailsByUserId(int userId);

	public String getEmployeeNameById(int userId);

	boolean saveTimeSheet(SavingTimeSheet saveTimeSheetEntity, Task tmTask, ProjectTaskEntity projTask,
			EmployeeWorkStatusEntity empWorkStatus);

	boolean updateTimeSheet(SavingTimeSheet updateTimeSheetEntity, Task tmTask, ProjectTaskEntity projTask,
			EmployeeWorkStatusEntity empWorkStatus);

	List<SavingTimeSheet> getTimeSheetDetails(int userId, int year, int month, int week);

	// List<SavingTimeSheet> getEmplTimeSheetDetailsByReportingManagerId(int repId);

	public String getReportingManagerNameById(int repId);

	public List<Object[]> getEmplTimeSheetDetailsByReportingManagerId(int userId, String status);

	public boolean timeSheetApproval(TimeSheetApprovalEntity timeSheetApprovalEntity);

	public List<EmployeeDetailsEntity> getEmpUserListByReportingManagerId(int repId);

	// public List<EmployeeDetailsEntity> getLeaveStatusByUserId(int userId);

	List<SavingTimeSheet> getTimeSheetDetailsByUserId(int userId);

	public TimeSheetApprovalStatusEntity getApprovalEntityByTimeSheetId(int timeSheetId);

	// public int saveProjectTask(Task tmTask, ProjectTaskEntity projTask);

	// Get All ReportingManagerName And Id Through This Service
	public List<EmployeeDetailsEntity> getAllReportingManagerNameId();

	ArrayList<SavingTimeSheet> getLeaveStatusByUserId(int userId, short calWeek);

	// Get Employee And Along With Working Details Of Employee Based On
	// ReportingManagerId Status calWeek And Year
	ArrayList<SavingTimeSheet> getEmployeeWorkingReportsByUserIdStatusCalweekYear(int userId, String status,
			short calWeek, int year);

	// Get All Status Count Based On ReportingManagerId calWeek And Year
	public TimeSheetStatusCountResponse getStatusCount(int userId, short calWeek);

	public ArrayList<SavingTimeSheet> getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus(int empId, int year,
			short month, short calWeek, String status);

	public ArrayList<SavingTimeSheet> getEmployeeDetailBasedOnRepIdEmpIdYearMonth(int empId, int year, short month);

	public List<TimeSheetWeekResponse> getWeekMonthNameByMonthId(short month);

	public List<TimeSheetWeekResponse> getMonth();

	boolean saveCalWeekMonthIdAndName(TimeSheetAddCalWeekMonthBean bean);

	public ArrayList<EmpTaskResponse> getEmpTask(int userId, short calWeek);

	ArrayList<SavingTimeSheet> getEmpDetailsByEmpId(int empId);

	public ArrayList<SavingTimeSheet> getEmpDetailsByEmpIdCalweekMonthYear(int empId, int year, short month,
			short calWeek);

	public ArrayList<SavingTimeSheet> getEmpDetailsByEmpIdYear(int empId, int year);

	public TimeSheetStatusCountResponse getStatusCountBasedOnYear(int userId, int year, int empId);

	public TimeSheetStatusCountResponse getStatusCount(int repId, int year, int empId, short month);

	public ArrayList<SavingTimeSheet> getEmpDetails(int userId, short calWeek, int year, short month);

	public TimeSheetStatusCountResponse getStatusCountBasedOnYearMonth(int userId, int year, int empId, short month);

	public HashMap<String, Object> getEmplTimeSheetDetails(int year, int userId);

	public ArrayList<SaveTimeSheetResponseClass> getEmployeeDetailBasedOnRepIdEmpIdYearMonthByEmpId(int empId, int year,
			short month);

	public ArrayList<SavingTimeSheet> getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus(int empId, int year,
			short month, String status);
	public TimeSheetStatusCountResponse getStatusCountByrepIdYearEmpIdMonthStatus(int userId, int empId, int year, short month, String  status);
	public  HashMap<String, Object> getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus1(int userId, int year, short month,
			 String status1);
	public ArrayList<SavingTimeSheet>getEmployeeDetailsBasedOnCanIdYearStatus(int empId,int year,String status);
	public HashMap<String, Object> getEmployeeDetailBasedOnCanIdYearStatus(int empId,int year,String status);
	
	public List<LeaveDateResponseInTimeSheet>getLeaveDateByEmpId(int empId,int year,int month);
}
