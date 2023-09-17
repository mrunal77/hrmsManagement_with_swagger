package com.rainier.dao.Impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.rainier.beans.EmpTaskResponse;
import com.rainier.beans.TimeSheetAddCalWeekMonthBean;
import com.rainier.dao.HrmsEmpTimeSheetDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.dto.requestBean.TimeSheetApprovalEntity;
import com.rainier.dto.responseBean.SaveTimeSheetResponseClass;
import com.rainier.dto.responseBean.TimeSheetWeekResponse;
import com.rainier.entities.ClientsEntity;
import com.rainier.entities.EmployeeDetailsEntity;
import com.rainier.entities.EmployeeWorkStatusEntity;
import com.rainier.entities.ProjectTaskEntity;
import com.rainier.entities.SavingTimeSheet;
import com.rainier.entities.Task;
import com.rainier.entities.TimeSheetAddCalWeekMonthEntity;
import com.rainier.entities.TimeSheetApprovalStatusEntity;
import com.rainier.response.LeaveDateResponseInTimeSheet;
import com.rainier.response.TimeSheetStatusCountResponse;

public class HrmsEmpTimeSheetDaoImpl implements HrmsEmpTimeSheetDao {
	private final static Logger logger = Logger.getLogger(HrmsEmpTimeSheetDaoImpl.class);

