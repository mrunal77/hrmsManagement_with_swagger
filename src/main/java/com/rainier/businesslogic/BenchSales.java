package com.rainier.businesslogic;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.rainier.beans.BenchSalesAddCandidateBean;
import com.rainier.beans.BenchSalesAddEmployeeBean;
import com.rainier.beans.BenchSalesAddTestimonialsBean;
import com.rainier.beans.BenchSalesRecruiterBean;
import com.rainier.beans.BenchSalesVendorCandidateMappingBean;
import com.rainier.beans.BenchSalesVendorDetailsBean;
import com.rainier.beans.CandSubmissionBean;
import com.rainier.beans.CommonResponseBean;
import com.rainier.beans.EntityStatusBean;
import com.rainier.beans.LovResponseBean;
import com.rainier.dao.HrmsBenchSalesDao;
import com.rainier.dao.HrmsEmployeeDao;
import com.rainier.dao.Impl.HrmsBenchSalesDaoImpl;
import com.rainier.dao.Impl.HrmsEmployeeDetails;
import com.rainier.dto.requestBean.CandidateSubmissionCount;
import com.rainier.dto.responseBean.BenchSalesVendorDetailsEntityResponse;
import com.rainier.dto.responseBean.CandidateRecruitersResponse;
import com.rainier.dto.responseBean.CandidateResponseObject;
import com.rainier.dto.responseBean.CandidateSalesManagerResponse;
import com.rainier.dto.responseBean.CityStateResponseBean;
import com.rainier.dto.responseBean.VendorSalesExecutiveResponseBean;
import com.rainier.entities.BenchSalesAddCandidateEntity;
import com.rainier.entities.BenchSalesAddEmployeeEntity;
import com.rainier.entities.BenchSalesAddTestimonialsEntity;
import com.rainier.entities.BenchSalesMailLogEntity;
import com.rainier.entities.BenchSalesRecruiterEntity;
import com.rainier.entities.BenchSalesVendorCandidateMappingEntity;
import com.rainier.entities.BenchSalesVendorDetailsEntity;
import com.rainier.entities.MailLogEntity;
import com.rainier.entities.Privileges;
import com.rainier.entities.Tbl_CitiesEntity;
import com.rainier.entities.User;
import com.rainier.response.CandidateRecruiterResponse;
import com.rainier.response.CandidateSubmissionCountResponse;
import com.rainier.response.VendorDetailsResponse;
import com.rainier.utility.HrmsGetDateAndTime;
import com.rainier.utility.SendMail;
import com.rainier.utility.SendMailBenchSalesEmployeeUtility;

public class BenchSales {
	public static Logger logger = Logger.getLogger(RecruitmentPortal.class);
	HrmsBenchSalesDao dao = new HrmsBenchSalesDaoImpl();
	CommonResponseBean response = new CommonResponseBean();
	private static final HrmsEmployeeDao db = new HrmsEmployeeDetails();

	public Response saveAddEmployee(BenchSalesAddEmployeeBean bean) {
		logger.info("saveAddEmployee() method in business logic ");
		CommonResponseBean response = new CommonResponseBean();
		SendMailBenchSalesEmployeeUtility mailTo = new SendMailBenchSalesEmployeeUtility();
		boolean flag = mailTo.sendToEmployee(bean.getPersonalEmailId(), bean.getFirstName());
		BenchSalesMailLogEntity mailLog = new BenchSalesMailLogEntity();
		boolean result = false;
		/*
		 * if (dao.saveAddEmployee(bean)) {
		 * response.setMessage("Successfully Employee Added.");
		 * response.setStatus(true); return Response.ok().entity(response).build(); }
		 * else {
		 * 
		 * response.setMessage("Please Check the Inputs."); response.setStatus(false);
		 * 
		 * return Response.serverError().entity(response).build(); }
		 */

		if (bean.getPassword().equals(bean.getConfirmPassword())) {
			result = dao.saveUpdateAddEmployee(bean);
		} else {
			response.setMessage("Your Password Should be Matched With the  First Passoword");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}
		if (result == true && bean.getId() > 0) {

			mailLog.setToEmails(bean.getPersonalEmailId());
			mailLog.setToName(bean.getFirstName());
			mailLog.setMessage("Congratulations !! Dear , Now You Update as Employee of Our Organaization..");
			mailLog.setHeader("Greetings From Bench sales team");
			mailLog.setIsSent(1);

			if (flag) {
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

				dao.sendingMailByBenchSalesAdmin(mailLog);
				response.setMessage("Employee Added in BenchSales Team And Email Sent Successfully.");
				response.setStatus(true);

			} else {
				mailLog.setIsSent(0);
				mailLog.setMessage("Message Not Sent.");
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				dao.sendingMailByBenchSalesAdmin(mailLog);
				response.setMessage("Employee Added in BenchSales Team But Email failed to sent Employee");
				response.setStatus(true);
			}

			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Employee Adding in Bench Sales Team .");
			response.setStatus(true);
			return Response.ok().entity(response).build();

		}

	}

	// fetch for Added Employee

	public Response fetchAddedEmployee() {
		LovResponseBean response = new LovResponseBean();

		List<BenchSalesAddEmployeeEntity> entityList = dao.fetchAddedEmployee();
		if (!entityList.isEmpty()) {
			response.setMessage("Employee List Retrived Successfully.");
			response.setStatus(true);
			response.setList(entityList);

			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Failed to Save Job Openings");
			response.setStatus(false);

			return Response.ok().entity(response).build();
		}

	}

