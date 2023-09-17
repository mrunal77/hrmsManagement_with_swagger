package com.rainier.businesslogic;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.rainier.beans.CommonResponseBean;
import com.rainier.beans.CurrentWeekDatesResponse;
import com.rainier.beans.EmpTaskResponse;
import com.rainier.beans.SaveTimesheetRequestBean;
import com.rainier.beans.SavingTimeSheetReqBean;
import com.rainier.beans.TimeSheetAddCalWeekMonthBean;
import com.rainier.beans.TimeSheetResponse;
import com.rainier.beans.TimeSheetResponseBean;
import com.rainier.beans.TimeSheetSummary;
import com.rainier.beans.TimeSheetsMonthlyBean;
import com.rainier.dao.HrmsEmpTimeSheetDao;
import com.rainier.dao.Impl.HrmsEmpTimeSheetDaoImpl;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.dto.requestBean.TimeSheetApprovalEntity;
import com.rainier.dto.responseBean.SaveTimeSheetResponseClass;
import com.rainier.dto.responseBean.TaskEmpResponse;
import com.rainier.dto.responseBean.TimeSheetWeekResponse;
import com.rainier.entities.EmployeeDetailsEntity;
import com.rainier.entities.EmployeeWorkStatusEntity;
import com.rainier.entities.ProjectTaskEntity;
import com.rainier.entities.SavingTimeSheet;
import com.rainier.entities.Task;
import com.rainier.entities.TimeSheetAddCalWeekMonthEntity;
import com.rainier.entities.TimeSheetApprovalStatusEntity;
import com.rainier.response.EmployeesForReportingManagerResponse;
import com.rainier.response.LeaveDateResponseInTimeSheet;
import com.rainier.response.TSResponseEmployeeName;
import com.rainier.response.TSResponseObj;
import com.rainier.response.TimeSheetApprovalResponse;
import com.rainier.response.TimeSheetResp;
import com.rainier.response.TimeSheetStatusCountResponse;
import com.rainier.utility.CurrentWeekDates;
import com.rainier.utility.HrmsGetDateAndTime;

public class TimeSheetDetails {
	private static final HrmsEmpTimeSheetDao hrmsEmpTimeSheetDao = new HrmsEmpTimeSheetDaoImpl();
	private static final Logger logger = Logger.getLogger(TimeSheetDetails.class);

	@SuppressWarnings({ "unchecked", "deprecation" })
	public TimeSheetResponse getMonthlyTimeSheetDetails(int year, int month, int userId) {
		logger.info("entered into getMonthlyTimeSheetDetails method of TimeSheetDetails class");
		TimeSheetResponse timeSheetResponse = new TimeSheetResponse();
		String queryForMonthName = null;
		ArrayList<TimeSheetSummary> timeSheetList = new ArrayList<TimeSheetSummary>();

		HashMap<String, Object> MonthlyTSHM = hrmsEmpTimeSheetDao.getEmplTimeSheetDetails(year, month, userId);

		boolean status = (boolean) MonthlyTSHM.get("status");
		queryForMonthName = "select distinct(month_name) from timeSheet_add_calWeek_month where month_id="
				+ month + "";
		String monthName = (String) DbConnect.DbCon().createSQLQuery(queryForMonthName).uniqueResult();
		if (status) {
			List<Object[]> listofMonthlyTSDetails = (List<Object[]>) MonthlyTSHM.get("monthlyList");
			if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
				for (Object[] row : listofMonthlyTSDetails) {
					TimeSheetSummary timeSheet = new TimeSheetSummary();

					timeSheet.setEmployeeId(userId);

					timeSheet.setMondayHrs(objToFloat(row[0]));
					timeSheet.setTuesdayHrs(objToFloat(row[1]));
					timeSheet.setWednesdayHrs(objToFloat(row[2]));
					timeSheet.setThursdayHrs(objToFloat(row[3]));
					timeSheet.setFridayHrs(objToFloat(row[4]));
					timeSheet.setSaturdayHrs(objToFloat(row[5]));
					timeSheet.setSundayHrs(objToFloat(row[6]));
					timeSheet.setMonth(objToInteger(row[7]));
					timeSheet.setYear(objToInteger(row[8]));
					timeSheet.setWeekNo(objToInteger(row[9]));
					timeSheet.setFromDate(objToString(row[10]));
					timeSheet.setToDate(objToString(row[11]));
					timeSheet.setMonday(((Date)row[12]).getDate());
					timeSheet.setTuesday(((Date)row[13]).getDate());
					timeSheet.setWednesday(((Date)row[14]).getDate());
					timeSheet.setThrusday(((Date)row[15]).getDate());
					timeSheet.setFriday(((Date)row[16]).getDate());
					timeSheet.setSaturday(((Date)row[17]).getDate());
					timeSheet.setSunday(((Date)row[18]).getDate());
					timeSheet.setTotalHours(objToFloat(row[19]));
					timeSheet.setEmployeeFullName(objToString(row[20]));
					timeSheet.setReportingManagerName(objToString(row[21]));
					timeSheet.setStatus(objToString(row[22]));
					int timeSheetId = objToInteger(row[23]);
				    List<LeaveDateResponseInTimeSheet>dates=hrmsEmpTimeSheetDao.getLeaveDateByEmpId(userId, year, month);
				    for(LeaveDateResponseInTimeSheet date:dates) {
				    	int date1=date.getLeaveFromDate().getDate();
				    	int date2=date.getLeaveToDate().getDate();
				    	int date3=date2-date1-1;
				    	logger.info(date3);
				    	timeSheet.setLeaveFromDate(date.getLeaveFromDate());
				    	timeSheet.setLeaveToDate(date.getLeaveToDate());
				    }
					timeSheet.setMonthName(monthName);
                     logger.info(timeSheetId);
					TimeSheetApprovalStatusEntity tsApp = hrmsEmpTimeSheetDao
							.getApprovalEntityByTimeSheetId(timeSheetId);

					if (tsApp != null) {
						timeSheet.setApproverName(tsApp.getApproverName());
						timeSheet.setRejectReason(tsApp.getRejectReason());
					}

					timeSheetList.add(timeSheet);
				}
				timeSheetResponse.setMessage("TimeSheet Details are retrieved successfully");
				timeSheetResponse.setStatus(true);

			} else {
				timeSheetResponse.setMessage("TimeSheet Details are not found");
				timeSheetResponse.setStatus(false);
			}
		} else {
			timeSheetResponse.setMessage("Error in fetching details" + MonthlyTSHM.get("message"));
			timeSheetResponse.setStatus(false);
		}
		timeSheetResponse.setMonthlyTimeSheetList(timeSheetList);