	HashMap<Integer, String> statusMapper = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getEmplTimeSheetDetails(int yea, int mon, int userId) {
		logger.info("entered into getEmplTimeSheetDetails of Dao Implementation class ");
		Short month = (short) mon;
		Short year = (short) yea;

		List<Object[]> listObject;
		HashMap<String, Object> hm = new HashMap<>();
		try {
			String hql = "select  sum(mon_duration),sum(tue_duration),sum(wed_duration),sum(thu_duration),sum(fri_duration),sum(sat_duration),"
					+ "sum(sun_duration), ts_month, ts_year, cal_week, sun_date as fromDate,sat_date as toDate,sun_date,mon_date,tue_date,wed_date,thu_date,fri_date,sat_date,week_duration,"
					+ " emp.userfullname,emp.reporting_manager_name, status,tm_emp_timesheets.id from tm_emp_timesheets "
					+ "inner join  main_employees_summary emp on emp.user_id=tm_emp_timesheets.emp_id "
					+ "where ts_year=:year and ts_month=:month and emp_id=:userId group by cal_week asc";

			DbConnect.DbCon().beginTransaction();
			Query<Object[]> query = DbConnect.DbCon().createNativeQuery(hql);
			query.setParameter("year", year);
			query.setParameter("month", month);
			query.setParameter("userId", userId);
			listObject = query.list();
			hm.put("monthlyList", listObject);
			hm.put("message", "success");
			hm.put("status", true);
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("catch block of  getEmplTimeSheetDetails of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			hm.put("message", e.getMessage());
			hm.put("status", false);
		} finally {
			DbConnect.DbCon().clear();
		}
		return hm;
	}

	@Override
	public ArrayList<SavingTimeSheet> getLeaveStatusByUserId(int userId, String status) {
		logger.info("entered getLeaveStatusByUserId of daoImpl class");

		List<SavingTimeSheet> lstTimeSheet;

		ArrayList<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();

		List<String> statusList = new ArrayList<>();

		try {
			String hql = null;

			if (status.equalsIgnoreCase("All")) {
				statusList.add("Approved");
				statusList.add("Rejected");
				statusList.add("Submitted");

				hql = "from SavingTimeSheet where employeeId=:userId and status in (:statusList) ";
			} else {
				statusList.add(status);

				hql = "from SavingTimeSheet where employeeId=:userId and status in (:statusList) ";
			}
			DbConnect.DbCon().beginTransaction();

			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("userId", userId)
					.setParameterList("statusList", statusList).getResultList();

			for (SavingTimeSheet sts : lstTimeSheet) {
				String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
				int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
				String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
				ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
				String hqlProjectName = "select p.project_Name from Project p where p.projectId =" + sts.getProjectId();
				String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
				String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
				String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
				sts.setClientId(client.getId());
				sts.setClientName(client.getClientName());
				sts.setProjectTaskName(taskName);
				sts.setProjectName(projectName);
				lstTimeSheet1.add(sts);
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	public List<SavingTimeSheet> getTSDetailsByUserId(int userId) {
		logger.info("entered getLeaveStatusByUserId of daoImpl class");

		List<SavingTimeSheet> lstTimeSheet = null;

		String hql = "from SavingTimeSheet where employeeId=:userId group by employeeId";
		try {
			DbConnect.DbCon().beginTransaction();
			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("userId", userId)
					.getResultList();

			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet;
	}

	public TimeSheetApprovalStatusEntity getApprovalEntityByTimeSheetId(int timeSheetId) {
		logger.info("entered getApprovalEntityByTimeSheetId of daoImpl class");

		TimeSheetApprovalStatusEntity tsAppEntity = null;

		try {
			DbConnect.DbCon().beginTransaction();

			String hql = "Select max(status_id) FROM TimeSheet_ApprovalStatusEntity where timesheet_id=:timeSheetId";
			@SuppressWarnings("deprecation")
			int maxId = (int) DbConnect.DbCon().createSQLQuery(hql).setParameter("timeSheetId", timeSheetId)
					.getSingleResult();
			String hql1 = "FROM TimeSheetApprovalStatusEntity where statusId="+maxId+"";
			tsAppEntity = (TimeSheetApprovalStatusEntity) DbConnect.DbCon()
					.createQuery(hql1, TimeSheetApprovalStatusEntity.class)
					.getSingleResult();

			DbConnect.DbCon().getTransaction().commit();

		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return tsAppEntity;
	}

	@SuppressWarnings({})
	/*
	 * public List<SavingTimeSheet> getEmplTimeSheetDetailsByReportingManagerId(int
	 * repId) { logger.
	 * info("entered into getEmplTimeSheetDetailsByReportingManagerId of Dao Implementation class "
	 * );
	 * 
	 * List<Integer> empUserIdList = getEmpUserIdListByReportingManagerId(repId);
	 * 
	 * List<SavingTimeSheet> timeSheetList = null;
	 * 
	 * try { if(empUserIdList != null && empUserIdList.size() > 0) {
	 * DbConnect.DbCon().beginTransaction();
	 * 
	 * String hql =
	 * " FROM SavingTimeSheet where status='submitted' and employeeId in (:empUserIdList)"
	 * ;
	 * 
	 * timeSheetList = (List<SavingTimeSheet>) DbConnect.DbCon().createQuery(hql,
	 * SavingTimeSheet.class).setParameterList("empUserIdList",
	 * empUserIdList).list();
	 * 
	 * DbConnect.DbCon().getTransaction().commit(); } } catch (Exception e) {
	 * logger.
	 * info("catch block of  getEmplTimeSheetDetailsByReportingManagerId of Dao Implementation class"
	 * + e); DbConnect.DbCon().getTransaction().rollback(); } finally {
	 * DbConnect.DbCon().clear(); } return timeSheetList; }
	 * 
	 */
	public List<EmployeeDetailsEntity> getEmpUserListByReportingManagerId(int repId) {
		logger.info("entered getEmpIdListByReportingManagerId of daoImpl class");
		List<EmployeeDetailsEntity> empDetList = null;

		List<Integer> idList = new ArrayList<>();
		try {
			DbConnect.DbCon().beginTransaction();

			String hql = "FROM EmployeeDetailsEntity where reportingManagerId=:repId ";

			empDetList = (List<EmployeeDetailsEntity>) DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class)
					.setParameter("repId", repId).list();

			DbConnect.DbCon().getTransaction().commit();

			for (EmployeeDetailsEntity entity : empDetList) {
				idList.add(entity.getUserId());
			}
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return empDetList;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getReportingManagerNameById(int repId) {
		logger.info("entered getReportingManagerNameById of daoImpl class");

		String reportingManagerName = null;
		try {
			DbConnect.DbCon().beginTransaction();

			String hql1 = "SELECT distinct reporting_manager_name  FROM EEAccess.main_employees_summary where reporting_manager=+'"
					+ repId + "'";

			Query<String> query1 = DbConnect.DbCon().createSQLQuery(hql1);

			reportingManagerName = query1.getSingleResult();

			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return reportingManagerName;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getEmployeeNameById(int userId) {
		logger.info("entered getReportingManagerNameById of daoImpl class");

		String employeeName = null;
		try {
			DbConnect.DbCon().beginTransaction();

			String hql1 = "SELECT distinct userfullname  FROM EEAccess.main_employees_summary where user_id=+'" + userId
					+ "'";

			Query<String> query1 = DbConnect.DbCon().createSQLQuery(hql1);

			employeeName = query1.getSingleResult();

			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return employeeName;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getReportingManagerNameByRMId(int repId) {
		logger.info("entered getReportingManagerNameById of daoImpl class");

		String reportingManagerName = null;
		try {
			String hql1 = "SELECT distinct reporting_manager_name  FROM main_employees_summary where reporting_manager=+'"
					+ repId + "'";

			Query<String> query1 = DbConnect.DbCon().createSQLQuery(hql1);

			reportingManagerName = query1.getSingleResult();

		} catch (Exception e) {
			logger.info("exception:" + e);

		} finally {
			DbConnect.DbCon().clear();
		}
		return reportingManagerName;
	}

	/*
	 * @SuppressWarnings({ "unchecked", "deprecation", "unused" }) public
	 * List<EmployeeDetailsEntity> getLeaveStatusByUserId(int userId) {
	 * logger.info("entered getReportingManagerNameById of daoImpl class");
	 * 
	 * List<EmployeeDetailsEntity> empEntityList = new ArrayList<>();
	 * 
	 * String reportingManagerName = null; try {
	 * DbConnect.DbCon().beginTransaction();
	 * 
	 * String hql1 =
	 * "SELECT *  FROM EEAccess.main_leaverequest where user_id=+'"+userId+"'";
	 * 
	 * Query<String> query1 = DbConnect.DbCon().createSQLQuery(hql1);
	 * 
	 * reportingManagerName = query1.getSingleResult();
	 * 
	 * DbConnect.DbCon().getTransaction().commit(); } catch (Exception e) {
	 * logger.info("exception:" + e); DbConnect.DbCon().getTransaction().rollback();
	 * } finally { DbConnect.DbCon().clear(); } return empEntityList; }
	 * 
	 */
	// SELECT * FROM EEAccess.tm_emp_timesheets where status='submitted' and emp_id
	// in (SELECT id FROM EEAccess.main_employees_summary where reporting_manager=0)
	@Override
	public boolean saveTimeSheet(SavingTimeSheet saveTimeSheetEntity, Task tmTask, ProjectTaskEntity projTask,
			EmployeeWorkStatusEntity empWorkStatus) {
		logger.info("entered saveTimeSheet of daoImpl class");
		boolean flag;

		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().save(tmTask);
			projTask.setTaskId(tmTask.getTaskId());
			DbConnect.DbCon().save(projTask);
			saveTimeSheetEntity.setProjectTaskId(projTask.getId());
			DbConnect.DbCon().save(saveTimeSheetEntity);
			DbConnect.DbCon().save(empWorkStatus);
			DbConnect.DbCon().getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			flag = false;
		} finally {
			DbConnect.DbCon().clear();
		}
		return flag;
	}

	@Override
	public boolean updateTimeSheet(SavingTimeSheet updateTimeSheetEntity, Task tmTask, ProjectTaskEntity projTask,
			EmployeeWorkStatusEntity empWorkStatus) {
		logger.info("entered updateTimeSheet of daoImpl class");
		boolean flag;
		try {
			DbConnect.DbCon().beginTransaction();
			String hql1 = "UPDATE EmployeeWorkStatusEntity SET status ='" + updateTimeSheetEntity.getStatus()
					+ "' where empId=" + updateTimeSheetEntity.getEmployeeId() + " and calweek="
					+ updateTimeSheetEntity.getCal_week() + " and month=" + updateTimeSheetEntity.getMonth()
					+ " and year=" + updateTimeSheetEntity.getYear() + "";
			Query query = DbConnect.DbCon().createQuery(hql1);
			query.executeUpdate();
			int projId = (int) DbConnect.DbCon().createQuery(
					"select ts.projectTaskId from SavingTimeSheet ts where saveId=" + updateTimeSheetEntity.getSaveId())
					.uniqueResult();
			String hql = "from ProjectTaskEntity where id =" + projId;
			projTask = DbConnect.DbCon().createQuery(hql, ProjectTaskEntity.class).getSingleResult();
			tmTask.setTaskId(projTask.getTaskId());
			DbConnect.DbCon().update(tmTask);
			DbConnect.DbCon().update(updateTimeSheetEntity);
			DbConnect.DbCon().getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			flag = false;
		} finally {

			DbConnect.DbCon().clear();
		}
		return flag;
	}

	@Override
	public List<SavingTimeSheet> getTimeSheetDetails(int userId, int year, int month, int week) {
		logger.info("entered getTimeSheetDetails of daoImpl class");
		// DbConnect db = new DbConnect();
		// Session session = DbConnect.DbCon();
		List<SavingTimeSheet> lstTimeSheet;
		List<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();
		String hql = "from SavingTimeSheet where employeeId =" + userId + " and year= " + year + " and month= " + month
				+ " and cal_week=" + week;
		try {
			DbConnect.DbCon().beginTransaction();
			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).getResultList();
			for (SavingTimeSheet sts : lstTimeSheet) {
				String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
				int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
				String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
				ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
				String hqlProjectName = "select p.project_Name from Project p where p.projectId =" + sts.getProjectId();
				String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
				String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
				String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
				sts.setClientId(client.getId());
				sts.setClientName(client.getClientName());
				sts.setProjectTaskName(taskName);
				sts.setProjectName(projectName);
				lstTimeSheet1.add(sts);
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	public List<SavingTimeSheet> getTimeSheetDetailsByUserId(int userId) {
		logger.info("entered getTimeSheetDetails of daoImpl class");

		List<SavingTimeSheet> lstTimeSheet;
		List<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();
		String hql = "from SavingTimeSheet where employeeId =" + userId + " and status='submitted'";
		try {
			DbConnect.DbCon().beginTransaction();

			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).getResultList();

			for (SavingTimeSheet sts : lstTimeSheet) {
				String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
				int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();

				String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
				ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
				String hqlProjectName = "select p.project_Name from Project p where p.projectId =" + sts.getProjectId();
				String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
				String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
				String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
				sts.setClientId(client.getId());
				sts.setClientName(client.getClientName());
				sts.setProjectTaskName(taskName);
				sts.setProjectName(projectName);
				lstTimeSheet1.add(sts);
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	@SuppressWarnings({ "unchecked" })
	public List<Object[]> getEmplTimeSheetDetailsByReportingManagerId(int userId, String status) {
		logger.info("entered into getEmplTimeSheetDetails of Dao Implementation class ");

		List<Object[]> listObject = null;

		List<String> statusList = new ArrayList<>();

		try {
			String hql = null;

			if (status.equalsIgnoreCase("All")) {
				statusList.add("Approved");
				statusList.add("Rejected");
				statusList.add("Submitted");

				hql = "select  sum(mon_duration),sum(tue_duration),sum(wed_duration),sum(thu_duration),sum(fri_duration),"
						+ "sum(sat_duration),sum(sun_duration), ts_month, ts_year, cal_week, sun_date as fromDate, sat_date as toDate, week_duration,"
						+ " emp.userfullname,emp.reporting_manager_name, status from tm_emp_timesheets , main_employees_summary emp"
						+ " where status in (:statusList) and emp_id=:userId and emp.user_id=emp_id"
						+ " group by cal_week ";
			} else {
				statusList.add(status);
				hql = "select  sum(mon_duration),sum(tue_duration),sum(wed_duration),sum(thu_duration),sum(fri_duration),"
						+ "sum(sat_duration),sum(sun_duration), ts_month, ts_year, cal_week, sun_date as fromDate, sat_date as toDate, week_duration,"
						+ " emp.userfullname,emp.reporting_manager_name, status from tm_emp_timesheets , main_employees_summary emp"
						+ " where status in (:statusList) and emp_id=:userId and emp.user_id=emp_id"
						+ " group by cal_week ";
			}

			DbConnect.DbCon().beginTransaction();

			Query<Object[]> query = DbConnect.DbCon().createNativeQuery(hql);

			query.setParameter("userId", userId);
			query.setParameterList("statusList", statusList);
			listObject = query.list();
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("catch block of  getEmplTimeSheetDetails of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return listObject;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public boolean timeSheetApproval(TimeSheetApprovalEntity timeSheetApprovalEntity) {
		logger.info("entered into timeSheetApproval of Dao Implementation class ");

		List<SavingTimeSheet> listObject = null;
		List<EmployeeWorkStatusEntity> listObject1 = null;
		List<SavingTimeSheet> listObject2 = null;
		List<EmployeeWorkStatusEntity> listObject3 = null;
		List<SavingTimeSheet> listObject4 = null;
		List<EmployeeWorkStatusEntity> listObject5 = null;

		try {
			String hql = "from SavingTimeSheet where employeeId=:employeeId and cal_week=:upCalWeek and year=:upYear and month=:upMonth and status='submitted'";
			String hql1 = "from EmployeeWorkStatusEntity where empId=:employeeId and calweek=:upCalWeek and year=:upYear and month=:upMonth and status='submitted'";
			String hql2 = "from SavingTimeSheet where employeeId=:employeeId and cal_week=:upCalWeek and year=:upYear and month=:upMonth and status='rejected'";
			String hql3 = "from EmployeeWorkStatusEntity where empId=:employeeId and calweek=:upCalWeek and year=:upYear and month=:upMonth and status='rejected'";
			String hql4 = "from SavingTimeSheet where employeeId=:employeeId and cal_week=:upCalWeek and year=:upYear and month=:upMonth and status='approved'";
			String hql5 = "from EmployeeWorkStatusEntity where empId=:employeeId and calweek=:upCalWeek and year=:upYear and month=:upMonth and status='approved'";
			DbConnect.DbCon().beginTransaction();

			Query<SavingTimeSheet> query = DbConnect.DbCon().createQuery(hql);
			Query<EmployeeWorkStatusEntity> query1 = DbConnect.DbCon().createQuery(hql1);
			Query<SavingTimeSheet> query2 = DbConnect.DbCon().createQuery(hql2);
			Query<EmployeeWorkStatusEntity> query3 = DbConnect.DbCon().createQuery(hql3);
			Query<SavingTimeSheet> query4 = DbConnect.DbCon().createQuery(hql4);
			Query<EmployeeWorkStatusEntity> query5 = DbConnect.DbCon().createQuery(hql5);
			TimeSheetApprovalStatusEntity approvalEntity = new TimeSheetApprovalStatusEntity();

			query.setParameter("employeeId", timeSheetApprovalEntity.getUserId())
					.setParameter("upCalWeek", timeSheetApprovalEntity.getCalWeek())
					.setParameter("upMonth", timeSheetApprovalEntity.getMonth())
					.setParameter("upYear", timeSheetApprovalEntity.getYear());
			query1.setParameter("employeeId", timeSheetApprovalEntity.getUserId())
					.setParameter("upCalWeek", timeSheetApprovalEntity.getCalWeek())
					.setParameter("upMonth", timeSheetApprovalEntity.getMonth())
					.setParameter("upYear", timeSheetApprovalEntity.getYear());
			query2.setParameter("employeeId", timeSheetApprovalEntity.getUserId())
					.setParameter("upCalWeek", timeSheetApprovalEntity.getCalWeek())
					.setParameter("upMonth", timeSheetApprovalEntity.getMonth())
					.setParameter("upYear", timeSheetApprovalEntity.getYear());
			query3.setParameter("employeeId", timeSheetApprovalEntity.getUserId())
					.setParameter("upCalWeek", timeSheetApprovalEntity.getCalWeek())
					.setParameter("upMonth", timeSheetApprovalEntity.getMonth())
					.setParameter("upYear", timeSheetApprovalEntity.getYear());
			query4.setParameter("employeeId", timeSheetApprovalEntity.getUserId())
					.setParameter("upCalWeek", timeSheetApprovalEntity.getCalWeek())
					.setParameter("upMonth", timeSheetApprovalEntity.getMonth())
					.setParameter("upYear", timeSheetApprovalEntity.getYear());
			query5.setParameter("employeeId", timeSheetApprovalEntity.getUserId())
					.setParameter("upCalWeek", timeSheetApprovalEntity.getCalWeek())
					.setParameter("upMonth", timeSheetApprovalEntity.getMonth())
					.setParameter("upYear", timeSheetApprovalEntity.getYear());
			listObject = query.list();
			listObject1 = query1.list();
			listObject2 = query2.list();
			listObject3 = query3.list();
			listObject4 = query4.list();
			listObject5 = query5.list();
			String upStatus = timeSheetApprovalEntity.getStatus();
			short upCalWeek = timeSheetApprovalEntity.getCalWeek();
			int upYear = timeSheetApprovalEntity.getYear();
			short upMonth = timeSheetApprovalEntity.getMonth();
			String approverName = null;
			DbConnect.DbCon().getTransaction().commit();
			for (SavingTimeSheet savingTimeSheet : listObject) {
				if (savingTimeSheet != null) {
					if (timeSheetApprovalEntity.getApproverRole().equalsIgnoreCase("SuperAdmin")) {
						approverName = getSuperAdminNameById(timeSheetApprovalEntity.getApproverRoleId());
					} else if (timeSheetApprovalEntity.getApproverRole().equalsIgnoreCase("ReportingManager")) {
						approverName = getReportingManagerNameByRMId(timeSheetApprovalEntity.getApproverRoleId());
					}
					DbConnect.DbCon().beginTransaction();
					approvalEntity.setTimeSheetId(savingTimeSheet.getSaveId());
					approvalEntity.setApproverName(approverName);
					approvalEntity.setApproverRole(timeSheetApprovalEntity.getApproverRole());
					approvalEntity.setRejectReason(timeSheetApprovalEntity.getReason());
					Query upQuery = DbConnect.DbCon().createSQLQuery(
							"Update tm_emp_timesheets set status=:upStatus  where cal_week=:upCalWeek and ts_month=:upMonth and ts_year=:upYear and status='"
									+ savingTimeSheet.getStatus() + "'")
							.setParameter("upStatus", upStatus).setParameter("upCalWeek", upCalWeek)
							.setParameter("upMonth", upMonth).setParameter("upYear", upYear);
					upQuery.executeUpdate();
					DbConnect.DbCon().save(approvalEntity);
				}
				DbConnect.DbCon().getTransaction().commit();
				DbConnect.DbCon().beginTransaction();
				for (EmployeeWorkStatusEntity employeeWorkStatusEntity : listObject1) {
					Query upQuery = DbConnect.DbCon().createSQLQuery(
							"Update employee_work_status_entity set status=:upStatus where cal_week=:upCalWeek and  ts_month=:upMonth and ts_year=:upYear and status='"
									+ employeeWorkStatusEntity.getStatus() + "'")
							.setParameter("upStatus", upStatus).setParameter("upCalWeek", upCalWeek)
							.setParameter("upMonth", upMonth).setParameter("upYear", upYear);
					upQuery.executeUpdate();
				}
				DbConnect.DbCon().getTransaction().commit();
				return true;

			}
			for (SavingTimeSheet savingTimeSheet : listObject2) {
				if (savingTimeSheet != null) {
					if (timeSheetApprovalEntity.getApproverRole().equalsIgnoreCase("SuperAdmin")) {
						approverName = getSuperAdminNameById(timeSheetApprovalEntity.getApproverRoleId());
					}
					DbConnect.DbCon().beginTransaction();
					approvalEntity.setTimeSheetId(savingTimeSheet.getSaveId());
					approvalEntity.setApproverName(approverName);
					approvalEntity.setApproverRole(timeSheetApprovalEntity.getApproverRole());
					approvalEntity.setRejectReason(timeSheetApprovalEntity.getReason());
					Query upQuery = DbConnect.DbCon().createSQLQuery(
							"Update tm_emp_timesheets set status=:upStatus  where cal_week=:upCalWeek and ts_month=:upMonth and ts_year=:upYear and status='"
									+ savingTimeSheet.getStatus() + "'")
							.setParameter("upStatus", upStatus).setParameter("upCalWeek", upCalWeek)
							.setParameter("upMonth", upMonth).setParameter("upYear", upYear);
					upQuery.executeUpdate();
					DbConnect.DbCon().save(approvalEntity);
				}
				for (EmployeeWorkStatusEntity employeeWorkStatusEntity : listObject3) {
					Query upQuery = DbConnect.DbCon().createSQLQuery(
							"Update employee_work_status_entity set status=:upStatus where cal_week=:upCalWeek and  ts_month=:upMonth and ts_year=:upYear and status='"
									+ employeeWorkStatusEntity.getStatus() + "'")
							.setParameter("upStatus", upStatus).setParameter("upCalWeek", upCalWeek)
							.setParameter("upMonth", upMonth).setParameter("upYear", upYear);
					upQuery.executeUpdate();
				}
				DbConnect.DbCon().getTransaction().commit();
				return true;

			}
			for (SavingTimeSheet savingTimeSheet1 : listObject4) {
				if (savingTimeSheet1 != null) {

					if (timeSheetApprovalEntity.getApproverRole().equalsIgnoreCase("SuperAdmin")) {
						approverName = getSuperAdminNameById(timeSheetApprovalEntity.getApproverRoleId());
					}
					DbConnect.DbCon().beginTransaction();
					approvalEntity.setTimeSheetId(savingTimeSheet1.getSaveId());
					approvalEntity.setApproverName(approverName);
					approvalEntity.setApproverRole(timeSheetApprovalEntity.getApproverRole());
					approvalEntity.setRejectReason(timeSheetApprovalEntity.getReason());
					Query upQuery = DbConnect.DbCon().createSQLQuery(
							"Update tm_emp_timesheets set status=:upStatus  where cal_week=:upCalWeek and ts_month=:upMonth and ts_year=:upYear and status='"
									+ savingTimeSheet1.getStatus() + "'")
							.setParameter("upStatus", upStatus).setParameter("upCalWeek", upCalWeek)
							.setParameter("upMonth", upMonth).setParameter("upYear", upYear);
					upQuery.executeUpdate();
					DbConnect.DbCon().save(approvalEntity);
				}
				for (EmployeeWorkStatusEntity employeeWorkStatusEntity1 : listObject5) {
					Query upQuery = DbConnect.DbCon().createSQLQuery(
							"Update employee_work_status_entity set status=:upStatus where cal_week=:upCalWeek and  ts_month=:upMonth and ts_year=:upYear and status='"
									+ employeeWorkStatusEntity1.getStatus() + "'")
							.setParameter("upStatus", upStatus).setParameter("upCalWeek", upCalWeek)
							.setParameter("upMonth", upMonth).setParameter("upYear", upYear);
					upQuery.executeUpdate();
				}
				DbConnect.DbCon().getTransaction().commit();

				return true;

			}
			return true;
		} catch (Exception e) {
			logger.info("catch block of  timeSheetApproval of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getSuperAdminNameById(int repId) {
		logger.info("entered getReportingManagerNameById of daoImpl class");

		String superAdminName = null;
		try {

			String hql1 = "SELECT distinct userfullname  FROM main_users where emprole=+'" + repId + "'";

			Query<String> query1 = DbConnect.DbCon().createSQLQuery(hql1);

			superAdminName = query1.getSingleResult();

		} catch (Exception e) {
			logger.info("exception:" + e);

		} finally {
			DbConnect.DbCon().clear();
		}
		return superAdminName;
	}

	// Get All ReportingManagerName And Id Through This Service
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EmployeeDetailsEntity> getAllReportingManagerNameId() {
		logger.info("Entered Into getAllReportingManagerNameId()");
		Transaction tx = null;
		List<Object[]> listsOfReportingManagerIdName = null;
		List<EmployeeDetailsEntity> listOfReportingManagerIdName = null;
		String queryForGetReportingManagerIdName = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			listOfReportingManagerIdName = new ArrayList<>();
			queryForGetReportingManagerIdName = "select distinct(reporting_manager),reporting_manager_name from main_employees_summary";
			listsOfReportingManagerIdName = DbConnect.DbCon().createSQLQuery(queryForGetReportingManagerIdName).list();
			if (listsOfReportingManagerIdName != null) {
				for (Object[] ob : listsOfReportingManagerIdName) {
					EmployeeDetailsEntity reportinMangerIdName = new EmployeeDetailsEntity();
					int reportingManagerId = (Integer) ob[0];
					String reportingManagerName = (String) ob[1];
					reportinMangerIdName.setReportingManagerId(reportingManagerId);
					reportinMangerIdName.setReportingManager(reportingManagerName);
					listOfReportingManagerIdName.add(reportinMangerIdName);
				}
			}
			tx.commit();
			logger.info("Retrived All ReportingManager Id Name SuccessFully");
			return listOfReportingManagerIdName;
		} catch (Exception e) {
			tx.rollback();
			logger.error("Retrived All ReportingManager Id Name UnSuccessFully");
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	@Override
	public ArrayList<SavingTimeSheet> getLeaveStatusByUserId(int userId, short calWeek) {
		logger.info("entered getLeaveStatusByUserId of daoImpl class");
		List<SavingTimeSheet> lstTimeSheet = null;
		ArrayList<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();
		try {
			String hql = null;
			hql = "from SavingTimeSheet where employeeId=:userId and cal_week=:calWeek order by cal_week desc";
			DbConnect.DbCon().beginTransaction();
			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("userId", userId)
					.setParameter("calWeek", calWeek).getResultList();
			if (lstTimeSheet != null) {
				for (SavingTimeSheet sts : lstTimeSheet) {
					String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
					int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
					String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
					ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
					String hqlProjectName = "select p.project_Name from Project p where p.projectId ="
							+ sts.getProjectId();
					String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
					String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
					String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
					sts.setClientId(client.getId());
					sts.setClientName(client.getClientName());
					sts.setProjectTaskName(taskName);
					sts.setProjectName(projectName);
					lstTimeSheet1.add(sts);
				}
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	@Override
	public ArrayList<SavingTimeSheet> getEmpDetails(int userId, short calWeek, int year, short month) {
		logger.info("entered getLeaveStatusByUserId of daoImpl class");
		List<SavingTimeSheet> lstTimeSheet = null;
		ArrayList<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();
		try {
			String hql = null;
			hql = "from SavingTimeSheet where employeeId=:userId and cal_week=:calWeek  and year=:year and month=:month  order by cal_week desc";
			DbConnect.DbCon().beginTransaction();
			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("userId", userId)
					.setParameter("calWeek", calWeek).setParameter("year", year).setParameter("month", month)
					.getResultList();
			if (lstTimeSheet != null) {
				for (SavingTimeSheet sts : lstTimeSheet) {
					String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
					int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
					String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
					ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
					String hqlProjectName = "select p.project_Name from Project p where p.projectId ="
							+ sts.getProjectId();
					String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
					String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
					String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
					sts.setClientId(client.getId());
					sts.setClientName(client.getClientName());
					sts.setProjectTaskName(taskName);
					sts.setProjectName(projectName);
					lstTimeSheet1.add(sts);
				}
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	// Get Employee And Along With Working Details Of Employee Based On
	// ReportingManagerId Status calWeek And Year
	@Override
	public ArrayList<SavingTimeSheet> getEmployeeWorkingReportsByUserIdStatusCalweekYear(int userId, String status,
			short calWeek, int year) {
		logger.info("entered getLeaveStatusByUserId of daoImpl class");

		List<SavingTimeSheet> lstTimeSheet;

		ArrayList<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();

		List<String> statusList = new ArrayList<>();

		try {
			String hql = null;

			if (status.equalsIgnoreCase("All")) {
				statusList.add("Approved");
				statusList.add("Rejected");
				statusList.add("Submitted");

				hql = "from SavingTimeSheet where employeeId=:userId and cal_week=:calWeek and year=:year and status in (:statusList) ";
			} else {
				statusList.add(status);

				hql = "from SavingTimeSheet where employeeId=:userId and cal_week=:calWeek and year=:year and status in (:statusList) ";
			}
			DbConnect.DbCon().beginTransaction();

			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("userId", userId)
					.setParameter("calWeek", calWeek).setParameter("year", year)
					.setParameterList("statusList", statusList).getResultList();

			for (SavingTimeSheet sts : lstTimeSheet) {
				String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
				int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
				String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
				ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
				String hqlProjectName = "select p.project_Name from Project p where p.projectId =" + sts.getProjectId();
				String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
				String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
				String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
				sts.setClientId(client.getId());
				sts.setClientName(client.getClientName());
				sts.setProjectTaskName(taskName);
				sts.setProjectName(projectName);
				lstTimeSheet1.add(sts);
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	@SuppressWarnings("deprecation")
	@Override
	public TimeSheetStatusCountResponse getStatusCount(int userId, short calWeek) {
		Transaction tx = null;
		String queryForAllStatus = null;
		String queryForApproved = null;
		String queryForRejected = null;
		String queryForSaved = null;
		String queryForSubmitted = null;
		BigInteger countForAllStatus = null;
		BigInteger countForApproved = null;
		BigInteger countForRejected = null;
		BigInteger countForSaved = null;
		BigInteger countForSubmitted = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			TimeSheetStatusCountResponse timeSheetStatusResp = new TimeSheetStatusCountResponse();
			queryForAllStatus = "SELECT count(distinct(status)) FROM employee_work_status_entity Where cal_week=:calWeek and rep_id=:userId";
			queryForApproved = "SELECT count(distinct(status)) FROM employee_work_status_entity Where  cal_week=:calWeek and rep_id=:userId and status='approved'";
			queryForRejected = "SELECT count(distinct(status)) FROM employee_work_status_entity Where  cal_week=:calWeek and rep_id=:userId and status='rejected'";
			queryForSaved = "SELECT count(distinct(status)) FROM employee_work_status_entity Where  cal_week=:calWeek  and rep_id=:userId and status='saved'";
			queryForSubmitted = "SELECT count(distinct(status)) FROM employee_work_status_entity Where cal_week=:calWeek and rep_id=:userId  and status='submitted'";
			countForAllStatus = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForAllStatus)
					.setParameter("calWeek", calWeek).setParameter("userId", userId).uniqueResult();
			countForApproved = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForApproved)
					.setParameter("calWeek", calWeek).setParameter("userId", userId).uniqueResult();
			countForRejected = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForRejected)
					.setParameter("calWeek", calWeek).setParameter("userId", userId).uniqueResult();
			countForSaved = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForSaved)
					.setParameter("calWeek", calWeek).setParameter("userId", userId).uniqueResult();
			countForSubmitted = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForSubmitted)
					.setParameter("calWeek", calWeek).setParameter("userId", userId).uniqueResult();
			timeSheetStatusResp.setAll(countForAllStatus);
			timeSheetStatusResp.setApproved(countForApproved);
			timeSheetStatusResp.setRejected(countForRejected);
			timeSheetStatusResp.setSaved(countForSaved);
			timeSheetStatusResp.setSubmitted(countForSubmitted);
			timeSheetStatusResp.setCalWeek(calWeek);
			tx.commit();
			logger.info("Staus Count Retrived SuccessFully");
			return timeSheetStatusResp;
		} catch (Exception e) {
			logger.info("exception:" + e);
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public TimeSheetStatusCountResponse getStatusCountBasedOnYear(int userId, int year, int empId) {
		Transaction tx = null;
		String queryForAllStatus = null;
		String queryForApproved = null;
		String queryForRejected = null;
		String queryForSaved = null;
		String queryForSubmitted = null;
		BigInteger countForAllStatus = null;
		BigInteger countForApproved = null;
		BigInteger countForRejected = null;
		BigInteger countForSaved = null;
		BigInteger countForSubmitted = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			TimeSheetStatusCountResponse timeSheetStatusResp = new TimeSheetStatusCountResponse();
			queryForAllStatus = "SELECT count(status) FROM employee_work_status_entity Where ts_year=:year and emp_id=:empId";
			queryForApproved = "SELECT count(status) FROM employee_work_status_entity Where  ts_year=:year and emp_id=:empId and status='approved'";
			queryForRejected = "SELECT count(status) FROM employee_work_status_entity Where  ts_year=:year and emp_id=:empId and status='rejected'";
			queryForSaved = "SELECT count(status) FROM employee_work_status_entity Where ts_year=:year and emp_id=:empId and status='saved'";
			queryForSubmitted = "SELECT count(status) FROM employee_work_status_entity Where ts_year=:year and emp_id=:empId and status='submitted'";
			countForAllStatus = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForAllStatus)
					.setParameter("year", year).setParameter("empId", empId)
					.uniqueResult();
			countForApproved = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForApproved)
					.setParameter("year", year).setParameter("empId", empId)
					.uniqueResult();
			countForRejected = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForRejected)
					.setParameter("year", year).setParameter("empId", empId)
					.uniqueResult();
			countForSaved = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForSaved).setParameter("year", year)
					.setParameter("empId", empId).uniqueResult();
			countForSubmitted = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForSubmitted)
					.setParameter("year", year).setParameter("empId", empId)
					.uniqueResult();
			timeSheetStatusResp.setAll(countForAllStatus);
			timeSheetStatusResp.setApproved(countForApproved);
			timeSheetStatusResp.setRejected(countForRejected);
			timeSheetStatusResp.setSaved(countForSaved);
			timeSheetStatusResp.setSubmitted(countForSubmitted);
			timeSheetStatusResp.setYear(year);
			tx.commit();
			logger.info("Staus Count Retrived SuccessFully");
			return timeSheetStatusResp;
		} catch (Exception e) {
			logger.info("exception:" + e);
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public TimeSheetStatusCountResponse getStatusCountBasedOnYearMonth(int userId, int year, int empId, short month) {
		Transaction tx = null;
		String queryForAllStatus = null;
		String queryForApproved = null;
		String queryForRejected = null;
		String queryForSaved = null;
		String queryForSubmitted = null;
		BigInteger countForAllStatus = null;
		BigInteger countForApproved = null;
		BigInteger countForRejected = null;
		BigInteger countForSaved = null;
		BigInteger countForSubmitted = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			TimeSheetStatusCountResponse timeSheetStatusResp = new TimeSheetStatusCountResponse();
			queryForAllStatus = "SELECT count(distinct(status)) FROM employee_work_status_entity Where ts_year=:year and rep_id=:userId and emp_id=:empId and ts_month=:month";
			queryForApproved = "SELECT count(distinct(status)) FROM employee_work_status_entity Where  ts_year=:year and emp_id=:empId and rep_id=:userId and ts_month=:month and status='approved'";
			queryForRejected = "SELECT count(distinct(status)) FROM employee_work_status_entity Where  ts_year=:year and emp_id=:empId and rep_id=:userId and ts_month=:month and status='rejected'";
			queryForSaved = "SELECT count(distinct(status)) FROM employee_work_status_entity Where  ts_year=:year and emp_id=:empId and rep_id=:userId and ts_month=:month and status='saved'";
			queryForSubmitted = "SELECT count(distinct(status)) FROM employee_work_status_entity Where ts_year=:year and emp_id=:empId and rep_id=:userId and ts_month=:month and status='submitted'";
			countForAllStatus = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForAllStatus)
					.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
					.setParameter("month", month).uniqueResult();
			countForApproved = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForApproved)
					.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
					.setParameter("month", month).uniqueResult();
			countForRejected = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForRejected)
					.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
					.setParameter("month", month).uniqueResult();
			countForSaved = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForSaved).setParameter("year", year)
					.setParameter("userId", userId).setParameter("empId", empId).setParameter("month", month)
					.uniqueResult();
			countForSubmitted = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForSubmitted)
					.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
					.setParameter("month", month).uniqueResult();
			timeSheetStatusResp.setAll(countForAllStatus);
			timeSheetStatusResp.setApproved(countForApproved);
			timeSheetStatusResp.setRejected(countForRejected);
			timeSheetStatusResp.setSaved(countForSaved);
			timeSheetStatusResp.setSubmitted(countForSubmitted);
			timeSheetStatusResp.setYear(year);
			tx.commit();
			logger.info("Staus Count Retrived SuccessFully");
			return timeSheetStatusResp;
		} catch (Exception e) {
			logger.info("exception:" + e);
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public TimeSheetStatusCountResponse getStatusCount(int userId, int year, int empId, short month) {
		Transaction tx = null;
		String queryForAllStatus = null;
		String queryForApproved = null;
		String queryForRejected = null;
		String queryForSaved = null;
		String queryForSubmitted = null;
		BigInteger countForAllStatus = null;
		BigInteger countForApproved = null;
		BigInteger countForRejected = null;
		BigInteger countForSaved = null;
		BigInteger countForSubmitted = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			TimeSheetStatusCountResponse timeSheetStatusResp = new TimeSheetStatusCountResponse();
			queryForAllStatus = "SELECT count(status) FROM employee_work_status_entity Where ts_year=:year and rep_id=:userId and emp_id=:empId and ts_month=:month";
			queryForApproved = "SELECT count(status) FROM employee_work_status_entity Where  ts_year=:year and emp_id=:empId and rep_id=:userId and ts_month=:month and status='approved'";
			queryForRejected = "SELECT count(status) FROM employee_work_status_entity Where  ts_year=:year and emp_id=:empId and rep_id=:userId and ts_month=:month and status='rejected'";
			queryForSaved = "SELECT count(status) FROM employee_work_status_entity Where  ts_year=:year and emp_id=:empId and rep_id=:userId  and ts_month=:month and status='saved'";
			queryForSubmitted = "SELECT count(status) FROM employee_work_status_entity Where ts_year=:year and emp_id=:empId and rep_id=:userId and ts_month=:month and status='submitted'";
			countForAllStatus = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForAllStatus)
					.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
					.setParameter("month", month).uniqueResult();
			countForApproved = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForApproved)
					.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
					.setParameter("month", month).uniqueResult();
			countForRejected = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForRejected)
					.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
					.setParameter("month", month).uniqueResult();
			countForSaved = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForSaved).setParameter("year", year)
					.setParameter("userId", userId).setParameter("empId", empId)
					.setParameter("month", month).uniqueResult();
			countForSubmitted = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForSubmitted)
					.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
					.setParameter("month", month).uniqueResult();
			timeSheetStatusResp.setAll(countForAllStatus);
			timeSheetStatusResp.setApproved(countForApproved);
			timeSheetStatusResp.setRejected(countForRejected);
			timeSheetStatusResp.setSaved(countForSaved);
			timeSheetStatusResp.setSubmitted(countForSubmitted);
			timeSheetStatusResp.setYear(year);
			tx.commit();
			logger.info("Staus Count Retrived SuccessFully");
			return timeSheetStatusResp;
		} catch (Exception e) {
			logger.info("exception:" + e);
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public TimeSheetStatusCountResponse getStatusCountByrepIdYearEmpIdMonthStatus(int userId, int empId, int year,
			short month, String status) {
		Transaction tx = null;
		BigInteger countForStatus = null;
		String queryForAllStatus = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			TimeSheetStatusCountResponse timeSheetStatusResp = new TimeSheetStatusCountResponse();
			if (status.equalsIgnoreCase("All")) {
				queryForAllStatus = "SELECT count(status) FROM employee_work_status_entity Where ts_year=:year and rep_id=:userId and emp_id=:empId and ts_month=:month";
				countForStatus = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForAllStatus)
						.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
						.setParameter("month", month).uniqueResult();

				timeSheetStatusResp.setAll(countForStatus);
				timeSheetStatusResp.setYear(year);
			} else if (status.equalsIgnoreCase("Approved")) {
				queryForAllStatus = "SELECT count(status) FROM employee_work_status_entity Where  ts_year=:year and emp_id=:empId and rep_id=:userId and ts_month=:month and status=:status";
				countForStatus = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForAllStatus)
						.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
						.setParameter("status", status).setParameter("month", month).uniqueResult();

				timeSheetStatusResp.setApproved(countForStatus);
				timeSheetStatusResp.setYear(year);
			} else if (status.equalsIgnoreCase("Rejected")) {
				queryForAllStatus = "SELECT count(status) FROM employee_work_status_entity Where  ts_year=:year and emp_id=:empId and rep_id=:userId and ts_month=:month and status=:status";
				countForStatus = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForAllStatus)
						.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
						.setParameter("status", status).setParameter("month", month).uniqueResult();
				timeSheetStatusResp.setApproved(countForStatus);
				timeSheetStatusResp.setYear(year);
			} else if (status.equalsIgnoreCase("Submitted")) {
				queryForAllStatus = "SELECT count(status) FROM employee_work_status_entity Where  ts_year=:year and emp_id=:empId and rep_id=:userId and ts_month=:month and status=:status";
				countForStatus = (BigInteger) DbConnect.DbCon().createSQLQuery(queryForAllStatus)
						.setParameter("year", year).setParameter("userId", userId).setParameter("empId", empId)
						.setParameter("status", status).setParameter("month", month).uniqueResult();
				timeSheetStatusResp.setApproved(countForStatus);
				timeSheetStatusResp.setYear(year);
			}
			tx.commit();
			logger.info("Staus Count Retrived SuccessFully");
			return timeSheetStatusResp;
		} catch (Exception e) {
			logger.info("exception:" + e);
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	@Override
	public ArrayList<SavingTimeSheet> getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus(int empId, int year,
			short month, short calWeek, String status) {
		logger.info("entered getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus of daoImpl class");

		List<SavingTimeSheet> lstTimeSheet;

		ArrayList<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();

		List<String> statusList = new ArrayList<>();

		try {
			String hql = null;

			if (status.equalsIgnoreCase("All")) {
				statusList.add("Approved");
				statusList.add("Rejected");
				statusList.add("Submitted");
				statusList.add("saved");
				hql = "from SavingTimeSheet where employeeId=:empId and cal_week=:calWeek and year=:year and month=:month and status in (:statusList) ";
			} else {
				statusList.add(status);

				hql = "from SavingTimeSheet where employeeId=:empId and cal_week=:calWeek and year=:year and month=:month  and status in (:statusList) ";
			}
			DbConnect.DbCon().beginTransaction();

			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("empId", empId)
					.setParameter("calWeek", calWeek).setParameter("year", year)
					.setParameterList("statusList", statusList).setParameter("month", month).getResultList();

			for (SavingTimeSheet sts : lstTimeSheet) {
				String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
				int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
				String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
				ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
				String hqlProjectName = "select p.project_Name from Project p where p.projectId =" + sts.getProjectId();
				String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
				String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
				String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
				sts.setClientId(client.getId());
				sts.setClientName(client.getClientName());
				sts.setProjectTaskName(taskName);
				sts.setProjectName(projectName);
				lstTimeSheet1.add(sts);
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	@Override
	public ArrayList<SavingTimeSheet> getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus(int empId, int year,
			short month, String status) {
		logger.info("entered getEmployeeDetailBasedOnRepIdEmpIdYearMonthCalStatus of daoImpl class");

		List<SavingTimeSheet> lstTimeSheet;

		ArrayList<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();

		List<String> statusList = new ArrayList<>();

		try {
			String hql = null;

			if (status.equalsIgnoreCase("All")) {
				statusList.add("Approved");
				statusList.add("Rejected");
				statusList.add("Submitted");
				statusList.add("saved");
				hql = "from SavingTimeSheet where employeeId=:empId and year=:year and month=:month and status in (:statusList) ";
			} else {
				statusList.add(status);

				hql = "from SavingTimeSheet where employeeId=:empId and year=:year and month=:month  and status in (:statusList) ";
			}
			DbConnect.DbCon().beginTransaction();

			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("empId", empId)
					.setParameter("year", year).setParameterList("statusList", statusList).setParameter("month", month)
					.getResultList();

			for (SavingTimeSheet sts : lstTimeSheet) {
				String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
				int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
				String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
				ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
				String hqlProjectName = "select p.project_Name from Project p where p.projectId =" + sts.getProjectId();
				String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
				String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
				String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
				sts.setClientId(client.getId());
				sts.setClientName(client.getClientName());
				sts.setProjectTaskName(taskName);
				sts.setProjectName(projectName);
				lstTimeSheet1.add(sts);
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	public ArrayList<SavingTimeSheet> getEmployeeDetailBasedOnRepIdEmpIdYearMonth(int empId, int year, short month) {
		logger.info("entered getEmployeeDetailBasedOnRepIdEmpIdYearMonth of daoImpl class");
		List<SavingTimeSheet> lstTimeSheet = null;
		ArrayList<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();
		try {
			String hql = null;
			hql = "from SavingTimeSheet where employeeId=:empId  and year=:year and month=:month  order by cal_week asc";
			DbConnect.DbCon().beginTransaction();
			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("empId", empId)
					.setParameter("year", year).setParameter("month", month).getResultList();
			if (lstTimeSheet != null) {
				for (SavingTimeSheet sts : lstTimeSheet) {
					String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
					int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
					String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
					ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
					String hqlProjectName = "select p.project_Name from Project p where p.projectId ="
							+ sts.getProjectId();
					String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
					String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
					String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
					sts.setClientId(client.getId());
					sts.setClientName(client.getClientName());
					sts.setProjectTaskName(taskName);
					sts.setProjectName(projectName);
					lstTimeSheet1.add(sts);
				}
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	@SuppressWarnings("deprecation")
	public ArrayList<SaveTimeSheetResponseClass> getEmployeeDetailBasedOnRepIdEmpIdYearMonthByEmpId(int empId, int year,
			short month) {
		logger.info("entered getEmployeeDetailBasedOnRepIdEmpIdYearMonth of daoImpl class");
		List<SavingTimeSheet> lstTimeSheet = null;
		ArrayList<SaveTimeSheetResponseClass> lstTimeSheet1 = new ArrayList<>();
		SaveTimeSheetResponseClass sts1 = new SaveTimeSheetResponseClass();
		try {
			String hql = null;
			hql = "from SavingTimeSheet where employeeId=:empId  and year=:year and month=:month  order by cal_week asc";
			DbConnect.DbCon().beginTransaction();
			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("empId", empId)
					.setParameter("year", year).setParameter("month", month).getResultList();
			if (lstTimeSheet != null) {
				for (SavingTimeSheet sts : lstTimeSheet) {
					String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
					int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
					String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
					ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
					String hqlProjectName = "select p.project_Name from Project p where p.projectId ="
							+ sts.getProjectId();
					String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
					String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
					String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
					sts1.setSaveId(sts.getSaveId());
					sts1.setEmployeeId(sts.getEmployeeId());
					sts1.setClientId(client.getId());
					sts1.setClientName(client.getClientName());
					sts1.setProjectTaskName(taskName);
					sts1.setProjectName(projectName);
					sts1.setProjectId(sts.getProjectId());
					sts1.setProjectTaskId(sts.getProjectTaskId());
					sts1.setYear(sts.getYear());
					sts1.setMonth(sts.getMonth());
					sts1.setWeekNo(sts.getWeekNo());
					sts1.setMonday(sts.getMon_date().getDate());
					sts1.setTuesday(sts.getTue_date().getDate());
					sts1.setWednesday(sts.getWed_Date().getDate());
					sts1.setThrusday(sts.getThurs_Date().getDate());
					sts1.setFriday(sts.getFri_Date().getDate());
					sts1.setSaturday(sts.getSat_Date().getDate());
					sts1.setSunday(sts.getSun_Date().getDate());
					sts1.setCal_week(sts.getCal_week());
					sts1.setMon_hours(sts.getMon_hours());
					sts1.setMon_date(sts.getMon_date());
					sts1.setTue_hours(sts.getTue_hours());
					sts1.setTue_date(sts.getTue_date());
					sts1.setWed_hours(sts.getWed_hours());
					sts1.setWed_Date(sts.getWed_Date());
					sts1.setThurs_hours(sts.getThurs_hours());
					sts1.setThurs_Date(sts.getThurs_Date());
					sts1.setFri_hours(sts.getFri_hours());
					sts1.setFri_Date(sts.getFri_Date());
					sts1.setSat_hours(sts.getSat_hours());
					sts1.setSat_Date(sts.getSat_Date());
					sts1.setSun_hours(sts.getSun_hours());
					sts1.setSun_Date(sts.getSun_Date());
					sts1.setTotalWeekHours(sts.getTotalWeekHours());
					sts1.setStatus(sts.getStatus());
					lstTimeSheet1.add(sts1);
				}
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<TimeSheetWeekResponse> getWeekMonthNameByMonthId(short monthId) {
		Transaction tx = null;
		List<Object[]> listOfWeeksMonths = null;
		List<TimeSheetWeekResponse> calWeekResponses = new ArrayList<>();
		String hql = "select cal_week,month_name from timeSheet_add_calWeek_month where month_id=" + monthId + "";
		try {
			tx = DbConnect.DbCon().beginTransaction();
			listOfWeeksMonths = DbConnect.DbCon().createSQLQuery(hql).list();
			if (listOfWeeksMonths != null) {
				for (Object[] calWeek : listOfWeeksMonths) {
					TimeSheetWeekResponse weekRes = new TimeSheetWeekResponse();
					short calWeekId = (short) calWeek[0];
					String monthName = (String) calWeek[1];
					weekRes.setCalWeek(calWeekId);
					weekRes.setMonthName(monthName);
					calWeekResponses.add(weekRes);
				}
			}
			tx.commit();
			logger.info("CalWeek MonthName Response Retrived SuccessFully");
			return calWeekResponses;
		} catch (Exception e) {
			tx.rollback();
			logger.info("CalWeek MonthName Response Retrived UnSuccessFully" + e);
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}
	}

	@Override
	public boolean saveCalWeekMonthIdAndName(TimeSheetAddCalWeekMonthBean bean) {
		Transaction tx = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			TimeSheetAddCalWeekMonthEntity entity = new TimeSheetAddCalWeekMonthEntity();
			entity.setMonthId(bean.getMonthId());
			entity.setCalWeek(bean.getCalWeek());
			entity.setMonthName(bean.getMonthName());
			DbConnect.DbCon().save(entity);
			tx.commit();
			logger.info("Saved CalWeek MonthId And Name SuccessFully");
			return true;
		} catch (Exception e) {
			tx.rollback();
			logger.info("Saved CalWeek MonthId And Name UnSuccessFully");
			return false;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	public List<TimeSheetWeekResponse> getMonth() {
		Transaction tx = null;
		List<Object[]> listOfMonths = null;
		List<TimeSheetWeekResponse> calWeekResponses = new ArrayList<>();
		String hql = "SELECT distinct(month_id),month_name FROM timeSheet_add_calWeek_month";
		try {
			tx = DbConnect.DbCon().beginTransaction();
			listOfMonths = DbConnect.DbCon().createSQLQuery(hql).list();
			if (listOfMonths != null) {
				for (Object[] calWeek : listOfMonths) {
					TimeSheetWeekResponse weekRes = new TimeSheetWeekResponse();
					short id = (short) calWeek[0];
					String monthName = (String) calWeek[1];
					weekRes.setMonthId(id);
					weekRes.setMonthName(monthName);
					calWeekResponses.add(weekRes);
				}
			}
			tx.commit();
			logger.info("Month Response Retrived SuccessFully");
			return calWeekResponses;
		} catch (Exception e) {
			tx.rollback();
			logger.info("Month Response Retrived UnSuccessFully" + e);
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}
	}

	@Override
	public ArrayList<EmpTaskResponse> getEmpTask(int userId, short calWeek) {
		logger.info("entered getLeaveStatusByUserId of daoImpl class");
		List<SavingTimeSheet> lstTimeSheet = null;
		ArrayList<EmpTaskResponse> lstTimeSheet1 = new ArrayList<>();
		try {
			String hql = null;
			hql = "from SavingTimeSheet where employeeId=:userId and cal_week=:calWeek order by cal_week desc";
			DbConnect.DbCon().beginTransaction();
			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("userId", userId)
					.setParameter("calWeek", calWeek).getResultList();
			if (lstTimeSheet != null) {
				for (SavingTimeSheet sts : lstTimeSheet) {
					EmpTaskResponse empResponse = new EmpTaskResponse();
					String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
					int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
					String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
					ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
					String hqlProjectName = "select p.project_Name from Project p where p.projectId ="
							+ sts.getProjectId();
					String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
					String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
					String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
					String hql3 = "select t.createdDate from Task t where TaskId =" + taskId;
					Date taskDate = (Date) DbConnect.DbCon().createQuery(hql3).uniqueResult();
					empResponse.setTaskName(taskName);
					empResponse.setEmpId(userId);
					empResponse.setDate(taskDate);
					lstTimeSheet1.add(empResponse);
				}
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	@Override
	public ArrayList<SavingTimeSheet> getEmpDetailsByEmpId(int empId) {
		logger.info("entered getLeaveStatusByUserId of daoImpl class");
		List<SavingTimeSheet> lstTimeSheet = null;
		ArrayList<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();
		try {
			String hql = null;
			hql = "from SavingTimeSheet where employeeId=:empId order by cal_week desc";
			DbConnect.DbCon().beginTransaction();
			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("empId", empId)
					.getResultList();
			if (lstTimeSheet != null) {
				for (SavingTimeSheet sts : lstTimeSheet) {
					String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
					int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
					String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
					ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
					String hqlProjectName = "select p.project_Name from Project p where p.projectId ="
							+ sts.getProjectId();
					String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
					String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
					String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
					sts.setClientId(client.getId());
					sts.setClientName(client.getClientName());
					sts.setProjectTaskName(taskName);
					sts.setProjectName(projectName);
					lstTimeSheet1.add(sts);
				}
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	@Override
	public ArrayList<SavingTimeSheet> getEmpDetailsByEmpIdCalweekMonthYear(int empId, int year, short month,
			short calWeek) {
		logger.info("entered getEmpDetailsByEmpIdCalweekMonthYear of daoImpl class");
		List<SavingTimeSheet> lstTimeSheet = null;
		ArrayList<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();
		try {
			String hql = null;
			hql = "from SavingTimeSheet where employeeId=:empId and cal_week=:calWeek and year=:year and month=:month order by cal_week desc";
			DbConnect.DbCon().beginTransaction();
			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("empId", empId)
					.setParameter("calWeek", calWeek).setParameter("year", year).setParameter("month", month)
					.getResultList();
			if (lstTimeSheet != null) {
				for (SavingTimeSheet sts : lstTimeSheet) {
					String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
					int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
					String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
					ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
					String hqlProjectName = "select p.project_Name from Project p where p.projectId ="
							+ sts.getProjectId();
					String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
					String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
					String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
					sts.setClientId(client.getId());
					sts.setClientName(client.getClientName());
					sts.setProjectTaskName(taskName);
					sts.setProjectName(projectName);
					lstTimeSheet1.add(sts);
				}
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	@Override
	public ArrayList<SavingTimeSheet> getEmpDetailsByEmpIdYear(int empId, int year) {
		logger.info("entered getEmpDetailsByEmpIdYear of daoImpl class");
		List<SavingTimeSheet> lstTimeSheet = null;
		ArrayList<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();
		try {
			String hql = null;
			hql = "from SavingTimeSheet where employeeId=:empId and year=:year order by cal_week desc";
			DbConnect.DbCon().beginTransaction();
			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("empId", empId)
					.setParameter("year", year).getResultList();
			if (lstTimeSheet != null) {
				for (SavingTimeSheet sts : lstTimeSheet) {
					String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
					int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
					String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
					ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
					String hqlProjectName = "select p.project_Name from Project p where p.projectId ="
							+ sts.getProjectId();
					String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
					String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
					String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
					sts.setClientId(client.getId());
					sts.setClientName(client.getClientName());
					sts.setProjectTaskName(taskName);
					sts.setProjectName(projectName);
					lstTimeSheet1.add(sts);
				}
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	@Override
	public HashMap<String, Object> getEmplTimeSheetDetails(int yea, int userId) {
		logger.info("entered into getEmplTimeSheetDetails of Dao Implementation class ");

		Short year = (short) yea;

		List<Object[]> listObject;
		HashMap<String, Object> hm = new HashMap<>();
		try {
			String hql = "select  sum(mon_duration),sum(tue_duration),sum(wed_duration),sum(thu_duration),sum(fri_duration),sum(sat_duration),"
					+ "sum(sun_duration), ts_month, ts_year, cal_week, sun_date as fromDate, sat_date as toDate, week_duration,"
					+ " emp.userfullname,emp.reporting_manager_name, status,tm_emp_timesheets.id from tm_emp_timesheets "
					+ "inner join  main_employees_summary emp on emp.user_id=tm_emp_timesheets.emp_id "
					+ "where ts_year=:year and emp_id=:userId group by cal_week asc";

			DbConnect.DbCon().beginTransaction();
			Query<Object[]> query = DbConnect.DbCon().createNativeQuery(hql);
			query.setParameter("year", year);
			query.setParameter("userId", userId);
			listObject = query.list();
			hm.put("monthlyList", listObject);
			hm.put("message", "success");
			hm.put("status", true);
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("catch block of  getEmplTimeSheetDetails of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			hm.put("message", e.getMessage());
			hm.put("status", false);
		} finally {
			DbConnect.DbCon().clear();
		}
		return hm;
	}
	public HashMap<String, Object> getEmployeeDetailBasedOnCanIdYearStatus(int empId,int year,String status){
		logger.info("entered into getEmployeeDetailBasedOnCanIdYearStatus of Dao Implementation class ");
		List<Object[]> listObject = null;
		List<String> statusList = new ArrayList<>();
		HashMap<String, Object> hm = new HashMap<>();
		try {
			String hql = null;
			if (status.equalsIgnoreCase("All")) {
				statusList.add("Approved");
				statusList.add("Rejected");
				statusList.add("Submitted");
				statusList.add("Saved");
				 hql = "select  sum(mon_duration),sum(tue_duration),sum(wed_duration),sum(thu_duration),sum(fri_duration),sum(sat_duration),"
						+ "sum(sun_duration), ts_month, ts_year, cal_week, sun_date as fromDate,sat_date as toDate,sun_date,mon_date,tue_date,wed_date,thu_date,fri_date,sat_date,week_duration,"
						+ " emp.userfullname,emp.reporting_manager_name, status,tm_emp_timesheets.id from tm_emp_timesheets "
						+ "inner join  main_employees_summary emp on emp.user_id=tm_emp_timesheets.emp_id "
						+ "where status in (:statusList) and  ts_year=:year and emp_id=:empId group by cal_week asc";
			} else {
				statusList.add(status);
				 hql = "select  sum(mon_duration),sum(tue_duration),sum(wed_duration),sum(thu_duration),sum(fri_duration),sum(sat_duration),"
						+ "sum(sun_duration), ts_month, ts_year, cal_week, sun_date as fromDate,sat_date as toDate,sun_date,mon_date,tue_date,wed_date,thu_date,fri_date,sat_date,week_duration,"
						+ " emp.userfullname,emp.reporting_manager_name, status,tm_emp_timesheets.id from tm_emp_timesheets "
						+ "inner join  main_employees_summary emp on emp.user_id=tm_emp_timesheets.emp_id "
						+ "where status in (:statusList) and ts_year=:year and emp_id=:empId group by cal_week asc";
			}

			DbConnect.DbCon().beginTransaction();
			Query<Object[]> query = DbConnect.DbCon().createNativeQuery(hql);
			query.setParameter("empId", empId);
			query.setParameter("year", year);
			query.setParameterList("statusList", statusList);
			listObject = query.getResultList();
			hm.put("monthlyList", listObject);
			hm.put("message", "success");
			hm.put("status", true);
			logger.info(listObject);
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("catch block of  getEmployeeDetailBasedOnCanIdYearStatus of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			hm.put("message", e.getMessage());
			hm.put("status", false);
		} finally {
			DbConnect.DbCon().clear();
		}
		return hm;
	}
//Status
	@SuppressWarnings({ "unchecked" })
	public  HashMap<String, Object> getEmployeeDetailBasedOnRepIdEmpIdYearMonthStatus1(int userId, int year, short month,String status) {
		logger.info("entered into getEmplTimeSheetDetails of Dao Implementation class ");
		List<Object[]> listObject = null;
		List<String> statusList = new ArrayList<>();
		HashMap<String, Object> hm = new HashMap<>();
		try {
			String hql = null;
			if (status.equalsIgnoreCase("All")) {
				statusList.add("Approved");
				statusList.add("Rejected");
				statusList.add("Submitted");
				statusList.add("Saved");
				 hql = "select  sum(mon_duration),sum(tue_duration),sum(wed_duration),sum(thu_duration),sum(fri_duration),sum(sat_duration),"
						+ "sum(sun_duration), ts_month, ts_year, cal_week, sun_date as fromDate,sat_date as toDate,sun_date,mon_date,tue_date,wed_date,thu_date,fri_date,sat_date,week_duration,"
						+ " emp.userfullname,emp.reporting_manager_name, status,tm_emp_timesheets.id from tm_emp_timesheets "
						+ "inner join  main_employees_summary emp on emp.user_id=tm_emp_timesheets.emp_id "
						+ "where status in (:statusList) and  ts_year=:year and ts_month=:month and emp_id=:userId group by cal_week asc";
			} else {
				statusList.add(status);
				 hql = "select  sum(mon_duration),sum(tue_duration),sum(wed_duration),sum(thu_duration),sum(fri_duration),sum(sat_duration),"
						+ "sum(sun_duration), ts_month, ts_year, cal_week, sun_date as fromDate,sat_date as toDate,sun_date,mon_date,tue_date,wed_date,thu_date,fri_date,sat_date,week_duration,"
						+ " emp.userfullname,emp.reporting_manager_name, status,tm_emp_timesheets.id from tm_emp_timesheets "
						+ "inner join  main_employees_summary emp on emp.user_id=tm_emp_timesheets.emp_id "
						+ "where status in (:statusList) and ts_year=:year and ts_month=:month and emp_id=:userId group by cal_week asc";
			}

			DbConnect.DbCon().beginTransaction();
			Query<Object[]> query = DbConnect.DbCon().createNativeQuery(hql);
			query.setParameter("userId", userId);
			query.setParameter("month", month);
			query.setParameter("year", year);
			query.setParameterList("statusList", statusList);
			listObject = query.getResultList();
			hm.put("monthlyList", listObject);
			hm.put("message", "success");
			hm.put("status", true);
			logger.info(listObject);
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("catch block of  getEmplTimeSheetDetails of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			hm.put("message", e.getMessage());
			hm.put("status", false);
		} finally {
			DbConnect.DbCon().clear();
		}
		return hm;
	}
	public ArrayList<SavingTimeSheet>getEmployeeDetailsBasedOnCanIdYearStatus(int empId,int year,String status){
		logger.info("entered getEmployeeDetailsBasedOnCanIdYearStatus of daoImpl class");

		List<SavingTimeSheet> lstTimeSheet;

		ArrayList<SavingTimeSheet> lstTimeSheet1 = new ArrayList<>();

		List<String> statusList = new ArrayList<>();

		try {
			String hql = null;

			if (status.equalsIgnoreCase("All")) {
				statusList.add("Approved");
				statusList.add("Rejected");
				statusList.add("Submitted");
				statusList.add("saved");
				hql = "from SavingTimeSheet where employeeId=:empId and year=:year and status in (:statusList) ";
			} else {
				statusList.add(status);

				hql = "from SavingTimeSheet where employeeId=:empId and year=:year and status in (:statusList) ";
			}
			DbConnect.DbCon().beginTransaction();

			lstTimeSheet = DbConnect.DbCon().createQuery(hql, SavingTimeSheet.class).setParameter("empId", empId)
					.setParameter("year", year).setParameterList("statusList", statusList)
					.getResultList();

			for (SavingTimeSheet sts : lstTimeSheet) {
				String hql1 = "select pt.taskId from ProjectTaskEntity pt where id=" + sts.getProjectTaskId();
				int taskId = (int) DbConnect.DbCon().createQuery(hql1).uniqueResult();
				String hqlclientId = "select p.client from Project p where p.projectId =" + sts.getProjectId();
				ClientsEntity client = (ClientsEntity) DbConnect.DbCon().createQuery(hqlclientId).uniqueResult();
				String hqlProjectName = "select p.project_Name from Project p where p.projectId =" + sts.getProjectId();
				String hql2 = "select t.taskName from Task t where TaskId =" + taskId;
				String taskName = (String) DbConnect.DbCon().createQuery(hql2).uniqueResult();
				String projectName = (String) DbConnect.DbCon().createQuery(hqlProjectName).uniqueResult();
				sts.setClientId(client.getId());
				sts.setClientName(client.getClientName());
				sts.setProjectTaskName(taskName);
				sts.setProjectName(projectName);
				lstTimeSheet1.add(sts);
			}
			DbConnect.DbCon().getTransaction().commit();
		} catch (Exception e) {
			logger.info("exception:" + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			DbConnect.DbCon().clear();
		}
		return lstTimeSheet1;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<LeaveDateResponseInTimeSheet> getLeaveDateByEmpId(int empId,int year,int month) {
		String hql=null;
		 List<LeaveDateResponseInTimeSheet> leavesDate=new ArrayList<LeaveDateResponseInTimeSheet>();
		 List<Object[]>leaveDates=null;
		try {
			StringBuilder s=new StringBuilder();
			s.append("'"+year).append("-").append("0").append(month).append("-%"+"'");
		   logger.info(s);
			hql="select from_date,to_date from main_leaverequest_summary where user_id="+empId+" and from_date like "+s+"";
			leaveDates=DbConnect.DbCon().createSQLQuery(hql).getResultList();
			for(Object[] ob: leaveDates) {
				LeaveDateResponseInTimeSheet leaveFromToDate=new LeaveDateResponseInTimeSheet();
				Date fromDate=(Date)ob[0];
				Date toDate=(Date)ob[1];
				leaveFromToDate.setLeaveFromDate(fromDate);
				leaveFromToDate.setLeaveToDate(toDate);
				leavesDate.add(leaveFromToDate);
				
			}
			return leavesDate;
		}catch(HibernateException e) {
			return null;
		}finally {
			DbConnect.DbCon().clear();
		}
		
	}
}
