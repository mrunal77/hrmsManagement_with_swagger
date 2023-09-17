package com.rainier.businesslogic;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import com.rainier.beans.BU_DU_EmpListResponse;
import com.rainier.beans.CommonResponseBean;
import com.rainier.beans.ContactDetailsRequestBean;
import com.rainier.beans.ContactDetailsResponseBean;
import com.rainier.beans.EmpOfficialDetailsBean;
import com.rainier.beans.EmployeeDataBean;
import com.rainier.beans.EmployeeDetailsResponseBean;
import com.rainier.beans.EmployeeIdAutoGenerationResponseBean;
import com.rainier.beans.EmployeeRequestBean;
import com.rainier.beans.EmployeeResponseBean;
import com.rainier.beans.EntityStatusBean;
import com.rainier.beans.FetchAddEmpleaveRequestBean;
import com.rainier.beans.LeaveAllottedRequestBean;
import com.rainier.beans.LeaveResponseBean;
import com.rainier.beans.PersonalDetailsRequestBean;
import com.rainier.beans.PersonalDetailsResponseBean;
import com.rainier.beans.SalaryDetailsBean;
import com.rainier.beans.SuccessResponseBean;
import com.rainier.dao.HrmsBenchSalesDao;
import com.rainier.dao.HrmsEmployeeDao;
import com.rainier.dao.HrmsListOfValuesDao;
import com.rainier.dao.Impl.HrmsBenchSalesDaoImpl;
import com.rainier.dao.Impl.HrmsEmployeeDetails;
import com.rainier.dao.Impl.HrmsListOfValues;
import com.rainier.dao.Impl.HrmsUserAuthentication;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.AllottedLeavesLogEntity;
import com.rainier.entities.CommunicationInfoEntity;
import com.rainier.entities.EmpPersonalDetailsEntity;
import com.rainier.entities.EmployeeDetailsEntity;
import com.rainier.entities.MailLogEntity;
import com.rainier.entities.Privileges;
import com.rainier.entities.SalaryDetailsEntity;
import com.rainier.entities.User;
import com.rainier.entities.UserRole;
import com.rainier.utility.AutoPasswordGenerator;
import com.rainier.utility.HrmsGetDateAndTime;
import com.rainier.utility.MD5Generator;
import com.rainier.utility.SendMail;

public class EmployeeDetailsSummary {

	private static final Logger logger = Logger.getLogger(EmployeeDetailsSummary.class);
	private static final HrmsEmployeeDao hed = new HrmsEmployeeDetails();

