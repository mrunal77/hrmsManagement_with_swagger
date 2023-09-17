package com.rainier.businesslogic;

import com.rainier.beans.*;
import com.rainier.dao.HrmsListOfValuesDao;
import com.rainier.dao.Impl.HrmsListOfValues;
import com.rainier.dto.requestBean.PositionRequestBean;
import com.rainier.entities.*;
import com.rainier.response.HrManagerIdNameResponse;
import com.rainier.response.ImmManagerIdNameResponse;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ListOfValues {
	private static final Logger logger = Logger.getLogger(ListOfValues.class);

	private static final HrmsListOfValuesDao dao = new HrmsListOfValues();

	public Response listOfPositions(int jobTitleId) {
		logger.info("entered into listOfPositions of business class ");

		List<PositionEntity> positionList = dao.listOfPositions(jobTitleId);
		ListOfPositionsResponseBean response = new ListOfPositionsResponseBean();
		if (!positionList.isEmpty()) {
			response.setStatus(true);
			response.setMessage("List Of Positions Retrived Successfully.");
			response.setListOfPositions(positionList);

		} else {
			response.setStatus(false);
			response.setMessage("List Of Positions is empty.");
			response.setListOfPositions(positionList);
		}
		return Response.ok().entity(response).build();
	}

	public Response reportingManagerList(int roleId) {
		logger.info("entered into reportingManagerList of business class ");

		List<EmployeeDetailsEntity> reportingManagerListDao = dao.reportingManagerList(roleId);
		List<ReportingManagerBean> reportingMangaerList = new ArrayList<>();
		ReportingManagerResponseBean response = new ReportingManagerResponseBean();
		if (!reportingManagerListDao.isEmpty()) {
			for (EmployeeDetailsEntity reportingManager : reportingManagerListDao) {
				ReportingManagerBean bean = new ReportingManagerBean();
				bean.setId(reportingManager.getUserId());
				bean.setManagerName(reportingManager.getFirstName() + " " + reportingManager.getLastName());
				bean.setRoleName(reportingManager.getRole());
				bean.setEmployeeId(reportingManager.getEmployeeId());
				reportingMangaerList.add(bean);
			}
			response.setStatus(true);
			response.setMessage("Reporting Manager List Retrived Successfully");
			response.setReportingManagerList(reportingMangaerList);
		} else {
			response.setStatus(false);
			response.setMessage("Reporting Manager List is Empty.");
			response.setReportingManagerList(reportingMangaerList);
		}
		return Response.ok().entity(response).build();
	}

	// for Leave Type by Organization
	public Response getTypeOfLeaveList() {
		EmployeeLeaveTypeResponseBean LeaveResponseBean = new EmployeeLeaveTypeResponseBean();
		// EmployeeLeaveTypeEntity listleavedata = new EmployeeLeaveTypeEntity();
		BU_DU_EmpListResponse responseBean = new BU_DU_EmpListResponse();
		/* HrmsEmployeeDao hed = new HrmsEmployeeDetails(); */

		try {
			List<EmployeeLeaveTypeEntity> listOfTypeLeave = dao.getLeaveType();
			if (listOfTypeLeave.size() != 0) {
				LeaveResponseBean.setLeaveTypelist(listOfTypeLeave);
				LeaveResponseBean.setMesssage(" Successfully retieved from Tables.");
				LeaveResponseBean.setStatus(true);

			} else {
				LeaveResponseBean.setLeaveTypelist(listOfTypeLeave);
				LeaveResponseBean.setMesssage("Something  Went Wrong...");
				LeaveResponseBean.setStatus(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setMessage("Error while fetching leave Types. - " + e.getMessage());
			responseBean.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(LeaveResponseBean).build();

	}

	public Response getJobTitleList(int id) {
		List<JobTitlesEntity> list = dao.getJobTitleList(id);
		List<JobTitleBean> jobTitleList = new ArrayList<>();
		JobTitleListResponseBean response = new JobTitleListResponseBean();
		if (!list.isEmpty()) {
			for (JobTitlesEntity entity : list) {
				JobTitleBean bean = new JobTitleBean();
				bean.setId(entity.getId());
				bean.setJobTitleCode(entity.getJobTitleCode());
				bean.setJobTitleName(entity.getJobTitleName());
				bean.setMinExpReq(entity.getMinExpReq());
				bean.setJobPayGradeCode(entity.getJobPayGradeCode());
				bean.setJobPayFrequency(entity.getJobPayFrequency());
				bean.setJobDescription(entity.getJobDescription());
				jobTitleList.add(bean);
			}
			response.setStatus(true);
			response.setMessage("Job Title List Retrived Successfully");
			response.setJobTitleList(jobTitleList);
		} else {
			response.setStatus(false);
			response.setMessage("No Job Title Found.");
			response.setJobTitleList(jobTitleList);
		}
		return Response.ok().entity(response).build();
	}

	// List of Roles
	public Response getRolesList() {
		List<UserRole> roleList = dao.getRolesList();
		List<RoleBean> list = new ArrayList<>();
		RoleListResponseBean response = new RoleListResponseBean();

		if (!roleList.isEmpty()) {
			for (UserRole role : roleList) {
				RoleBean bean = new RoleBean();
				bean.setId(role.getuId());
				bean.setRoleName(role.getRoleName());
				list.add(bean);
			}
			response.setStatus(true);
			response.setMessage("Role List Retrived Successfully");
			response.setRoleList(list);
		} else {
			response.setStatus(false);
			response.setMessage("Role List is empty.");
			response.setRoleList(list);
		}
		return Response.ok().entity(response).build();
	}

	// For getting Reporting Manager for specified user.

	public Response getReportingmanager(Integer userId) {

		List<EmployeeDetailsEntity> reportMgrListDao = dao.getReportManagerBasedOnUserId(userId);
		ReportingManager responseBean = new ReportingManager();

		try {
			// ArrayList<ReportingManager> reportAl = new ArrayList<ReportingManager>();
			if (!reportMgrListDao.isEmpty()) {

				for (EmployeeDetailsEntity report : reportMgrListDao) {

					responseBean.setEmployeeId(report.getEmployeeId());
					responseBean.setReporting_managerId(report.getReportingManagerId());
					responseBean.setReportingManager(report.getReportingManager());
					responseBean.setEmployeeUserId(report.getUserId());

					List<Object[]> leavesList = dao.getAvailableLeaves(userId);
					if (leavesList.size() != 0) {
						// System.out.println("le" + leavesList.get(0) + leavesList.size());
						for (Object[] row : leavesList) {
							responseBean.setAvilableCasualLeaves(objToString(row[0]));
							responseBean.setAvilableSickLeaves(objToString(row[1]));
						}
					}
					// reportAl.add(bean);

				}
				responseBean.setMessage("Retrieved Successfully.");
				responseBean.setStatus(true);

			} else {
				responseBean.setMessage("No Details found for userId: " + userId);
				responseBean.setStatus(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setMessage("Error while fetching  reporting manager. - " + e.getMessage());
			responseBean.setStatus(false);
		}
		return Response.ok().entity(responseBean).build();

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
	public Integer objToInteger(Object obj) {

		if (obj != null)
			return Integer.parseInt(obj.toString());

		return 0;
	}

	private final LovResponseBean response = new LovResponseBean();

	// LOV for Gender
	public Response getGenders() {
		List<GenderEntity> list = dao.getGenders();
		if (!list.isEmpty()) {
			response.setStatus(true);
			response.setMessage("Genders Fetched Successfully");
			response.setList(list);
		} else {
			response.setStatus(false);
			response.setMessage("Genders not available.");
			response.setList(list);
		}
		return Response.ok().entity(response).build();
	}
	
	//Love For Delete Genders
	public Response  deleteGenders(int id) {
		CommonResponseBean response = new CommonResponseBean();
	
		if (dao.deleteGenders(id)) {
			response.setMessage("Deleted Successfully.");
			response.setStatus(true);
		} else {
			response.setMessage("Genders Data Not available.");
			response.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// LOV for Marital Status
	public Response getMaritalStatus() {
		List<MaritalStatusEntity> list = dao.getMaritalStatus();
		if (!list.isEmpty()) {
			response.setStatus(true);
			response.setMessage("Marital Status List Fetched Successfully");
			response.setList(list);
		} else {
			response.setStatus(false);
			response.setMessage("Marital Status not available.");
			response.setList(list);
		}
		return Response.ok().entity(response).build();
	}
	
// Leave for Delete Marital Status
	public Response deleteMaritalStatus(int id) {
		    CommonResponseBean response = new CommonResponseBean();
		if(dao.deleteMaritalStatus(id)) {
			response.setMessage("Deleted MaritalStatus Successfully");
		    response.setStatus(true);
		  }
		 else {
				response.setMessage(" Marital Status Data not available.");
				response.setStatus(false);
			  }
		return Response.status(Response.Status.OK).entity(response).build();

		
	}
	// LOV for Nationality
	public Response getNationality() {
		List<NationalityEntity> list = dao.getNationality();
		if (!list.isEmpty()) {
			response.setStatus(true);
			response.setMessage("Nationality List Fetched Successfully");
			response.setList(list);
		} else {
			response.setStatus(false);
			response.setMessage("Nationality not available.");
			response.setList(list);
		}
		return Response.ok().entity(response).build();
	}
	
	//Leave for Delete Nationality
	public Response deletNationality(int id) {
	    CommonResponseBean response = new CommonResponseBean();
	if(dao.deletNationality(id)) {
		response.setMessage(" Nationality Deleted Successfully");
	    response.setStatus(true);
	  }
	 else {
			response.setMessage("Nationality Data not available.");
			response.setStatus(false);
		  }
	return Response.status(Response.Status.OK).entity(response).build();

	
}

	// LOV for Ethinic Code
	public Response getEthinicCode() {
		List<EthinicCodeEntity> list = dao.getEthinicCode();
		if (!list.isEmpty()) {
			response.setStatus(true);
			response.setMessage("Ethinic Code List Fetched Successfully");
			response.setList(list);
		} else {
			response.setStatus(false);
			response.setMessage("Ethinic Code not available.");
			response.setList(list);
		}
		return Response.ok().entity(response).build();
		
	}
	//Love for Delete Ethinic code
	public Response  deleteEthinicCode(int id) {
		CommonResponseBean response = new CommonResponseBean();
		
		if (dao.deleteEthinicCode(id)) {
			response.setMessage("Deleted Successfully.");
			response.setStatus(true);
		} else {
			response.setMessage("Ethinic code not available.");
			response.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// LOV for Race Code
	public Response getRaceCode() {
		List<RaceCodeEntity> list = dao.getRaceCodes();
		if (!list.isEmpty()) {
			response.setStatus(true);
			response.setMessage("Race Code List Fetched Successfully");
			response.setList(list);
		} else {
			response.setStatus(false);
			response.setMessage("Race Code not available.");
			response.setList(list);
		}
		return Response.ok().entity(response).build();
	}
	
	//Leave for Delete Race coode
	public Response deletRaceCodes(int id) {
		CommonResponseBean response = new CommonResponseBean();
		if (dao.deletRaceCodes(id)) {
			response.setMessage("Race Code Deleted Successfully.");
			response.setStatus(true);
		}
	else {
		response.setMessage("Race code not available.");
		response.setStatus(false);
	}
    	return Response.status(Response.Status.OK).entity(response).build();
		
	}

	// LOV for Languages
	public Response getLanguages() {
		List<LanguageEntity> list = dao.getLanguages();
		if (!list.isEmpty()) {
			response.setStatus(true);
			response.setMessage("Languages Fetched Successfully");
			response.setList(list);
		} else {
			response.setStatus(false);
			response.setMessage("Languages not available.");
			response.setList(list);
		}
		return Response.ok().entity(response).build();
	}

	// Personal Gender type Add Logic.
	public Response addGender(PersonalGenderBean genderBean) {
		LovResponseBean response = new LovResponseBean();
		GenderEntity entitybean = new GenderEntity();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entitybean, genderBean);

			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();

			entitybean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entitybean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			entitybean.setId(genderBean.getId());
			entitybean.setGenderCode(genderBean.getGenderCode());
			entitybean.setGenderName(genderBean.getGenderName());
			dao.saveGenderType(entitybean);
			logger.info("Inserted..");
			response.setMessage("Gender Type Added.");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while saving details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// Marital Status Add Logic.
	public Response addmarital(PersonalMaritalStatusBean maritalBean) {
		LovResponseBean response = new LovResponseBean();
		MaritalStatusEntity entityBean = new MaritalStatusEntity();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entityBean, maritalBean);

			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifieddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			entityBean.setId(maritalBean.getId());
			entityBean.setMaritalCode(maritalBean.getMaritalCode());
			entityBean.setMaritalStatusName(maritalBean.getMaritalStatusName());

			dao.saveMaritalStatus(entityBean);
			logger.info("Inserted Successfully.");
			response.setMessage("Marital Statuse Added Successfully.");
			response.setStatus(true);

			return Response.ok().entity(response).build();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while saving details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	public Response addNationality(PersonalNationalityBean nationBean) {
		LovResponseBean response = new LovResponseBean();
		NationalityEntity entityBean = new NationalityEntity();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entityBean, nationBean);

			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setId(nationBean.getId());
			entityBean.setNationalityCode(nationBean.getNationalityCode());
			// entityBean.setCreatedBy(1);
			dao.saveNationality(entityBean);
			response.setMessage("Nationality Added Successfully.");
			response.setStatus(true);
			return Response.ok().entity(response).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while Saving Details::" + e.getMessage());
			response.setStatus(false);
			return Response.ok().entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// Ethnic code saving.
	public Response addEthnicCode(PersonalEthnicCodeBean ethnicBean) {
		LovResponseBean response = new LovResponseBean();
		EthinicCodeEntity entityBean = new EthinicCodeEntity();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entityBean, ethnicBean);

			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();

			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setId(ethnicBean.getId());
			entityBean.setEthinicCode(ethnicBean.getEthinicCode());
			entityBean.setEthinicName(ethnicBean.getEthinicName());

			dao.saveEthnicCode(entityBean);
			response.setMessage("EthnicCode Added Successfully.");
			response.setStatus(true);

			return Response.ok().entity(response).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while Saving Details::" + e.getMessage());
			response.setStatus(false);
			return Response.ok().entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// Race code Saving
	public Response addRaceCode(PersonalRaceCodeBean raceBean) {
		LovResponseBean response = new LovResponseBean();
		RaceCodeEntity entityBean = new RaceCodeEntity();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entityBean, raceBean);

			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();

			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			entityBean.setId(raceBean.getId());
			entityBean.setDescription(raceBean.getDescription());
			entityBean.setRaceCode(raceBean.getRaceCode());
			entityBean.setRaceName(raceBean.getRaceName());

			dao.saveRaceCode(entityBean);
			response.setMessage("Race Code Added Successfully.");
			response.setStatus(true);

			return Response.ok().entity(response).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while Saving Details::" + e.getMessage());
			response.setStatus(false);
			return Response.ok().entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// Saving Languages.
	public Response addlanguage(personalLanguageBean langBean) {
		LovResponseBean response = new LovResponseBean();
		LanguageEntity entityBean = new LanguageEntity();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entityBean, langBean);

			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();

			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			entityBean.setId(langBean.getId());
			entityBean.setDescription(langBean.getDescription());
			entityBean.setLanguageName(langBean.getLanguageName());

			dao.saveLanguage(entityBean);
			response.setMessage("Language  Added Successfully.");
			response.setStatus(true);

			return Response.ok().entity(response).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while Saving Details::" + e.getMessage());
			response.setStatus(false);
			return Response.ok().entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// lov Currency ....
	public Response getCurrency() {
		try {
			List<SalarycurrencyEntity> lst = dao.getCurrenncy();
			if (!lst.isEmpty()) {
				response.setMessage("Currency List .");
				response.setStatus(true);
				response.setList(lst);
			} else {
				response.setMessage("Currency List not listed .");
				response.setStatus(false);
				response.setList(lst);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error while fetching leave Types. - " + e.getMessage());
			response.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// lov Pay frequency.
	public Response getPayfrequency() {
		try {
			List<SalaryPayfrequencyEntity> listOfPay = dao.getPayrequency();
			if (!listOfPay.isEmpty()) {
				response.setMessage(" Payfrequency List .");
				response.setStatus(true);
				response.setList(listOfPay);
			} else {
				response.setMessage(" Payfrequency List not listed .");
				response.setStatus(false);
				response.setList(listOfPay);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error while fetching leave Types. - " + e.getMessage());
			response.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	// lov Account class Type.
	public Response getAccountclassType() {
		try {
			List<SalaryAccountClassTypeEntity> listOfPay = dao.getAccountClassType();
			if (!listOfPay.isEmpty()) {
				response.setMessage(" AccountclassType List .");
				response.setStatus(true);
				response.setList(listOfPay);
			} else {
				response.setMessage(" AccountclassType List not listed .");
				response.setStatus(false);
				response.setList(listOfPay);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error while fetching leave Types. - " + e.getMessage());
			response.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	
	//Leave for Delete Account ClassType
	
		public Response deleteAccountClassType(int id) {
			CommonResponseBean response = new CommonResponseBean();
			if (dao.deleteAccountClassType(id)) {
				response.setMessage("AccountClass Deleted Successfully.");
				response.setStatus(true);
			}
		else {
			response.setMessage("deleteAccountClassType not available.");
			response.setStatus(false);
		}
	    	return Response.status(Response.Status.OK).entity(response).build();
			
		}
	
	
	
	
	
	
	// lov Account class Type.
	public Response getBankAccountType() {
		try {
			List<SalaryBankAccountEntity> listOfPay = dao.getBankAccount();
			if (!listOfPay.isEmpty()) {
				response.setMessage(" BankAccountType List .");
				response.setStatus(true);
				response.setList(listOfPay);
			} else {
				response.setMessage(" BankAccountType List not listed .");
				response.setStatus(false);
				response.setList(listOfPay);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error while fetching leave Types. - " + e.getMessage());
			response.setStatus(false);
		}
		// return Response.ok().entity(listOfPay).build();
		return Response.status(Response.Status.OK).entity(response).build();
	}

	// saving Currency.
	public Response addcurrency(SalaryCurrencyBean requestBean) {
		LovResponseBean response = new LovResponseBean();
		SalarycurrencyEntity entityBean = new SalarycurrencyEntity();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entityBean, requestBean);

			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();

			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			entityBean.setId(requestBean.getId());
			entityBean.setDiscription(requestBean.getDiscription());
			entityBean.setCurrencyCode(requestBean.getCurrencyCode());
			entityBean.setCurrencyName(requestBean.getCurrencyName());
			entityBean.setUserId(requestBean.getUserId());

			dao.saveCurrency(entityBean);
			response.setMessage("Currency  Added Successfully.");
			response.setStatus(true);

			return Response.ok().entity(response).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while Saving Details::" + e.getMessage());
			response.setStatus(false);
			return Response.ok().entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}
	//Leave for Deleted Currency
	

	public Response deleteCurrency(int id) {
		CommonResponseBean response = new CommonResponseBean();
		if (dao.deleteCurrency(id)) {
			response.setMessage("Currency Deleted Successfully.");
			response.setStatus(true);
		}
	else {
		response.setMessage("deleted Currency not available.");
		response.setStatus(false);
	}
    	return Response.status(Response.Status.OK).entity(response).build();
		
	}

	// Saving Pay Frequency
	public Response savePayFrequency(SalaryPayFrequencyBean requestBean) {
		LovResponseBean response = new LovResponseBean();
		SalaryPayfrequencyEntity entityBean = new SalaryPayfrequencyEntity();
		// HrmsListOfValues dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entityBean, requestBean);
			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setIsactive((byte) 1);
			/*
			 * entityBean.setId(requestBean.getId());
			 * entityBean.setDiscription(requestBean.getDiscription());
			 * entityBean.setFreqCode(requestBean.getFreqCode());
			 * entityBean.setFreqType(requestBean.getFreqType());
			 * entityBean.setUserId(requestBean.getUserId());
			 */

			dao.savePayFrequency(entityBean);
			logger.info(" pay frequency Added Successfully");
			response.setMessage("pay frequency Added Successfully");
			response.setStatus(true);
			return Response.ok().entity(response).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while Saving Details::" + e.getMessage());
			response.setStatus(false);
			return Response.ok().entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// Saving Account class type.

	public Response saveAccountClassType(SalaryAccountclassTypeBean requestBean) {
		LovResponseBean response = new LovResponseBean();
		SalaryAccountClassTypeEntity entityBean = new SalaryAccountClassTypeEntity();
		// HrmsListOfValues dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entityBean, requestBean);
			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			entityBean.setId(requestBean.getId());
			entityBean.setDiscription(requestBean.getDiscription());
			entityBean.setAccountClassType(requestBean.getAccountClassType());
			entityBean.setUserId(requestBean.getUserId());

			dao.saveAccountClassType(entityBean);
			logger.info(" Account class Type Added Successfully");
			response.setMessage(" Account class Type Added Successfully");
			response.setStatus(true);
			return Response.ok().entity(response).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while Saving Details::" + e.getMessage());
			response.setStatus(false);
			return Response.ok().entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// Saving Bank Account type.

	public Response saveBankAccountType(SalaryBankAccountTypeBean requestBean) {
		LovResponseBean response = new LovResponseBean();
		SalaryBankAccountEntity entityBean = new SalaryBankAccountEntity();
		// HrmsListOfValues dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entityBean, requestBean);
			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			entityBean.setId(requestBean.getId());
			entityBean.setDiscription(requestBean.getDiscription());
			entityBean.setBankAccountType(requestBean.getBankAccountType());
			entityBean.setUserId(requestBean.getUserId());

			dao.saveBankAccount(entityBean);
			logger.info("Bank  Account class Type Added Successfully");
			response.setMessage(" Bank Account class Type Added Successfully");
			response.setStatus(true);
			return Response.ok().entity(response).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while Saving Details::" + e.getMessage());
			response.setStatus(false);
			return Response.ok().entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// Visa Types List
	public Response getVisaTypes() {
		List<VisaTypeEntity> list = dao.getVisaType();
		if (!list.isEmpty()) {
			response.setMessage(" Visa Type List Fetched.");
			response.setStatus(true);
			response.setList(list);
		} else {
			response.setMessage(" Visa Types not listed .");
			response.setStatus(false);
			response.setList(list);
		}
		return Response.ok().entity(response).build();
	}

	// Visa Types List
	public Response getVisaDocuments() {
		List<VisaDocumentsEntity> list = dao.getVisaDocuments();
		if (!list.isEmpty()) {
			response.setMessage(" Visa Documents List Fetched.");
			response.setStatus(true);
			response.setList(list);
		} else {
			response.setMessage(" Visa Documents List not listed .");
			response.setStatus(false);
			response.setList(list);
		}
		return Response.ok().entity(response).build();
	}

	// Holidays List.
	public Response getHolidays() {
		try {
			List<HolidaysEntity> holidays = dao.getHolidays();
			if (!holidays.isEmpty()) {
				response.setMessage("Holidays Listed.");
				response.setStatus(true);
				response.setList(holidays);
			} else {
				response.setMessage(" Holidays List not listed .");
				response.setStatus(false);
				response.setList(holidays);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error while fetching Holidays. - " + e.getMessage());
			response.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	// Saving Holidays.

	public Response saveHolidaysGroup(HolidaysBean requestBean) {
		LovResponseBean response = new LovResponseBean();
		HolidaysEntity entityBean = new HolidaysEntity();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();

		try {
			BeanUtils.copyProperties(entityBean, requestBean);

			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifieddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setUserId(requestBean.getUserId());
			entityBean.setId(requestBean.getId());
			entityBean.setDescription(requestBean.getDescription());
			entityBean.setGroupName(requestBean.getGroupName());

			dao.saveHolidays(entityBean);
			logger.info(" Holidays Group Added Successfully");
			response.setMessage(" Holidays Group Added Successfully");
			response.setStatus(true);
			return Response.ok().entity(response).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while Saving Details::" + e.getMessage());
			response.setStatus(false);
			return Response.ok().entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// saving Skills.
	public Response saveSkill(SkillsBean bean) {
		SkillsEntity entityBean = new SkillsEntity();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();
		LovResponseBean response = new LovResponseBean();
		try {
			BeanUtils.copyProperties(entityBean, bean);
			// Timestamp createdDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			// Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();

			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			entityBean.setId(bean.getId());
			entityBean.setUserId(bean.getUserId());
			entityBean.setSkillName(bean.getSkillName());
			entityBean.setYearSkillLastUsed(bean.getYearSkillLastUsed());
			entityBean.setYearsOfExp(bean.getYearsOfExp());
			entityBean.setCompetencyLevelId(bean.getCompetencyLevelId());
			entityBean.setIsActive((byte) 1);

			dao.saveSkills(entityBean);
			logger.info("inserted.");
			response.setMessage("Skills Added");
			response.setStatus(true);

			return Response.ok().entity(response).build();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while Saving Details::" + e.getMessage());
			response.setStatus(false);
			return Response.ok().entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving States  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}

	// Skills listing
	public Response getSkills(int userId) {
		try {
			List<SkillsEntity> skills = dao.getSkills(userId);
			if (!skills.isEmpty()) {
				response.setMessage("skills Listed.");
				response.setStatus(true);
				response.setList(skills);
			} else {
				response.setMessage(" skills List not listed .");
				response.setStatus(false);
				response.setList(skills);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error while fetching Skills. - " + e.getMessage());
			response.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	// Update Skills.

	public Response updateSkills(SkillsBean bean) {
		SkillsEntity entityBean = new SkillsEntity();
		LovResponseBean response = new LovResponseBean();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entityBean, bean);
			// Timestamp modifieddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			dao.updateSkills(entityBean);
			logger.info("Updated Successfully.");
			response.setMessage("Updated Successfully.");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// System.out.println("this is exception" + e1);
			response.setMessage("Error while updating - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// System.out.println("this is exception" + e1);
			response.setMessage("Error while updating - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	public Response addOrUpdateJobTitle(JobTitleBean bean) {
		JobTitlesEntity entity = new JobTitlesEntity();
		entity.setId(bean.getId());
		entity.setJobTitleName(bean.getJobTitleName());
		entity.setJobTitleCode(bean.getJobTitleCode());
		entity.setJobDescription(bean.getJobDescription());
		entity.setMinExpReq(bean.getMinExpReq());
		entity.setJobPayGradeCode(bean.getJobPayGradeCode());
		entity.setJobPayFrequency(bean.getJobPayFrequency());
		entity.setCreatedBy(bean.getCreatedOrModifiedBy());
		entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
		entity.setIsActive(1);

		if (bean.getId() > 0) {
			entity.setModifiedBy(bean.getCreatedOrModifiedBy());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
		}

		CommonResponseBean response = new CommonResponseBean();

		if (dao.saveOrUpdateJobTitles(entity)) {
			if (bean.getId() > 0) {
				response.setMessage("Job Title Updated successfully.");
			} else {
				response.setMessage("Job Title saved successfully.");
			}
			response.setStatus(true);
		} else {
			response.setMessage("Something went wrong.");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();
	}

	public Response deleteJobTitle(int id) {
		CommonResponseBean response = new CommonResponseBean();
		JobTitlesEntity entity = dao.deleteJobTitle(id);
		if (entity.getIsActive() == 0) {
			response.setMessage(entity.getJobTitleName() + " deleted successfully.");
			response.setStatus(true);
		} else {
			response.setMessage("There are some positions in under " + entity.getJobTitleName()
					+ " Job Title, Can't delete this Job Title.");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();
	}

	// Save Update Positions
	public Response addOrUpdatePositions(PositionRequestBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		if (dao.saveUpdatePositions(bean)) {
			if (bean.getId() > 0) {
				response.setMessage("Position Updated successfully.");
			} else {
				response.setMessage("Position saved successfully.");
			}
			response.setStatus(true);
		} else {
			response.setMessage("Something went wrong.");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();
	}

	public Response deletePosition(int id) {
		CommonResponseBean response = new CommonResponseBean();
		PositionEntity entity = dao.deletePosition(id);
		if (entity.getIsActive() == 0) {
			response.setMessage(entity.getPositionName() + " deleted successfully.");
			response.setStatus(true);
		} else {
			response.setMessage(
					"There are some employees having " + entity.getPositionName() + " position, Can't delete.");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();
	}

	// update Pay Frequency.
	public Response updatePayFrequency(SalaryPayFrequencyBean bean) {
		// CommonResponseBean responseBean= new CommonResponseBean();
		SalaryPayfrequencyEntity entityBean = new SalaryPayfrequencyEntity();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();
		try {
			BeanUtils.copyProperties(entityBean, bean);
			// Timestamp modifieddate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setIsactive((byte) 1);
			dao.updatePayFrequency(entityBean);
			logger.info("Updated Successfully.");
			response.setMessage("Updated Successfully.");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// System.out.println("this is exception" + e1);
			response.setMessage("Error while updating - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// System.out.println("this is exception" + e1);
			response.setMessage("Error while updating - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// Delete Pay Frequency.
	public Response deletePayFrequency(int id) {
		CommonResponseBean response = new CommonResponseBean();
		// HrmsListOfValuesDao dao = new HrmsListOfValues();
		if (dao.deletePayFrequency(id)) {
			response.setMessage("Deleted Successfully.");
			response.setStatus(true);
		} else {
			response.setMessage("Conflicts In Deleting Pay Frequency.");
			response.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// Hr Manager and Super Imm Manager Bu logic.
	public Response fetchHRmanager(int businessunitId) {
		CommonResponseBean response = new CommonResponseBean();

		List<EmployeeDetailsEntity> hrSuperManagerList = dao.getHrManager(businessunitId);
		System.out.println(hrSuperManagerList);
		if (hrSuperManagerList.size() != 0) {

			response.setMessage("HR manager list  With management Retrived Successfully.");
			response.setStatus(true);
			response.setList(hrSuperManagerList);
		} else {

			response.setMessage("Please Select Other Role.");
			response.setStatus(true);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}
	public Response getHrManagerIdNameByBusinessId(int businessunitId) {
		CommonResponseBean response = new CommonResponseBean();

		List<HrManagerIdNameResponse> listsOfHrIdName = dao.getHrManagerByBusinessId(businessunitId);
		System.out.println(listsOfHrIdName);
		if (listsOfHrIdName.size() != 0) {
			response.setMessage("HR manager list  With Id NAme Retrived Successfully.");
			response.setStatus(true);
			response.setList(listsOfHrIdName);
		} else {

			response.setMessage("Please Select Other Role.");
			response.setStatus(true);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}
	public Response getImmManagerIdNameByBusinessId(int businessunitId) {
		CommonResponseBean response = new CommonResponseBean();

		List<ImmManagerIdNameResponse> hrSuperManagerList = dao.getImmManagerByBusinessId(businessunitId);
		System.out.println(hrSuperManagerList);
		if (hrSuperManagerList.size() != 0) {

			response.setMessage("Imm Manager With Management Retrived Successfully.");
			response.setStatus(true);
			response.setList(hrSuperManagerList);
		} else {

			response.setMessage("Please Select Other Role.");
			response.setStatus(true);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}
	// Super Imm Manager Bu logic.
	public Response fetchIMMmanager(int businessunitId) {
		CommonResponseBean response = new CommonResponseBean();

		List<EmployeeDetailsEntity> hrSuperManagerList = dao.getImmManager(businessunitId);
		System.out.println(hrSuperManagerList);
		if (hrSuperManagerList.size() != 0) {

			response.setMessage("Imm Manager With Management Retrived Successfully.");
			response.setStatus(true);
			response.setList(hrSuperManagerList);
		} else {

			response.setMessage("Please Select Other Role.");
			response.setStatus(true);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// Reporting Manager List ---new Service.

	public Response fetchReportingManagerList(int roleId, int businessunitId, int departmentId) {
		CommonResponseBean response = new CommonResponseBean();

		List<EmployeeDetailsEntity> reportMgrList = dao.reportingManagerListAddEmployee(roleId, businessunitId,
				departmentId);

		if (reportMgrList.size() != 0) {

			response.setMessage("Report Manager With Management Retrived Successfully.");
			response.setStatus(true);
			response.setList(reportMgrList);
		} else {

			response.setMessage("Please Select Other Role.");
			response.setStatus(true);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// DropDown List for HRMANAGER----
	public Response getHRList() {
		CommonResponseBean response = new CommonResponseBean();

		List<Object[]> listOfHR = dao.getDropDownHRList();
		if (listOfHR.size() != 0) {
			response.setMessage("HR List Show In Dropdown.");
			response.setStatus(true);
			response.setList(listOfHR);
		} else {
			response.setMessage("HR List Mismatched.");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// DropDown List for ImmMANAGER----
	public Response getImmList() {
		CommonResponseBean response = new CommonResponseBean();

		List<Object[]> listOfImm = dao.getDropDownIMMList();
		if (listOfImm.size() != 0) {
			response.setMessage("Imm List Show In Dropdown.");
			response.setStatus(true);
			response.setList(listOfImm);
		} else {
			response.setMessage("Imm List Mismatched.");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

}