		return timeSheetResponse;
	}

	public Response getEmplTimeSheetDetailsByReportingManagerId(int repId, String status) {
		logger.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of TimeSheetDetails class");

		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);

		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();

		TSResponseObj tsResp = new TSResponseObj();

		TSResponseEmployeeName tsEmp = null;

		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				ArrayList<SavingTimeSheet> listofMonthlyTSDetails = hrmsEmpTimeSheetDao
						.getLeaveStatusByUserId(empEntity.getUserId(), status);

				String employeeName = null;

				if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
					tsEmp = new TSResponseEmployeeName();

					employeeName = empEntity.getEmployeeName();

					tsEmp.setEmployeeName(employeeName);
					tsEmp.setStartDate(listofMonthlyTSDetails.get(0).getMon_date());
					tsEmp.setEndDate(listofMonthlyTSDetails.get(0).getSun_Date());
					tsEmp.setTotalHours(listofMonthlyTSDetails.get(0).getTotalWeekHours());
					tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
					tsEmp.setStatus(status);
					// employeeName =
					// hrmsEmpTimeSheetDao.getEmployeeNameById(empEntity.getUserId());
					tsEmp.setTimeSheetListForEmployee(listofMonthlyTSDetails);

					listOfDetails.add(tsEmp);
				}
			}
		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}

		return Response.ok().entity(tsResp).build();
	}

	public Response getTSDetailsByUserId(int userId, String status) {
		logger.info("entered into getTSDetailsByUserId method of TimeSheetDetails class");

		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();

		TSResponseObj tsResp = new TSResponseObj();

		TSResponseEmployeeName tsEmp = null;

		ArrayList<SavingTimeSheet> listofMonthlyTSDetails = hrmsEmpTimeSheetDao.getLeaveStatusByUserId(userId, status);

		String employeeName = null;

		if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
			tsEmp = new TSResponseEmployeeName();

			employeeName = hrmsEmpTimeSheetDao.getEmployeeNameById(userId);

			tsEmp.setEmployeeName(employeeName);
			tsEmp.setStartDate(listofMonthlyTSDetails.get(0).getMon_date());
			tsEmp.setEndDate(listofMonthlyTSDetails.get(0).getSun_Date());
			tsEmp.setTotalHours(listofMonthlyTSDetails.get(0).getTotalWeekHours());
			tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
			tsEmp.setStatus(status);
			tsEmp.setTimeSheetListForEmployee(listofMonthlyTSDetails);

			listOfDetails.add(tsEmp);
		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}

		return Response.ok().entity(tsResp).build();
	}

	@SuppressWarnings("unused")
	public Response getEmployeesByReportingManagerId(int repId) {
		logger.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of TimeSheetDetails class");

		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);

		EmployeesForReportingManagerResponse tsResp = new EmployeesForReportingManagerResponse();

		List<EmployeeDetailsEntity> empDetailsList = new ArrayList<>();
		;

		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				List<SavingTimeSheet> listofMonthlyTSDetails = hrmsEmpTimeSheetDao
						.getTSDetailsByUserId(empEntity.getUserId());

				String employeeName = null;

				if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
					empDetailsList.add(empEntity);
				}
			}
		}

		if (empDetailsList != null && empDetailsList.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setReportingMangerId(repId);
			tsResp.setEmpDetailsList(empDetailsList);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}
		return Response.ok().entity(tsResp).build();
	}

	public Response saveTimeSheet(SaveTimesheetRequestBean saveBean) {
		logger.info("entered into saveTimeSheet of BusinessClass..");
		CommonResponseBean timeSheetRes = new CommonResponseBean();
		EmployeeWorkStatusEntity empWorkStatus = new EmployeeWorkStatusEntity();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SavingTimeSheet timeSheet = new SavingTimeSheet();
		boolean flag = false;

		for (SavingTimeSheetReqBean sheet : saveBean.getTimeSheetBean()) {
			timeSheet.setEmployeeId(saveBean.getUserId());
			empWorkStatus.setEmpId(saveBean.getUserId());
			empWorkStatus.setRepId(saveBean.getRepId());
			timeSheet.setProjectId(sheet.getProjectId());
            timeSheet.setClientId(saveBean.getClientId());
			timeSheet.setYear(saveBean.getYear());
			empWorkStatus.setYear(saveBean.getYear());
			timeSheet.setMonth(saveBean.getMonth());
			empWorkStatus.setMonth(saveBean.getMonth());
			timeSheet.setWeekNo(saveBean.getWeekNo());

			timeSheet.setMon_hours(Integer.toString(sheet.getMon_hours()));
			timeSheet.setTue_hours(Integer.toString(sheet.getTue_hours()));
			timeSheet.setWed_hours(Integer.toString(sheet.getWed_hours()));
			timeSheet.setThurs_hours(Integer.toString(sheet.getThurs_hours()));
			timeSheet.setFri_hours(Integer.toString(sheet.getFri_hours()));
			timeSheet.setSat_hours(Integer.toString(sheet.getSat_hours()));
			timeSheet.setSun_hours(Integer.toString(sheet.getSun_hours()));
			try {
				timeSheet.setMon_date(new Date(dateFormat.parse(sheet.getMon_date()).getTime()));
				timeSheet.setTue_date(new Date(dateFormat.parse(sheet.getTue_date()).getTime()));
				timeSheet.setWed_Date(new Date(dateFormat.parse(sheet.getWed_Date()).getTime()));
				timeSheet.setThurs_Date(new Date(dateFormat.parse(sheet.getThurs_Date()).getTime()));
				timeSheet.setFri_Date(new Date(dateFormat.parse(sheet.getFri_Date()).getTime()));
				timeSheet.setSat_Date(new Date(dateFormat.parse(sheet.getSat_Date()).getTime()));
				timeSheet.setSun_Date(new Date(dateFormat.parse(sheet.getSun_Date()).getTime()));
			} catch (Exception e) {
				// System.out.println("Something Went Wrong." + e);
				flag = false;
				break;
			}
			timeSheet.setStatus(saveBean.getStatus());
			empWorkStatus.setStatus(saveBean.getStatus());
			timeSheet.setCal_week(saveBean.getYearWeek());
			empWorkStatus.setCalweek(saveBean.getYearWeek());
			timeSheet.setTotalWeekHours(Double.toString(saveBean.getTotalHrs()));
			timeSheet.setCreatedBy(saveBean.getCreatedOrModifiedBy());
			timeSheet.setCreated_Date(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			timeSheet.setIsActive((short) 1);
			Task tmTask = new Task();
			tmTask.setTaskName(sheet.getProjectTaskName());
			ProjectTaskEntity projTask = new ProjectTaskEntity();
			projTask.setProjectId(sheet.getProjectId());
			if (sheet.getId() == 0) {
				// timeSheet.setProjectTaskId(hrmsEmpTimeSheetDao.saveProjectTask());
				timeSheet.setSaveId(0);
				timeSheet.setModifiedBy(saveBean.getCreatedOrModifiedBy());
				timeSheet.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				flag = hrmsEmpTimeSheetDao.saveTimeSheet(timeSheet, tmTask, projTask, empWorkStatus);
			} else {
				timeSheet.setSaveId(sheet.getId());
				timeSheet.setModifiedBy(saveBean.getCreatedOrModifiedBy());
				timeSheet.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				flag = hrmsEmpTimeSheetDao.updateTimeSheet(timeSheet, tmTask, projTask, empWorkStatus);
			}
			// System.out.println(flag);
		}
		if (flag) {
			if (saveBean.getStatus().equals("saved"))
				timeSheetRes.setMessage("Saved Successfully");
			else
				timeSheetRes.setMessage("Submitted Successfully");
			timeSheetRes.setStatus(true);
		} else {
			timeSheetRes.setMessage("Saving Failed");
			timeSheetRes.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(timeSheetRes).build();
	}

	private final TimeSheetResponseBean responseTimeSheet = new TimeSheetResponseBean();

	public Response getTimeSheetDetails(int userId, int year, int month, int week) {
		List<SavingTimeSheet> list = hrmsEmpTimeSheetDao.getTimeSheetDetails(userId, year, month, week);
		if (!list.isEmpty()) {
			responseTimeSheet.setMessage("Timesheet Details Fetched Successfully.");
			responseTimeSheet.setStatus(true);
			responseTimeSheet.setTimeSheetDetails(list);
			responseTimeSheet.setWeeklyStatus(list.get(0).getStatus());
			responseTimeSheet.setStatus1(list.get(0).getStatus());
		} else {
			responseTimeSheet.setMessage("Timesheet Details Not Found.");
			responseTimeSheet.setStatus(false);
			responseTimeSheet.setTimeSheetDetails(list);

		}
		Calendar cal = Calendar.getInstance();
		cal.setWeekDate(year, week, 2);
		java.util.Date date = cal.getTime();
		HashMap<?, ?> DateHM = new CurrentWeekDates().getDateDetails(date);
		responseTimeSheet.setMonth(month);
		responseTimeSheet.setYear(year);
		responseTimeSheet.setWeek(week);
		responseTimeSheet.setTimeSheetDetails(list);
		responseTimeSheet.setCurrentWeek(DateHM.get("currentWeek").toString());
		responseTimeSheet.setPreviousWeek(DateHM.get("previousWeek").toString());
		responseTimeSheet.setNextWeek(DateHM.get("nextWeek").toString());
		return Response.ok().entity(responseTimeSheet).build();
	}

	public Response getTimeSheetDetailsByUserId(int userId) {
		List<Object[]> listofMonthlyTSDetails = hrmsEmpTimeSheetDao.getEmplTimeSheetDetailsByReportingManagerId(userId,
				"submitted");

		ArrayList<TimeSheetSummary> timeSheetList = new ArrayList<TimeSheetSummary>();

		TimeSheetResp timeSheetResponse = new TimeSheetResp();

		TimeSheetSummary timeSheet = null;

		if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
			for (Object[] row : listofMonthlyTSDetails) {
				timeSheet = new TimeSheetSummary();
				timeSheet.setEmployeeId(userId);
				timeSheet.setEmployeeFullName(objToString(row[13]));
				timeSheet.setReportingManagerName(objToString(row[14]));
				timeSheet.setMondayHrs(objToFloat(row[0]));
				timeSheet.setTuesdayHrs(objToFloat(row[1]));
				timeSheet.setWednesdayHrs(objToFloat(row[2]));
				timeSheet.setThursdayHrs(objToFloat(row[3]));
				timeSheet.setFridayHrs(objToFloat(row[4]));
				timeSheet.setSaturdayHrs(objToFloat(row[5]));
				timeSheet.setSundayHrs(objToFloat(row[6]));
				timeSheet.setWeekNo(objToInteger(row[9]));
				timeSheet.setFromDate(objToString(row[10]));
				timeSheet.setToDate(objToString(row[11]));
				timeSheet.setTotalHours(objToFloat(row[12]));
				timeSheet.setStatus(objToString(row[15]));
				// timeSheet.setRejectReason(objToString(row[17]));

				timeSheetList.add(timeSheet);
			}

			timeSheetResponse.setTimeSheetList(timeSheetList);
			timeSheetResponse.setMessage("TimeSheet Details retrieved successfully");
			timeSheetResponse.setStatus(true);
		}

		return Response.ok().entity(timeSheetResponse).build();

		/*
		 * List<SavingTimeSheet> list =
		 * hrmsEmpTimeSheetDao.getTimeSheetDetailsByUserId(userId );
		 * 
		 * TimeSheetResponseEmp tsEmp = new TimeSheetResponseEmp();
		 * 
		 * if (!list.isEmpty() ) {
		 * tsEmp.setMessage("Timesheet Details Fetched Successfully.");
		 * tsEmp.setStatus(true); tsEmp.setTimeSheetDetails(list); } else {
		 * tsEmp.setMessage("Timesheet Details Not Found."); tsEmp.setStatus(false);
		 * tsEmp.setTimeSheetDetails(list); }
		 * 
		 */

	}

	/*
	 * public Response getEmplTimeSheetDetailsByReportingManagerId(int repId) {
	 * logger.
	 * info("entered into getEmplTimeSheetDetailsByReportingManagerId Method of TimeSheetDetails BusinessClass.."
	 * );
	 * 
	 * List<SavingTimeSheet> timeSheetList =
	 * hrmsEmpTimeSheetDao.getEmplTimeSheetDetailsByReportingManagerId(repId);
	 * 
	 * String reportingManagerName =
	 * hrmsEmpTimeSheetDao.getReportingManagerNameById(repId);
	 * 
	 * TimeSheetResponseForReportingManager timeSheetResponse = new
	 * TimeSheetResponseForReportingManager();
	 * 
	 * if (timeSheetList != null && !timeSheetList.isEmpty()) {
	 * timeSheetResponse.setMessage("Timesheet Details Fetched Successfully.");
	 * timeSheetResponse.setStatus(true);
	 * timeSheetResponse.setReportingMangerId(repId);
	 * timeSheetResponse.setReportingManagerName(reportingManagerName);
	 * timeSheetResponse.setTimeSheetList(timeSheetList); } else {
	 * timeSheetResponse.setReportingMangerId(repId); timeSheetResponse.
	 * setMessage("Timesheet Details Not Found for Employees Under ReportingManager : "
	 * +reportingManagerName); timeSheetResponse.setStatus(false); }
	 * 
	 * return Response.ok().entity(timeSheetResponse).build(); }
	 */

	public Response timeSheetApproval(TimeSheetApprovalEntity timeSheetApprovalEntity) {
		logger.info("entered into timeSheetApproval Method of TimeSheetDetails BusinessClass..");

		boolean updatedApprovalFlag = hrmsEmpTimeSheetDao.timeSheetApproval(timeSheetApprovalEntity);

		TimeSheetApprovalResponse timeSheetResponse = new TimeSheetApprovalResponse();

		if (updatedApprovalFlag) {
			timeSheetResponse.setStatus(true);
			timeSheetResponse.setMessage("TimeSheetApproval Success");
		} else {
			timeSheetResponse.setStatus(false);
			timeSheetResponse.setMessage("TimeSheetApproval Failed");
		}

		return Response.ok().entity(timeSheetResponse).build();

	}

	public Response getEmpUserListByReportingManagerId(int repId) {
		logger.info("entered into getReportingManagerNameById Method of TimeSheetDetails BusinessClass..");

		List<EmployeeDetailsEntity> empDetailsList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);

		EmployeesForReportingManagerResponse empListResponse = new EmployeesForReportingManagerResponse();

		if (empDetailsList != null && !empDetailsList.isEmpty()) {
			empListResponse.setMessage("Timesheet Details Fetched Successfully.");
			empListResponse.setStatus(true);
			empListResponse.setReportingMangerId(repId);
			empListResponse.setEmpDetailsList(empDetailsList);
		} else {
			empListResponse.setReportingMangerId(repId);
			empListResponse.setMessage("Timesheet Details Not Found for Employees Under ReportingManager : " + repId);
			empListResponse.setStatus(false);
		}

		return Response.ok().entity(empListResponse).build();
	}

	public CurrentWeekDatesResponse getWeekDates(java.util.Date date) {
		HashMap<?, ?> DateHM = new CurrentWeekDates().getDateDetails(date);
		CurrentWeekDatesResponse currentWeekDatesResponse = new CurrentWeekDatesResponse();
		currentWeekDatesResponse.setCurrentWeek(DateHM.get("currentWeek").toString());
		currentWeekDatesResponse.setNextWeek(DateHM.get("nextWeek").toString());
		currentWeekDatesResponse.setPreviousWeek(DateHM.get("previousWeek").toString());
		currentWeekDatesResponse.setMonDate((String) DateHM.get("monday"));
		currentWeekDatesResponse.setTueDate((String) DateHM.get("tuesday"));
		currentWeekDatesResponse.setWedDate((String) DateHM.get("wednesday"));
		currentWeekDatesResponse.setThuDate((String) DateHM.get("thursday"));
		currentWeekDatesResponse.setFriDate((String) DateHM.get("friday"));
		currentWeekDatesResponse.setSatDate((String) DateHM.get("saturday"));
		currentWeekDatesResponse.setSunDate((String) DateHM.get("sunday"));
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		currentWeekDatesResponse.setCalweek(calendar.get(Calendar.WEEK_OF_YEAR));
		currentWeekDatesResponse.setYear(Integer.parseInt((String) DateHM.get("year")));
		currentWeekDatesResponse.setMonth(Integer.parseInt((String) DateHM.get("month")));
		currentWeekDatesResponse.setMonthlyWeek(calendar.get(Calendar.WEEK_OF_MONTH));
		return currentWeekDatesResponse;
	}

	/*
	 * Converts Object DataType to String Input - Object returns String If Null
	 * returns empty string
	 */
	private String objToString(Object obj) {

		if (obj != null)
			return obj.toString();

		return "";
	}

	/*
	 * Converts Object DataType to Integer Input - Object returns Integer If Null
	 * returns 0
	 */
	private float objToFloat(Object obj) {

		if (obj != null)
			return Float.parseFloat(obj.toString());

		return 0;
	}

	/*
	 * Converts Object DataType to Integer Input - Object returns Integer If Null
	 * returns 0
	 */
	private int objToInteger(Object obj) {

		// // System.out.println(obj);
		if (obj != null)
			return Integer.parseInt(obj.toString());

		return 0;
	}

	// Get All ReportingManagerName And Id Through This Service

	public Response getAllReportingManagerIdName() {
		logger.info("Entered Into getAllReportingManagerIdName()");
		EmployeesForReportingManagerResponse responseReportingManagerNameIdList = new EmployeesForReportingManagerResponse();
		List<EmployeeDetailsEntity> getReportingManagerNameIdList = hrmsEmpTimeSheetDao.getAllReportingManagerNameId();
		if (getReportingManagerNameIdList != null) {
			responseReportingManagerNameIdList.setMessage("Retrived ReportingManger Id And Name SuccessFully");
			responseReportingManagerNameIdList.setStatus(true);
			responseReportingManagerNameIdList.setEmpDetailsList(getReportingManagerNameIdList);
			return Response.ok().entity(responseReportingManagerNameIdList).build();
		} else {
			responseReportingManagerNameIdList.setMessage("Retrived ReportingManger Id And Name UnSuccessFully");
			responseReportingManagerNameIdList.setStatus(true);
			responseReportingManagerNameIdList.setEmpDetailsList(getReportingManagerNameIdList);
			return Response.ok().entity(responseReportingManagerNameIdList).build();
		}

	}

	// Get EmployeeDetails With Status By Passing Reporting ManagerId and CalWeek
	@SuppressWarnings("deprecation")
	public Response getEmplDetailsByReportingManagerId(int repId, short calWeek) {
		logger.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of TimeSheetDetails class");

		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();
		TSResponseObj tsResp = new TSResponseObj();
		TSResponseEmployeeName tsEmp = null;
		String queryForMonTotalHours = null;
		String queryForTueTotalHours = null;
		String queryForWedTotalHours = null;
		String queryForThrTotalHours = null;
		String queryForFriTotalHours = null;
		String queryForSatTotalHours = null;
		String queryForSunTotalHours = null;
		String queryForMonthName = null;
		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				ArrayList<SavingTimeSheet> listofMonthlyTSDetails = hrmsEmpTimeSheetDao
						.getLeaveStatusByUserId(empEntity.getUserId(), calWeek);

				String employeeName = null;
				int employeeId = 0;

				if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
					employeeName = empEntity.getEmployeeName();
					employeeId = empEntity.getUserId();
					tsEmp = new TSResponseEmployeeName();
					queryForMonTotalHours = "SELECT sum(mon_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + "";
					queryForTueTotalHours = "SELECT sum(tue_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + "";
					queryForWedTotalHours = "SELECT sum(wed_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + "";
					queryForThrTotalHours = "SELECT sum(thu_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + "";
					queryForFriTotalHours = "SELECT sum(fri_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + "";
					queryForSatTotalHours = "SELECT sum(sat_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + "";
					queryForSunTotalHours = "SELECT sum(sun_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + "";
					queryForMonthName = "select distinct(month_name) from timeSheet_add_calWeek_month where month_id="
							+ listofMonthlyTSDetails.get(0).getMonth() + "";
					String monthName = (String) DbConnect.DbCon().createSQLQuery(queryForMonthName).uniqueResult();
					Double monTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForMonTotalHours)
							.uniqueResult();
					Double tueTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForTueTotalHours)
							.uniqueResult();
					Double wedTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForWedTotalHours)
							.uniqueResult();
					Double thrTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForThrTotalHours)
							.uniqueResult();
					Double friTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForFriTotalHours)
							.uniqueResult();
					Double satTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSatTotalHours)
							.uniqueResult();
					Double sunTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSunTotalHours)
							.uniqueResult();
					Double totalHours = monTotalHours + tueTotalHours + wedTotalHours + thrTotalHours + friTotalHours
							+ satTotalHours + sunTotalHours;
					tsEmp.setUserId(employeeId);
					tsEmp.setEmployeeName(employeeName);
					tsEmp.setStartDate((Date) DbConnect.DbCon()
							.createSQLQuery("SELECT max(sun_date) FROM tm_emp_timesheets Where emp_id=" + employeeId
									+ " and cal_week=" + calWeek + " ")
							.uniqueResult());
					tsEmp.setEndDate((Date) DbConnect.DbCon()
							.createSQLQuery("SELECT max(sat_date) FROM tm_emp_timesheets Where emp_id=" + employeeId
									+ " and cal_week=" + calWeek + "")
							.uniqueResult());
					tsEmp.setRepManId(repId);
					tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
					tsEmp.setCalWeek(listofMonthlyTSDetails.get(0).getCal_week());
					tsEmp.setMonDate(listofMonthlyTSDetails.get(0).getMon_date());
					tsEmp.setMonTotalHours(monTotalHours);
					tsEmp.setMonday(listofMonthlyTSDetails.get(0).getMon_date().getDate());
					tsEmp.setTueDate(listofMonthlyTSDetails.get(0).getTue_date());
					tsEmp.setTueTotalHours(tueTotalHours);
					tsEmp.setTuesday(listofMonthlyTSDetails.get(0).getTue_date().getDate());
					tsEmp.setWedDate(listofMonthlyTSDetails.get(0).getWed_Date());
					tsEmp.setWedTotalHours(wedTotalHours);
					tsEmp.setWednesday(listofMonthlyTSDetails.get(0).getWed_Date().getDate());
					tsEmp.setThursDate(listofMonthlyTSDetails.get(0).getThurs_Date());
					tsEmp.setThursTotalHours(thrTotalHours);
					tsEmp.setThrusday(listofMonthlyTSDetails.get(0).getThurs_Date().getDate());
					tsEmp.setFriDate(listofMonthlyTSDetails.get(0).getFri_Date());
					tsEmp.setFriTotalHours(friTotalHours);
					tsEmp.setFriday(listofMonthlyTSDetails.get(0).getFri_Date().getDate());
					tsEmp.setSatDate(listofMonthlyTSDetails.get(0).getSat_Date());
					tsEmp.setSatTotalHours(satTotalHours);
					tsEmp.setSaturday(listofMonthlyTSDetails.get(0).getSat_Date().getDate());
					tsEmp.setSunDate(listofMonthlyTSDetails.get(0).getSun_Date());
					tsEmp.setSunTotalHours(sunTotalHours);
					tsEmp.setSunday(listofMonthlyTSDetails.get(0).getSun_Date().getDate());
					tsEmp.setStatus(listofMonthlyTSDetails.get(0).getStatus());
					tsEmp.setTotalHour(totalHours);
					tsEmp.setYear(listofMonthlyTSDetails.get(0).getYear());
					tsEmp.setMonthName(monthName);
					tsEmp.setMonth(listofMonthlyTSDetails.get(0).getMonth());
					tsEmp.setTimeSheetListForEmployee(listofMonthlyTSDetails);
					listOfDetails.add(tsEmp);
				}
			}
		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}

		return Response.ok().entity(tsResp).build();
	}

	// Get Employee And Along With Working Details Of Employee Based On
	// ReportingManagerId Status calWeek And Year
	@SuppressWarnings("deprecation")
	public Response getEmployeeWorkingReportsByUserIdStatusCalweekYear(int repId, String status, short calWeek,
			int year) {
		logger.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of TimeSheetDetails class");

		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);

		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();

		TSResponseObj tsResp = new TSResponseObj();
		String queryForMonTotalHours = null;
		String queryForTueTotalHours = null;
		String queryForWedTotalHours = null;
		String queryForThrTotalHours = null;
		String queryForFriTotalHours = null;
		String queryForSatTotalHours = null;
		String queryForSunTotalHours = null;
		TSResponseEmployeeName tsEmp = null;

		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				ArrayList<SavingTimeSheet> listofMonthlyTSDetails = hrmsEmpTimeSheetDao
						.getEmployeeWorkingReportsByUserIdStatusCalweekYear(empEntity.getUserId(), status, calWeek,
								year);

				String employeeName = null;
				int employeeId = 0;
				if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
					employeeName = empEntity.getEmployeeName();
					employeeId = empEntity.getUserId();
					tsEmp = new TSResponseEmployeeName();
					queryForMonTotalHours = "SELECT sum(mon_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + " and ts_year=" + year + "";
					queryForTueTotalHours = "SELECT sum(tue_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + " and ts_year=" + year + "";
					queryForWedTotalHours = "SELECT sum(wed_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + " and ts_year=" + year + "";
					queryForThrTotalHours = "SELECT sum(thu_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + " and ts_year=" + year + "";
					queryForFriTotalHours = "SELECT sum(fri_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + " and ts_year=" + year + "";
					queryForSatTotalHours = "SELECT sum(sat_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + " and ts_year=" + year + "";
					queryForSunTotalHours = "SELECT sum(sun_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and cal_week=" + calWeek + " and ts_year=" + year + "";
					Double monTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForMonTotalHours)
							.uniqueResult();
					Double tueTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForTueTotalHours)
							.uniqueResult();
					Double wedTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForWedTotalHours)
							.uniqueResult();
					Double thrTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForThrTotalHours)
							.uniqueResult();
					Double friTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForFriTotalHours)
							.uniqueResult();
					Double satTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSatTotalHours)
							.uniqueResult();
					Double sunTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSunTotalHours)
							.uniqueResult();
					Double totalHours = monTotalHours + tueTotalHours + wedTotalHours + thrTotalHours + friTotalHours
							+ satTotalHours + sunTotalHours;
					tsEmp.setUserId(employeeId);
					tsEmp.setEmployeeName(employeeName);
					tsEmp.setStartDate((Date) DbConnect
							.DbCon().createSQLQuery("SELECT max(sun_date) FROM tm_emp_timesheets Where emp_id="
									+ employeeId + " and cal_week=" + calWeek + " and ts_year=" + year + " ")
							.uniqueResult());
					tsEmp.setEndDate((Date) DbConnect
							.DbCon().createSQLQuery("SELECT max(sat_date) FROM tm_emp_timesheets Where emp_id="
									+ employeeId + " and cal_week=" + calWeek + " and ts_year=" + year + "")
							.uniqueResult());
					tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
					tsEmp.setCalWeek(listofMonthlyTSDetails.get(0).getCal_week());
					tsEmp.setMonDate(listofMonthlyTSDetails.get(0).getMon_date());
					tsEmp.setMonTotalHours(monTotalHours);
					tsEmp.setTueDate(listofMonthlyTSDetails.get(0).getTue_date());
					tsEmp.setTueTotalHours(tueTotalHours);
					tsEmp.setWedDate(listofMonthlyTSDetails.get(0).getWed_Date());
					tsEmp.setWedTotalHours(wedTotalHours);
					tsEmp.setThursDate(listofMonthlyTSDetails.get(0).getThurs_Date());
					tsEmp.setThursTotalHours(thrTotalHours);
					tsEmp.setFriDate(listofMonthlyTSDetails.get(0).getFri_Date());
					tsEmp.setFriTotalHours(friTotalHours);
					tsEmp.setSatDate(listofMonthlyTSDetails.get(0).getSat_Date());
					tsEmp.setSatTotalHours(satTotalHours);
					tsEmp.setSunDate(listofMonthlyTSDetails.get(0).getSun_Date());
					tsEmp.setSunTotalHours(sunTotalHours);
					tsEmp.setStatus(listofMonthlyTSDetails.get(0).getStatus());
					tsEmp.setTotalHour(totalHours);
					tsEmp.setTimeSheetListForEmployee(listofMonthlyTSDetails);
					listOfDetails.add(tsEmp);
				}
			}
		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}

		return Response.ok().entity(tsResp).build();
	}

	// Get All Status Count Based On ReportingManagerId calWeek And Year
	public Response getStatusEmployeeStatusInTimeSheet(int repId, short calWeek) {
		TSResponseObj tsResp = new TSResponseObj();
		TimeSheetStatusCountResponse countRes = null;
		TimeSheetStatusCountResponse getStatusCount = hrmsEmpTimeSheetDao.getStatusCount(repId, calWeek);
		if (getStatusCount != null) {
			countRes = new TimeSheetStatusCountResponse();
			countRes.setAll(getStatusCount.getAll());
			countRes.setApproved(getStatusCount.getApproved());
			countRes.setRejected(getStatusCount.getRejected());
			countRes.setSaved(getStatusCount.getSaved());
			countRes.setSubmitted(getStatusCount.getSubmitted());
			countRes.setCalWeek(calWeek);

		}

		if (countRes != null) {
			tsResp.setMessage("TimeSheet Status Count are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetCountResponse(countRes);
		} else {
			tsResp.setMessage("TimeSheet  Status Count are not found");
			tsResp.setStatus(false);
		}
		return Response.ok().entity(tsResp).build();
	}

	
	public Response	getStatusCountInTimeSheetOfEmpBasedOnYear (int repId, int year,int empId) {
		TSResponseObj tsResp = new TSResponseObj();
		TimeSheetStatusCountResponse countRes = null;
		TimeSheetStatusCountResponse getStatusCount = hrmsEmpTimeSheetDao.getStatusCountBasedOnYear(repId, year,empId);
		if (getStatusCount != null) {
			countRes = new TimeSheetStatusCountResponse();
			countRes.setAll(getStatusCount.getAll());
			countRes.setApproved(getStatusCount.getApproved());
			countRes.setRejected(getStatusCount.getRejected());
			countRes.setSaved(getStatusCount.getSaved());
			countRes.setSubmitted(getStatusCount.getSubmitted());
			countRes.setYear(year);
		}

		if (countRes != null) {
			tsResp.setMessage("TimeSheet Status Count are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetCountResponse(countRes);
		} else {
			tsResp.setMessage("TimeSheet  Status Count are not found");
			tsResp.setStatus(false);
		}
		return Response.ok().entity(tsResp).build();
	}
	public Response	getStatusCountInTimeSheetOfEmpBasedOnYearMonthEmpId (int repId, int year,int empId,short month) {
		TSResponseObj tsResp = new TSResponseObj();
		TimeSheetStatusCountResponse countRes = null;
		TimeSheetStatusCountResponse getStatusCount = hrmsEmpTimeSheetDao.getStatusCountBasedOnYearMonth(repId, year,empId,month);
		if (getStatusCount != null) {
			countRes = new TimeSheetStatusCountResponse();
			countRes.setAll(getStatusCount.getAll());
			countRes.setApproved(getStatusCount.getApproved());
			countRes.setRejected(getStatusCount.getRejected());
			countRes.setSaved(getStatusCount.getSaved());
			countRes.setSubmitted(getStatusCount.getSubmitted());
			countRes.setYear(year);
		}

		if (countRes != null) {
			tsResp.setMessage("TimeSheet Status Count are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetCountResponse(countRes);
		} else {
			tsResp.setMessage("TimeSheet  Status Count are not found");
			tsResp.setStatus(false);
		}
		return Response.ok().entity(tsResp).build();
	}
	public Response	getStatusCount(int repId, int year,int empId,short month) {
		TSResponseObj tsResp = new TSResponseObj();
		TimeSheetStatusCountResponse countRes = null;
		TimeSheetStatusCountResponse getStatusCount = hrmsEmpTimeSheetDao.getStatusCount(repId, year,empId, month);
		if (getStatusCount != null) {
			countRes = new TimeSheetStatusCountResponse();
			countRes.setAll(getStatusCount.getAll());
			countRes.setApproved(getStatusCount.getApproved());
			countRes.setRejected(getStatusCount.getRejected());
			countRes.setSaved(getStatusCount.getSaved());
			countRes.setSubmitted(getStatusCount.getSubmitted());
			countRes.setYear(year);
		}

		if (countRes != null) {
			tsResp.setMessage("TimeSheet Status Count are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetCountResponse(countRes);
		} else {
			tsResp.setMessage("TimeSheet  Status Count are not found");
			tsResp.setStatus(false);
		}
		return Response.ok().entity(tsResp).build();
	}

	public Response	getStatusCountByrepIdYearEmpIdMonthStatus(int repId,int empId ,int year,short month,String status) {
		TSResponseObj tsResp = new TSResponseObj();
		TimeSheetStatusCountResponse getStatusCount = hrmsEmpTimeSheetDao.getStatusCountByrepIdYearEmpIdMonthStatus(repId,empId,year,month,status );
		
		if (getStatusCount != null) {
			tsResp.setMessage("TimeSheet Status Count are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetCountResponse(getStatusCount);
		} else {
			tsResp.setMessage("TimeSheet  Status Count are not found");
			tsResp.setStatus(false);
		}
		return Response.ok().entity(tsResp).build();
	}
	@SuppressWarnings("deprecation")
	public Response getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus(int repId, int empId, int year, short month,
			short calWeek, String status) {
		logger.info("entered into getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus method of TimeSheetDetails class");

		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();
		TSResponseObj tsResp = new TSResponseObj();
		TSResponseEmployeeName tsEmp = null;
		String queryForMonTotalHours = null;
		String queryForTueTotalHours = null;
		String queryForWedTotalHours = null;
		String queryForThrTotalHours = null;
		String queryForFriTotalHours = null;
		String queryForSatTotalHours = null;
		String queryForSunTotalHours = null;
		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				if (empEntity.getUserId().equals(empId)) {
					ArrayList<SavingTimeSheet> listofMonthlyTSDetails = hrmsEmpTimeSheetDao
							.getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus(empId, year, month, calWeek, status);

					String employeeName = null;
					int employeeId = 0;

					if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
						employeeName = empEntity.getEmployeeName();
						employeeId = empEntity.getUserId();
						tsEmp = new TSResponseEmployeeName();
						queryForMonTotalHours = "SELECT sum(mon_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForTueTotalHours = "SELECT sum(tue_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForWedTotalHours = "SELECT sum(wed_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForThrTotalHours = "SELECT sum(thu_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForFriTotalHours = "SELECT sum(fri_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForSatTotalHours = "SELECT sum(sat_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForSunTotalHours = "SELECT sum(sun_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						Double monTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForMonTotalHours)
								.uniqueResult();
						Double tueTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForTueTotalHours)
								.uniqueResult();
						Double wedTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForWedTotalHours)
								.uniqueResult();
						Double thrTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForThrTotalHours)
								.uniqueResult();
						Double friTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForFriTotalHours)
								.uniqueResult();
						Double satTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSatTotalHours)
								.uniqueResult();
						Double sunTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSunTotalHours)
								.uniqueResult();
						Double totalHours = monTotalHours + tueTotalHours + wedTotalHours + thrTotalHours
								+ friTotalHours + satTotalHours + sunTotalHours;
						tsEmp.setUserId(employeeId);
						tsEmp.setEmployeeName(employeeName);
						tsEmp.setStartDate((Date) DbConnect.DbCon()
								.createSQLQuery("SELECT max(sun_date) FROM tm_emp_timesheets Where emp_id=" + employeeId
										+ " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year=" + year
										+ "")
								.uniqueResult());
						tsEmp.setEndDate((Date) DbConnect.DbCon()
								.createSQLQuery("SELECT max(sat_date) FROM tm_emp_timesheets Where emp_id=" + employeeId
										+ " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year=" + year
										+ "")
								.uniqueResult());
						tsEmp.setRepManId(repId);
						tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
						tsEmp.setCalWeek(listofMonthlyTSDetails.get(0).getCal_week());
						tsEmp.setMonDate(listofMonthlyTSDetails.get(0).getMon_date());
						tsEmp.setMonTotalHours(monTotalHours);
						tsEmp.setTueDate(listofMonthlyTSDetails.get(0).getTue_date());
						tsEmp.setTueTotalHours(tueTotalHours);
						tsEmp.setWedDate(listofMonthlyTSDetails.get(0).getWed_Date());
						tsEmp.setWedTotalHours(wedTotalHours);
						tsEmp.setThursDate(listofMonthlyTSDetails.get(0).getThurs_Date());
						tsEmp.setThursTotalHours(thrTotalHours);
						tsEmp.setFriDate(listofMonthlyTSDetails.get(0).getFri_Date());
						tsEmp.setFriTotalHours(friTotalHours);
						tsEmp.setSatDate(listofMonthlyTSDetails.get(0).getSat_Date());
						tsEmp.setSatTotalHours(satTotalHours);
						tsEmp.setSunDate(listofMonthlyTSDetails.get(0).getSun_Date());
						tsEmp.setSunTotalHours(sunTotalHours);
						tsEmp.setStatus(listofMonthlyTSDetails.get(0).getStatus());
						tsEmp.setTotalHour(totalHours);
						tsEmp.setTimeSheetListForEmployee(listofMonthlyTSDetails);
						listOfDetails.add(tsEmp);
					}
				}
			}
		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}

		return Response.ok().entity(tsResp).build();
	}

	
	@SuppressWarnings("deprecation")
	public Response getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus(int repId, int empId, int year, short month,
			 String status1) {
		logger.info("entered into getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus method of TimeSheetDetails class");
		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		logger.info("entered into getMonthlyTimeSheetDetails method of TimeSheetDetails class");
		TimeSheetResponse timeSheetResponse = new TimeSheetResponse();
		String queryForMonthName = null;

		ArrayList<TimeSheetsMonthlyBean> timeSheetList = new ArrayList<TimeSheetsMonthlyBean>();
		for (EmployeeDetailsEntity employeeId : empUserList) {
			if (employeeId.getUserId().equals(empId)) {
				HashMap<String, Object> MonthlyTSHM = hrmsEmpTimeSheetDao.getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus1(empId,year, month,status1);
				List<SavingTimeSheet> lisofTimeSheet = hrmsEmpTimeSheetDao
						.getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus(empId, year, month,status1);
				boolean status = (boolean) MonthlyTSHM.get("status");

				if (status) {
					@SuppressWarnings("unchecked")
					List<Object[]> listofMonthlyTSDetails = (List<Object[]>) MonthlyTSHM.get("monthlyList");
					if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
						for (Object[] row : listofMonthlyTSDetails) {
							TimeSheetsMonthlyBean timeSheet = new TimeSheetsMonthlyBean();

							timeSheet.setUserId(empId);
							queryForMonthName = "select distinct(month_name) from timeSheet_add_calWeek_month where month_id="
									+ month + "";
							String monthName = (String) DbConnect.DbCon().createSQLQuery(queryForMonthName)
									.uniqueResult();
							timeSheet.setMonTotalHours(objToFloat(row[0]));
							timeSheet.setTueTotalHours(objToFloat(row[1]));
							timeSheet.setWedTotalHours(objToFloat(row[2]));
							timeSheet.setThursTotalHours(objToFloat(row[3]));
						    timeSheet.setFriTotalHours(objToFloat(row[4]));
							timeSheet.setSatTotalHours(objToFloat(row[5]));
							timeSheet.setSunTotalHours(objToFloat(row[6]));
						    timeSheet.setMonth(objToInteger(row[7]));
							timeSheet.setYear(objToInteger(row[8]));
							timeSheet.setCalWeek(objToInteger(row[9]));
							timeSheet.setStartDate(objToString(row[10]));
							timeSheet.setEndDate(objToString(row[11]));
							timeSheet.setMonDate(((Date)row[13]));
							timeSheet.setTueDate(((Date)row[14]));
							timeSheet.setWedDate(((Date)row[15]));
							timeSheet.setThursDate(((Date)row[16]));
							timeSheet.setFriDate(((Date)row[17]));
							timeSheet.setSatDate(((Date)row[18]));
							timeSheet.setTotalHour(objToFloat(row[19]));
							timeSheet.setEmployeeName(objToString(row[20]));
							timeSheet.setReportingManagerName(objToString(row[21]));
							timeSheet.setStatus(objToString(row[22]));
							int timeSheetId = objToInteger(row[23]);
							timeSheet.setSunDate(((Date)row[12]));
							timeSheet.setSunday(timeSheet.getSunDate().getDate());
							timeSheet.setMonday(timeSheet.getMonDate().getDate());
							timeSheet.setTuesday(timeSheet.getTueDate().getDate());
							timeSheet.setWednesday(timeSheet.getWedDate().getDate());
							timeSheet.setThrusday(timeSheet.getThursDate().getDate());
							timeSheet.setFriday(timeSheet.getFriDate().getDate());
							timeSheet.setSaturday(timeSheet.getSatDate().getDate());
							timeSheet.setRepManId(repId);
							timeSheet.setMonthName(monthName);
							timeSheet.setTimeSheetListForEmployee(lisofTimeSheet);
							TimeSheetApprovalStatusEntity tsApp = hrmsEmpTimeSheetDao
									.getApprovalEntityByTimeSheetId(timeSheetId);

							if (tsApp != null) {
								timeSheet.setApproverName(tsApp.getApproverName());
								timeSheet.setRejectReason(tsApp.getRejectReason());
							}

							timeSheetList.add(timeSheet);

						
						}
						timeSheetResponse.setMessage("TimeSheet Details are retrieved successfully");
						timeSheetResponse.setStatus(true);

					} else {
						timeSheetResponse.setMessage("TimeSheet Details are not found");
						timeSheetResponse.setStatus(false);
					}
				} else {
					timeSheetResponse.setMessage("Error in fetching details" + MonthlyTSHM.get("message"));
					timeSheetResponse.setStatus(false);
				}
			}
		}
		timeSheetResponse.setTimeSheetListForRM(timeSheetList);

		return Response.ok().entity(timeSheetResponse).build();
	}
	@SuppressWarnings("deprecation")
	public Response getEmployeeDetailBasedOnRepIdEmpIdYearMonth(int repId, int empId, int year, short month) {
		logger.info("entered into getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus method of TimeSheetDetails class");
		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		logger.info("entered into getMonthlyTimeSheetDetails method of TimeSheetDetails class");
		TimeSheetResponse timeSheetResponse = new TimeSheetResponse();
		String queryForMonthName = null;

		ArrayList<TimeSheetsMonthlyBean> timeSheetList = new ArrayList<TimeSheetsMonthlyBean>();
		for (EmployeeDetailsEntity employeeId : empUserList) {
			if (employeeId.getUserId().equals(empId)) {
				HashMap<String, Object> MonthlyTSHM = hrmsEmpTimeSheetDao.getEmplTimeSheetDetails(year, month, empId);
				List<SavingTimeSheet> lisofTimeSheet = hrmsEmpTimeSheetDao
						.getEmployeeDetailBasedOnRepIdEmpIdYearMonth(empId, year, month);
				boolean status = (boolean) MonthlyTSHM.get("status");

				if (status) {
					@SuppressWarnings("unchecked")
					List<Object[]> listofMonthlyTSDetails = (List<Object[]>) MonthlyTSHM.get("monthlyList");
					if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
						for (Object[] row : listofMonthlyTSDetails) {
							TimeSheetsMonthlyBean timeSheet = new TimeSheetsMonthlyBean();

							timeSheet.setUserId(empId);
							queryForMonthName = "select distinct(month_name) from timeSheet_add_calWeek_month where month_id="
									+ month + "";
							String monthName = (String) DbConnect.DbCon().createSQLQuery(queryForMonthName)
									.uniqueResult();
							timeSheet.setMonTotalHours(objToFloat(row[0]));
							timeSheet.setMonDate(lisofTimeSheet.get(0).getMon_date());
							timeSheet.setTueTotalHours(objToFloat(row[1]));
							timeSheet.setTueDate(lisofTimeSheet.get(0).getTue_date());
							timeSheet.setWedTotalHours(objToFloat(row[2]));
							timeSheet.setWedDate(lisofTimeSheet.get(0).getWed_Date());
							timeSheet.setThursTotalHours(objToFloat(row[3]));
							timeSheet.setThursDate(lisofTimeSheet.get(0).getThurs_Date());
							timeSheet.setFriTotalHours(objToFloat(row[4]));
							timeSheet.setFriDate(lisofTimeSheet.get(0).getFri_Date());
							timeSheet.setSatTotalHours(objToFloat(row[5]));
							timeSheet.setSatDate(lisofTimeSheet.get(0).getSat_Date());
							timeSheet.setSunTotalHours(objToFloat(row[6]));
							timeSheet.setSunDate(lisofTimeSheet.get(0).getSun_Date());
							timeSheet.setMonth(objToInteger(row[7]));
							timeSheet.setYear(objToInteger(row[8]));
							timeSheet.setCalWeek(objToInteger(row[9]));
							timeSheet.setStartDate(objToString(row[10]));
							timeSheet.setEndDate(objToString(row[11]));
							timeSheet.setSunday(((Date)row[12]).getDate());
							timeSheet.setMonday(((Date)row[13]).getDate());
							timeSheet.setTuesday(((Date)row[14]).getDate());
							timeSheet.setWednesday(((Date)row[15]).getDate());
							timeSheet.setThrusday(((Date)row[16]).getDate());
							timeSheet.setFriday(((Date)row[17]).getDate());
							timeSheet.setSaturday(((Date)row[18]).getDate());
							timeSheet.setTotalHour(objToFloat(row[19]));
							timeSheet.setEmployeeName(objToString(row[20]));
							timeSheet.setReportingManagerName(objToString(row[21]));
							timeSheet.setStatus(objToString(row[22]));
							int timeSheetId = objToInteger(row[23]);
							timeSheet.setRepManId(repId);
							timeSheet.setMonthName(monthName);
							timeSheet.setTimeSheetListForEmployee(lisofTimeSheet);
							TimeSheetApprovalStatusEntity tsApp = hrmsEmpTimeSheetDao
									.getApprovalEntityByTimeSheetId(timeSheetId);

							if (tsApp != null) {
								timeSheet.setApproverName(tsApp.getApproverName());
								timeSheet.setRejectReason(tsApp.getRejectReason());
							}

							timeSheetList.add(timeSheet);

						
						}
						timeSheetResponse.setMessage("TimeSheet Details are retrieved successfully");
						timeSheetResponse.setStatus(true);

					} else {
						timeSheetResponse.setMessage("TimeSheet Details are not found");
						timeSheetResponse.setStatus(false);
					}
				} else {
					timeSheetResponse.setMessage("Error in fetching details" + MonthlyTSHM.get("message"));
					timeSheetResponse.setStatus(false);
				}
			}
		}
		timeSheetResponse.setTimeSheetListForRM(timeSheetList);

		return Response.ok().entity(timeSheetResponse).build();
	}

	public Response getWeekMonthNameByMonthId(short month) {
		TSResponseObj tResp = new TSResponseObj();
		List<TimeSheetWeekResponse> listOfWeeks = hrmsEmpTimeSheetDao.getWeekMonthNameByMonthId(month);
		if (listOfWeeks != null) {
			tResp.setMessage("CalWeek Retrived SuccessFully");
			tResp.setStatus(true);
			tResp.setTimeSheetCountResponse(listOfWeeks);
		} else {
			tResp.setMessage("CalWeek Retrived UnSuccessFully");
			tResp.setStatus(false);
		}
		return Response.ok().entity(tResp).build();
	}

	public Response saveWeekMonthidNameInTimeSheet(TimeSheetAddCalWeekMonthBean bean) {
		TSResponseObj tRes = new TSResponseObj();
		TimeSheetAddCalWeekMonthEntity entity = new TimeSheetAddCalWeekMonthEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			boolean result = hrmsEmpTimeSheetDao.saveCalWeekMonthIdAndName(bean);
			if (result == true) {
				tRes.setMessage("Saved CalWeek MonthId And Name SuccessFully ");
				tRes.setStatus(true);
			} else {
				tRes.setMessage("Saved CalWeek MonthId And Name UnSuccessFully");
				tRes.setStatus(false);
			}
			return Response.ok().entity(tRes).build();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			tRes.setMessage("Error while saving And Updating details - " + e.getMessage());
			tRes.setStatus(false);
			return Response.status(Response.Status.OK).entity(tRes).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			tRes.setMessage("Error while saving And Updating details - " + e.getMessage());
			tRes.setStatus(false);
			return Response.status(Response.Status.OK).entity(tRes).build();
		}

	}

	// Get EmployeeDetails With Status By Passing Reporting ManagerId and Month Year
	@SuppressWarnings("deprecation")
	public Response getEmplDetailsByReportingManagerIdMonthYear(int repId, int year, short month) {
		logger.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of TimeSheetDetails class");

		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();
		TSResponseObj tsResp = new TSResponseObj();
		TSResponseEmployeeName tsEmp = null;
		String queryForMonTotalHours = null;
		String queryForTueTotalHours = null;
		String queryForWedTotalHours = null;
		String queryForThrTotalHours = null;
		String queryForFriTotalHours = null;
		String queryForSatTotalHours = null;
		String queryForSunTotalHours = null;
		String queryForMonthName = null;
		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				ArrayList<SavingTimeSheet> listofMonthlyTSDetails = hrmsEmpTimeSheetDao
						.getEmployeeDetailBasedOnRepIdEmpIdYearMonth(empEntity.getUserId(), year, month);

				String employeeName = null;
				int employeeId = 0;

				if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
					employeeName = empEntity.getEmployeeName();
					employeeId = empEntity.getUserId();
					tsEmp = new TSResponseEmployeeName();
					queryForMonTotalHours = "SELECT sum(mon_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForTueTotalHours = "SELECT sum(tue_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForWedTotalHours = "SELECT sum(wed_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForThrTotalHours = "SELECT sum(thu_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForFriTotalHours = "SELECT sum(fri_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForSatTotalHours = "SELECT sum(sat_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForSunTotalHours = "SELECT sum(sun_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForMonthName = "select distinct(month_name) from timeSheet_add_calWeek_month where month_id="
							+ month + "";
					String monthName = (String) DbConnect.DbCon().createSQLQuery(queryForMonthName).uniqueResult();
					Double monTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForMonTotalHours)
							.uniqueResult();
					Double tueTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForTueTotalHours)
							.uniqueResult();
					Double wedTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForWedTotalHours)
							.uniqueResult();
					Double thrTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForThrTotalHours)
							.uniqueResult();
					Double friTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForFriTotalHours)
							.uniqueResult();
					Double satTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSatTotalHours)
							.uniqueResult();
					Double sunTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSunTotalHours)
							.uniqueResult();
					Double totalHours = monTotalHours + tueTotalHours + wedTotalHours + thrTotalHours + friTotalHours
							+ satTotalHours + sunTotalHours;
					tsEmp.setUserId(employeeId);
					tsEmp.setEmployeeName(employeeName);
					tsEmp.setStartDate((Date) DbConnect
							.DbCon().createSQLQuery("SELECT max(sun_date) FROM tm_emp_timesheets Where emp_id="
									+ employeeId + " and ts_month=" + month + " and ts_year=" + year + " ")
							.uniqueResult());
					tsEmp.setEndDate((Date) DbConnect
							.DbCon().createSQLQuery("SELECT max(sat_date) FROM tm_emp_timesheets Where emp_id="
									+ employeeId + " and ts_month=" + month + " and ts_year=" + year + "")
							.uniqueResult());
					tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
					tsEmp.setCalWeek(listofMonthlyTSDetails.get(0).getCal_week());
					tsEmp.setMonDate(listofMonthlyTSDetails.get(0).getMon_date());
					tsEmp.setMonday(listofMonthlyTSDetails.get(0).getMon_date().getDate());
					tsEmp.setMonTotalHours(monTotalHours);
					tsEmp.setTueDate(listofMonthlyTSDetails.get(0).getTue_date());
					tsEmp.setTuesday(listofMonthlyTSDetails.get(0).getTue_date().getDate());
					tsEmp.setTueTotalHours(tueTotalHours);
					tsEmp.setWedDate(listofMonthlyTSDetails.get(0).getWed_Date());
					tsEmp.setWedTotalHours(wedTotalHours);
					tsEmp.setWednesday(listofMonthlyTSDetails.get(0).getWed_Date().getDate());
					tsEmp.setThursDate(listofMonthlyTSDetails.get(0).getThurs_Date());
					tsEmp.setThursTotalHours(thrTotalHours);
					tsEmp.setThrusday(listofMonthlyTSDetails.get(0).getThurs_Date().getDate());
					tsEmp.setFriDate(listofMonthlyTSDetails.get(0).getFri_Date());
					tsEmp.setFriTotalHours(friTotalHours);
					tsEmp.setFriday(listofMonthlyTSDetails.get(0).getFri_Date().getDate());
					tsEmp.setSatDate(listofMonthlyTSDetails.get(0).getSat_Date());
					tsEmp.setSatTotalHours(satTotalHours);
					tsEmp.setSaturday(listofMonthlyTSDetails.get(0).getSat_Date().getDate());
					tsEmp.setSunDate(listofMonthlyTSDetails.get(0).getSun_Date());
					tsEmp.setSunTotalHours(sunTotalHours);
					tsEmp.setSunday(listofMonthlyTSDetails.get(0).getSun_Date().getDate());
					tsEmp.setStatus(listofMonthlyTSDetails.get(0).getStatus());
					tsEmp.setTotalHour(totalHours);
					tsEmp.setMonthName(monthName);
					tsEmp.setTimeSheetListForEmployee(listofMonthlyTSDetails);
					listOfDetails.add(tsEmp);
				}
			}
		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}

		return Response.ok().entity(tsResp).build();
	}

	@SuppressWarnings("deprecation")
	public Response getEmplDetailsByReportingManagerIdMonthYearEmpId(int repId, int year, short month,int empId) {
		logger.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of TimeSheetDetails class");

		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();
		Set<SaveTimeSheetResponseClass>listOfEmpDetails=null;
		TSResponseObj tsResp = new TSResponseObj();
		TSResponseEmployeeName tsEmp = null;
		String queryForMonTotalHours = null;
		String queryForTueTotalHours = null;
		String queryForWedTotalHours = null;
		String queryForThrTotalHours = null;
		String queryForFriTotalHours = null;
		String queryForSatTotalHours = null;
		String queryForSunTotalHours = null;
		String queryForMonthName = null;
		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				if (empEntity.getUserId().equals(empId)) {
				ArrayList<SaveTimeSheetResponseClass> listofMonthlyTSDetails = hrmsEmpTimeSheetDao
						.getEmployeeDetailBasedOnRepIdEmpIdYearMonthByEmpId(empId, year, month);

				String employeeName = null;
				int employeeId = 0;

				if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
					employeeName = empEntity.getEmployeeName();
					tsEmp = new TSResponseEmployeeName();
					queryForMonTotalHours = "SELECT sum(mon_duration) FROM  tm_emp_timesheets Where emp_id="
							+ empId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForTueTotalHours = "SELECT sum(tue_duration) FROM  tm_emp_timesheets Where emp_id="
							+ empId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForWedTotalHours = "SELECT sum(wed_duration) FROM  tm_emp_timesheets Where emp_id="
							+ empId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForThrTotalHours = "SELECT sum(thu_duration) FROM  tm_emp_timesheets Where emp_id="
							+ empId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForFriTotalHours = "SELECT sum(fri_duration) FROM  tm_emp_timesheets Where emp_id="
							+ empId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForSatTotalHours = "SELECT sum(sat_duration) FROM  tm_emp_timesheets Where emp_id="
							+ empId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForSunTotalHours = "SELECT sum(sun_duration) FROM  tm_emp_timesheets Where emp_id="
							+ empId + " and ts_month=" + month + " and ts_year=" + year + "";
					queryForMonthName = "select distinct(month_name) from timeSheet_add_calWeek_month where month_id="
							+ month + "";
					String monthName = (String) DbConnect.DbCon().createSQLQuery(queryForMonthName).uniqueResult();
					Double monTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForMonTotalHours)
							.uniqueResult();
					Double tueTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForTueTotalHours)
							.uniqueResult();
					Double wedTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForWedTotalHours)
							.uniqueResult();
					Double thrTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForThrTotalHours)
							.uniqueResult();
					Double friTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForFriTotalHours)
							.uniqueResult();
					Double satTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSatTotalHours)
							.uniqueResult();
					Double sunTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSunTotalHours)
							.uniqueResult();
					Double totalHours = monTotalHours + tueTotalHours + wedTotalHours + thrTotalHours + friTotalHours
							+ satTotalHours + sunTotalHours;
					tsEmp.setUserId(empId);
					tsEmp.setEmployeeName(employeeName);
					tsEmp.setStartDate((Date) DbConnect
							.DbCon().createSQLQuery("SELECT max(sun_date) FROM tm_emp_timesheets Where emp_id="
									+ employeeId + " and ts_month=" + month + " and ts_year=" + year + " ")
							.uniqueResult());
					tsEmp.setEndDate((Date) DbConnect
							.DbCon().createSQLQuery("SELECT max(sat_date) FROM tm_emp_timesheets Where emp_id="
									+ employeeId + " and ts_month=" + month + " and ts_year=" + year + "")
							.uniqueResult());
					tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
					tsEmp.setCalWeek(listofMonthlyTSDetails.get(0).getCal_week());
					tsEmp.setMonDate(listofMonthlyTSDetails.get(0).getMon_date());
					tsEmp.setMonday(listofMonthlyTSDetails.get(0).getMon_date().getDate());
					tsEmp.setMonTotalHours(monTotalHours);
					tsEmp.setTueDate(listofMonthlyTSDetails.get(0).getTue_date());
					tsEmp.setTuesday(listofMonthlyTSDetails.get(0).getTue_date().getDate());
					tsEmp.setTueTotalHours(tueTotalHours);
					tsEmp.setWedDate(listofMonthlyTSDetails.get(0).getWed_Date());
					tsEmp.setWedTotalHours(wedTotalHours);
					tsEmp.setWednesday(listofMonthlyTSDetails.get(0).getWed_Date().getDate());
					tsEmp.setThursDate(listofMonthlyTSDetails.get(0).getThurs_Date());
					tsEmp.setThursTotalHours(thrTotalHours);
					tsEmp.setThrusday(listofMonthlyTSDetails.get(0).getThurs_Date().getDate());
					tsEmp.setFriDate(listofMonthlyTSDetails.get(0).getFri_Date());
					tsEmp.setFriTotalHours(friTotalHours);
					tsEmp.setFriday(listofMonthlyTSDetails.get(0).getFri_Date().getDate());
					tsEmp.setSatDate(listofMonthlyTSDetails.get(0).getSat_Date());
					tsEmp.setSatTotalHours(satTotalHours);
					tsEmp.setSaturday(listofMonthlyTSDetails.get(0).getSat_Date().getDate());
					tsEmp.setSunDate(listofMonthlyTSDetails.get(0).getSun_Date());
					tsEmp.setSunTotalHours(sunTotalHours);
					tsEmp.setSunday(listofMonthlyTSDetails.get(0).getSun_Date().getDate());
					tsEmp.setStatus(listofMonthlyTSDetails.get(0).getStatus());
					tsEmp.setTotalHour(totalHours);
					tsEmp.setMonthName(monthName);
					tsEmp.setRepManId(repId);
					tsEmp.setMonth(month);
					tsEmp.setYear(year);
					listOfEmpDetails=new HashSet<SaveTimeSheetResponseClass>(listofMonthlyTSDetails);
					tsEmp.setTimeSheetListForEmployees(listOfEmpDetails);
					listOfDetails.add(tsEmp);
				}
			}
		}
		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}

		return Response.ok().entity(tsResp).build();
	}
	
	public Response getMonthAndId() {
		TSResponseObj tResp = new TSResponseObj();
		List<TimeSheetWeekResponse> listOfWeeks = hrmsEmpTimeSheetDao.getMonth();
		if (listOfWeeks != null) {
			tResp.setMessage("Month Retrived SuccessFully");
			tResp.setStatus(true);
			tResp.setTimeSheetCountResponse(listOfWeeks);
		} else {
			tResp.setMessage("Month Retrived UnSuccessFully");
			tResp.setStatus(false);
		}
		return Response.ok().entity(tResp).build();
	}

	public Response getEmpTaskDetail(int repId, int empId, short calWeek) {
		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		TaskEmpResponse tsEmp = new TaskEmpResponse();
		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				if (empEntity.getUserId().equals(empId)) {
					ArrayList<EmpTaskResponse> listofMonthlyTSDetails = hrmsEmpTimeSheetDao.getEmpTask(empId, calWeek);
					if (listofMonthlyTSDetails != null) {
						tsEmp.setMessage("TimeSheet Details are retrieved successfully");
						tsEmp.setStatus(true);
						tsEmp.setTsakResponse(listofMonthlyTSDetails);
						return Response.ok().entity(tsEmp).build();
					} else {
						tsEmp.setMessage("TimeSheet Details are retrieved Unsuccessfully");
						tsEmp.setStatus(false);
						return Response.ok().entity(tsEmp).build();
					}
				}
			}
		}
		return Response.ok().entity(tsEmp).build();

	}

	// Get Employee Details By reportingManagerId
	@SuppressWarnings("deprecation")
	public Response getEmployeeDetailsByReportingManagerId(int repId) {
		logger.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of TimeSheetDetails class");

		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();
		TSResponseObj tsResp = new TSResponseObj();
		TSResponseEmployeeName tsEmp = null;
		String queryForMonTotalHours = null;
		String queryForTueTotalHours = null;
		String queryForWedTotalHours = null;
		String queryForThrTotalHours = null;
		String queryForFriTotalHours = null;
		String queryForSatTotalHours = null;
		String queryForSunTotalHours = null;
		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				ArrayList<SavingTimeSheet> listofMonthlyTSDetails = hrmsEmpTimeSheetDao
						.getEmpDetailsByEmpId(empEntity.getUserId());

				String employeeName = null;
				int employeeId = 0;

				if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
					employeeName = empEntity.getEmployeeName();
					employeeId = empEntity.getUserId();
					tsEmp = new TSResponseEmployeeName();
					queryForMonTotalHours = "SELECT sum(mon_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + "";
					queryForTueTotalHours = "SELECT sum(tue_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + "";
					queryForWedTotalHours = "SELECT sum(wed_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + "";
					queryForThrTotalHours = "SELECT sum(thu_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + "";
					queryForFriTotalHours = "SELECT sum(fri_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + "";
					queryForSatTotalHours = "SELECT sum(sat_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + "";
					queryForSunTotalHours = "SELECT sum(sun_duration) FROM  tm_emp_timesheets Where emp_id="
							+ employeeId + "";
					Double monTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForMonTotalHours)
							.uniqueResult();
					Double tueTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForTueTotalHours)
							.uniqueResult();
					Double wedTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForWedTotalHours)
							.uniqueResult();
					Double thrTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForThrTotalHours)
							.uniqueResult();
					Double friTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForFriTotalHours)
							.uniqueResult();
					Double satTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSatTotalHours)
							.uniqueResult();
					Double sunTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSunTotalHours)
							.uniqueResult();
					Double totalHours = monTotalHours + tueTotalHours + wedTotalHours + thrTotalHours + friTotalHours
							+ satTotalHours + sunTotalHours;
					tsEmp.setUserId(employeeId);
					tsEmp.setEmployeeName(employeeName);
					tsEmp.setStartDate((Date) DbConnect.DbCon()
							.createSQLQuery(
									"SELECT max(sun_date) FROM tm_emp_timesheets Where emp_id=" + employeeId + "")
							.uniqueResult());
					tsEmp.setEndDate((Date) DbConnect.DbCon()
							.createSQLQuery(
									"SELECT max(sat_date) FROM tm_emp_timesheets Where emp_id=" + employeeId + "")
							.uniqueResult());
					tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
					tsEmp.setCalWeek(listofMonthlyTSDetails.get(0).getCal_week());
					tsEmp.setMonDate(listofMonthlyTSDetails.get(0).getMon_date());
					tsEmp.setMonTotalHours(monTotalHours);
					tsEmp.setMonday(listofMonthlyTSDetails.get(0).getMon_date().getDate());
					tsEmp.setTueDate(listofMonthlyTSDetails.get(0).getTue_date());
					tsEmp.setTueTotalHours(tueTotalHours);
					tsEmp.setTuesday(listofMonthlyTSDetails.get(0).getTue_date().getDate());
					tsEmp.setWedDate(listofMonthlyTSDetails.get(0).getWed_Date());
					tsEmp.setWedTotalHours(wedTotalHours);
					tsEmp.setWednesday(listofMonthlyTSDetails.get(0).getWed_Date().getDate());
					tsEmp.setThursDate(listofMonthlyTSDetails.get(0).getThurs_Date());
					tsEmp.setThursTotalHours(thrTotalHours);
					tsEmp.setThrusday(listofMonthlyTSDetails.get(0).getThurs_Date().getDate());
					tsEmp.setFriDate(listofMonthlyTSDetails.get(0).getFri_Date());
					tsEmp.setFriTotalHours(friTotalHours);
					tsEmp.setFriday(listofMonthlyTSDetails.get(0).getFri_Date().getDate());
					tsEmp.setSatDate(listofMonthlyTSDetails.get(0).getSat_Date());
					tsEmp.setSatTotalHours(satTotalHours);
					tsEmp.setSaturday(listofMonthlyTSDetails.get(0).getSat_Date().getDate());
					tsEmp.setSunDate(listofMonthlyTSDetails.get(0).getSun_Date());
					tsEmp.setSunTotalHours(sunTotalHours);
					tsEmp.setSunday(listofMonthlyTSDetails.get(0).getSun_Date().getDate());
					tsEmp.setStatus(listofMonthlyTSDetails.get(0).getStatus());
					tsEmp.setTotalHour(totalHours);
					tsEmp.setTimeSheetListForEmployee(listofMonthlyTSDetails);
					listOfDetails.add(tsEmp);
				}
			}
		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}

		return Response.ok().entity(tsResp).build();
	}

	@SuppressWarnings("deprecation")
	public Response getEmployeeDetailsBasedOnEmpIdRepIdCalWeek(int repId, int empId, short calWeek) {
		logger.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of TimeSheetDetails class");

		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();
		TSResponseObj tsResp = new TSResponseObj();
		TSResponseEmployeeName tsEmp = null;
		String queryForMonTotalHours = null;
		String queryForTueTotalHours = null;
		String queryForWedTotalHours = null;
		String queryForThrTotalHours = null;
		String queryForFriTotalHours = null;
		String queryForSatTotalHours = null;
		String queryForSunTotalHours = null;
		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				if (empEntity.getUserId().equals(empId)) {
					ArrayList<SavingTimeSheet> listofMonthlyTSDetails = hrmsEmpTimeSheetDao
							.getLeaveStatusByUserId(empId, calWeek);

					String employeeName = null;
					int employeeId = 0;

					if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
						employeeName = empEntity.getEmployeeName();
						employeeId = empEntity.getUserId();
						tsEmp = new TSResponseEmployeeName();
						queryForMonTotalHours = "SELECT sum(mon_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + "";
						queryForTueTotalHours = "SELECT sum(tue_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + "";
						queryForWedTotalHours = "SELECT sum(wed_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + "";
						queryForThrTotalHours = "SELECT sum(thu_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + "";
						queryForFriTotalHours = "SELECT sum(fri_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + "";
						queryForSatTotalHours = "SELECT sum(sat_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + "";
						queryForSunTotalHours = "SELECT sum(sun_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + "";
						Double monTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForMonTotalHours)
								.uniqueResult();
						Double tueTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForTueTotalHours)
								.uniqueResult();
						Double wedTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForWedTotalHours)
								.uniqueResult();
						Double thrTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForThrTotalHours)
								.uniqueResult();
						Double friTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForFriTotalHours)
								.uniqueResult();
						Double satTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSatTotalHours)
								.uniqueResult();
						Double sunTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSunTotalHours)
								.uniqueResult();
						Double totalHours = monTotalHours + tueTotalHours + wedTotalHours + thrTotalHours
								+ friTotalHours + satTotalHours + sunTotalHours;
						tsEmp.setUserId(employeeId);
						tsEmp.setEmployeeName(employeeName);
						tsEmp.setStartDate((Date) DbConnect.DbCon()
								.createSQLQuery("SELECT max(sun_date) FROM tm_emp_timesheets Where emp_id=" + employeeId
										+ " and cal_week=" + calWeek + "")
								.uniqueResult());
						tsEmp.setEndDate((Date) DbConnect.DbCon()
								.createSQLQuery("SELECT max(sat_date) FROM tm_emp_timesheets Where emp_id=" + employeeId
										+ " and cal_week=" + calWeek + "")
								.uniqueResult());
						tsEmp.setRepManId(repId);
						tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
						tsEmp.setCalWeek(listofMonthlyTSDetails.get(0).getCal_week());
						tsEmp.setMonDate(listofMonthlyTSDetails.get(0).getMon_date());
						tsEmp.setMonTotalHours(monTotalHours);
						tsEmp.setTueDate(listofMonthlyTSDetails.get(0).getTue_date());
						tsEmp.setTueTotalHours(tueTotalHours);
						tsEmp.setWedDate(listofMonthlyTSDetails.get(0).getWed_Date());
						tsEmp.setWedTotalHours(wedTotalHours);
						tsEmp.setThursDate(listofMonthlyTSDetails.get(0).getThurs_Date());
						tsEmp.setThursTotalHours(thrTotalHours);
						tsEmp.setFriDate(listofMonthlyTSDetails.get(0).getFri_Date());
						tsEmp.setFriTotalHours(friTotalHours);
						tsEmp.setSatDate(listofMonthlyTSDetails.get(0).getSat_Date());
						tsEmp.setSatTotalHours(satTotalHours);
						tsEmp.setSunDate(listofMonthlyTSDetails.get(0).getSun_Date());
						tsEmp.setSunTotalHours(sunTotalHours);
						tsEmp.setStatus(listofMonthlyTSDetails.get(0).getStatus());
						tsEmp.setTotalHour(totalHours);
						tsEmp.setTimeSheetListForEmployee(listofMonthlyTSDetails);
						listOfDetails.add(tsEmp);
					}
				}
			}
		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}

		return Response.ok().entity(tsResp).build();
	}

	
	
	
	@SuppressWarnings("deprecation")
	public Response getEmplDetailsByReportinManagerIdEmpIdCalweekMonthYear(int repId, int empId, short calWeek,int year,short month) {
		logger.info("entered into getEmplTimeSheetDetailsByReportingManagerId method of TimeSheetDetails class");

		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();
		TSResponseObj tsResp = new TSResponseObj();
		TSResponseEmployeeName tsEmp = null;
		String queryForMonTotalHours = null;
		String queryForTueTotalHours = null;
		String queryForWedTotalHours = null;
		String queryForThrTotalHours = null;
		String queryForFriTotalHours = null;
		String queryForSatTotalHours = null;
		String queryForSunTotalHours = null;
		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				if (empEntity.getUserId().equals(empId)) {
					ArrayList<SavingTimeSheet> listofMonthlyTSDetails = hrmsEmpTimeSheetDao
							.getEmpDetails(empId, calWeek,year,month);

					String employeeName = null;
					int employeeId = 0;

					if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
						employeeName = empEntity.getEmployeeName();
						employeeId = empEntity.getUserId();
						tsEmp = new TSResponseEmployeeName();
						queryForMonTotalHours = "SELECT sum(mon_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_year="+year+" and ts_month="+month+"";
						queryForTueTotalHours = "SELECT sum(tue_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_year="+year+" and ts_month="+month+"";
						queryForWedTotalHours = "SELECT sum(wed_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_year="+year+" and ts_month="+month+"";
						queryForThrTotalHours = "SELECT sum(thu_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_year="+year+" and ts_month="+month+"";
						queryForFriTotalHours = "SELECT sum(fri_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_year="+year+" and ts_month="+month+"";
						queryForSatTotalHours = "SELECT sum(sat_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_year="+year+" and ts_month="+month+"";
						queryForSunTotalHours = "SELECT sum(sun_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_year="+year+" and ts_month="+month+"";
						Double monTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForMonTotalHours)
								.uniqueResult();
						Double tueTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForTueTotalHours)
								.uniqueResult();
						Double wedTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForWedTotalHours)
								.uniqueResult();
						Double thrTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForThrTotalHours)
								.uniqueResult();
						Double friTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForFriTotalHours)
								.uniqueResult();
						Double satTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSatTotalHours)
								.uniqueResult();
						Double sunTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSunTotalHours)
								.uniqueResult();
						Double totalHours = monTotalHours + tueTotalHours + wedTotalHours + thrTotalHours
								+ friTotalHours + satTotalHours + sunTotalHours;
						tsEmp.setUserId(employeeId);
						tsEmp.setEmployeeName(employeeName);
						tsEmp.setStartDate((Date) DbConnect.DbCon()
								.createSQLQuery("SELECT max(sun_date) FROM tm_emp_timesheets Where emp_id=" + employeeId
										+ " and cal_week=" + calWeek + " and ts_year="+year+" and ts_month="+month+"")
								.uniqueResult());
						tsEmp.setEndDate((Date) DbConnect.DbCon()
								.createSQLQuery("SELECT max(sat_date) FROM tm_emp_timesheets Where emp_id=" + employeeId
										+ " and cal_week=" + calWeek + " and ts_year="+year+" and ts_month="+month+"")
								.uniqueResult());
						tsEmp.setRepManId(repId);
						tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
						tsEmp.setCalWeek(listofMonthlyTSDetails.get(0).getCal_week());
						tsEmp.setMonDate(listofMonthlyTSDetails.get(0).getMon_date());
						tsEmp.setMonTotalHours(monTotalHours);
						tsEmp.setMonday(listofMonthlyTSDetails.get(0).getMon_date().getDate());
						tsEmp.setTueDate(listofMonthlyTSDetails.get(0).getTue_date());
						tsEmp.setTueTotalHours(tueTotalHours);
						tsEmp.setTuesday(listofMonthlyTSDetails.get(0).getTue_date().getDate());
						tsEmp.setWedDate(listofMonthlyTSDetails.get(0).getWed_Date());
						tsEmp.setWedTotalHours(wedTotalHours);
						tsEmp.setWednesday(listofMonthlyTSDetails.get(0).getWed_Date().getDate());
						tsEmp.setThursDate(listofMonthlyTSDetails.get(0).getThurs_Date());
						tsEmp.setThursTotalHours(thrTotalHours);
						tsEmp.setThrusday(listofMonthlyTSDetails.get(0).getThurs_Date().getDate());
						tsEmp.setFriDate(listofMonthlyTSDetails.get(0).getFri_Date());
						tsEmp.setFriTotalHours(friTotalHours);
						tsEmp.setFriday(listofMonthlyTSDetails.get(0).getFri_Date().getDate());
						tsEmp.setSatDate(listofMonthlyTSDetails.get(0).getSat_Date());
						tsEmp.setSatTotalHours(satTotalHours);
						tsEmp.setSaturday(listofMonthlyTSDetails.get(0).getSat_Date().getDate());
						tsEmp.setSunDate(listofMonthlyTSDetails.get(0).getSun_Date());
						tsEmp.setSunTotalHours(sunTotalHours);
						tsEmp.setSunday(listofMonthlyTSDetails.get(0).getSun_Date().getDate());
						tsEmp.setStatus(listofMonthlyTSDetails.get(0).getStatus());
						tsEmp.setTotalHour(totalHours);
						tsEmp.setTimeSheetListForEmployee(listofMonthlyTSDetails);
						listOfDetails.add(tsEmp);
					}
				}
			}
		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}

		return Response.ok().entity(tsResp).build();
	}

	@SuppressWarnings("deprecation")
	public Response getEmpDetailsByEmpIdCalweekMonthYear(int repId, int empId, int year, short month, short calWeek) {
		logger.info("entered into getEmpDetailsByEmpIdCalweekMonthYear method of TimeSheetDetails class");

		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		List<TSResponseEmployeeName> listOfDetails = new ArrayList<>();
		TSResponseObj tsResp = new TSResponseObj();
		TSResponseEmployeeName tsEmp = null;
		String queryForMonTotalHours = null;
		String queryForTueTotalHours = null;
		String queryForWedTotalHours = null;
		String queryForThrTotalHours = null;
		String queryForFriTotalHours = null;
		String queryForSatTotalHours = null;
		String queryForSunTotalHours = null;
		String queryForMonthName = null;
		if (empUserList != null && empUserList.size() != 0) {
			for (EmployeeDetailsEntity empEntity : empUserList) {
				if (empEntity.getUserId().equals(empId)) {
					ArrayList<SavingTimeSheet> listofMonthlyTSDetails = hrmsEmpTimeSheetDao
							.getEmpDetailsByEmpIdCalweekMonthYear(empId, year, month, calWeek);

					String employeeName = null;
					int employeeId = 0;

					if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
						employeeName = empEntity.getEmployeeName();
						employeeId = empEntity.getUserId();
						tsEmp = new TSResponseEmployeeName();
						queryForMonTotalHours = "SELECT sum(mon_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForTueTotalHours = "SELECT sum(tue_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForWedTotalHours = "SELECT sum(wed_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForThrTotalHours = "SELECT sum(thu_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForFriTotalHours = "SELECT sum(fri_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForSatTotalHours = "SELECT sum(sat_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForSunTotalHours = "SELECT sum(sun_duration) FROM  tm_emp_timesheets Where emp_id="
								+ employeeId + " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year="
								+ year + "";
						queryForMonthName = "select distinct(month_name) from timeSheet_add_calWeek_month where month_id="
								+ month + "";
						String monthName = (String) DbConnect.DbCon().createSQLQuery(queryForMonthName).uniqueResult();
						Double monTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForMonTotalHours)
								.uniqueResult();
						Double tueTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForTueTotalHours)
								.uniqueResult();
						Double wedTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForWedTotalHours)
								.uniqueResult();
						Double thrTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForThrTotalHours)
								.uniqueResult();
						Double friTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForFriTotalHours)
								.uniqueResult();
						Double satTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSatTotalHours)
								.uniqueResult();
						Double sunTotalHours = (Double) DbConnect.DbCon().createSQLQuery(queryForSunTotalHours)
								.uniqueResult();
						Double totalHours = monTotalHours + tueTotalHours + wedTotalHours + thrTotalHours
								+ friTotalHours + satTotalHours + sunTotalHours;
						tsEmp.setUserId(employeeId);
						tsEmp.setEmployeeName(employeeName);
						tsEmp.setStartDate((Date) DbConnect.DbCon()
								.createSQLQuery("SELECT max(sun_date) FROM tm_emp_timesheets Where emp_id=" + employeeId
										+ " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year=" + year
										+ "")
								.uniqueResult());
						tsEmp.setEndDate((Date) DbConnect.DbCon()
								.createSQLQuery("SELECT max(sat_date) FROM tm_emp_timesheets Where emp_id=" + employeeId
										+ " and cal_week=" + calWeek + " and ts_month=" + month + " and ts_year=" + year
										+ "")
								.uniqueResult());
						tsEmp.setRepManId(repId);
						tsEmp.setWeekNo(listofMonthlyTSDetails.get(0).getWeekNo());
						tsEmp.setCalWeek(listofMonthlyTSDetails.get(0).getCal_week());
						tsEmp.setMonDate(listofMonthlyTSDetails.get(0).getMon_date());
						tsEmp.setMonTotalHours(monTotalHours);
						tsEmp.setMonday(listofMonthlyTSDetails.get(0).getMon_date().getDate());
						tsEmp.setTueDate(listofMonthlyTSDetails.get(0).getTue_date());
						tsEmp.setTueTotalHours(tueTotalHours);
						tsEmp.setTuesday(listofMonthlyTSDetails.get(0).getTue_date().getDate());
						tsEmp.setWedDate(listofMonthlyTSDetails.get(0).getWed_Date());
						tsEmp.setWedTotalHours(wedTotalHours);
						tsEmp.setWednesday(listofMonthlyTSDetails.get(0).getWed_Date().getDate());
						tsEmp.setThursDate(listofMonthlyTSDetails.get(0).getThurs_Date());
						tsEmp.setThursTotalHours(thrTotalHours);
						tsEmp.setThrusday(listofMonthlyTSDetails.get(0).getThurs_Date().getDate());
						tsEmp.setFriDate(listofMonthlyTSDetails.get(0).getFri_Date());
						tsEmp.setFriTotalHours(friTotalHours);
						tsEmp.setFriday(listofMonthlyTSDetails.get(0).getFri_Date().getDate());
						tsEmp.setSatDate(listofMonthlyTSDetails.get(0).getSat_Date());
						tsEmp.setSatTotalHours(satTotalHours);
						tsEmp.setSaturday(listofMonthlyTSDetails.get(0).getSat_Date().getDate());
						tsEmp.setSunDate(listofMonthlyTSDetails.get(0).getSun_Date());
						tsEmp.setSunTotalHours(sunTotalHours);
						tsEmp.setSunday(listofMonthlyTSDetails.get(0).getSun_Date().getDate());
						tsEmp.setStatus(listofMonthlyTSDetails.get(0).getStatus());
						tsEmp.setTotalHour(totalHours);
						tsEmp.setYear(year);
						tsEmp.setMonth(month);
						tsEmp.setMonthName(monthName);
						tsEmp.setTimeSheetListForEmployee(listofMonthlyTSDetails);
						listOfDetails.add(tsEmp);
					}
				}
			}
		}
		if (listOfDetails != null && listOfDetails.size() != 0) {
			tsResp.setMessage("TimeSheet Details are retrieved successfully");
			tsResp.setStatus(true);
			tsResp.setTimeSheetListForRM(listOfDetails);
		} else {
			tsResp.setMessage("TimeSheet Details are not found");
			tsResp.setStatus(false);
		}

		return Response.ok().entity(tsResp).build();
	}

	@SuppressWarnings("deprecation")
	public Response getEmpDetailsByEmpIdYear(int repId, int empId, int year) {
		logger.info("entered into getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus method of TimeSheetDetails class");
		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		logger.info("entered into getMonthlyTimeSheetDetails method of TimeSheetDetails class");
		TimeSheetResponse timeSheetResponse = new TimeSheetResponse();
		ArrayList<TimeSheetsMonthlyBean> timeSheetList = new ArrayList<TimeSheetsMonthlyBean>();
		for (EmployeeDetailsEntity employeeId : empUserList) {
			if (employeeId.getUserId().equals(empId)) {
				HashMap<String, Object> MonthlyTSHM = hrmsEmpTimeSheetDao.getEmplTimeSheetDetails(year, empId);
				List<SavingTimeSheet> lisofTimeSheet = hrmsEmpTimeSheetDao
						.getEmpDetailsByEmpIdYear(empId, year);
				boolean status = (boolean) MonthlyTSHM.get("status");

				if (status) {
					@SuppressWarnings("unchecked")
					List<Object[]> listofMonthlyTSDetails = (List<Object[]>) MonthlyTSHM.get("monthlyList");
					if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
						for (Object[] row : listofMonthlyTSDetails) {
							TimeSheetsMonthlyBean timeSheet = new TimeSheetsMonthlyBean();
                        	timeSheet.setUserId(empId);
							timeSheet.setMonTotalHours(objToFloat(row[0]));
							timeSheet.setMonDate(lisofTimeSheet.get(0).getMon_date());
							timeSheet.setMonday(lisofTimeSheet.get(0).getMon_date().getDate());
							timeSheet.setTueTotalHours(objToFloat(row[1]));
							timeSheet.setTueDate(lisofTimeSheet.get(0).getTue_date());
							timeSheet.setTuesday(lisofTimeSheet.get(0).getTue_date().getDate());
							timeSheet.setWedTotalHours(objToFloat(row[2]));
							timeSheet.setWedDate(lisofTimeSheet.get(0).getWed_Date());
							timeSheet.setWednesday(lisofTimeSheet.get(0).getWed_Date().getDate());
							timeSheet.setThursTotalHours(objToFloat(row[3]));
							timeSheet.setThursDate(lisofTimeSheet.get(0).getThurs_Date());
							timeSheet.setThrusday(lisofTimeSheet.get(0).getThurs_Date().getDate());
							timeSheet.setFriTotalHours(objToFloat(row[4]));
							timeSheet.setFriDate(lisofTimeSheet.get(0).getFri_Date());
							timeSheet.setFriday(lisofTimeSheet.get(0).getFri_Date().getDate());
							timeSheet.setSatTotalHours(objToFloat(row[5]));
							timeSheet.setSatDate(lisofTimeSheet.get(0).getSat_Date());
							timeSheet.setSaturday(lisofTimeSheet.get(0).getSat_Date().getDate());
							timeSheet.setSunTotalHours(objToFloat(row[6]));
							timeSheet.setSunDate(lisofTimeSheet.get(0).getSun_Date());
							timeSheet.setSunday(lisofTimeSheet.get(0).getSun_Date().getDate());
							timeSheet.setMonth(objToInteger(row[7]));
							timeSheet.setYear(objToInteger(row[8]));
							timeSheet.setCalWeek(objToInteger(row[9]));
							timeSheet.setStartDate(objToString(row[10]));
							timeSheet.setEndDate(objToString(row[11]));
							timeSheet.setTotalHour(objToFloat(row[12]));
							timeSheet.setEmployeeName(objToString(row[13]));
							timeSheet.setReportingManagerName(objToString(row[14]));
							timeSheet.setStatus(objToString(row[15]));
							int timeSheetId = objToInteger(row[16]);
							timeSheet.setRepManId(repId);
							
							timeSheet.setTimeSheetListForEmployee(lisofTimeSheet);
							TimeSheetApprovalStatusEntity tsApp = hrmsEmpTimeSheetDao
									.getApprovalEntityByTimeSheetId(timeSheetId);

							if (tsApp != null) {
								timeSheet.setApproverName(tsApp.getApproverName());
								timeSheet.setRejectReason(tsApp.getRejectReason());
							}

							timeSheetList.add(timeSheet);

						}
						timeSheetResponse.setMessage("TimeSheet Details are retrieved successfully");
						timeSheetResponse.setStatus(true);

					} else {
						timeSheetResponse.setMessage("TimeSheet Details are not found");
						timeSheetResponse.setStatus(false);
					}
				} else {
					timeSheetResponse.setMessage("Error in fetching details" + MonthlyTSHM.get("message"));
					timeSheetResponse.setStatus(false);
				}
			}
		}
		timeSheetResponse.setTimeSheetListForRM(timeSheetList);

		return Response.ok().entity(timeSheetResponse).build();
	}
	@SuppressWarnings("deprecation")
	public Response getEmployeeDetailsBasedOnCanIdYearStatus(int repId,int empId,int year,String status1) {
		logger.info("entered into getEmployeeDetailsBasedOnCanIdYearStatus method of TimeSheetDetails class");
		List<EmployeeDetailsEntity> empUserList = hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
		logger.info("entered into getMonthlyTimeSheetDetails method of TimeSheetDetails class");
		TimeSheetResponse timeSheetResponse = new TimeSheetResponse();
		ArrayList<TimeSheetsMonthlyBean> timeSheetList = new ArrayList<TimeSheetsMonthlyBean>();
		for (EmployeeDetailsEntity employeeId : empUserList) {
			if (employeeId.getUserId().equals(empId)) {
				HashMap<String, Object> MonthlyTSHM = hrmsEmpTimeSheetDao.getEmployeeDetailBasedOnCanIdYearStatus(empId,year,status1);
				List<SavingTimeSheet> lisofTimeSheet = hrmsEmpTimeSheetDao
						.getEmployeeDetailsBasedOnCanIdYearStatus(empId, year,status1);
				boolean status = (boolean) MonthlyTSHM.get("status");

				if (status) {
					@SuppressWarnings("unchecked")
					List<Object[]> listofMonthlyTSDetails = (List<Object[]>) MonthlyTSHM.get("monthlyList");
					if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
						for (Object[] row : listofMonthlyTSDetails) {
							TimeSheetsMonthlyBean timeSheet = new TimeSheetsMonthlyBean();

							timeSheet.setUserId(empId);
							timeSheet.setMonTotalHours(objToFloat(row[0]));
							timeSheet.setTueTotalHours(objToFloat(row[1]));
							timeSheet.setWedTotalHours(objToFloat(row[2]));
							timeSheet.setThursTotalHours(objToFloat(row[3]));
						    timeSheet.setFriTotalHours(objToFloat(row[4]));
							timeSheet.setSatTotalHours(objToFloat(row[5]));
							timeSheet.setSunTotalHours(objToFloat(row[6]));
						    timeSheet.setMonth(objToInteger(row[7]));
							timeSheet.setYear(objToInteger(row[8]));
							timeSheet.setCalWeek(objToInteger(row[9]));
							timeSheet.setStartDate(objToString(row[10]));
							timeSheet.setEndDate(objToString(row[11]));
							timeSheet.setMonDate(((Date)row[13]));
							timeSheet.setTueDate(((Date)row[14]));
							timeSheet.setWedDate(((Date)row[15]));
							timeSheet.setThursDate(((Date)row[16]));
							timeSheet.setFriDate(((Date)row[17]));
							timeSheet.setSatDate(((Date)row[18]));
							timeSheet.setTotalHour(objToFloat(row[19]));
							timeSheet.setEmployeeName(objToString(row[20]));
							timeSheet.setReportingManagerName(objToString(row[21]));
							timeSheet.setStatus(objToString(row[22]));
							int timeSheetId = objToInteger(row[23]);
							timeSheet.setSunDate(((Date)row[12]));
							timeSheet.setSunday(timeSheet.getSunDate().getDate());
							timeSheet.setMonday(timeSheet.getMonDate().getDate());
							timeSheet.setTuesday(timeSheet.getTueDate().getDate());
							timeSheet.setWednesday(timeSheet.getWedDate().getDate());
							timeSheet.setThrusday(timeSheet.getThursDate().getDate());
							timeSheet.setFriday(timeSheet.getFriDate().getDate());
							timeSheet.setSaturday(timeSheet.getSatDate().getDate());
							timeSheet.setRepManId(repId);
							timeSheet.setTimeSheetListForEmployee(lisofTimeSheet);
							TimeSheetApprovalStatusEntity tsApp = hrmsEmpTimeSheetDao
									.getApprovalEntityByTimeSheetId(timeSheetId);

							if (tsApp != null) {
								timeSheet.setApproverName(tsApp.getApproverName());
								timeSheet.setRejectReason(tsApp.getRejectReason());
							}

							timeSheetList.add(timeSheet);

						
						}
						timeSheetResponse.setMessage("TimeSheet Details are retrieved successfully");
						timeSheetResponse.setStatus(true);

					} else {
						timeSheetResponse.setMessage("TimeSheet Details are not found");
						timeSheetResponse.setStatus(false);
					}
				} else {
					timeSheetResponse.setMessage("Error in fetching details" + MonthlyTSHM.get("message"));
					timeSheetResponse.setStatus(false);
				}
			}
		}
		timeSheetResponse.setTimeSheetListForRM(timeSheetList);

		return Response.ok().entity(timeSheetResponse).build();
	}
}