	// Delete Recruiters Service
	public Response deleteRecruiterDetails(int id) {
		EntityStatusBean bean = new EntityStatusBean();
		logger.info("Delete RecruiterDetails of BenchSales ");
		try {
			dao.deleteRecruiters(id);
			bean.setMessage(" Deleted Succesfully.");
			bean.setStatus(true);
			return Response.status(Response.Status.OK).entity(bean).build();
		} catch (Exception e) {
			logger.info("control entered into catch block Exception will come" + e);
			bean.setMessage("Error while Deleting  RecruiterDetails.");
			bean.setStatus(false);
			return Response.status(Response.Status.OK).entity(bean).build();
		}
	}

	// Delete Employee Service
	public Response deleteEmployeeDetails(int id) {
		EntityStatusBean bean = new EntityStatusBean();
		logger.info("Delete EmployeeDetails of BenchSales ");
		try {
			dao.deleteEmployee(id);
			bean.setMessage(" Deleted Succesfully.");
			bean.setStatus(true);
			return Response.status(Response.Status.OK).entity(bean).build();
		} catch (Exception e) {
			logger.info("control entered into catch block Exception will come" + e);
			bean.setMessage("Error while Deleting EmployeeDetails.");
			bean.setStatus(false);
			return Response.status(Response.Status.OK).entity(bean).build();
		}
	}

