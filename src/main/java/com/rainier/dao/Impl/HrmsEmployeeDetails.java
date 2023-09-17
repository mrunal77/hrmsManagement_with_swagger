package com.rainier.dao.Impl;

import com.rainier.beans.EmployeeRequestBean;
import com.rainier.beans.EmployeeResponseBean;
import com.rainier.beans.PersonalDetailsRequestBean;
import com.rainier.dao.HrmsEmployeeDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.*;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import static com.rainier.dbconfiguration.DbConnect.DbCon;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HrmsEmployeeDetails implements HrmsEmployeeDao {
	private final static Logger logger = Logger.getLogger(EmployeeDetailsEntity.class);

	// for getting All employee info list....
	public List<EmployeeDetailsEntity> getListOfEmp(int userId, String filter) {
		logger.info("entered into getListOfEmp of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			String hql = "from EmployeeDetailsEntity where isactive=1 and userId!=:userId";
			if (filter != null) {
				try {
					int roleId = Integer.parseInt(filter);
					hql = "from EmployeeDetailsEntity where isactive=1 and userId!=:userId and emprole =" + roleId;
				} catch (Exception e) {
					// System.out.println("Not Convertable to Int" + e);
					hql = "from EmployeeDetailsEntity where isactive=1 and userId!=:userId and firstName like '%"
							+ filter + "%' or lastName like'%" + filter + "%'";
				}
			}
			DbConnect.DbCon().beginTransaction();
			Query<EmployeeDetailsEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);
			query.setParameter("userId", userId);
			List<EmployeeDetailsEntity> listOfEmpDetails = query.list();
			DbConnect.DbCon().getTransaction().commit();
			// System.out.println("List of Employee details fetched");
			return listOfEmpDetails;
		} catch (Exception e) {
			logger.info("catch block of  getListOfEmp of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// System.out.println("failed to execute query" + e);
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			// System.out.println("Session status: " + DbConnect.DbCon().isOpen());
			// System.out.println("Session Factory Status: " +
			// DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}

	}

	// emp listing based on bu and dept .
	public List<EmployeeDetailsEntity> getListOfBUDUEmp(int businessunitId, int departmentId) {
		logger.info("entered into getListOfBUDUEmp of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			String hql = "from EmployeeDetailsEntity where businessunitId=:businessunitId and departmentId=:departmentId and isactive=1";
			DbConnect.DbCon().beginTransaction();
			Query<EmployeeDetailsEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);
			query.setParameter("businessunitId", businessunitId);
			query.setParameter("departmentId", departmentId);
			List<EmployeeDetailsEntity> buDuEmpList = query.list();
			DbConnect.DbCon().getTransaction().commit();
			// System.out.println("based on unit id and department id total employee listing
			// here....");
			return buDuEmpList;
		} catch (Exception e) {
			logger.info("catch block of  getListOfBUDUEmp of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// System.out.println("failed to execute query" + e);
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			// System.out.println("Session status: " + DbConnect.DbCon().isOpen());
			// System.out.println("Session Factory Status: " +
			// DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// only set Employee leave limit by super Admin or Management.
	public String insertEmployeeLeave(AllottedLeavesLogEntity leaveLog) {
		logger.info("entered into insertEmployeeLeave of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().save(leaveLog);
			DbConnect.DbCon().getTransaction().commit();
			return "Alloted leaves saved for  this employeeId";
		} catch (Exception e) {
			logger.info("catch block of  insertEmployeeLeave of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// System.out.println("Exception:" + e);
			return "failed to save ! something wrong .";
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			// System.out.println("Session status: " + DbConnect.DbCon().isOpen());
			// System.out.println("Session Factory Status: " +
			// DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// for fetching employee leave limit ...
	@Override
	public List<?> addEmployeeLeaveLimit(AllottedLeavesLogEntity leaveLimit) {
		logger.info("entered into addEmployeeLeaveLimit of Dao Implementation class ");
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			// String hql="select es.firstName,es.lastName,es.employeeId FROM
			// EmployeeDetailsEntity es JOIN AllottedLeavesLogEntity al ON
			// es.userId=al.userId and al.userId=userId";
			String hql = "select es.firstName,es.lastName,es.employeeId,al.assignedLeaves  FROM EmployeeDetailsEntity es JOIN AllottedLeavesLogEntity al ON es.userId=al.userId";
			DbConnect.DbCon().beginTransaction();
			Query<EmployeeDetailsEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);
			// query.setParameter("userId", userId);
			List<EmployeeDetailsEntity> LimitLeaveList = query.list();
			DbConnect.DbCon().getTransaction().commit();
			// System.out.println("based on the joining condition leave details of All
			// employee");
			return LimitLeaveList;
		} catch (Exception e) {
			logger.info("catch block of  addEmployeeLeaveLimit of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// System.out.println("failed to execute hql query" + e);
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			// System.out.println("Session status: " + DbConnect.DbCon().isOpen());
			// System.out.println("Session Factory Status: " +
			// DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}

	}

	// for employee profile.... based on the employeeId.

	@Override
	public List<EmployeeDetailsEntity> getEmployeeprofile(String employeeId) {
		// TODO Auto-generated method stub
		logger.info("entered into getEmployeeprofile of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			String hql = "from EmployeeDetailsEntity where employeeId=:employeeId and isactive=1";
			DbConnect.DbCon().beginTransaction();
			Query<EmployeeDetailsEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);
			query.setParameter("employeeId", employeeId);
			List<EmployeeDetailsEntity> listOfEmpProfile = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("based on employee Id employee data retieved.");
			return listOfEmpProfile;
		} catch (Exception e) {
			logger.info("catch block of  getEmployeeprofile of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// System.out.println("failed to execute query:" + e);
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			// System.out.println("Session status: " + DbConnect.DbCon().isOpen());
			// System.out.println("Session Factory Status: " +
			// DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	public List<Object[]> getAvailableLeaves(int userid) {
		logger.info("entered into getAvailableLeaves of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			String hql = "select (select numberofdays from main_employeeleavetypes where leavetype='casual leave') - "
					+ "(select IFNULL(used_casual_leaves,0) from main_employeeleaves where user_id=:userid1 and alloted_year=:year1) as casualleaves,"
					+ "(select numberofdays from main_employeeleavetypes where leavetype='sick leave') - "
					+ "(select IFNULL(used_sick_leaves,0) from main_employeeleaves where user_id=:userid2 and alloted_year=:year2) as sickleaves";

			DbConnect.DbCon().beginTransaction();
			Query sqlquery = DbConnect.DbCon().createNativeQuery(hql);
			sqlquery.setParameter("userid1", userid);
			sqlquery.setParameter("userid2", userid);
			Calendar cal = Calendar.getInstance();
			int currentYear = cal.get(Calendar.YEAR);
			sqlquery.setParameter("year1", currentYear);
			sqlquery.setParameter("year2", currentYear);
			List<Object[]> availLeavelist = sqlquery.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("based on employee Id available leaves retieved." + availLeavelist);
			return availLeavelist;
		} catch (Exception e) {
			logger.info("catch block of  getAvailableLeaves of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// // System.out.println("failed to execute query:"+e);
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
		}
	}

	// Add Employee Official Details
	public int saveEmployeeOfficialDetails(EmployeeRequestBean bean) {
		logger.info("entered into saveEmployeeOfficialDetails of Dao Implementation class ");
		try {
			Date dateOfJoining = new HrmsGetDateAndTime().GetUTCDateAsString(bean.getDateOfJoining());
			Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			StringBuilder visaDocuments = new StringBuilder();
			for (int i = 0; i < bean.getVisaDocumentId().length; i++) {
				if (i != (bean.getVisaDocumentId().length - 1))
					visaDocuments.append(bean.getVisaDocumentId()[i]).append(",");
				else
					visaDocuments.append(bean.getVisaDocumentId()[i]);
			}
			DbConnect.DbCon().beginTransaction();
			StoredProcedureQuery query = DbConnect.DbCon().createStoredProcedureQuery("Emp_Insert")
					.registerStoredProcedureParameter("employeeId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("prefixId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("firstName", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("lastName", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("fullName", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("modeOfEntry", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("roleId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("businessId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("departmentId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("reportingManagerId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("jobTitleId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("positionId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("employeeStatusId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("dateOfJoining", Date.class, ParameterMode.IN)
					.registerStoredProcedureParameter("experiance", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("officeNumber", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("extensionNumber", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("faxNumber", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("password", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("createdBy", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("createdDate", Timestamp.class, ParameterMode.IN)
					.registerStoredProcedureParameter("currencyId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("salaryType", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("salary", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("bankName", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("visaId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("VisaDocuments", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("hrManagerId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("immManagerId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("employeeStatus", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("userId", Integer.class, ParameterMode.OUT)
					.setParameter("employeeId", bean.getEmployeeId()).setParameter("prefixId", bean.getPrefixId())
					.setParameter("firstName", bean.getFirstName()).setParameter("lastName", bean.getLastName())
					.setParameter("fullName", bean.getFullName())
					.setParameter("modeOfEntry", bean.getModeOfEmployment()).setParameter("roleId", bean.getRoleId())
					.setParameter("businessId", bean.getBusinessunitId())
					.setParameter("departmentId", bean.getDepartmentId())
					.setParameter("reportingManagerId", bean.getReportingManagerId())
					.setParameter("jobTitleId", bean.getJobTitleId()).setParameter("positionId", bean.getPositionId())
					.setParameter("employeeStatusId", bean.getEmploymentStatusId())
					.setParameter("dateOfJoining", dateOfJoining).setParameter("experiance", bean.getYearOfExp())
					.setParameter("officeNumber", bean.getWorkTelephoneNo())
					.setParameter("extensionNumber", bean.getExtensionNo()).setParameter("faxNumber", bean.getFaxNo())
					.setParameter("email", bean.getEmail()).setParameter("password", bean.getPassword())
					.setParameter("createdBy", bean.getCreatedBy()).setParameter("createdDate", createdDate)
					.setParameter("currencyId", bean.getCurrencyId()).setParameter("salaryType", bean.getPayFrequency())
					.setParameter("salary", bean.getSalary()).setParameter("bankName", bean.getBankName())
					.setParameter("visaId", bean.getVisaId()).setParameter("VisaDocuments", visaDocuments.toString())
					.setParameter("hrManagerId", bean.getHrManagerId())
					.setParameter("immManagerId", bean.getImmManagerId())
					.setParameter("employeeStatus", bean.getEmployeeStatus());
			if (bean.getEmployeeStatus().equals("On Bench")) {
				BenchSalesAddCandidateEntity entity = new BenchSalesAddCandidateEntity();
				entity.setFirstName(bean.getFirstName());
				entity.setEmailAddress(bean.getEmail());
				entity.setLastName(bean.getLastName());
				entity.setWorkPhone(bean.getWorkTelephoneNo());
				DbConnect.DbCon().save(entity);
			}
			query.execute();
			Integer userId = (Integer) query.getOutputParameterValue("userId");
			logger.info(userId);
			for (int i = 0; i < bean.getVisaDocumentId().length; i++) {
				VisaDocumentsEntity entity = DbConnect.DbCon().get(VisaDocumentsEntity.class,
						bean.getVisaDocumentId()[i]);
				EmployeeDocumentsEntity docEntity = new EmployeeDocumentsEntity();
				docEntity.setUserId(userId);
				docEntity.setDocumentId(bean.getVisaDocumentId()[i]);
				docEntity.setDocumentName(entity.getDocumentName());
				docEntity.setIsExpires(1);
				docEntity.setCreatedBy(bean.getCreatedBy());
				docEntity.setCreatedDate(createdDate);
				DbConnect.DbCon().save(docEntity);
			}
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Official Information Insertion Successful.");
			return userId;
		} catch (Exception e) {
			logger.info("catch block of  saveEmployeeOfficialDetails of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("Conflicts in saving Details." + e);
			return 0;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// Update Employee Officials Details
	@Override
	public String updateEmployeeOfficialDetails(EmployeeRequestBean bean) {
		logger.info("entered into updateEmployeeOfficialDetails() for Updating the Employee Officials Details");
		try {
			Date dateOfJoining = new HrmsGetDateAndTime().GetUTCDateAsString(bean.getDateOfJoining());
			Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			StringBuilder visaDocuments = new StringBuilder();
			for (int i = 0; i < bean.getVisaDocumentId().length; i++) {
				if (i != (bean.getVisaDocumentId().length - 1))
					visaDocuments.append(bean.getVisaDocumentId()[i]).append(",");
				else
					visaDocuments.append(bean.getVisaDocumentId()[i]);
			}
			logger.info(dateOfJoining);
			DbConnect.DbCon().beginTransaction();
			StoredProcedureQuery query = DbConnect.DbCon().createStoredProcedureQuery("Emp_Update")
					.registerStoredProcedureParameter("UserId", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Prefix_id", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Firstname", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Lastname", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Userfullname", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Modeofentry", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Role", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Business_id", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Department_id", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Reporting_manager", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Job_title_id", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Position_id", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Emp_status_id", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Date_of_joining", Date.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Years_exp", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Office_number", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Extension_number", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Office_faxnumber", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Modifiedby",Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("ModifiedOn",Timestamp.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Currencyid", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Salarytype", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Salary", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Bankname", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("VisaID", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Documents", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("hr_manager", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("imm_manager", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("employee_status", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("messages",String.class, ParameterMode.OUT)
					.setParameter("UserId", bean.getUserId()).setParameter("Prefix_id", bean.getPrefixId())
					.setParameter("Firstname", bean.getFirstName()).setParameter("Lastname", bean.getLastName())
					.setParameter("Userfullname", bean.getFullName())
					.setParameter("Modeofentry", bean.getModeOfEmployment())
					.setParameter("Role", bean.getRoleId()).setParameter("Business_id", bean.getBusinessunitId())
					.setParameter("Department_id", bean.getDepartmentId())
					.setParameter("Reporting_manager", bean.getReportingManagerId())
					.setParameter("Job_title_id", bean.getJobTitleId()).setParameter("Position_id", bean.getPositionId())
					.setParameter("Emp_status_id", bean.getEmploymentStatusId())
					.setParameter("Date_of_joining", dateOfJoining).setParameter("Years_exp", bean.getYearOfExp())
					.setParameter("Office_number", bean.getWorkTelephoneNo())
					.setParameter("Extension_number", bean.getExtensionNo())
					.setParameter("Office_faxnumber", bean.getFaxNo())
					.setParameter("Modifiedby", bean.getModifiedBy())
					.setParameter("ModifiedOn",modifiedDate)
					.setParameter("Currencyid", bean.getCurrencyId())
					.setParameter("Salarytype", bean.getPayFrequency())
					.setParameter("Salary", bean.getSalary())
					.setParameter("Bankname", bean.getBankName())
					.setParameter("VisaID", bean.getVisaId())
					.setParameter("Documents",visaDocuments.toString())
					.setParameter("hr_manager", bean.getHrManagerId())
					.setParameter("imm_manager", bean.getImmManagerId())
					.setParameter("employee_status",bean.getEmployeeStatus());
			query.execute();
			String message = (String)query.getOutputParameterValue("messages");
			logger.info(message);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Official Information Updated Successful.");
			return message;
		} catch (Exception e) {
			logger.info("Official Information Not Updated Successful.");
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("Conflicts in Updating Details." + e);
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	public void insertMailLog(MailLogEntity mailLog) {
		logger.info("entered into insertMailLog of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().save(mailLog);
			// System.out.println("Inserting Complete");
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Email Log Insertion Successful.");
		} catch (HibernateException e) {
			logger.error("Conflicts in saving MailLog." + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// Save Employee Personal Details
	public boolean saveEmpPersonalDetails(PersonalDetailsRequestBean bean) {
		logger.info("entered into saveEmpPersonalDetails of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		EmpPersonalDetailsEntity entity = new EmpPersonalDetailsEntity();
		try {
			DbConnect.DbCon().beginTransaction();
			// entity.setId(bean.getId());
			entity.setUserId(bean.getUserId());
			entity.setGenderId(DbConnect.DbCon().get(GenderEntity.class, bean.getGenderId()));
			entity.setMaritalStatusId(DbConnect.DbCon().get(MaritalStatusEntity.class, bean.getMaritalStatusId()));
			entity.setNationalityId(DbConnect.DbCon().get(NationalityEntity.class, bean.getNationalityId()));
			entity.setEthinicCodeId(DbConnect.DbCon().get(EthinicCodeEntity.class, bean.getEthinicCodeId()));
			entity.setRaceCodeId(DbConnect.DbCon().get(RaceCodeEntity.class, bean.getRaceCodeId()));
			entity.setLanguageId(DbConnect.DbCon().get(LanguageEntity.class, bean.getLanguageId()));
			/*
			 * entity.setDateOfBirth(new Date(new
			 * SimpleDateFormat("yyyy-MM-dd").parse(bean.getDateOfBirth()).getTime()));
			 * entity.setBloodGroup(bean.getBloodGroup());
			 * entity.setIdentityDocuments(bean.getPassport() + ":" +
			 * bean.getPassportExpDate() + "," + bean.getDrivingLicence() + ":" +
			 * bean.getDrivingLicenceExpDate());
			 */
			entity.setBloodGroup(bean.getBloodGroup());
			entity.setDateOfBirth(bean.getDateOfBirth());
			entity.setPassport(bean.getPassport());
			entity.setPassportExpDate(bean.getPassportExpDate());
			entity.setDrivingLicence(bean.getDrivingLicence());
			entity.setDrivingLicenceExpDate(bean.getDrivingLicenceExpDate());
			entity.setCreatedBy(bean.getUserId());
			entity.setModifiedBy(bean.getUserId());
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsActive(1);
			DbConnect.DbCon().save(entity);
			// System.out.println("Inserting Complete");
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Employee Personal details Insertion Successful.");
			return true;
		} catch (Exception e) {
			logger.error("Conflicts in saving Employee Personal Details." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// Read Employee Personal Details
	public List<EmpPersonalDetailsEntity> getEmpPersonalDetails(int userId) {
		logger.info("entered into getEmpPersonalDetails of Dao Implementation class ");

		// DbConnect db = new DbConnect();
		try {
			/*
			 * String sql =
			 * "select p.user_id,p.dob,p.bloodgroup,p.identity_documents,g.gendername," +
			 * "n.nationalitycode,ms.maritalstatusname,ec.ethnicname,rc.racename,lang.languagename, "
			 * + "p.id, p.genderid, p.maritalstatusid, p.nationalityid, p.ethniccodeid, " +
			 * "p.racecodeid, p.languageid from main_emppersonaldetails p inner join main_gender g "
			 * +
			 * "on g.id=p.genderid inner join main_nationality n on n.id=p.nationalityid inner join "
			 * +
			 * "main_maritalstatus ms on ms.id=p.maritalstatusid inner join main_ethniccode ec on "
			 * +
			 * "ec.id=p.ethniccodeid inner join main_racecode rc on rc.id=p.racecodeid inner join "
			 * + "main_language lang on lang.id=p.languageid and p.user_id=:userId";
			 */
			String hql = "from EmpPersonalDetailsEntity epd where epd.userId=:userid and epd.isActive =1";
			DbConnect.DbCon().beginTransaction();
			// Query query = db.DbCon().createNativeQuery(sql).setParameter("userId",
			// userId);
			List<EmpPersonalDetailsEntity> listOfEmpPersonalDetail = DbConnect.DbCon()
					.createQuery(hql, EmpPersonalDetailsEntity.class).setParameter("userid", userId).getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Employee Personal Details retrived successfully.");
			return listOfEmpPersonalDetail;
		} catch (Exception e) {
			logger.info("catch block of  getEmpPersonalDetails of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// System.out.println("failed to execute query:" + e);
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			// System.out.println("Session status: " + DbConnect.DbCon().isOpen());
			// System.out.println("Session Factory Status: " +
			// DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// update Employee Personal Details
	public boolean updateEmpPersonalDetails(PersonalDetailsRequestBean bean) {
		logger.info("entered into updateEmpPersonalDetails of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		EmpPersonalDetailsEntity entity = new EmpPersonalDetailsEntity();
		try {
			DbConnect.DbCon().beginTransaction();
			entity.setId(bean.getId());
			entity.setUserId(bean.getUserId());
			entity.setGenderId(DbConnect.DbCon().get(GenderEntity.class, bean.getGenderId()));
			entity.setMaritalStatusId(DbConnect.DbCon().get(MaritalStatusEntity.class, bean.getMaritalStatusId()));
			entity.setNationalityId(DbConnect.DbCon().get(NationalityEntity.class, bean.getNationalityId()));
			entity.setEthinicCodeId(DbConnect.DbCon().get(EthinicCodeEntity.class, bean.getEthinicCodeId()));
			entity.setRaceCodeId(DbConnect.DbCon().get(RaceCodeEntity.class, bean.getRaceCodeId()));
			entity.setLanguageId(DbConnect.DbCon().get(LanguageEntity.class, bean.getLanguageId()));
			/*
			 * entity.setDateOfBirth(new Date(new
			 * SimpleDateFormat("yyyy-MM-dd").parse(bean.getDateOfBirth()).getTime()));
			 * entity.setBloodGroup(bean.getBloodGroup());
			 * entity.setIdentityDocuments(bean.getPassport() + ":" +
			 * bean.getPassportExpDate() + "," + bean.getDrivingLicence() + ":" +
			 * bean.getDrivingLicenceExpDate());
			 */
			entity.setDateOfBirth(bean.getDateOfBirth());
			entity.setPassport(bean.getPassport());
			entity.setPassportExpDate(bean.getPassportExpDate());
			entity.setDrivingLicence(bean.getDrivingLicence());
			entity.setDrivingLicenceExpDate(bean.getDrivingLicenceExpDate());
			entity.setCreatedBy(bean.getUserId());
			entity.setModifiedBy(bean.getUserId());
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsActive(1);
			DbConnect.DbCon().update(entity);
			// System.out.println("Inserting Complete");
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Employee Personal details Update Successful.");
			return true;
		} catch (Exception e) {
			logger.error("Conflicts in updating Employee Personal Details." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// Save EmpContact Details
	public int saveEmpContactDetails(CommunicationInfoEntity entity) {
		logger.info("entered into saveEmpContactDetails of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().save(entity);
			// System.out.println("Inserting Complete");
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Employee Contact details Saved Successful.");
			return 1;
		} catch (Exception e) {
			logger.error("Conflicts in Saving Employee Contact Details." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return 0;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// Update EmpContact Details
	public int updateEmpContactDetails(CommunicationInfoEntity entity) {
		logger.info("entered into updateEmpContactDetails of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().update(entity);
			// System.out.println("Updating Complete");
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Employee Contact details update Successful.");
			return 1;
		} catch (Exception e) {
			logger.error("Conflicts in updating Employee Contact Details." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return 0;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// Read Contact Details
	public List<Object[]> getEmpContactDetails(int userId) {
		logger.info("entered into getEmpContactDetails of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			String sql = "select ecom.id, ecom.user_id, ecom.personalemail,ecom.perm_streetaddress,"
					+ "ecom.perm_country,pc.country as p_country,ecom.perm_state,ps.state as p_state, ecom.perm_city, pv.city as p_city, "
					+ "ecom.perm_pincode, ecom.current_streetaddress,ecom.current_country,cc.country as c_country,"
					+ "ecom.current_state,cs.state as c_state, ecom.current_city, cv.city as c_city, ecom.current_pincode, "
					+ "ecom.emergency_number, ecom.emergency_name, ecom.emergency_email from "
					+ "main_empcommunicationdetails ecom inner join  main_countries pc on "
					+ "ecom.perm_country = pc.country_id_org inner join main_states ps on "
					+ "ecom.perm_state = ps.state_id_org inner join main_cities pv on "
					+ "ecom.perm_city = pv.city_org_id inner join  main_countries cc on "
					+ "ecom.current_country = cc.country_id_org inner join main_states cs on "
					+ "ecom.current_state = cs.state_id_org inner join main_cities cv on "
					+ "ecom.current_city = cv.city_org_id where ecom.user_id=:userId";
			DbConnect.DbCon().beginTransaction();
			Query query = DbConnect.DbCon().createNativeQuery(sql).setParameter("userId", userId);
			List<Object[]> listOfContactDetails = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Employee Contact Details retrived successfully.");
			return listOfContactDetails;
		} catch (Exception e) {

			logger.info("catch block of  getEmpContactDetails of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// System.out.println("failed to execute query:" + e);
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			// System.out.println("Session status: " + DbConnect.DbCon().isOpen());
			// System.out.println("Session Factory Status: " +
			// DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// Fetch Employee Salary Details
	public SalaryDetailsEntity getEmpSalaryDetails(int userId) {
		logger.info("entered into getEmpSalaryDetails of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			String hql = "from SalaryDetailsEntity where userId = :userId";

			Transaction tx = DbConnect.DbCon().beginTransaction();
			SalaryDetailsEntity enity = DbConnect.DbCon().createQuery(hql, SalaryDetailsEntity.class)
					.setParameter("userId", userId).uniqueResult();
			tx.commit();
			// System.out.println("Fetched Salary Details.");
			return enity;
		} catch (Exception e) {
			logger.info("catch block of  getEmpSalaryDetails of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// System.out.println("failed to execute hql query" + e);
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			// System.out.println("Session status: " + DbConnect.DbCon().isOpen());
			// System.out.println("Session Factory Status: " +
			// DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// Update Salary Details
	public int updateSalaryDetails(SalaryDetailsEntity entity) {
		logger.info("entered into updateSalaryDetails of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			Transaction tx = DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().update(entity);
			// System.out.println("Updating Complete");
			tx.commit();
			logger.info("Employee Salary details update Successful.");
			return 1;
		} catch (Exception e) {
			logger.error("Conflicts in updating Employee Salary Details." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return 0;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}

	}

	public List<Object[]> getAdditionalDetails(int userId) {

		logger.info("entered into getAdditionalDetails of Dao Implementation class ");
		// DbConnect db = new DbConnect();
		try {
			String sql = "select s.currencyid, c.currencyname ,s.salarytype, p.freqtype ,salary ,bankname ,visa_id ,visa_name,employeeId\r\n"
					+ " from main_empsalarydetails s , main_employees_summary ss, main_currency c, main_payfrequency p where \r\n"
					+ " s.user_id=ss.user_id\r\n" + " and ss.user_id=:userId\r\n" + " and s.currencyid = c.id\r\n"
					+ " and s.salarytype = p.id; ";
			Transaction tx = DbConnect.DbCon().beginTransaction();
			Query query = DbConnect.DbCon().createNativeQuery(sql).setParameter("userId", userId);
			List<Object[]> additionalDetail = query.list();
			tx.commit();
			logger.info("Employee Additional details Successfully retrieved.");
			return additionalDetail;
		} catch (Exception e) {
			logger.error("Error in fetching Employee Additional Details." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	@Override
	public boolean updateSignaturePath(String filePath, int userId) {
		logger.info("updateSignaturePath() of Dao Implementation.");
		Transaction tx = DbConnect.DbCon().getTransaction();
		try {
			tx.begin();
			User employee = DbConnect.DbCon().load(User.class, userId);
			employee.setSignature(filePath);
			DbConnect.DbCon().update(employee);
			EmployeeDetailsEntity dataEmployee = (EmployeeDetailsEntity) DbConnect.DbCon()
					.createCriteria(EmployeeDetailsEntity.class).add(Restrictions.eq("userId", userId)).uniqueResult();
			dataEmployee.setSignature(filePath);
			DbConnect.DbCon().update(dataEmployee);
			tx.commit();
			logger.info("Success.. siganture Uploaded.");
			return true;
		} catch (HibernateException e) {
			logger.error("Signature path updating Failed.");
			tx.rollback();
			return false;
		} finally {
			logger.debug("Transaction Status:" + tx.isActive());
			DbConnect.DbCon().clear();
		}
	}

	// Hr Manager will get only that no of Employee whoever added by himself.
	@Override
	public List<EmployeeDetailsEntity> getListOfEmpAddedByHrMgr(String hrManagerName) {
		logger.info("entered into getListOfEmpAddedByHrMgr of Dao Implementation class ");

		try {
			String hql = "from EmployeeDetailsEntity where isactive=1 and hrManagerName=:hrManagerName";

			DbConnect.DbCon().beginTransaction();
			Query<EmployeeDetailsEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);

			query.setParameter("hrManagerName", hrManagerName);
			List<EmployeeDetailsEntity> listOfEmpDetailsforHr = query.list();
			DbConnect.DbCon().getTransaction().commit();

			return listOfEmpDetailsforHr;
		} catch (Exception e) {
			logger.info("catch block of  getListOfEmp of Dao Implementation class" + e);
			DbConnect.DbCon().getTransaction().rollback();

			return null;
		} finally {

			DbConnect.DbCon().clear();
		}

	}

	// Email Address Duplicate Check Service for Adding Employee.
	@Override
	public EmployeeDetailsEntity getDuplicateEmailAddress(String emailAddress) {
		try {
			String hql = "From EmployeeDetailsEntity where emailAddress=:email and isActive=1";
			DbConnect.DbCon().beginTransaction();
			Query query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);
			query.setParameter("email", emailAddress);
			EmployeeDetailsEntity existedEmail = (EmployeeDetailsEntity) query.uniqueResult();
			DbConnect.DbCon().getTransaction().commit();

			logger.info("Unique Email Finded.");
			return existedEmail;

		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	@Override
	public List<User> getListOfSellsExecutive() {
		List<User> list = null;
		try {
			logger.info("entered into getListOfUserName of Dao Implementation class ");
			// DbConnect db = new DbConnect();

			String hql = "From User where position_name='Sales Executive'";
			DbConnect.DbCon().beginTransaction();
			Query<User> query = DbConnect.DbCon().createQuery(hql, User.class);
			list = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully Sells Executive List retrived.");
			return list;
		} catch (Exception e) {
			logger.error("failed to fetch Sells Executive details:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// db.DbCon().close();
			// db.dbSessionFactory().close();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
			DbCon().clear();
		}

	}
	//Empolyee Soft Delete Service 
	@Override
	public int softDeleteEmployee(int userId) {
		Transaction tx = null;
		String hql = null;
		Query query = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			hql = "UPDATE User SET isactive =0  WHERE isactive=1 and  Id =" + userId + "";
			query = DbConnect.DbCon().createQuery(hql);
			query.executeUpdate();
			logger.info("Employee Soft Deleted SuccessFully");
			tx.commit();
			return 1;
		} catch (HibernateException e) {
			logger.error("Employee Soft Deleted UnSuccessfully");
			tx.rollback();
			return 0;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Integer getReportingMangerIdByUserId(int userId) {
		Transaction tx = null;
		String hql = null;
		Integer repId = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			hql = "select reporting_manager from main_employees_summary  where user_id=:userId";
			repId = (Integer) DbConnect.DbCon().createSQLQuery(hql).setParameter("userId", userId).uniqueResult();
			logger.info("ReportinManger Id Get SuccessFully");
			tx.commit();
			return repId;
		} catch (HibernateException e) {
			logger.error("ReportinManger Id Get UnSuccessFully");
			tx.rollback();
			return repId;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	@Override
	public List<EmployeeDetailsEntity> getAllInActiveEmp() {
		List<EmployeeDetailsEntity> list = null;
		Transaction tx=null;
		try {
			logger.info("entered into getAllInActiveEmp of Dao Implementation class ");
			tx=DbConnect.DbCon().beginTransaction();
			String hql = "From EmployeeDetailsEntity e where e.isactive=0";
			Query<EmployeeDetailsEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);
			list = query.list();
			tx.commit();
			logger.info("All InActive Employee Retrived SuccessFully");
			return list;
		} catch (Exception e) {
			logger.error("failed to fetch All InActive Employee  details:" + e);
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	@Override
	public List<EmployeeResponseBean> getAllEmpByRepId(int repId) {
		List<EmployeeResponseBean> listOfEmp=new ArrayList<>();
		Transaction tx=null;
		try {
			logger.info("entered into getAllInActiveEmp of Dao Implementation class ");
			tx=DbConnect.DbCon().beginTransaction();
			String hql = "select user_id,userfullname From main_employees_summary  where reporting_manager="+repId+" and isactive=1";
			@SuppressWarnings("deprecation")
			List<Object[]> query = DbConnect.DbCon().createSQLQuery(hql).list();
			for(Object[] ob:query) {
				EmployeeResponseBean emResp=new EmployeeResponseBean();
				Integer empId=(Integer)ob[0];
				String empName=(String)ob[1];
				emResp.setUserId(empId);
				emResp.setEmployeeName(empName);
				listOfEmp.add(emResp);
			}
			tx.commit();
			logger.info("All  Employee Retrived SuccessFully");
			return listOfEmp;
		} catch (Exception e) {
			logger.error("failed to fetch All  Employee  details:" + e);
			tx.rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	@Override
	public java.util.Date getDateOfJoiningOfEmp(int userId) {
		Transaction tx = null;
		String hql = null;
		Date dateOfJoining = null;
		try {
			tx = DbConnect.DbCon().beginTransaction();
			hql = "select date_of_joining from main_employees_summary  where user_id=:userId";
			dateOfJoining = (Date) DbConnect.DbCon().createSQLQuery(hql).setParameter("userId", userId).uniqueResult();
			logger.info("ReportinManger Id Get SuccessFully");
			tx.commit();
			return dateOfJoining;
		} catch (HibernateException e) {
			logger.error("ReportinManger Id Get UnSuccessFully");
			tx.rollback();
			return dateOfJoining;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

}