/*
 * @SuppressWarnings("unused") public Response getLeaveStatusByUserId(int
 * userId) { logger.
 * info("entered into getLeaveStatusByUserId Method of TimeSheetDetails BusinessClass.."
 * );
 * 
 * List<EmployeeDetailsEntity> empDetailsList =
 * hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(userId);
 * 
 * EmployeesForReportingManagerResponse empListResponse = new
 * EmployeesForReportingManagerResponse();
 * 
 * if (empDetailsList != null && !empDetailsList.isEmpty()) {
 * empListResponse.setMessage("Timesheet Details Fetched Successfully.");
 * empListResponse.setStatus(true); empListResponse.setReportingMangerId(repId);
 * empListResponse.setEmpDetailsList(empDetailsList); } else {
 * empListResponse.setReportingMangerId(repId); empListResponse.
 * setMessage("Timesheet Details Not Found for Employees Under ReportingManager : "
 * +repId); empListResponse.setStatus(false); }
 * 
 * return Response.ok().entity(empListResponse).build(); }
 */
/*
 * public Response getLeaveStatusByUserId(int year,int month, int userId, int
 * calWeek) { logger.
 * info("entered into getLeaveStatusByUserId method of TimeSheetDetails class");
 * 
 * List<SavingTimeSheet> list = hrmsEmpTimeSheetDao.getLeaveStatusByUserId(year,
 * month, userId, calWeek); if (!list.isEmpty() ) {
 * responseTimeSheet.setMessage("Timesheet Details Fetched Successfully.");
 * responseTimeSheet.setStatus(true);
 * responseTimeSheet.setTimeSheetDetails(list);
 * responseTimeSheet.setWeeklyStatus( list.get(0).getStatus()); } else {
 * responseTimeSheet.setMessage("Timesheet Details Not Found.");
 * responseTimeSheet.setStatus(false);
 * responseTimeSheet.setTimeSheetDetails(list);
 * 
 * } Calendar cal = Calendar.getInstance(); cal.setWeekDate(year, calWeek, 2);
 * java.util.Date date = cal.getTime(); HashMap<?, ?> DateHM = new
 * CurrentWeekDates().getDateDetails(date); responseTimeSheet.setMonth(month);
 * responseTimeSheet.setYear(year); responseTimeSheet.setWeek(calWeek);
 * responseTimeSheet.setTimeSheetDetails(list);
 * responseTimeSheet.setCurrentWeek(DateHM.get("currentWeek").toString());
 * responseTimeSheet.setPreviousWeek(DateHM.get("previousWeek").toString());
 * responseTimeSheet.setNextWeek(DateHM.get("nextWeek").toString()); return
 * Response.ok().entity(responseTimeSheet).build(); }
 */