	// add recruiters name ....
	public Response saveRecruitersName(BenchSalesRecruiterBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		BenchSalesRecruiterEntity entity = new BenchSalesRecruiterEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			boolean result = dao.addRecruiterName(entity);
			if (result == true && bean.getId() > 0) {
				response.setMessage("Recruiter Details Updated.");
				response.setStatus(true);
			} else if (result == true) {
				response.setMessage("Recruiters Details Saved.");
				response.setStatus(true);
			} else {
				response.setMessage("failed to save or Update.");
				response.setStatus(false);
			}
			return Response.ok().entity(response).build();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}
	}

	// fetch All Recruiters List ..
	public Response fetchAllRecruiters() {
		LovResponseBean response = new LovResponseBean();

		List<BenchSalesRecruiterEntity> entityList = dao.getAllRecruiters();
		if (!entityList.isEmpty()) {
			response.setMessage("Recruiters List Retrived Successfully.");
			response.setStatus(true);
			response.setList(entityList);

			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Failed to Save Job Openings");
			response.setStatus(false);

			return Response.ok().entity(response).build();
		}

	}

	// Save and Update Candidate..
	public Response saveAddCandidate(BenchSalesAddCandidateBean bean) {
		logger.info("SaveAndUpdate Candidate() in Business logic");
		SendMail mail = null;
		boolean emailFlag = false;
		MailLogEntity mailLog = null;
		CommonResponseBean response = new CommonResponseBean();
		HrmsBenchSalesDao hrmsBenchSalesDao = new HrmsBenchSalesDaoImpl();
		BenchSalesAddCandidateEntity entity = new BenchSalesAddCandidateEntity();
		List<Integer> idList = bean.getIdList();
		int canId = bean.getId();
		List<User> userList = hrmsBenchSalesDao.getRecruiterEmailListByIdList(idList);
		List<String> recList = new ArrayList<>();
		String superAdminEmail = hrmsBenchSalesDao.getSuperAdminEmail();
		String salesManagerMail = null;
		String canEmail = hrmsBenchSalesDao.getCandidateEmailById(canId);
		try {
			mailLog = new MailLogEntity();
			mail = new SendMail();
			BeanUtils.copyProperties(entity, bean);
			boolean result = dao.saveUpdateCandidate(bean);
			if (result == true) {
				for (int idList1 : idList) {
					logger.info(idList1);

					salesManagerMail = hrmsBenchSalesDao.getReportingManagerMail(idList1);
					logger.info(salesManagerMail);
					// Sending mail to salesManager
					emailFlag = mail.send(salesManagerMail, bean.getFirstName(), bean.getLastName());
				}
				for (User user : userList) {
					recList.add(user.getFirstName());
					// sending email to recruiters
					emailFlag = mail.send(user.getUserName(), user.getFirstName(), bean.getFirstName());
				}

				// sending mail to super admin
				mail.send(superAdminEmail, recList, bean.getFirstName());
				mail.send(canEmail, canId);
				mailLog.setToEmails(bean.getEmailAddress());
				mailLog.setEmailSubject("EEAccess");
				mailLog.setIsSent(1);
				mailLog.setMessage("Message Sent");
				mailLog.setHeader("Greeting from EEAccess");

				if (emailFlag) {
					mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
					db.insertMailLog(mailLog);
					response.setMessage(
							"Candidate Updated Or Saved SuccessFully Mail Go To  Super Admin And Sales Executive That Are Their In That List ");
					response.setStatus(true);
				}

			}

			return Response.ok().entity(response).build();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while saving And Save details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving And Save details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}
	}

	// Fetch Candidate List
	public Response retriveAllCandidate(int role, int menuId) {
		LovResponseBean response = new LovResponseBean();
		Privileges prevList = null;
		prevList = dao.getPriviligesForCand(role, menuId);
		List<BenchSalesAddCandidateBean> entityList = dao.retriveCandidateList();
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Candidate List SuccessFully");
			response.setStatus(true);
			response.setList(entityList);
			response.setPrivillegeslist(prevList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Based On SalesManagerId SalesExecutive And UnderCandidate will Come
	public Response retriveCandidateBasedOnSalesManagerAndSalesExecutive(int role, int menuId, int saleManId) {
		LovResponseBean response = new LovResponseBean();
		Privileges prevList = null;
		prevList = dao.getPriviligesForCand(role, menuId);
		Set<CandidateSalesManagerResponse> entityList = dao
				.retriveCandidateBasedOnSalesManagerAndSalesExecutive(saleManId);
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Candidate List SuccessFully");
			response.setStatus(true);
			response.setList(entityList);
			response.setPrivillegeslist(prevList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Delete Candidate in BenchSales
	public Response deleteCandidate(int id) {
		logger.info("Delete Candidate of BenchSales ");
		EntityStatusBean bean = new EntityStatusBean();
		HrmsBenchSalesDao hrmsBenchSalesDao = new HrmsBenchSalesDaoImpl();
		BenchSalesAddCandidateEntity candEntity = new BenchSalesAddCandidateEntity();
		SendMail mail = null;
		boolean emailFlag = false;
		MailLogEntity mailLog = null;
		mailLog = new MailLogEntity();
		mail = new SendMail();
		candEntity = hrmsBenchSalesDao.getCandidateEntityById(id);
		dao.deleteCandidate(id);
		// sending email to candidate
		emailFlag = mail.send(candEntity.getEmailAddress(), candEntity.getFirstName());
		mailLog.setToEmails(candEntity.getEmailAddress());
		mailLog.setEmailSubject("EEAccess");
		mailLog.setIsSent(1);
		mailLog.setMessage("Message Sent");
		mailLog.setHeader("Greeting from EEAccess");

		if (emailFlag) {
			db.insertMailLog(mailLog);
			response.setMessage("Deleted Succesfully and Email Sent to Candidate ");
			response.setStatus(true);
		} else {

			response.setMessage("Deleted UnSuccesfully and Email Sent to Candidate");
			response.setStatus(false);
		}

		return Response.ok().entity(response).build();
	}

	// Save And Update Testimonials in BusinessLogic
	public Response saveUpdateTestimonial(BenchSalesAddTestimonialsBean bean) {
		logger.info("Save And Update ()");
		CommonResponseBean responseBean = new CommonResponseBean();
		BenchSalesAddTestimonialsEntity entity = new BenchSalesAddTestimonialsEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			boolean result = dao.saveUpdateTestimonials(bean);
			if (result == true && bean.getId() > 0) {
				responseBean.setMessage("Testimonials Detail updated ");
				responseBean.setStatus(true);
			} else {
				responseBean.setMessage("Testimonilas Detail Saved");
				responseBean.setStatus(true);
			}
			return Response.ok().entity(responseBean).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while saving And Updating details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving And Updating details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}

	// Fetch Testimonilas List
	public Response retriveAllTestimonils() {
		LovResponseBean response = new LovResponseBean();
		List<BenchSalesAddTestimonialsEntity> entityList = dao.fetchTestimonilasList();
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Testimonilas List SuccessFully");
			response.setStatus(true);
			response.setList(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Testimonilas  List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// get Candidate Based On The Recruiters That Are Under On that Recruiters
	public Response getCandidateBasedOnTheRec(int recId, int role, int menuId) {
		LovResponseBean response = null;
		Set<CandidateRecruitersResponse> candidateList = null;
		Privileges prevList = null;
		candidateList = dao.getCandidateList(recId);
		prevList = dao.getPriviligesForCand(role, menuId);
		response = new LovResponseBean();
		if (candidateList != null) {
			response.setMessage("Candidate List Retrived SuccessFully That are Their In That ReCruiters");
			response.setStatus(true);
			response.setList(candidateList);
			response.setPrivillegeslist(prevList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate List Retrived UnSuccessFully That are Their In That ReCruiters");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}
	}

	// GET Candidate That are Not Related That Recruiters
	public Response getCandidate(int recId, int role, int menuId) {
		LovResponseBean response = null;

		Set<CandidateRecruitersResponse> candidateList = null;
		candidateList = dao.getCandidate(recId);

		response = new LovResponseBean();
		if (candidateList != null) {
			response.setMessage("Candidate List Retrived SuccessFully That are Not Their In That ReCruiters");
			response.setStatus(true);
			response.setList(candidateList);

			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate List Retrived UnSuccessFully That are Not Their In That ReCruiters");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}
	}

	// Delete Testimonilas Service
	public Response deleteTestimonilas(int id) {
		EntityStatusBean bean = new EntityStatusBean();
		logger.info("Delete Testimonials of BenchSales ");
		try {
			dao.deleteTestimonials(id);
			bean.setMessage(" Deleted Succesfully.");
			bean.setStatus(true);
			return Response.status(Response.Status.OK).entity(bean).build();
		} catch (Exception e) {
			logger.info("control entered into catch block Exception will come" + e);
			bean.setMessage("Error while Deleting Testimonials.");
			bean.setStatus(false);
			return Response.status(Response.Status.OK).entity(bean).build();
		}
	}

	// Add Vendor Details Service
	public Response saveVendorDetails(BenchSalesVendorDetailsBean bean) {
		CommonResponseBean responseBean = new CommonResponseBean();
		BenchSalesVendorDetailsEntity entity = new BenchSalesVendorDetailsEntity();
		int i = 0;
		try {
			BeanUtils.copyProperties(entity, bean);
			i = dao.saveVendorList(bean);
			if (i == 1) {
				responseBean.setMessage("Vendor Details Inserted Successful");
				responseBean.setStatus(true);
			} else {
				responseBean.setMessage("Vendor Details Inserted UnSuccessful");
				responseBean.setStatus(false);
			}
			return Response.ok().entity(responseBean).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while saving  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(responseBean).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(responseBean).build();

		}

	}

	// get VendorList Service Based On VendorName
	public Response retriveAllVendors(String venName) {
		LovResponseBean response = new LovResponseBean();
		List<BenchSalesVendorDetailsEntity> entityList = dao.getVendorList(venName);
		logger.info(entityList);
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Vendor List SuccessFully");
			response.setStatus(true);
			response.setList(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Vendor List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Save Vendor And Candidate
	@SuppressWarnings("null")
	public Response saveVendorDetails(BenchSalesVendorDetailsBean bean, int id) {
		CommonResponseBean responseBean = new CommonResponseBean();
		SendMail mail = null;
		boolean emailFlag = false;
		MailLogEntity mailLog = null;
		int createdBy = bean.getCreatedBy();
		int saleExecId = bean.getCreatedBy();
		int i = 0;
		HrmsBenchSalesDao hrmsBenchSalesDao = new HrmsBenchSalesDaoImpl();
		String superAdminEmail = hrmsBenchSalesDao.getSuperAdminEmail();
		String canEmail = hrmsBenchSalesDao.getCandidateEmailById(id);
		// Validation For Candidate Submission With Organisation ClientName And Location
		if (id == hrmsBenchSalesDao.checkWithCandIdThatOrgClieLoc(bean.getVendorName(), bean.getClientName(),
				bean.getLocation())) {
			responseBean.setMessage("Candidate Is All Ready There With Under The Organisation ClientName And Location");
			return Response.ok().entity(responseBean).build();
		} else {
			i = dao.addCandidateVendor(bean, id);
			if (i == 1) {
				String vendorMail = dao.getCandidateEmailByCreatedby(createdBy);
				String candMail = dao.getCandidateEmailById(id);
				mailLog = new MailLogEntity();
				mail = new SendMail(bean.getClientName(),bean.getLocation(),"Submission");
				emailFlag = mail.sendMailAfterSubmitted(superAdminEmail, bean.getClientName(), bean.getLocation(), id);
				emailFlag = mail.sendMailAfterSubmitted(candMail, bean.getClientName(), bean.getLocation(), id);
				emailFlag = mail.sendMailAfterSubmitted(vendorMail, bean.getClientName(), bean.getLocation(), id);
				emailFlag = mail.sendMailAfterSubmitted(canEmail, bean.getClientName(), bean.getLocation(), id);
				emailFlag = mail.sendMailAfterSubmitted(hrmsBenchSalesDao.getReportingManagerMail(saleExecId),
						bean.getClientName(), bean.getLocation(), id);
				mailLog.setToEmails(bean.getEmailAddress());
				mailLog.setToName(bean.getVendorName());
				mailLog.setEmailSubject("EEAccess");
				mailLog.setIsSent(1);
				mailLog.setMessage("Message Sent");
				mailLog.setHeader("Greeting from EEAccess");
				if (emailFlag) {
					mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
					db.insertMailLog(mailLog);
					responseBean
							.setMessage("Vendor Official Information Saved Succesfully and Email Sent to Super Admin ");
					responseBean.setStatus(true);
				} else {
					mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
					db.insertMailLog(mailLog);
					responseBean.setMessage("Vendor Details Candidate Inserted UnSuccessful Mail Not Sent");
					responseBean.setStatus(false);
				}
			}
			return Response.ok().entity(responseBean).build();
		}
	}

	public Response updateCandidateStatus(BenchSalesVendorCandidateMappingBean bean) {
		CommonResponseBean responseBean = new CommonResponseBean();
		BenchSalesVendorCandidateMappingEntity entity = new BenchSalesVendorCandidateMappingEntity();
		boolean i = false;
		try {
			BeanUtils.copyProperties(entity, bean);
			i = dao.updateStatusCandidate(bean);
			if (i == true) {
				responseBean.setMessage("Candidate Status Successful Update");
				responseBean.setStatus(true);
			} else {
				responseBean.setMessage("Candidate Status UnSuccessful Update");
				responseBean.setStatus(false);
			}
			return Response.ok().entity(responseBean).build();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setMessage("Error while saving  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(responseBean).build();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setMessage("Error while saving details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(responseBean).build();

		}

	}

	// fetch vendor Data based on the candidate id
	public Response retriveAllVendorsBasedOnCandidatateId(int id) {
		CommonResponseBean response = new CommonResponseBean();
		List<Object[]> entityList = dao.listOfVendorBasedonCandidateId(id);
		List<BenchSalesVendorDetailsBean> lists = new ArrayList<BenchSalesVendorDetailsBean>();
		for (Object[] ob : entityList) {
			BenchSalesVendorDetailsBean entity1 = new BenchSalesVendorDetailsBean();
			int vendorId = (Integer) ob[0];
			int id1 = (Integer) ob[1];
			String vendorName = (String) ob[2];
			String emailAddress = (String) ob[3];
			String phoneNo = (String) ob[4];
			String clientName = (String) ob[5];
			String location = (String) ob[6];
			String rate = (String) ob[7];
			Date dateOfSubmission = (Date) ob[8];
			int createdBy = (int) ob[9];
			String status = (String) ob[10];
			Date statusSubDate = (Date) ob[11];
			String comments = (String) ob[12];
			String payType = (String) ob[13];
			entity1.setVendorId(vendorId);
			entity1.setId(id1);
			entity1.setVendorName(vendorName);
			entity1.setEmailAddress(emailAddress);
			entity1.setClientName(clientName);
			entity1.setLocation(location);
			entity1.setRate(rate);
			entity1.setPhoneNo(phoneNo);
			entity1.setDateOfSubmission(dateOfSubmission);
			entity1.setStatusSubDate(statusSubDate);
			entity1.setStatus(status);
			entity1.setCreatedBy(createdBy);
			entity1.setComments(comments);
			entity1.setPayType(payType);
			lists.add(entity1);
		}
		logger.info(entityList.size());
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Vendor List SuccessFully");
			response.setStatus(true);
			response.setLists(lists);
			// response.setVendorEntity(entity1);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Vendor List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Update Vendor Details Based On Vendor id
	public Response updateVendorList(BenchSalesVendorDetailsBean bean, int id) {
		CommonResponseBean responseBean = new CommonResponseBean();
		boolean emailFlag = false;
		MailLogEntity mailLog = null;
		boolean i = false;
		int createdBy = bean.getCreatedBy();
		int saleExecId = bean.getCreatedBy();
		HrmsBenchSalesDao hrmsBenchSalesDao = new HrmsBenchSalesDaoImpl();
		String superAdminEmail = hrmsBenchSalesDao.getSuperAdminEmail();
		String vendorMail = dao.getCandidateEmailByCreatedby(createdBy);
		mailLog = new MailLogEntity();
		mailLog = new MailLogEntity();
		i = dao.updateVendorList(bean, id);
		if (i == true) {
			String canEmail = hrmsBenchSalesDao.getCandidateEmailById(id);
			if ("InterViewed".equals(bean.getStatus())) {
				SendMail mail =new SendMail(bean.getClientName(), bean.getLocation(),"Interviewed");
				emailFlag = mail.sendMailAfterInterviewed(superAdminEmail, bean.getClientName(), bean.getLocation());
				emailFlag = mail.sendMailAfterInterviewed(hrmsBenchSalesDao.getReportingManagerMail(saleExecId),
						bean.getClientName(), bean.getLocation());
				emailFlag = mail.sendMailAfterInterviewed(vendorMail, bean.getClientName(), bean.getLocation());
				emailFlag = mail.sendMailAfterInterviewed(canEmail, bean.getClientName(), bean.getLocation());
				mailLog.setToEmails(bean.getEmailAddress());
				mailLog.setToName(bean.getVendorName());
				mailLog.setEmailSubject("EEAccess");
				mailLog.setIsSent(1);
				mailLog.setMessage("Message Sent");
				mailLog.setHeader("Greeting from EEAccess");
			} else if ("Selected".equals(bean.getStatus())) {
				SendMail mail=new SendMail(bean.getClientName(), bean.getLocation(),"Selected");
				emailFlag = mail.sendMailAfterSelected(superAdminEmail);
				emailFlag = mail.sendMailAfterSelected(hrmsBenchSalesDao.getReportingManagerMail(saleExecId));
				emailFlag = mail.sendMailAfterSelected(vendorMail);
				emailFlag = mail.sendMailAfterSelected(canEmail);
				mailLog.setToEmails(bean.getEmailAddress());
				mailLog.setToName(bean.getVendorName());
				mailLog.setEmailSubject("EEAccess");
				mailLog.setIsSent(1);
				mailLog.setMessage("Message Sent");
				mailLog.setHeader("Greeting from EEAccess");
			}
			if (emailFlag) {
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				db.insertMailLog(mailLog);
				responseBean
						.setMessage("Vendor Official Information Updated Succesfully and Email Sent to Super Admin ");
				responseBean.setStatus(true);
			} else {
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				db.insertMailLog(mailLog);
				responseBean.setMessage("Vendor Details Candidate Updated UnSuccessful Mail Not Sent");
				responseBean.setStatus(false);
			}
		}
		return Response.ok().entity(responseBean).build();
	}

	// Intelligecie Service Based On Vendor word
	public Response retriveVendors(String name, String exName) {
		LovResponseBean response = new LovResponseBean();
		List<BenchSalesVendorDetailsEntity> entityList = dao.getvendorBasedOnFirstAlphabet(name, exName);
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Vendor List Based On The Intelligencie That You Given  SuccessFully");
			response.setStatus(true);
			response.setList(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Vendor List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Intelligecie Service Based On Email Executive Super Admin
	public Response retriveVendors(String name) {
		LovResponseBean response = new LovResponseBean();
		List<BenchSalesVendorDetailsEntity> entityList = dao.getvendorBasedOnFirstAlphabet(name);
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Vendor List Based On The Intelligencie That You Given  SuccessFully");
			response.setStatus(true);
			response.setList(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Vendor List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Based on SaleExecutive We need To Fetch Candidate
	public Response retriveAllCandidate(String name) {
		LovResponseBean response = new LovResponseBean();
		List<BenchSalesAddCandidateEntity> entityList = dao.getCandidatesBasedOnExecutive(name);
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Candidate List SuccessFully");
			response.setStatus(true);
			response.setList(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Based On The Candidate Id and Recruiters Name All The Vendor List Come
	public Response retriveAllVendorsBasedOnCandidatateIdAndRecNa(String recName, int id) {
		CommonResponseBean response = new CommonResponseBean();
		List<Object[]> entityList = dao.listOfVendorBasedonCandidateIdAndRecName(recName, id);
		List<BenchSalesVendorDetailsEntity> lists = new ArrayList<BenchSalesVendorDetailsEntity>();
		for (Object[] ob : entityList) {
			BenchSalesVendorDetailsEntity entity1 = new BenchSalesVendorDetailsEntity();
			int vendorId = (Integer) ob[0];
			String vendorName = (String) ob[1];
			String emailAddress = (String) ob[2];
			String phoneNo = (String) ob[3];
			String clientName = (String) ob[4];
			String location = (String) ob[5];
			String rate = (String) ob[6];
			Date dateOfSubmission = (Date) ob[7];
			int createdBy = (int) ob[8];
			String status = (String) ob[9];
			Date statusSubDate = (Date) ob[10];
			String comments = (String) ob[11];
			String payType = (String) ob[12];
			entity1.setVendorId(vendorId);
			entity1.setVendorName(vendorName);
			entity1.setEmailAddress(emailAddress);
			entity1.setClientName(clientName);
			entity1.setLocation(location);
			entity1.setRate(rate);
			entity1.setPhoneNo(phoneNo);
			entity1.setDateOfSubmission(dateOfSubmission);
			/*
			 * entity1.setStatusSubDate(statusSubDate); entity1.setStatus(status);
			 */
			entity1.setCreatedBy(createdBy);
			// entity1.setComments(comments);
			entity1.setPayType(payType);
			lists.add(entity1);
		}
		logger.info(entityList.size());
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Vendor List SuccessFully");
			response.setStatus(true);
			response.setLists2(lists);
			// response.setVendorEntity(entity1);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Vendor List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	/*
	 * // Recruiters Service Under The Candidate Will Come public Response
	 * getCandidate() { CommonResponseBean response = new CommonResponseBean();
	 * 
	 * List<CandidateResponseObject> entityList =
	 * dao.getCandidateUnderTheRecruites();
	 * 
	 * if (!entityList.isEmpty()) {
	 * response.setMessage("Retrived Candidate List SuccessFully");
	 * response.setStatus(true); response.setMajorList(entityList); //
	 * response.setVendorEntity(entity1); return
	 * Response.ok().entity(response).build(); } else {
	 * response.setMessage("Candidate List Retrived UnSuccessFully");
	 * response.setStatus(false); return Response.ok().entity(response).build(); }
	 * 
	 * }
	 */

	// Vendor Search Based On VendorName,Phone,EmailAddress,Location
	public Response retriveVendorsLists(BenchSalesVendorDetailsBean bean) {
		LovResponseBean response = new LovResponseBean();
		List<BenchSalesVendorDetailsEntity> entityList = dao.getVendorLists(bean);
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Vendor List ");
			response.setStatus(true);
			response.setList(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Vendor List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Candidate Search Based On CandidateName,Phone,EmailAddress,Location
	public Response retriveCandidateLists(BenchSalesAddCandidateBean bean) {
		LovResponseBean response = new LovResponseBean();
		List<BenchSalesAddCandidateEntity> entityList = dao.getCandidateLists(bean);
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Candidate List ");
			response.setStatus(true);
			response.setList(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate List UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Get All InActive Candidate In This Service
	public Response getInActiveCandidateLists() {
		LovResponseBean response = new LovResponseBean();
		List<BenchSalesAddCandidateEntity> candidateList = dao.getInActiveCandidateList();
		logger.info(candidateList);
		if (candidateList != null) {
			response.setMessage(" inActive Candidate Retrived SuccessFully ");
			response.setStatus(true);
			response.setList(candidateList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage(" inActive Candidate Retrived UnSuccessFully ");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}
	}

	// Get Candidate Vendor Status And Date Based On VendorId And Candidate Id
	public Response retriveAllVendorsCandidatateStatusDate(int canId, int venId) {
		CommonResponseBean response = new CommonResponseBean();
		List<Object[]> entityList = dao.getStatusDate(canId, venId);
		List<BenchSalesVendorDetailsBean> lists = new ArrayList<BenchSalesVendorDetailsBean>();
		for (Object[] ob : entityList) {
			BenchSalesVendorDetailsBean entity1 = new BenchSalesVendorDetailsBean();
			String status = (String) ob[0];
			Date statusSubDate = (Date) ob[1];
			entity1.setStatusSubDate(statusSubDate);
			entity1.setStatus(status);
			lists.add(entity1);
		}
		logger.info(entityList.size());
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived  Vendor Status And Date SuccessFully");
			response.setStatus(true);
			response.setLists(lists);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Vendor  Vendor Status And Date UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Get Vendor Count Basd On Candidate Id
	public Response getVendorCount() {
		CommonResponseBean response = new CommonResponseBean();
		List<ArrayList<BigInteger>> i = dao.getVendorCount();
		if (i != null) {
			response.setMessage("Vendor Count Retrived SuccessFul");
			response.setStatus(true);
			response.setList3(i);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Vendor Count Retrived UnSuccessFul");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}
	}

	// Vendor Client Submission Service
	@SuppressWarnings("null")
	public Response vendorclientSub(BenchSalesVendorDetailsBean bean) {
		CommonResponseBean responseBean = new CommonResponseBean();
		SendMail mail = null;
		String canEmail = null;
		int canId = 0;
		boolean emailFlag = false;
		MailLogEntity mailLog = null;
		int createdBy = bean.getCreatedBy();
		int salExeReqId = bean.getReqId();
		boolean i = false;
		List<Integer> idList = bean.getRecIdList();
		int saleExecId = bean.getCreatedBy();
		HrmsBenchSalesDao hrmsBenchSalesDao = new HrmsBenchSalesDaoImpl();
		String superAdminEmail = hrmsBenchSalesDao.getSuperAdminEmail();
		List<User> userList = hrmsBenchSalesDao.getRecruiterEmailListByIdList(idList);
		String vendorMail = dao.getCandidateEmailByCreatedby(createdBy);
		List<String> recList = new ArrayList<>();
		User user1 = hrmsBenchSalesDao.getRecruiterEmailById(salExeReqId);
		if (canId != 0) {
			canId = bean.getId();
			canEmail = hrmsBenchSalesDao.getCandidateEmailById(canId);
		}
		i = dao.vendorClientCandidateSub(bean);
		if (i == true) {
			mailLog = new MailLogEntity();
			mail = new SendMail();
			for (User user : userList) {
				recList.add(user.getFirstName());
				// sending email to recruiters
				emailFlag = mail.send(user.getUserName());
			}
			emailFlag = mail.send(superAdminEmail);
			emailFlag = mail.send(vendorMail);
			if (canEmail != null) {
				emailFlag = mail.send(canEmail);
			}
			emailFlag = mail.send(hrmsBenchSalesDao.getReportingManagerMail(saleExecId));
			if (salExeReqId != 0) {
				emailFlag = mail.send(user1.getUserName());
			}
			mailLog.setToEmails(bean.getEmailAddress());
			mailLog.setToName(bean.getVendorName());
			mailLog.setEmailSubject("EEAccess");
			mailLog.setIsSent(1);
			mailLog.setMessage("Message Sent");
			mailLog.setHeader("Greeting from EEAccess");
			if (emailFlag) {
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				db.insertMailLog(mailLog);
				responseBean.setMessage("Vendor Client Submission  Saved Succesfully and Email Sent to Super Admin ");
				responseBean.setStatus(true);
			} else {
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				db.insertMailLog(mailLog);
				responseBean.setMessage("Vendor Client Submission  Inserted UnSuccessful Mail Not Sent");
				responseBean.setStatus(false);
			}
		}
		return Response.ok().entity(responseBean).build();
	}

	// Based On The Candidate Id and Recruiters Id All The Vendor List Come
		public Response retriveAllVendorsBasedOnCandidatateIdAndRecId(int recId, int id) {
			CommonResponseBean response = new CommonResponseBean();
			Set<BenchSalesVendorDetailsBean>setVendorList = dao.listOfVendorBasedonCandidateIdAndRecId(recId, id);
			if (setVendorList!=null) {
				response.setMessage("Retrived Vendor List SuccessFully");
				response.setStatus(true);
				response.setSetOfVendor(setVendorList);
				// response.setVendorEntity(entity1);
				return Response.ok().entity(response).build();
			} else {
				response.setMessage("Vendor List Retrived UnSuccessFully");
				response.setStatus(false);
				return Response.ok().entity(response).build();
			}

		}
	// Based On The Candidate Id and Recruiters Id All The Vendor List Come For
	// AuthoriZation
	public Response retriveAllVendorsBasedOnCandidatateIdAndRecIdForAuth(int recId, int id) {
		CommonResponseBean response = new CommonResponseBean();
		List<Object[]> entityList = dao.listOfVendorBasedonCandidateIdAndRecIdForAuth(recId, id);
		List<BenchSalesVendorDetailsBean> lists = new ArrayList<BenchSalesVendorDetailsBean>();
		Set<BenchSalesVendorDetailsBean> setVendorList = null;
		for (Object[] ob : entityList) {
			BenchSalesVendorDetailsBean entity1 = new BenchSalesVendorDetailsBean();
			int vendorId = (Integer) ob[0];
			String vendorName = (String) ob[1];
			String emailAddress = (String) ob[2];
			String phoneNo = (String) ob[3];
			String clientName = (String) ob[4];
			String location = (String) ob[5];
			String rate = (String) ob[6];
			Date dateOfSubmission = (Date) ob[7];
			int createdBy = (int) ob[8];
			String status = (String) ob[9];
			Date statusSubDate = (Date) ob[10];
			String comments = (String) ob[11];
			String payType = (String) ob[12];
			entity1.setVendorId(vendorId);
			entity1.setVendorName(vendorName);
			entity1.setEmailAddress(emailAddress);
			entity1.setClientName(clientName);
			entity1.setLocation(location);
			entity1.setRate(rate);
			entity1.setPhoneNo(phoneNo);
			entity1.setDateOfSubmission(dateOfSubmission);
			entity1.setStatusSubDate(statusSubDate);
			entity1.setStatus(status);
			entity1.setCreatedBy(createdBy);
			entity1.setComments(comments);
			entity1.setPayType(payType);
			lists.add(entity1);
		}
		logger.info(entityList.size());
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Vendor List SuccessFully");
			response.setStatus(true);
			response.setLists(lists);
			// response.setVendorEntity(entity1);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Vendor List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// getCandidate Submission
	public Response getcandidateSubmission(int recId) {
		CandidateSubmissionCountResponse response = new CandidateSubmissionCountResponse();

		List<CandSubmissionBean> entityList = dao.getCandidateSubmissionDetails(recId);

		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Candidate List SuccessFully");
			response.setStatus(true);
			response.setCandSubmissionBeans(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	public Response getCandidate() {
		CandidateRecruiterResponse response = new CandidateRecruiterResponse();

		List<CandidateResponseObject> entityList = dao.getAllCandidatesAndRecruiters();

		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Candidate List SuccessFully");
			response.setStatus(true);
			response.setCandidateAndRecruiters(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// getCandidateSubmissionWithInDates
	public Response getCandidateSubmissionWithInDates(CandidateSubmissionCount candSubCount) {
		CandidateSubmissionCountResponse response = new CandidateSubmissionCountResponse();

		List<CandSubmissionBean> entityList = dao.getCandidateSubmissionWithInDates(candSubCount);

		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Candidate Submisson List SuccessFully");
			response.setStatus(true);
			response.setCandSubmissionBeans(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate List Retrived Submisson UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	public Response getVendorByCandidateId(int candId) {
		VendorDetailsResponse response = new VendorDetailsResponse();

		List<BenchSalesVendorDetailsEntityResponse> entityList = dao.getVendorByCandidateId(candId);

		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Candidate List SuccessFully");
			response.setStatus(true);
			response.setVendorDetailsEntities(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Get VendorList With Sales Executive Name
	public Response retriveAllVendorWithSalesExeName() {
		LovResponseBean response = new LovResponseBean();
		List<VendorSalesExecutiveResponseBean> entityList = dao.getVendorListWithSaleExecutiveName();
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived Candidate List SuccessFully");
			response.setStatus(true);
			response.setVenExeList(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// Through This Service We Are Making InActive Candidate To Active Mode
	public Response activeCandidate(int id) {
		logger.info("Active Candidate of BenchSales ");
		HrmsBenchSalesDao hrmsBenchSalesDao = new HrmsBenchSalesDaoImpl();
		BenchSalesAddCandidateEntity candEntity = new BenchSalesAddCandidateEntity();
		SendMail mail = null;
		boolean emailFlag = false;
		MailLogEntity mailLog = null;
		mailLog = new MailLogEntity();
		mail = new SendMail();
		candEntity = hrmsBenchSalesDao.getCandidateEntityById(id);
		dao.activeCandidate(id);
		// sending email to candidate
		emailFlag = mail.send(candEntity.getEmailAddress(), candEntity.getFirstName());
		mailLog.setToEmails(candEntity.getEmailAddress());
		mailLog.setEmailSubject("EEAccess");
		mailLog.setIsSent(1);
		mailLog.setMessage("Message Sent");
		mailLog.setHeader("Greeting from EEAccess");

		if (emailFlag) {
			db.insertMailLog(mailLog);
			response.setMessage("Active Succesfully and Email Sent to Candidate ");
			response.setStatus(true);
		} else {

			response.setMessage("Active UnSuccesfully and Email Sent to Candidate");
			response.setStatus(false);
		}

		return Response.ok().entity(response).build();
	}

	// Get All States
	public Response getAllStates() {
		CommonResponseBean responseStates = new CommonResponseBean();
		List<CityStateResponseBean> getStates = dao.getAllStates();
		if (getStates != null) {
			responseStates.setMessage("Retrived States SuccessFul");
			responseStates.setStatus(true);
			responseStates.setStateList(getStates);
			return Response.ok().entity(responseStates).build();
		} else {
			responseStates.setMessage("Retrived States UnSuccessFully");
			responseStates.setStatus(false);
			return Response.ok().entity(responseStates).build();
		}

	}

	// Get All Cities Based State Id
	public Response getAllCities(int id) {
		CommonResponseBean responseStates = new CommonResponseBean();
		List<CityStateResponseBean> getCities = dao.getAllCities(id);
		if (getCities != null) {
			responseStates.setMessage("Retrived Cities SuccessFul");
			responseStates.setStatus(true);
			responseStates.setList(getCities);
			return Response.ok().entity(responseStates).build();
		} else {
			responseStates.setMessage("Retrived Cities UnSuccessFully");
			responseStates.setStatus(false);
			return Response.ok().entity(responseStates).build();
		}

	}

	// Based On The SalesManId The SalesExecutive List Will Come
	public Response listOfSalesExecutive(int salManId) {
		LovResponseBean response = new LovResponseBean();
		List<User> entityList = dao.listOfSalesExecutive(salManId);
		if (!entityList.isEmpty()) {
			response.setMessage("Retrived SalesExecutive List SuccessFully");
			response.setStatus(true);
			response.setList(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Retrived SalesExecutive UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// get All SalesExceutive And Candidate Based On SalManagerId
	public Response getSalesExeCandidate(int salManId) {
		CandidateRecruiterResponse response = new CandidateRecruiterResponse();

		List<CandidateResponseObject> entityList = dao.getSaleExeAndCanBasedOnSalManId(salManId);

		if (entityList != null) {
			response.setMessage("Retrived Candidate SalesExe List SuccessFully");
			response.setStatus(true);
			response.setCandidateAndRecruiters(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Candidate SalesExe List Retrived UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}
}