	public Response getOfficialdetailsOfEmployee(int roleId, int menuId, int userId, String filter) {

		logger.info("entered into getOfficialdetailsOfEmployee method of BussinessLogic Class ");
		EmployeeDetailsResponseBean responseBean = new EmployeeDetailsResponseBean();
		// EmployeeDetailsEntity listEmp = new EmployeeDetailsEntity();
		try {
			List<EmployeeDetailsEntity> empListData = hed.getListOfEmp(userId, filter);
			ArrayList<EmployeeDataBean> empArrayList = new ArrayList<EmployeeDataBean>();
			List<Privileges> listPrivileges = new HrmsUserAuthentication().getPrivileges(roleId, menuId);
			if (empListData.size() != 0 && !listPrivileges.isEmpty()) {
				for (EmployeeDetailsEntity details : empListData) {
					EmployeeDataBean employeeDataBean = new EmployeeDataBean();
					employeeDataBean.setId(details.getId());
					employeeDataBean.setDepartmentId(details.getDepartmentId());
					// // System.out.println(details.getWorkTelephoneNo());
					employeeDataBean.setContactNumber(details.getWorkTelephoneNo());
					employeeDataBean.setDepartmentName(details.getDepartmentName());
					employeeDataBean.setDesignation(details.getDesignation());
					employeeDataBean.setEmail(details.getEmail());
					employeeDataBean.setEmployeeName(details.getEmployeeName());
					employeeDataBean.setEmploymentStatus(details.getEmploymentStatus());
					employeeDataBean.setFirstName(details.getFirstName());
					employeeDataBean.setLastName(details.getLastName());
					employeeDataBean.setPrefix(details.getPrefix());
					employeeDataBean.setRole(details.getRole());
					employeeDataBean.setEmployeeId(details.getEmployeeId());
					employeeDataBean.setUserId(details.getUserId());
					employeeDataBean.setProfileImage(details.getProfileImg());
					empArrayList.add(employeeDataBean);
				}
				responseBean.setMessage("Employee details Retrieved Successfully");
				responseBean.setStatus(true);
				responseBean.setEmployeeList(empArrayList);
				responseBean.setPrivilegesList(listPrivileges);
			} else {
				responseBean.setMessage("Employee details fetching something problem!!");
				responseBean.setStatus(false);
				responseBean.setEmployeeList(empArrayList);
				responseBean.setPrivilegesList(listPrivileges);
			}
		} catch (Exception e) {
			logger.info("logging the occured exception in businessClass  getOfficialdetailsOfEmployee method::" + e);
			e.printStackTrace();
			responseBean.setMessage("Error while fetching employee  details - " + e.getMessage());
			responseBean.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(responseBean).build();
	}

	// emp listing based on bu and du.....
	public Response getEmpDataBUDUInfo(int businessunitId, int departmentId) {
		logger.info("entered into getEmpDataBUDUInfo method of business class ... ");
		BU_DU_EmpListResponse responseBean = new BU_DU_EmpListResponse();
		// HrmsEmployeeDao hed = new HrmsEmployeeDetails();
		try {
			List<EmployeeDetailsEntity> listOfBUDUEmp = hed.getListOfBUDUEmp(businessunitId, departmentId);
			if (listOfBUDUEmp.size() != 0) {
				responseBean.setBUDUEmpList(listOfBUDUEmp);
				responseBean.setMessage("emp retrieved ...");
				responseBean.setStatus(true);

			} else {
				responseBean.setBUDUEmpList(listOfBUDUEmp);
				responseBean.setMessage(
						"this employee not in this unit and department id search in any other department ...");
				responseBean.setStatus(false);
			}
		} catch (Exception e) {
			logger.info("looging exception occured in getEmpDataBUDUInfo method of business class:: " + e);
			e.printStackTrace();
			responseBean.setMessage("Error while fetching budu based employee details - " + e.getMessage());
			responseBean.setStatus(false);

		}

		return Response.status(Response.Status.OK).entity(responseBean).build();

	}

	// saving Add Employee Leave for HR module.
	public Response getEmployeeLeave(LeaveAllottedRequestBean leaveBean) {

		logger.info("entered into getEmployeeLeave method of businessLogic class ");
		AllottedLeavesLogEntity entityBean = new AllottedLeavesLogEntity();
		// HrmsEmployeeDao hed = new HrmsEmployeeDetails();
		LeaveResponseBean response = new LeaveResponseBean();
		try {
			BeanUtils.copyProperties(entityBean, leaveBean);
			String inserted = hed.insertEmployeeLeave(entityBean);
			logger.info(inserted);
			// System.out.println(inserted);
			response.setMessage("employee leave saved successfully");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.info(
					"logging exception occured, it is catch block of  getEmployeeLeave method of businessLogic class "
							+ e);
			e.printStackTrace();
			response.setMessage("Error while saving emp leave - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.info(
					"logging exception occured, it is catch block of  getEmployeeLeave method of businessLogic class "
							+ e);

			e.printStackTrace();
			response.setMessage("Error while saving BU details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// Adding all employee leave limit set by super Admin or Management.

	/*
	 * public Response addLeaveLimitByAdmin(FetchAddEmpleaveRequestBean addLimit) {
	 * AllottedLeavesLogEntity entityBean=new AllottedLeavesLogEntity();
	 * logger.info("entered into addLeaveLimitByAdmin method of business class");
	 * LeaveResponseBean responseBean = new LeaveResponseBean(); HrmsEmployeeDao
	 * hed=new HrmsEmployeeDetails(); //AllottedLeavesLogEntity leaveLimit = null;
	 * //int userId = 0; try { List<FetchAddEmpleaveRequestBean>
	 * listOfAddedEmpLeave=(List<FetchAddEmpleaveRequestBean>)
	 * hed.addEmployeeLeaveLimit(entityBean); ArrayList<FetchAddEmpleaveRequestBean>
	 * leaveAl=new ArrayList<FetchAddEmpleaveRequestBean>();
	 * if(listOfAddedEmpLeave.size()!=0) { for(FetchAddEmpleaveRequestBean
	 * leave:listOfAddedEmpLeave) { FetchAddEmpleaveRequestBean requestBean=new
	 * FetchAddEmpleaveRequestBean();
	 * requestBean.setFirstName(leave.getFirstName());
	 * requestBean.setLastname(leave.getLastname());
	 * requestBean.setEmployeeId(leave.getEmployeeId());
	 * requestBean.setAssignedLeaves(leave.getAssignedLeaves());
	 * leaveAl.add(requestBean); responseBean.setMessage("leave Limit is this :");
	 * responseBean.setStatus(true);
	 * responseBean.setAddEmployeeLeaveLimit(listOfAddedEmpLeave); } } else {
	 * responseBean.setMessage("leave Limit  is not set for  this :");
	 * responseBean.setStatus(false);
	 * responseBean.setAddEmployeeLeaveLimit(listOfAddedEmpLeave);
	 *
	 * } }catch(Exception e) { e.printStackTrace();
	 * responseBean.setMessage("Error while fetching leave details - " +
	 * e.getMessage()); responseBean.setStatus(false);
	 *
	 * }
	 *
	 * return Response.status(Response.Status.OK).entity(responseBean).build();
	 *
	 * }
	 */

	// Adding all employee leave limit set by super Admin or Management.

	public Response addLeaveLimitByAdmin(FetchAddEmpleaveRequestBean addLimit) {
		logger.info("entered into addLeaveLimitByAdmin method of business class.. ");
		AllottedLeavesLogEntity entityBean = new AllottedLeavesLogEntity();

		LeaveResponseBean responseBean = new LeaveResponseBean();
		// HrmsEmployeeDao hed = new HrmsEmployeeDetails();
		// AllottedLeavesLogEntity leaveLimit = null;
		// int userId = 0;
		try {
			List<FetchAddEmpleaveRequestBean> listOfAddedEmpLeave = (List<FetchAddEmpleaveRequestBean>) hed
					.addEmployeeLeaveLimit(entityBean);
			if (listOfAddedEmpLeave.size() != 0) {
				responseBean.setMessage("leave Limit is allotted by your Authorised employee!! :");
				responseBean.setStatus(true);
				responseBean.setAddEmployeeLeaveLimit(listOfAddedEmpLeave);

			} else {
				responseBean.setMessage("leave Limit  is not set kindly contact to your HR. :");
				responseBean.setStatus(false);
				responseBean.setAddEmployeeLeaveLimit(listOfAddedEmpLeave);

			}
		} catch (Exception e) {
			logger.info("exception occured in addLeaveLimitByAdmin method of business class::: " + e);
			e.printStackTrace();
			responseBean.setMessage("Error while fetching leave details - " + e.getMessage());
			responseBean.setStatus(false);

		}

		return Response.status(Response.Status.OK).entity(responseBean).build();

	}

	// for employee profile based on the employeeId....
	public Response getEmployeeProfile(String employeeId) {
		logger.info("entered into getEmployeeProfile method of business class.. ");
		EmployeeDetailsResponseBean responseBean = new EmployeeDetailsResponseBean();
		int userId = 0;
		try {
			List<EmployeeDetailsEntity> empProfileList = hed.getEmployeeprofile(employeeId);
			ArrayList<EmpOfficialDetailsBean> profileAl = new ArrayList<EmpOfficialDetailsBean>();
			if (!empProfileList.isEmpty()) {
				for (EmployeeDetailsEntity profile : empProfileList) {
					EmpOfficialDetailsBean bean = new EmpOfficialDetailsBean();
					bean.setProfileImg(profile.getProfileImg());
					bean.setSignature(profile.getSignature());
					bean.setContactNumber(profile.getContactnumber());
					bean.setBusinessunitId(profile.getBusinessunitId());
					bean.setBusinessunitName(profile.getBusinessunitName());
					bean.setDateOfJoining(profile.getDateOfJoining());
					bean.setDateOfleaving(profile.getDateOfleaving());
					bean.setDepartmentId(profile.getDepartmentId());
					bean.setDesignation(profile.getDesignation());
					bean.setDepartmentName(profile.getDepartmentName());
					bean.setEmployeeName(profile.getEmployeeName());
					bean.setEmail(profile.getEmail());
					bean.setEmployeeId(profile.getEmployeeId());
					bean.setEmploymentStatusId(Integer.parseInt(profile.getEmp_status_id()));
					bean.setEmploymentStatus(profile.getEmploymentStatus());
					bean.setExtensionNo(profile.getExtensionNo());
					bean.setFaxNo(profile.getFaxNo());
					bean.setFirstName(profile.getFirstName());
					bean.setId(profile.getId());
					bean.setJobTitleId(profile.getJobtitle_id());
					bean.setJobTitleName(profile.getJobTitleName());
					bean.setPositionId(profile.getPosition_id());
					bean.setPositionName(profile.getDesignation());
					bean.setLastName(profile.getLastName());
					bean.setModeOfEmployment(profile.getModeOfEmployment());
					bean.setPrefix(profile.getPrefix());
					bean.setReportingManagerId(profile.getReportingManagerId());
					bean.setReportingManager(profile.getReportingManager());
					bean.setRole(profile.getRole());
					bean.setEmprole(profile.getEmprole());
					userId = profile.getUserId();
					bean.setUserId(userId);
					bean.setWorkTelephoneNo(profile.getWorkTelephoneNo());
					bean.setYearOfExp(profile.getYearOfExp());
                    bean.setEmployeeStatus(profile.getEmployeeStatus());
                    bean.setPrefixId(profile.getPrefix_id());
                    bean.setRoleId(profile.getEmprole());
					bean.setImmManagerName(profile.getImmManagerName());
					bean.setImmManagerId(profile.getImmManagerId());
					bean.setHrManagerId(profile.getHrManagerId());
					bean.setHrManagerName(profile.getHrManagerName());

					bean.setActive(profile.getIsactive() == 1);
					String ids = profile.getSelectedDocumentsIds();
					if (ids != null) {
						ArrayList<Integer> documentIds = new ArrayList<Integer>();
						if (ids.length() > 0) {
							String[] docId = ids.split(",");
							for (String iter : docId) {
								documentIds.add(Integer.parseInt(iter));
								// System.out.println(Integer.parseInt(iter));
							}
						}
						bean.setDocumentIds(documentIds);
					}
					List<Object[]> leavesList = hed.getAvailableLeaves(userId);
					if (leavesList.size() != 0)
					{
						// System.out.println("le" + leavesList.get(0) + leavesList.size());
						for (Object[] row : leavesList)
						{
							bean.setAvilableCasualLeaves(objToString(row[0]));
							bean.setAvilableSickLeaves(objToString(row[1]));
						}
					}
					List<Object[]> additionalList = hed.getAdditionalDetails(userId);
					if (additionalList.size() != 0) {
						// System.out.println("le" + additionalList.get(0) + additionalList.size());
						for (Object[] row : additionalList) 
						{
							bean.setCurrencyId(objToInteger(row[0]));
							bean.setCurrencyName(objToString(row[1]));
							bean.setSalaryType(objToInteger(row[2]));
							bean.setFrequencyType(objToString(row[3]));
							bean.setSalary(objToString(row[4]));
							bean.setBankname(objToString(row[5]));
							bean.setVisaId(objToInteger(row[6]));
							bean.setVisaName(objToString(row[7]));
						}
					}
					profileAl.add(bean);
				}
				responseBean.setMessage("employee profile based on the employeeId");
				responseBean.setStatus(true);
				responseBean.setEmployeeList(profileAl);
			} else {
				responseBean.setMessage("employee profile fetching problem  based on the employeeId");
				responseBean.setStatus(false);
			}
		} catch (Exception e) {
			logger.info("exception occured in getEmployeeProfile method of business class::: " + e);
			e.printStackTrace();
			responseBean.setMessage("Error while fetching employee profile details - " + e.getMessage());
			responseBean.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(responseBean).build();
	}

	private String password;
	private static final HrmsEmployeeDao db = new HrmsEmployeeDetails();
	private final EntityStatusBean response = new EntityStatusBean();

	// Save Official details for Employee
	public Response saveEmployeeOfficialDetails(EmployeeRequestBean bean) {
		logger.info("entered into saveEmployeeOfficialDetails method of business class.. ");
		HrmsBenchSalesDao hrmsBenchSalesDao = new HrmsBenchSalesDaoImpl();
		boolean emailFlag =false;
		bean.setFullName(bean.getFirstName() + " " + bean.getLastName());
		password = new AutoPasswordGenerator().randomPassword();
		String hashedPassword = new MD5Generator().generate(password);
		bean.setPassword(hashedPassword);
		int userId = db.saveEmployeeOfficialDetails(bean);
		logger.info(userId);
		String canEmail=null;
		SendMail mail = new SendMail(bean.getFirstName(),bean.getLastName(),"addCandiadte");

		if(bean.getEmployeeStatus().equals("On Bench")) {
		String hql="select max(id) from BenchSalesAddCandidateEntity";
		int canId=(int)DbConnect.DbCon().createQuery(hql).uniqueResult();
		if(canId!=0) 
		{
			canEmail=hrmsBenchSalesDao.getCandidateEmailById(canId);
			logger.info(canEmail);
			if( canEmail!=null) 
			{
				emailFlag= mail.send(canEmail, bean.getEmployeeStatus(),password,bean.getEmployeeId(), bean.getFullName(),bean.getFirstName());
			}

		}
		}

		if (userId != 0)
		{
			
			emailFlag= mail.send(bean.getEmail(), bean.getEmployeeId(), password, bean.getFullName(),bean.getFirstName() , bean.getLastName());

			emailFlag = mail.send("madhu.gurram72155@gmail.com", bean.getEmployeeId(), password, bean.getFullName(),bean.getFirstName() , "Madhu Reddy");

			emailFlag = mail.send("sureshk.ajs@gmail.com", bean.getEmployeeId(), password, bean.getFullName(),bean.getFirstName() , "Bharath Kumar");

			emailFlag = mail.send("mobeenasyed888@gmail.com", bean.getEmployeeId(), password, bean.getFullName(),bean.getFirstName() , "Bharath Kumar");
			MailLogEntity mailLog = new MailLogEntity();

			mailLog.setToEmails(bean.getEmail());
			mailLog.setToName(bean.getFullName());
			mailLog.setEmailSubject("EEAccess");
			mailLog.setIsSent(1);
			mailLog.setMessage("Message Sent");
			mailLog.setHeader("Greeting from EEAccess");
			if (emailFlag)
			{
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				db.insertMailLog(mailLog);
				response.setMessage("Employee Official Information Saved Succesfully and Email Sent to Employee,Super Admin and Management");
				response.setStatus(true);
				response.setUserId(userId);
			}
			else 
			{
				mailLog.setIsSent(0);
				mailLog.setMessage("Message Not Sent.");
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				db.insertMailLog(mailLog);
				response.setMessage("Employee Official Information Saved UnSuccesfully but Email Not Sent to Employee");
				response.setStatus(false);
			}
			return Response.ok().entity(response).build();
		}
		else 
		{
			response.setMessage("Employee Official Information saving failed.");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}
	}
	//Update Officials details for Employee
	public Response updateEmployeeOfficialDetails(EmployeeRequestBean bean) {
		logger.info("entered into updateEmployeeOfficialDetails method of business class.. ");
		HrmsBenchSalesDao hrmsBenchSalesDao = new HrmsBenchSalesDaoImpl();
		boolean emailFlag =false;
		bean.setFullName(bean.getFirstName() + " " + bean.getLastName());
		password = new AutoPasswordGenerator().randomPassword();
		String hashedPassword = new MD5Generator().generate(password);
		bean.setPassword(hashedPassword);
		String message = db.updateEmployeeOfficialDetails(bean);
		logger.info(message);
		String canEmail=null;
		SendMail mail = new SendMail(bean.getFirstName(),bean.getLastName(),"addCandiadte");

		if(bean.getEmployeeStatus().equals("On Bench")) {
		String hql="select max(id) from BenchSalesAddCandidateEntity";
		int canId=(int)DbConnect.DbCon().createQuery(hql).uniqueResult();
		if(canId!=0) 
		{
			canEmail=hrmsBenchSalesDao.getCandidateEmailById(canId);
			logger.info(canEmail);
			if( canEmail!=null) 
			{
				emailFlag= mail.send(canEmail, bean.getEmployeeStatus(),password,bean.getEmployeeId(), bean.getFullName(),bean.getFirstName());
			}

		}
		}

		if (message != null)
		{
			
			//emailFlag= mail.send(bean.getEmail(), bean.getEmployeeId(), password, bean.getFullName(),bean.getFirstName() , bean.getLastName());

			emailFlag = mail.send("madhu.gurram72155@gmail.com", bean.getEmployeeId(), password, bean.getFullName(),bean.getFirstName() , "Madhu Reddy");

			emailFlag = mail.send("sureshk.ajs@gmail.com", bean.getEmployeeId(), password, bean.getFullName(),bean.getFirstName() , "Bharath Kumar");

			emailFlag = mail.send("mobeenasyed888@gmail.com", bean.getEmployeeId(), password, bean.getFullName(),bean.getFirstName() , "Bharath Kumar");
			MailLogEntity mailLog = new MailLogEntity();

			mailLog.setToEmails(bean.getEmail());
			mailLog.setToName(bean.getFullName());
			mailLog.setEmailSubject("EEAccess");
			mailLog.setIsSent(1);
			mailLog.setMessage("Message Sent");
			mailLog.setHeader("Greeting from EEAccess");
			if (emailFlag)
			{
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				db.insertMailLog(mailLog);
				response.setMessage("Employee Official Information Update Succesfully and Email Sent to Employee,Super Admin and Management");
				response.setStatus(true);
				response.setMessage(message);
			}
			else 
			{
				mailLog.setIsSent(0);
				mailLog.setMessage("Message Not Sent.");
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				db.insertMailLog(mailLog);
				response.setMessage("Employee Official Information Saved UnSuccesfully but Email Not Sent to Employee");
				response.setStatus(false);
			}
			return Response.ok().entity(response).build();
		}
		else 
		{
			response.setMessage("Employee Official Information saving failed.");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}
	}

	private static final HrmsListOfValuesDao dao = new HrmsListOfValues();

	public Response employeeIdAutoGeneration() {

		logger.info("entered into employeeIdAutoGeneration method of business class");

		EmployeeIdAutoGenerationResponseBean response = new EmployeeIdAutoGenerationResponseBean();

		String employeeId = dao.EmployeeId();

		String[] arr = employeeId.split("(?<=\\D)(?=\\d)");
		String Id = Integer.toString(Integer.parseInt(arr[1]) + 1);

		// // System.out.println(Id);

		response.setEmployeeId(Id);
		response.setPreFix(arr[0]);
		response.setStatus(true);

		return Response.ok().entity(response).build();
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

	// Save Personal Details in database
	public Response savePersonalDetails(PersonalDetailsRequestBean bean) {
		boolean flag = db.saveEmpPersonalDetails(bean);
		if(flag) {

			response.setMessage("Employee Personal Details Inserting Successfully.");
			response.setStatus(true);
			return Response.ok().entity(response).build();
		}
		else {
			response.setMessage("Employee Personal Details failed to Insert.");
			response.setStatus(false);
			return Response.ok().entity(response).build();



		}
		/*SendMail mail = new SendMail();
		boolean emailFlag = mail.sendTo(empBean.getEmail(), empBean.getEmployeeId(), empBean.getFullName());
	   MailLogEntity mailLog = new MailLogEntity();
		mailLog.setToEmails(empBean.getEmail());
		mailLog.setToName(empBean.getFullName());
		mailLog.setEmailSubject("EEAccess");
		mailLog.setIsSent(1);
		mailLog.setMessage("Message Sent");
		mailLog.setHeader("Greeting from EEAccess");
		if (emailFlag) {
			mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			db.insertMailLog(mailLog);
			response.setMessage("Employee Passport Expired Date is Expired So Employee  Get  Mail");*/


		/*else {
			mailLog.setIsSent(0);
			mailLog.setMessage("Message Not Sent.");
			mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			db.insertMailLog(mailLog);
			response.setMessage("Employee Passport Expired Date is Not Expired So Employee Not Get Any Mail. ");*/





		//	if(drivingLicenceExpDate!=null) {
		//		SendMail mail = new SendMail();
		//		boolean emailFlag = mail.sendTo(empBean.getEmail(), empBean.getEmployeeId(), empBean.getFullName());
		//	   MailLogEntity mailLog = new MailLogEntity();
		//		mailLog.setToEmails(empBean.getEmail());
		//		mailLog.setToName(empBean.getFullName());
		//		mailLog.setEmailSubject("EEAccess");
		//		mailLog.setIsSent(1);
		//		mailLog.setMessage("Message Sent");
		//		mailLog.setHeader("Greeting from EEAccess");
		//		if (emailFlag) {
		//			mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
		//			db.insertMailLog(mailLog);
		//			response.setMessage("Employee Personal Details Saved Successfully And Email  Sent to Employee");
		//			response.setStatus(true);
		//			
		//		} else {
		//			mailLog.setIsSent(0);
		//			mailLog.setMessage("Message Not Sent.");
		//			mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
		//			db.insertMailLog(mailLog);
		//			response.setMessage("Employee Personal Details Already Exists but Email Not Sent to Employee");
		//			response.setStatus(true);
		//		}
		//	
		//	}

	}


	/*
	 * try { BeanUtils.copyProperties(entity, bean); entity.setDateOfBirth(new
	 * EmployeeDetailsSummary().date(bean.getDateOfBirth()));
	 * entity.setCreatedBy(bean.getUserId()); entity.setCreatedDate(new
	 * HrmsGetDateAndTime().GetUTCdatetimeAsString());
	 * entity.setIdentityDocuments(bean.getPassport() + ":" +
	 * bean.getPassportExpDate() + "," + bean.getDrivingLicence() + ":" +
	 * bean.getDrivingLicenceExpDate()); entity.setIsActive(1); int flag =
	 * db.saveEmpPersonalDetails(entity); if (flag == 1)
	 * response.setMessage("Employee Personal Details Saved Successfully."); else
	 * response.setMessage("Employee Personal Details Already Exists.");
	 * response.setStatus(true); return Response.ok().entity(response).build(); }
	 * catch (IllegalAccessException e) { e.printStackTrace();
	 * response.setMessage("Employee Personal details failed to save.");
	 * response.setStatus(false); return Response.ok().entity(response).build(); }
	 * catch (InvocationTargetException e) { e.printStackTrace();
	 * response.setMessage("Employee Personal details failed to save.");
	 * response.setStatus(false); return Response.ok().entity(response).build(); }
	 */


	// Update personal details
	public Response updatePersonalDetails(PersonalDetailsRequestBean bean) {
		boolean flag = db.updateEmpPersonalDetails(bean);
		if (flag) {
			response.setMessage("Employee Personal Details Updated Successfully.");
			response.setStatus(true);
		} else {
			response.setMessage("Employee Personal Details failed to update.");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();
		/*
		 * EmpPersonalDetailsEntity entity = new EmpPersonalDetailsEntity(); try {
		 * BeanUtils.copyProperties(entity, bean); entity.setDateOfBirth(new
		 * EmployeeDetailsSummary().date(bean.getDateOfBirth()));
		 * entity.setModifiedBy(bean.getUserId()); entity.setModifiedDate(new
		 * HrmsGetDateAndTime().GetUTCdatetimeAsString());
		 * entity.setIdentityDocuments(bean.getPassport() + ":" +
		 * bean.getPassportExpDate() + "," + bean.getDrivingLicence() + ":" +
		 * bean.getDrivingLicenceExpDate()); entity.setIsActive(1); int flag =
		 * db.updateEmpPersonalDetails(entity); if (flag == 1)
		 * response.setMessage("Employee Personal Details Updated Successfully."); else
		 * response.setMessage("Employee Personal Details Update Failed.");
		 * response.setStatus(true); return Response.ok().entity(response).build(); }
		 * catch (IllegalAccessException e) { e.printStackTrace();
		 * response.setMessage("Employee Personal details failed to update.");
		 * response.setStatus(false); return Response.ok().entity(response).build(); }
		 * catch (InvocationTargetException e) { e.printStackTrace();
		 * response.setMessage("Employee Personal details failed to update.");
		 * response.setStatus(false); return Response.ok().entity(response).build(); }
		 */
	}

	// String to Sql Date Format
	public java.sql.Date date(String dob) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date parsedDate = date.parse(dob);
			java.sql.Date dateOfBirth = new java.sql.Date(parsedDate.getTime());
			return dateOfBirth;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Read Employee Personal Details
	public Response getEmpPersonalDetails(int userId) {
		List<EmpPersonalDetailsEntity> list = db.getEmpPersonalDetails(userId);
		PersonalDetailsResponseBean bean = new PersonalDetailsResponseBean();
		List<PersonalDetailsResponseBean> responseList = new ArrayList<>();
		EmployeeDetailsResponseBean response = new EmployeeDetailsResponseBean();
		if (!list.isEmpty()) {
			EmpPersonalDetailsEntity empPersonalDetail = list.get(0);
			if (empPersonalDetail != null) {
				bean.setId(empPersonalDetail.getId());
				bean.setUserId(empPersonalDetail.getUserId());
				bean.setDateOfBirth(empPersonalDetail.getDateOfBirth());
				bean.setBloodGroup(empPersonalDetail.getBloodGroup());
				/*
				 * String identityDocuments = empPersonalDetail.getIdentityDocuments(); String[]
				 * iD = null; String[] passport = null; String passportNo = ""; String
				 * passportExpDate = ""; String[] drivingLicence = null; String drivingLicenceNo
				 * = ""; String drivingLicenceExpDate = ""; try { iD =
				 * identityDocuments.split(","); passport = iD[0].split(":"); passportNo =
				 * passport[0]; passportExpDate = passport[1]; drivingLicence =
				 * iD[1].split(":"); drivingLicenceNo = drivingLicence[0]; drivingLicenceExpDate
				 * = drivingLicence[1]; } catch (Exception e) { passportNo = ""; passportExpDate
				 * = ""; drivingLicenceNo = ""; drivingLicenceExpDate = ""; }
				 */
				bean.setPassport(empPersonalDetail.getPassport());
				bean.setPassportExpDate(empPersonalDetail.getPassportExpDate());
				bean.setDrivingLicence(empPersonalDetail.getDrivingLicence());
				bean.setDrivingLicenceExpDate(empPersonalDetail.getDrivingLicenceExpDate());
				bean.setGender(empPersonalDetail.getGenderId().getGenderName());
				bean.setNationality(empPersonalDetail.getNationalityId().getNationalityCode());
				bean.setMaritalStatus(empPersonalDetail.getMaritalStatusId().getMaritalStatusName());
				bean.setEthinicCode(empPersonalDetail.getEthinicCodeId().getEthinicName());
				bean.setRaceCode(empPersonalDetail.getRaceCodeId().getRaceName());
				bean.setLanguage(empPersonalDetail.getLanguageId().getLanguageName());
				bean.setGenderId(empPersonalDetail.getGenderId().getId());
				bean.setNationalityId(empPersonalDetail.getNationalityId().getId());
				bean.setMaritalStatusId(empPersonalDetail.getMaritalStatusId().getId());
				bean.setEthinicCodeId(empPersonalDetail.getEthinicCodeId().getId());
				bean.setRaceCodeId(empPersonalDetail.getRaceCodeId().getId());
				bean.setLanguageId(empPersonalDetail.getLanguageId().getId());
				responseList.add(bean);
			}
			response.setMessage("Personal Information Fetched Successfully.");
			response.setStatus(true);
			response.setEmployeeList(responseList);
		} else {
			// System.out.println("Personal Information Not Found");
			response.setMessage("No Personal Information please add.");
			response.setStatus(false);
			response.setEmployeeList(responseList);
		}
		return Response.ok().entity(response).build();
	}

	private Integer objToInteger(Object obj) {

		if (obj != null)
			return Integer.parseInt(obj.toString());

		return 0;
	}

	// Save Contact Details
	public Response saveContactDetails(ContactDetailsRequestBean bean) {
		CommunicationInfoEntity entity = new CommunicationInfoEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setCreatedBy(bean.getUserId());
			entity.setIsActive(1);
			int flag = db.saveEmpContactDetails(entity);
			if (flag == 1) {
				response.setMessage("Employee Contact Details Saved Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("Employee Contact Details Saving Failed.");
				response.setStatus(false);
			}
		} catch (IllegalAccessException e) {
			response.setMessage("Bad Request.");
			response.setStatus(false);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			response.setMessage("Bad Request.");
			response.setStatus(false);
			e.printStackTrace();
		}
		return Response.ok().entity(response).build();
	}

	// Update Contact Details
	public Response updateContactDetails(ContactDetailsRequestBean bean) {
		CommunicationInfoEntity entity = new CommunicationInfoEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			entity.setModifiedBy(bean.getCreatedBy());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsActive(1);
			int flag = db.updateEmpContactDetails(entity);
			if (flag == 1) {
				response.setMessage("Employee Contact Details Update Successful.");
				response.setStatus(true);
			} else {
				response.setMessage("Employee Contact Details Updating Failed.");
				response.setStatus(false);
			}
		} catch (IllegalAccessException e) {
			response.setMessage("Bad Request.");
			response.setStatus(false);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			response.setMessage("Bad Request.");
			response.setStatus(false);
			e.printStackTrace();
		}
		return Response.ok().entity(response).build();
	}

	// Read Employee Contact Details
	public Response getEmpContactDetails(int userId) {
		List<Object[]> list = db.getEmpContactDetails(userId);
		ContactDetailsResponseBean bean = new ContactDetailsResponseBean();
		List<ContactDetailsResponseBean> responseList = new ArrayList<>();
		EmployeeDetailsResponseBean response = new EmployeeDetailsResponseBean();
		if (!list.isEmpty()) {
			Object[] empContactDetails = list.get(0);
			bean.setId(objToInteger(empContactDetails[0]));
			bean.setUserId(objToInteger(empContactDetails[1]));
			bean.setPersonalEmail(objToString(empContactDetails[2]));
			bean.setPermStreetAddress(objToString(empContactDetails[3]));
			bean.setPermCountry(objToInteger(empContactDetails[4]));
			bean.setPermCountryName(objToString(empContactDetails[5]));
			bean.setPermState(objToInteger(empContactDetails[6]));
			bean.setPermStateName(objToString(empContactDetails[7]));
			bean.setPermCity(objToInteger(empContactDetails[8]));
			bean.setPermCityName(objToString(empContactDetails[9]));
			bean.setPermPinCode(objToString(empContactDetails[10]));
			bean.setCurrentStreetAddress(objToString(empContactDetails[11]));
			bean.setCurrentCountry(objToInteger(empContactDetails[12]));
			bean.setCurrentCountryName(objToString(empContactDetails[13]));
			bean.setCurrentState(objToInteger(empContactDetails[14]));
			bean.setCurrentStateName(objToString(empContactDetails[15]));
			bean.setCurrentCity(objToInteger(empContactDetails[16]));
			bean.setCurrentCityName(objToString(empContactDetails[17]));
			bean.setCurrentPinCode(objToString(empContactDetails[18]));
			bean.setEmergencyNumber(objToString(empContactDetails[19]));
			bean.setEmergencyName(objToString(empContactDetails[20]));
			bean.setEmergencyEmail(objToString(empContactDetails[21]));

			// Adding bean to responseList
			responseList.add(bean);

			// Setting Response bean class
			response.setMessage("Contact Details Fetched Successfully.");
			response.setStatus(true);
			response.setEmployeeList(responseList);
		} else {
			// System.out.println("Contact Details Not Found");
			response.setMessage("No Contact Information please add.");
			response.setStatus(false);
			response.setEmployeeList(responseList);
		}
		return Response.ok().entity(response).build();
	}

	// Fetch employee salary details
	public Response getEmpSalaryDetails(int userId) {
		SalaryDetailsBean bean = new SalaryDetailsBean();
		SalaryDetailsEntity entity = db.getEmpSalaryDetails(userId);
		EmployeeDetailsResponseBean response = new EmployeeDetailsResponseBean();
		List<SalaryDetailsBean> list = new ArrayList<>();

		if (entity != null) {
			try {
				BeanUtils.copyProperties(bean, entity);
				list.add(bean);
				response.setMessage("Salary Details For userId = " + bean.getUserId());
				response.setStatus(true);
				response.setEmployeeList(list);
			} catch (Exception e) {
				response.setMessage("Something Went Wrong");
				response.setStatus(false);
				response.setEmployeeList(list);
				e.printStackTrace();
			}
		} else {
			response.setMessage("Salary Details Not Found.");
			response.setStatus(false);
			response.setEmployeeList(list);
		}
		return Response.ok().entity(response).build();
	}

	// Update Employee Salary Details
	public Response updateSalaryDetails(SalaryDetailsBean bean) {
		SalaryDetailsEntity entity = new SalaryDetailsEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			entity.setModifiedBy(2);
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			int flag = db.updateSalaryDetails(entity);
			if (flag == 1) {
				response.setMessage("Employee Salary Details Update Successful.");
				response.setStatus(true);
			} else {
				response.setMessage("Employee Salary Details Updating Failed.");
				response.setStatus(false);
			}
		} catch (Exception e) {
			response.setMessage("Bad Request.");
			response.setStatus(false);
			e.printStackTrace();
		}
		return Response.ok().entity(response).build();
	}

	public Response signaturePathUpdate(String filePath, int userId) {
		SuccessResponseBean response = new SuccessResponseBean();
		if (hed.updateSignaturePath(filePath, userId)) {
			response.setStatus(true);
			response.setMessage("Signature Uploaded Successfully..");
			response.setUploadURL(filePath);
		} else {
			response.setStatus(false);
			response.setMessage("Signature Upload Failed..");
			File deleFile = new File(filePath);
			if (deleFile.exists())
				deleFile.delete();
		}
		return Response.ok().entity(response).build();
	}

	public Response getOfficialdetailsOfEmployeeForHR(String hrManagerName, int roleId, int menuId) {

		logger.info("entered into getOfficialdetailsOfEmployee method of BussinessLogic Class ");
		EmployeeDetailsResponseBean responseBean = new EmployeeDetailsResponseBean();

		try {
			List<Privileges> listOfPrivilleges = new HrmsUserAuthentication().getPrivileges(roleId, menuId);
			List<EmployeeDetailsEntity> empListData = hed.getListOfEmpAddedByHrMgr(hrManagerName);
			if (!empListData.isEmpty())

			{
				responseBean.setMessage("Employee details Retrieved Successfully");
				responseBean.setStatus(true);
				responseBean.setEmployeeList(empListData);
				responseBean.setPrivilegesList(listOfPrivilleges);

			} else {
				responseBean.setMessage("Employee details fetching something problem!!");
				responseBean.setStatus(false);
				responseBean.setEmployeeList(empListData);

			}
		} catch (Exception e) {
			logger.info("logging the occured exception in businessClass  getOfficialdetailsOfEmployee method::" + e);
			e.printStackTrace();
			responseBean.setMessage("Error while fetching employee  details - " + e.getMessage());
			responseBean.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(responseBean).build();
	}


	// Email Address Duplicate Check Service for Adding Employee.

	public Response existedEmailFindService(String emailAddress) {
		CommonResponseBean response= new CommonResponseBean();
		EmployeeDetailsEntity emailUniqueList=hed.getDuplicateEmailAddress(emailAddress);
		if(emailUniqueList!=null) {
			response.setMessage("Email Id Already Exists.");
			response.setStatus(true);

		}else {
			response.setMessage("Email Id can be Use.");
			response.setStatus(true);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}
	public Response getListOfusername() {
		logger.info("inside of getListOfusername method business class");
		CommonResponseBean response= new CommonResponseBean();	
		List<User> entity=hed.getListOfSellsExecutive();
		if(entity !=null) {
			response.setMessage("Sells Executivedata Retrived  sucessfully");
			response.setStatus(true);
			response.setList(entity);
		}
		else {
			response.setMessage("Sells Executivedata Reteived Unsucessfully");
			response.setStatus(false);
		}


		return Response.status(Response.Status.OK).entity(response).build();

	}
	
	//Empolyee Soft Delete Service 
	public Response softDeleteResponse(int userId) {
		logger.info("Enter into softDeleteResponse() ");
		CommonResponseBean response=new CommonResponseBean();
		int softDeleteEmp=hed.softDeleteEmployee(userId);
		if(softDeleteEmp==1) {
			response.setMessage("Employee Soft Delete Successfully");
			response.setStatus(true);
		}else {
			response.setMessage("Employee Soft Delete UnSuccessfully");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();
	}
	
	public Response getAllInActiveEmp() {
		CommonResponseBean responseBean= new CommonResponseBean();
		List<EmployeeDetailsEntity>lisOfInActiveEmp=hed.getAllInActiveEmp();
		if(!lisOfInActiveEmp.isEmpty()) {
			responseBean.setMessage("All InActive Retrived SuccessFul");
			responseBean.setStatus(true);
			responseBean.setList(lisOfInActiveEmp);
		}else {
			responseBean.setMessage("All InActive Retrived UnSuccessFul");
			responseBean.setStatus(false);
		}
		return Response.ok().entity(responseBean).build();
	}
	public Response getAllEmpByRepId(int repId) {
		CommonResponseBean responseBean= new CommonResponseBean();
		List<EmployeeResponseBean>listOfEmp=hed.getAllEmpByRepId(repId);
		if(!listOfEmp.isEmpty()) {
			responseBean.setMessage("All InActive Retrived SuccessFul");
			responseBean.setStatus(true);
			responseBean.setList(listOfEmp);
		}else {
			responseBean.setMessage("All InActive Retrived UnSuccessFul");
			responseBean.setStatus(false);
		}
		return Response.ok().entity(responseBean).build();
	}
}