/*
 * public Response getEmplTimeSheetDetailsByReportingManagerId(int repId, String
 * status) { logger.
 * info("entered into getMonthlyTimeSheetDetails method of TimeSheetDetails class"
 * );
 * 
 * TimeSheetResponseForReportingManager timeSheetResponse = new
 * TimeSheetResponseForReportingManager(); ArrayList<TimeSheetSummary>
 * timeSheetList = new ArrayList<TimeSheetSummary>();
 * 
 * List<EmployeeDetailsEntity> empUserList =
 * hrmsEmpTimeSheetDao.getEmpUserListByReportingManagerId(repId);
 * 
 * if(empUserList != null && empUserList.size() !=0 ) {
 * for(EmployeeDetailsEntity empEntity : empUserList) { TimeSheetSummary
 * timeSheet = null; List<Object[]> listofMonthlyTSDetails =
 * hrmsEmpTimeSheetDao.getEmplTimeSheetDetailsByReportingManagerId(empEntity.
 * getUserId() , status);
 * 
 * if (listofMonthlyTSDetails != null && listofMonthlyTSDetails.size() != 0) {
 * for (Object[] row : listofMonthlyTSDetails) { timeSheet = new
 * TimeSheetSummary();
 * 
 * timeSheet.setEmployeeId(empEntity.getUserId());
 * 
 * timeSheet.setEmployeeFullName(objToString(row[13]));
 * timeSheet.setReportingManagerName(objToString(row[14]));
 * timeSheet.setMondayHrs(objToFloat(row[0]));
 * timeSheet.setTuesdayHrs(objToFloat(row[1]));
 * timeSheet.setWednesdayHrs(objToFloat(row[2]));
 * timeSheet.setThursdayHrs(objToFloat(row[3]));
 * timeSheet.setFridayHrs(objToFloat(row[4]));
 * timeSheet.setSaturdayHrs(objToFloat(row[5]));
 * timeSheet.setSundayHrs(objToFloat(row[6]));
 * timeSheet.setWeekNo(objToInteger(row[9]));
 * timeSheet.setFromDate(objToString(row[10]));
 * timeSheet.setToDate(objToString(row[11]));
 * timeSheet.setTotalHours(objToFloat(row[12]));
 * timeSheet.setStatus(objToString(row[15]));
 * 
 * } timeSheetList.add(timeSheet);
 * 
 * timeSheetResponse.setReportingMangerId(repId);
 * timeSheetResponse.setMessage("TimeSheet Details are retrieved successfully");
 * timeSheetResponse.setStatus(true); } } }
 * 
 * else { timeSheetResponse.setReportingMangerId(repId);
 * timeSheetResponse.setMessage("TimeSheet Details are not found");
 * timeSheetResponse.setStatus(false); }
 * timeSheetResponse.setTimeSheetList(timeSheetList);
 * 
 * return Response.ok().entity(timeSheetResponse).build(); }
 * 
 */
